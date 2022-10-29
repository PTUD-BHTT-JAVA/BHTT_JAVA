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
public class GD_TaoDonHang extends javax.swing.JInternalFrame implements Runnable{

    
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
    public void dongPanel(Component c){
        pnlTab.remove(c);
    }
    public GD_TaoDonHang(String _username) {
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
//    
//     private String maTuSinh() {
//        String ma = "HD";
//        int tachMa;
//        int i = 0, j = 1;
//        int[] dem = new int[999];
//        String id;
//        for (HoaDon hd : hd_dao.getallDSHoaDon()) {
//            id = hd.getMaHD();
//            tachMa = Integer.parseInt(id.substring(2, 5));
//            dem[i] = tachMa;
//            i++;
//        }
//        i = 0;
//        while (j < 999) {
//            if (dem[i] < j) {
//                if (j <= 9) {
//                    ma += "00" + (j);
//                } else {
//                    ma += "0" + (j);
//                }
//                break;
//            } else if (dem[i] > j) {
//                j = dem[i];
//            } else {
//
//                i++;
//                j++;
//            }
//        }
//        return ma;
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
////            Thư khóa
//   XoaHetDLTrenTbale(tableDonHang);
//            XoaHetDLTrenTbale(jtbSanPham);
//            DocDuLieuLenTable();
//
//        } else {
//            setTongThanhTien();
////            Thư khóa
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
        pnlDongHo1 = new javax.swing.JPanel();
        txtGio = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        txtNgay = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlThongTinCuaHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlKhachHang2 = new javax.swing.JPanel();
        txtSDT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtLoaiKH = new javax.swing.JTextField();
        btnThemKH = new javax.swing.JButton();
        btnTimKH = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnTaoDH = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        pnlTab = new javax.swing.JTabbedPane();

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

        pnlDongHo1.setBackground(new java.awt.Color(204, 204, 255));
        pnlDongHo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDongHo1.setPreferredSize(new java.awt.Dimension(227, 200));

        txtGio.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        txtGio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGio.setEnabled(false);
        txtGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioActionPerformed(evt);
            }
        });

        jTextField10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("001");
        jTextField10.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField10.setEnabled(false);

        txtNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNgay.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNgay.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ĐƠN HÀNG :");

        javax.swing.GroupLayout pnlDongHo1Layout = new javax.swing.GroupLayout(pnlDongHo1);
        pnlDongHo1.setLayout(pnlDongHo1Layout);
        pnlDongHo1Layout.setHorizontalGroup(
            pnlDongHo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDongHo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDongHo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGio)
                    .addComponent(txtNgay)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDongHo1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDongHo1Layout.setVerticalGroup(
            pnlDongHo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDongHo1Layout.createSequentialGroup()
                .addGroup(pnlDongHo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlDau.add(pnlDongHo1);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
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

        pnlKhachHang2.setBackground(new java.awt.Color(204, 204, 255));
        pnlKhachHang2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Thông tin khách hàng"));

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel13.setText("Số điện thoại :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setText("Tên khách hàng :");

        txtTenKH.setEnabled(false);
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel15.setText("Loại khách hàng:");

        txtLoaiKH.setEnabled(false);
        txtLoaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoaiKHActionPerformed(evt);
            }
        });

        btnThemKH.setBackground(new java.awt.Color(99, 203, 116));
        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        btnThemKH.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKH.setText("+");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnTimKH.setBackground(new java.awt.Color(99, 203, 116));
        btnTimKH.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnTimKH.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKhachHang2Layout = new javax.swing.GroupLayout(pnlKhachHang2);
        pnlKhachHang2.setLayout(pnlKhachHang2Layout);
        pnlKhachHang2Layout.setHorizontalGroup(
            pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHang2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(txtLoaiKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addComponent(btnTimKH, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlKhachHang2Layout.setVerticalGroup(
            pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHang2Layout.createSequentialGroup()
                .addGroup(pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlKhachHang2Layout.createSequentialGroup()
                        .addGroup(pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlKhachHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHang2Layout.createSequentialGroup()
                        .addComponent(btnTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlDau.add(pnlKhachHang2);

        pnlMain.add(pnlDau);

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));

        btnTaoDH.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTaoDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnTaoDH.setText("Đơn hàng mới");
        btnTaoDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDHActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/huydonhang.png"))); // NOI18N
        btnHuy.setText("Đóng đơn hàng");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1747, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnTaoDH, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnTaoDH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(jPanel7);

        pnlTab.setBackground(new java.awt.Color(204, 204, 255));
        pnlMain.add(pnlTab);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtLoaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoaiKHActionPerformed

    private void txtGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        GD_KhachHang frKhachHang = new GD_KhachHang(username);
        openComponent(frKhachHang);

    }//GEN-LAST:event_btnThemKHActionPerformed
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
    }
    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        kh_dao=new DAO_KhachHang();
        String s=txtSDT.getText();
        KhachHang kh = new KhachHang();
        kh=kh_dao.layKhachHangBangSDT(s);
        if(kh != null){
            txtTenKH.setText(kh.getTenKH());
            txtLoaiKH.setText(kh.getLoaiKhachHang().getTenLoai());
        }else{
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
            txtTenKH.setText("");
        }
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void btnTaoDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDHActionPerformed
        if (txtTenKH.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy chọn khách hàng để tạo hóa đơn!");
        }else{
    
        Panel_ChiTietHoaDon pnl=new Panel_ChiTietHoaDon(txtSDT.getText(),txtTenNV.getText());
        pnlTab.add(txtTenKH.getText(),pnl);
        pnlTab.setSelectedComponent(pnl);
        txtSDT.setText("");
        txtTenKH.setText("");
        txtLoaiKH.setText("");
        }
    }//GEN-LAST:event_btnTaoDHActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (pnlTab.getSelectedComponent()==null){
            JOptionPane.showMessageDialog(this, "Không tồn tại đơn hàng nào để hủy!");
        }else
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn đóng đơn hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            pnlTab.remove(pnlTab.getSelectedComponent());
        }
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoDH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JPanel pnlDau;
    private javax.swing.JPanel pnlDongHo1;
    private javax.swing.JPanel pnlKhachHang2;
    private javax.swing.JPanel pnlMain;
    public javax.swing.JTabbedPane pnlTab;
    private javax.swing.JPanel pnlThongTinCuaHang;
    private javax.swing.JTextField txtGio;
    private javax.swing.JTextField txtLoaiKH;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
           txtGio =new JTextField();
           txtNgay =new JTextField();
			Date thoiGianHienTai = new Date();
			SimpleDateFormat sdf_Gio = new SimpleDateFormat("hh:mm:ss");
			SimpleDateFormat sdf_Ngay = new SimpleDateFormat("dd/MM/yyyy");
			while (true) {
				thoiGianHienTai = new Date(); // lấy thời gian hiện tại
				String ngayTrongTuan = "";
				if (thoiGianHienTai.getDay()== 0)
					ngayTrongTuan = "Chủ nhật, ";
				else
					ngayTrongTuan = "Thứ " + (thoiGianHienTai.getDay() + 1) + ", ";// do getDay() tính từ 1.
				txtGio.setText(sdf_Gio.format(thoiGianHienTai));
				txtNgay.setText(ngayTrongTuan + sdf_Ngay.format(thoiGianHienTai));
				thread.sleep(1000); // cho phép ngủ trong khoảng 1000 mns =1s
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
