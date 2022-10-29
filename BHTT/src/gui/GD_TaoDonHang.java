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
    private final DefaultTableModel modolSP;
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
        modolSP = (DefaultTableModel) jtbSanPham.getModel();

        DocDuLieuLenTable();
        DocDuLieuVaoCombobox();
//        moKhoaTextfields(false);

        tr = new TableRowSorter<DefaultTableModel>(modolSP);
        hd_dao = new DAO_HoaDon();
        cthd_dao = new DAO_ChiTietHoaDon();
        kh = new DAO_KhachHang();
        nv = new DAO_NhanVien();
        jtbSanPham.setRowSorter(tr);
        
        
    }
    private void XoaHetDLTrenTbale() {
        DefaultTableModel fm = (DefaultTableModel) jtbSanPham.getModel();
        fm.getDataVector().removeAllElements();
    }
    private void DocDuLieuVaoCombobox() {
        lsp_dao = new DAO_LoaiSP();
        ArrayList<LoaiSanPham> listLSP = lsp_dao.getAllLSP();
        for (LoaiSanPham lsp : listLSP) {
            cbxPL.addItem(lsp.getTenLoaiSP());
            
        }
        mau_dao = new DAO_MauSac();
        ArrayList<MauSac> listMau = mau_dao.getAllMau();
        for (MauSac m : listMau) {
            cbxMS.addItem(m.getTenMau());
        }
        cl_dao = new DAO_ChatLieu();
        ArrayList<ChatLieu> listCL = cl_dao.getAllCL();
        for (ChatLieu cl : listCL) {
            cbxCL.addItem(cl.getTenChatLieu());
        }

//        ncc_dao = new DAO_NhaCungCap();
//        ArrayList<NhaCungCap> listNCC = ncc_dao.getalltbNhaCungCap();
//        for (NhaCungCap ncc : listNCC) {
//            cbxNCC.addItem(ncc.getTenNCC());
//        }
        kt_dao = new DAO_KichThuoc();
        ArrayList<KichThuoc> listKT = kt_dao.getAllKT();
        for (KichThuoc kt : listKT) {
            cbxKT.addItem(kt.getTenKichThuoc());
        }
    }
    private void DocDuLieuLenTable() {
        sp_dao = new DAO_SanPham();
        ArrayList<SanPham> ds = sp_dao.getAllSP();
        int i = 1;
        for (SanPham sp : ds) {
            modolSP.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getGiaGoc(), sp.getLoaiSanPham().getTenLoaiSP(), sp.getMauSac().getTenMau(), sp.getChatLieu().getTenChatLieu(), sp.getKichThuoc().getTenKichThuoc(),
               });
        }

    }
    
     private String maTuSinh() {
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
    public double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }
    
    private void XoaHetDLTrenTbale(JTable table) {
        DefaultTableModel fm = (DefaultTableModel) table.getModel();
        fm.getDataVector().removeAllElements();
    }
    
    private void setTongThanhTien() {
        tongThanhTien = 0;
        for (int i = 0; i < modelDonHang.getRowCount(); i++) {
            tongThanhTien += Double.parseDouble(modelDonHang.getValueAt(i, 4).toString());
        }
        txtTongTien.setText(df.format(tongThanhTien));
    }
    public void thanhToan(){
        KhachHang khTT = kh.layKhachHangBangSDT(txtSDT.getText());
        NhanVien nvTT = nv.layNhanVienBangTen(txtTenNV.getText());
        String diaChi = "12 Nguyễn Văn Bảo,phường 4,Gò Vấp,Hồ Chí Minh";
        Date ngayLapHD = new Date();
        HoaDon hd = new HoaDon(maTuSinh(), ngayLapHD, Double.parseDouble(txtTienKhachDua.getText()),
                diaChi, new NhanVien(nvTT.getMaNV()), new KhachHang(khTT.getMaKH()));
        hd_dao.themHoaDon(hd);
        HoaDon hoaDonMoiThem = hd_dao.getHoaDonMoiNhat();
        for (int i = 0; i < modelDonHang.getRowCount(); i++) {
            double tongTien = ParseDouble(modelDonHang.getValueAt(i, 4).toString());
            dsSPTrongDonHang = sp_dao.layDSSPBangMa(modelDonHang.getValueAt(i, 0).toString());
            for (SanPham sp : dsSPTrongDonHang) {
                ChiTietHoaDon cthd = new ChiTietHoaDon(Integer.parseInt(modelDonHang.getValueAt(i, 2).toString()), 0.1, tongTien, tienThoi, hoaDonMoiThem, sp);
                cthd_dao.themCTHD(cthd);
            }
        }
        for (int i = 0; i < modelDonHang.getRowCount(); i++) {
            int soLuongMua = Integer.parseInt(modelDonHang.getValueAt(i, 2).toString());
            for (SanPham sp : dsSPTrongDonHang) {
                SanPham spCu = null;
                try {
                    spCu = sp_dao.laySanPhamBangMa(modelDonHang.getValueAt(i, 0).toString());
                } catch (Exception e) {
                    System.out.println("loi sql");
                    e.printStackTrace();
                }
                if (spCu != null) {
                    sp_dao.capNhatSoLuong(spCu.getMaSP(), spCu.getSoLuong() - soLuongMua);
                }
            }
        }
        if (khTT.getLoaiKhachHang().getMaLoaiKH().equals("LKH001")) {
            tongThanhTien = 0;
            for (int i = 0; i < modelDonHang.getRowCount(); i++) {
                tongThanhTien += Double.parseDouble(modelDonHang.getValueAt(i, 4).toString());
            }
            tongThanhTien *= 0.1;
            txtTongTien.setText(df.format(tongThanhTien));
            XoaHetDLTrenTbale(tableDonHang);
            XoaHetDLTrenTbale(jtbSanPham);
            DocDuLieuLenTable();

        } else {
            setTongThanhTien();
            XoaHetDLTrenTbale(tableDonHang);
            XoaHetDLTrenTbale(jtbSanPham);
            DocDuLieuLenTable();
        }
    }
   
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
        pnlCenter = new javax.swing.JPanel();
        pnlTKnSL = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbxPL = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbxKT = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbxCL = new javax.swing.JComboBox<>();
        btnThemSP = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbxMS = new javax.swing.JComboBox<>();
        jspSoLuong = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        textFieldAnimation1 = new swing.TextFieldAnimation();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSanPham = new javax.swing.JTable();
        pnlLast = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tab1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDonHang = new javax.swing.JTable();
        tblDonHang = new javax.swing.JScrollPane();
        tableDonHangCho = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        btnTaoDH = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnXoaCTHD = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTienKhachDua = new swing.TextField();
        lblTienThoi = new javax.swing.JLabel();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
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
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btnTimKH, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
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

        pnlCenter.setMaximumSize(new java.awt.Dimension(2147483647, 450));
        pnlCenter.setPreferredSize(new java.awt.Dimension(780, 370));
        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlTKnSL.setBackground(new java.awt.Color(204, 204, 255));
        pnlTKnSL.setMaximumSize(new java.awt.Dimension(32767, 100));
        pnlTKnSL.setPreferredSize(new java.awt.Dimension(780, 100));
        pnlTKnSL.setRequestFocusEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setText("Phân loại :");

        cbxPL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxPL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setText("Kích thước :");

        cbxKT.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxKT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel18.setText("Chất liệu :");

        cbxCL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbxCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCLActionPerformed(evt);
            }
        });

        btnThemSP.setBackground(new java.awt.Color(0, 153, 51));
        btnThemSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSP.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-product.png"))); // NOI18N
        btnThemSP.setText("Thêm sản phẩm vào đơn hàng");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel19.setText("Màu sắc :");

        cbxMS.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbxMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMSActionPerformed(evt);
            }
        });

        jspSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("Nhập số lượng:");

        javax.swing.GroupLayout pnlTKnSLLayout = new javax.swing.GroupLayout(pnlTKnSL);
        pnlTKnSL.setLayout(pnlTKnSLLayout);
        pnlTKnSLLayout.setHorizontalGroup(
            pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTKnSLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textFieldAnimation1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxKT, 0, 151, Short.MAX_VALUE)
                    .addComponent(cbxPL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTKnSLLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxMS, 0, 174, Short.MAX_VALUE)
                    .addComponent(cbxCL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlTKnSLLayout.setVerticalGroup(
            pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTKnSLLayout.createSequentialGroup()
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jspSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(textFieldAnimation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCenter.add(pnlTKnSL, java.awt.BorderLayout.PAGE_START);

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

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlCenter);

        pnlLast.setMaximumSize(new java.awt.Dimension(65534, 500));
        pnlLast.setPreferredSize(new java.awt.Dimension(1122, 400));
        pnlLast.setLayout(new java.awt.BorderLayout());

        pnlDonHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(800, 350));
        pnlDonHang.setLayout(new javax.swing.BoxLayout(pnlDonHang, javax.swing.BoxLayout.Y_AXIS));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("ĐƠN HÀNG");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlDonHang.add(jLabel5);

        tableDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tableDonHang);

        tab1.addTab("Đơn hàng", jScrollPane2);

        tblDonHang.setPreferredSize(new java.awt.Dimension(700, 450));

        tableDonHangCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        tableDonHangCho.setPreferredSize(new java.awt.Dimension(900, 400));
        tableDonHangCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDonHangChoMouseClicked(evt);
            }
        });
        tblDonHang.setViewportView(tableDonHangCho);

        tab1.addTab("Đơn hàng chờ", tblDonHang);

        pnlDonHang.add(tab1);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("TỔNG CỘNG:");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(255, 51, 51));
        txtTongTien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTongTien.setText("0");
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 204, 102)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(548, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTien))
                .addContainerGap())
        );

        pnlDonHang.add(jPanel5);

        pnlLast.add(pnlDonHang, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 300));

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/huydonhang.png"))); // NOI18N
        btnHuy.setText("Hủy đơn hàng");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnTaoDH.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTaoDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnTaoDH.setText("Đơn hàng mới");
        btnTaoDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDHActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(204, 0, 204));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnXoaCTHD.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnXoaCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xoa_sanpham.png"))); // NOI18N
        btnXoaCTHD.setText("Xóa sản phẩm");
        btnXoaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTHDActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/capnhatKH.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật số lượng");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel4.setText("Tiền thồi:");

        txtTienKhachDua.setHintText("Tiền khách đưa");
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        lblTienThoi.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblTienThoi.setForeground(new java.awt.Color(255, 51, 51));
        lblTienThoi.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTienThoi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTaoDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(btnXoaCTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoDH, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblTienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(78, 78, 78)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlLast.add(jPanel3, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlLast);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
       int row = jtbSanPham.getSelectedRow();
       if(row < 0){
           JOptionPane.showMessageDialog(null,"Chọn sản phẩm để thêm vào đơn hàng");
       }else{
            int soluong = (int) jspSoLuong.getValue();
            if(soluong <= 0){
                JOptionPane.showMessageDialog(null,"Số lượng lớn hơn 0","Cảnh báo",JOptionPane.ERROR_MESSAGE);
            }else{
                String maSP = (modolSP.getValueAt(row, 0).toString());
                SanPham sp = sp_dao.laySanPhamBangMa(maSP);
                if(soluong > sp.getSoLuong()){
                    JOptionPane.showMessageDialog(null,"Số lượng phải nhỏ hơn hoặc bằng số lượng tồn");
                    jspSoLuong.setValue(0);
                }else{
                    modelDonHang = (DefaultTableModel) tableDonHang.getModel();
                    modelDonHang.addRow(new Object[]{
                        jtbSanPham.getValueAt(row, 0).toString(),
                        jtbSanPham.getValueAt(row, 1).toString(),
                        soluong,
                        jtbSanPham.getValueAt(row, 3).toString(),
                        Double.parseDouble(jtbSanPham.getValueAt(row, 3).toString()) * soluong
                    });
                    jspSoLuong.setValue(0);
                    setTongThanhTien();
                }
                
            }
       }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtLoaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoaiKHActionPerformed

    private void cbxMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMSActionPerformed

    private void cbxCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCLActionPerformed

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
    private void tableDonHangChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDonHangChoMouseClicked
        try {
             int row = tableDonHangCho.getSelectedRow();
            jspSoLuong.setValue(modelDonHang.getValueAt(row,2));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tableDonHangChoMouseClicked

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

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
       int row = tableDonHang.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(null,"Chọn sản phẩm cần cập nhật");
        }else{
            int slCapNhat = (int) jspSoLuong.getValue();
            modelDonHang.setValueAt(jspSoLuong.getValue(),row,2);
            modelDonHang.setValueAt(Double.parseDouble(tableDonHang.getValueAt(row, 3).toString()) * slCapNhat , row, 4);
            setTongThanhTien();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTHDActionPerformed
        
        
         int r = tableDonHang.getSelectedRow();
       if(r < 0){
           JOptionPane.showMessageDialog(null,"Chọn sản phẩm cần xóa");
       }else{
           modelDonHang.removeRow(r);
           setTongThanhTien();
       }
    }//GEN-LAST:event_btnXoaCTHDActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if(txtTienKhachDua.getText().equals("")){
           JOptionPane.showMessageDialog(null,"Cần nhập tiền khách đưa","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }else if(txtSDT.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Không tin thấy khách hàng","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }else{
            if(tienThoi < 0){
                JOptionPane.showMessageDialog(null,"Số tiền không đủ");
            }else{
                try {
                    thanhToan();
                } catch (Exception ex) {
                    Logger.getLogger(GD_TaoDonHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                tienThoi = 0;
                tongThanhTien = 0;
                lblTienThoi.setText(df.format(tienThoi));
                txtTongTien.setText(df.format(tongThanhTien));
                txtTienKhachDua.setText("");
                JOptionPane.showMessageDialog(null,"Thanh toán thành công");
                XoaHetDLTrenTbale(tableDonHang);   
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTaoDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaoDHActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy đơn hàng này không ?", "Xóa đơn hàng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            XoaHetDLTrenTbale(tableDonHang);
            setTongThanhTien();
            tienThoi = 0;
            lblTienThoi.setText(df.format(tienThoi));
            txtTienKhachDua.setText("");
        } 
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        tienThoi = 0;
        try {
            tienThoi = ParseDouble(txtTienKhachDua.getText()) - tongThanhTien;
            lblTienThoi.setText(df.format(tienThoi)); 
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoDH;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnXoaCTHD;
    private javax.swing.JComboBox<String> cbxCL;
    private javax.swing.JComboBox<String> cbxKT;
    private javax.swing.JComboBox<String> cbxMS;
    private javax.swing.JComboBox<String> cbxPL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JSpinner jspSoLuong;
    private javax.swing.JTable jtbSanPham;
    private javax.swing.JLabel lblTienThoi;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDau;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlDongHo1;
    private javax.swing.JPanel pnlKhachHang2;
    private javax.swing.JPanel pnlLast;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTKnSL;
    private javax.swing.JPanel pnlThongTinCuaHang;
    private javax.swing.JTabbedPane tab1;
    private javax.swing.JTable tableDonHang;
    public javax.swing.JTable tableDonHangCho;
    private javax.swing.JScrollPane tblDonHang;
    private swing.TextFieldAnimation textFieldAnimation1;
    private javax.swing.JTextField txtGio;
    private javax.swing.JTextField txtLoaiKH;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private swing.TextField txtTienKhachDua;
    private javax.swing.JTextField txtTongTien;
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
