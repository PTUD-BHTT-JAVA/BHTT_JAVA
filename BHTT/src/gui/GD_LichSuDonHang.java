/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connectDB.ConnectDB;
import dao.DAO_ChatLieu;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_KichThuoc;
import dao.DAO_LoaiKhachHang;
import dao.DAO_LoaiSP;
import dao.DAO_MauSac;
import dao.DAO_NhaCungCap;
import dao.DAO_NhanVien;
import dao.DAO_SanPham;
import entity.ChatLieu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KichThuoc;
import entity.LoaiSanPham;
import entity.MauSac;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ACER
 */
public class GD_LichSuDonHang extends javax.swing.JInternalFrame implements Runnable{

    
    private String username;
     private DAO_SanPham sp_dao;
    private DAO_LoaiSP lsp_dao;
    private DAO_MauSac mau_dao;
    private DAO_NhaCungCap ncc_dao;
    private DAO_ChatLieu cl_dao;
    private DAO_KhachHang kh_dao;
    private DAO_KichThuoc kt_dao;
    private String filename = null;
    private byte[] anhSP = null;
//    private final DefaultTableModel modolSP;
    List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
    private TableRowSorter<DefaultTableModel> tr;
//    int index = -1;
    public DefaultTableModel modelDonHang;
    DecimalFormat df = new DecimalFormat("#,##0 VND");
    private Thread thread = new Thread(this);
    
    private double tongThanhTien;
    private double tienThoi;
    private DAO_KhachHang kh;
    private DAO_LoaiKhachHang lkh;
    private DAO_NhanVien nv;
    private DAO_ChiTietHoaDon cthd_dao;
    public TaiKhoan tkDN;
    private DAO_HoaDon hd_dao;
    private ArrayList<SanPham> dsSPTrongDonHang;

    public GD_LichSuDonHang(String _username) {
        thread.start();
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
        }
        
        this.setRootPaneCheckingEnabled(false);
        javax.swing.plaf.InternalFrameUI ui
                = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);
        initComponents();
        this.setFocusable(true);
        username=_username;
//        modolSP = (DefaultTableModel) jtbSanPham.getModel();

//        DocDuLieuLenTable();
//        DocDuLieuVaoCombobox();
//        moKhoaTextfields(false);

//        tr = new TableRowSorter<DefaultTableModel>(modolSP);
        hd_dao = new DAO_HoaDon();
        cthd_dao = new DAO_ChiTietHoaDon();
        kh = new DAO_KhachHang();
        nv = new DAO_NhanVien();
//        jtbSanPham.setRowSorter(tr);
        
        
    }
//    private void XoaHetDLTrenTbale() {
//        DefaultTableModel fm = (DefaultTableModel) jtbSanPham.getModel();
//        fm.getDataVector().removeAllElements();
//    }
//    private void DocDuLieuVaoCombobox() {
//        lsp_dao = new DAO_LoaiSP();
//        ArrayList<LoaiSanPham> listLSP = lsp_dao.getAllLSP();
//        for (LoaiSanPham lsp : listLSP) {
//            cbxPL.addItem(lsp.getTenLoaiSP());
//            
//        }
//        mau_dao = new DAO_MauSac();
//        ArrayList<MauSac> listMau = mau_dao.getAllMau();
//        for (MauSac m : listMau) {
//            cbxMS.addItem(m.getTenMau());
//        }
//        cl_dao = new DAO_ChatLieu();
//        ArrayList<ChatLieu> listCL = cl_dao.getAllCL();
//        for (ChatLieu cl : listCL) {
//            cbxCL.addItem(cl.getTenChatLieu());
//        }
//    private void XoaHetDLTrenTbale() {
//        DefaultTableModel fm = (DefaultTableModel) jtbSanPham.getModel();
//        fm.getDataVector().removeAllElements();
//    }
//    private void DocDuLieuVaoCombobox() {
//        lsp_dao = new DAO_LoaiSP();
//        ArrayList<LoaiSanPham> listLSP = lsp_dao.getAllLSP();
//        for (LoaiSanPham lsp : listLSP) {
//            cbxPL.addItem(lsp.getTenLoaiSP());
//            
//        }
//        mau_dao = new DAO_MauSac();
//        ArrayList<MauSac> listMau = mau_dao.getAllMau();
//        for (MauSac m : listMau) {
//            cbxMS.addItem(m.getTenMau());
//        }
//        cl_dao = new DAO_ChatLieu();
//        ArrayList<ChatLieu> listCL = cl_dao.getAllCL();
//        for (ChatLieu cl : listCL) {
//            cbxCL.addItem(cl.getTenChatLieu());
//        }

//        ncc_dao = new DAO_NhaCungCap();
//        ArrayList<NhaCungCap> listNCC = ncc_dao.getalltbNhaCungCap();
//        for (NhaCungCap ncc : listNCC) {
//            cbxNCC.addItem(ncc.getTenNCC());
//        }
//        kt_dao = new DAO_KichThuoc();
//        ArrayList<KichThuoc> listKT = kt_dao.getAllKT();
//        for (KichThuoc kt : listKT) {
//            cbxKT.addItem(kt.getTenKichThuoc());
//        }

    /**
     *



      public  void dongPanel(Component c){
          pnlTab.remove(c);
      }
    
//    private void DocDuLieuLenTable() {
//        sp_dao = new DAO_SanPham();
//        ArrayList<SanPham> ds = sp_dao.getAllSP();
//        int i = 1;
//        for (SanPham sp : ds) {
//            modolSP.addRow(new Object[]{
//                sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getGiaGoc(), sp.getLoaiSanPham().getTenLoaiSP(), sp.getMauSac().getTenMau(), sp.getChatLieu().getTenChatLieu(), sp.getKichThuoc().getTenKichThuoc(),
//               });
//        }
//
//    }
 

//    public double ParseDouble(String strNumber) {
//        if (strNumber != null && strNumber.length() > 0) {
//            try {
//                return Double.parseDouble(strNumber);
//            } catch (Exception e) {
//                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
//            }
//        } else {
//            return 0;
//        }
//    }
    
//    private void XoaHetDLTrenTbale(JTable table) {
//        DefaultTableModel fm = (DefaultTableModel) table.getModel();
//        fm.getDataVector().removeAllElements();
//    }
//    
//    private void setTongThanhTien() {
//        tongThanhTien = 0;
//        for (int i = 0; i < modelDonHang.getRowCount(); i++) {
//            tongThanhTien += Double.parseDouble(modelDonHang.getValueAt(i, 4).toString());
//        }
//        txtTongTien.setText(df.format(tongThanhTien));
//    }
//    public void thanhToan(){
//        KhachHang khTT = kh.layKhachHangBangSDT(txtSDT.getText());
//        NhanVien nvTT = nv.layNhanVienBangTen(txtTenNV.getText());
//        String diaChi = "12 Nguyễn Văn Bảo,phường 4,Gò Vấp,Hồ Chí Minh";
//        Date ngayLapHD = new Date();
//        HoaDon hd = new HoaDon(maTuSinh(), ngayLapHD, Double.parseDouble(txtTienKhachDua.getText()),
//                diaChi, new NhanVien(nvTT.getMaNV()), new KhachHang(khTT.getMaKH()));
//        hd_dao.themHoaDon(hd);
//        HoaDon hoaDonMoiThem = hd_dao.getHoaDonMoiNhat();
//        for (int i = 0; i < modelDonHang.getRowCount(); i++) {
//            double tongTien = ParseDouble(modelDonHang.getValueAt(i, 4).toString());
//            dsSPTrongDonHang = sp_dao.layDSSPBangMa(modelDonHang.getValueAt(i, 0).toString());
//            for (SanPham sp : dsSPTrongDonHang) {
//                ChiTietHoaDon cthd = new ChiTietHoaDon(Integer.parseInt(modelDonHang.getValueAt(i, 2).toString()), 0.1, tongTien, tienThoi, hoaDonMoiThem, sp);
//                cthd_dao.themCTHD(cthd);
//            }
//        }
//        for (int i = 0; i < modelDonHang.getRowCount(); i++) {
//            int soLuongMua = Integer.parseInt(modelDonHang.getValueAt(i, 2).toString());
//            for (SanPham sp : dsSPTrongDonHang) {
//                SanPham spCu = null;
//                try {
//                    spCu = sp_dao.laySanPhamBangMa(modelDonHang.getValueAt(i, 0).toString());
//                } catch (Exception e) {
//                    System.out.println("loi sql");
//                    e.printStackTrace();
//                }
//                if (spCu != null) {
//                    sp_dao.capNhatSoLuong(spCu.getMaSP(), spCu.getSoLuong() - soLuongMua);
//                }
//            }
//        }
//        if (khTT.getLoaiKhachHang().getMaLoaiKH().equals("LKH001")) {
//            tongThanhTien = 0;
//            for (int i = 0; i < modelDonHang.getRowCount(); i++) {
//                tongThanhTien += Double.parseDouble(modelDonHang.getValueAt(i, 4).toString());
//            }
//            tongThanhTien *= 0.1;
//            txtTongTien.setText(df.format(tongThanhTien));
//           Thư khóa
//   XoaHetDLTrenTbale(tableDonHang);
//            XoaHetDLTrenTbale(jtbSanPham);
//            DocDuLieuLenTable();
//
//        } else {
//            setTongThanhTien();
//            Thư khóa
//            XoaHetDLTrenTbale(tableDonHang);
//            XoaHetDLTrenTbale(jtbSanPham);
//            DocDuLieuLenTable();
//        }
//    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlDau = new javax.swing.JPanel();
        pnlThongTinCuaHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSanPham = new javax.swing.JTable();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        pnlMain.setBackground(new java.awt.Color(204, 204, 255));
        pnlMain.setForeground(new java.awt.Color(204, 204, 255));
        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));

        pnlDau.setMaximumSize(new java.awt.Dimension(98301, 100));
        pnlDau.setPreferredSize(new java.awt.Dimension(1197, 100));
        pnlDau.setLayout(new javax.swing.BoxLayout(pnlDau, javax.swing.BoxLayout.X_AXIS));

        pnlThongTinCuaHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cửa hàng thời trang BHTT");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setRequestFocusEnabled(false);

        jLabel3.setText("Địa chỉ : ");

        jLabel10.setText("Tên nhân viên :");

        txtTenNV.setText("Nguyễn Văn A");

        jLabel11.setText("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp,  ");

        jLabel12.setText("thành phố Hồ Chí Minh");

        javax.swing.GroupLayout pnlThongTinCuaHangLayout = new javax.swing.GroupLayout(pnlThongTinCuaHang);
        pnlThongTinCuaHang.setLayout(pnlThongTinCuaHangLayout);
        pnlThongTinCuaHangLayout.setHorizontalGroup(
            pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlThongTinCuaHangLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        pnlThongTinCuaHangLayout.setVerticalGroup(
            pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinCuaHangLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDau.add(pnlThongTinCuaHang);

        pnlMain.add(pnlDau);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 300));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(16, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        jtbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Giá", "Phân loại", "Màu sắc", "Chất liệu", "Kích thước"
            }
        ));
        jScrollPane1.setViewportView(jtbSanPham);

        pnlMain.add(jScrollPane1);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void openComponent(JInternalFrame frame) {
        Component[] components = this.getComponents();
        Component component = null;
        for (int i = 0; i < components.length; i++) {
            component = components[i];
            if (component != null) {
                component.setVisible(false);
            }
        }
        this.add(frame);
        frame.setVisible(true);
    }     private String maTuSinh() {
        String ma = "HD";
        int tachMa;
        int i = 0, j = 1;
        int[] dem = new int[999];
        String id;
        for (HoaDon hd : hd_dao.getallDSHoaDon()) {
            id = hd.getMaHD();
            tachMa = Integer.parseInt(id.substring(2, 5));
            dem[i] = tachMa;
            i++;
        }
        i = 0;
        while (j < 999) {
            if (dem[i] < j) {
                if (j <= 9) {
                    ma += "00" + (j);
                } else {
                    ma += "0" + (j);
                }
                break;
            } else if (dem[i] > j) {
                j = dem[i];
            } else {

                i++;
                j++;
            }
        }
        return ma;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbSanPham;
    private javax.swing.JPanel pnlDau;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlThongTinCuaHang;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
    
    }
}
