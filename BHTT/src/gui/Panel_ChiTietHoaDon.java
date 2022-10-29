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
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import java.awt.Component;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


/**
 *
 * @author Anh Thu
 */
public class Panel_ChiTietHoaDon extends javax.swing.JPanel{

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

    
    private double tongThanhTien;
    private double tienThoi;
    private DAO_KhachHang kh;
    private DAO_LoaiKhachHang lkh;
    private DAO_NhanVien nv;
    private DAO_ChiTietHoaDon cthd_dao;
    public TaiKhoan tkDN;
    private DAO_HoaDon hd_dao;
    private ArrayList<SanPham> dsSPTrongDonHang;
    private String txtSDT,txtTenNV;
    
    public Panel_ChiTietHoaDon(String sdt, String tenNV) {
        txtSDT=sdt;
        txtTenNV=tenNV;
        initComponents();
        this.setFocusable(true);

        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
        }
        
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
        KhachHang khTT = kh.layKhachHangBangSDT(txtSDT);
        NhanVien nvTT = nv.layNhanVienBangTen(txtTenNV);
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
//            Thư khóa
   XoaHetDLTrenTbale(tableDonHang);
            XoaHetDLTrenTbale(jtbSanPham);
            DocDuLieuLenTable();

        } else {
            setTongThanhTien();
//            Thư khóa
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

        jPanel6 = new javax.swing.JPanel();
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
        txtTim = new swing.TextFieldAnimation();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSanPham = new javax.swing.JTable();
        pnlLast = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDonHang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnXoaCTHD = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTienKhachDua = new swing.TextField();
        lblTienThoi = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setLayout(new javax.swing.OverlayLayout(this));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

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
        cbxPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPLActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setText("Kích thước :");

        cbxKT.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxKT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbxKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKTActionPerformed(evt);
            }
        });

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

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTKnSLLayout = new javax.swing.GroupLayout(pnlTKnSL);
        pnlTKnSL.setLayout(pnlTKnSLLayout);
        pnlTKnSLLayout.setHorizontalGroup(
            pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTKnSLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jPanel6.add(pnlCenter);

        pnlLast.setMaximumSize(new java.awt.Dimension(65534, 500));
        pnlLast.setPreferredSize(new java.awt.Dimension(1122, 400));
        pnlLast.setLayout(new javax.swing.BoxLayout(pnlLast, javax.swing.BoxLayout.Y_AXIS));

        pnlDonHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(800, 350));
        pnlDonHang.setLayout(new java.awt.GridLayout(1, 3));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ĐƠN HÀNG");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlDonHang.add(jLabel5);

        pnlLast.add(pnlDonHang);

        pnlForm.setLayout(new javax.swing.OverlayLayout(pnlForm));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        tableDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tableDonHang);

        jPanel2.add(jScrollPane2);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTien))
                .addContainerGap())
        );

        jPanel2.add(jPanel5);

        jPanel1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setMaximumSize(new java.awt.Dimension(400, 32767));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 300));

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

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblTienThoi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnXoaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCapNhat))))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        pnlForm.add(jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1122, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );

        pnlForm.add(jPanel4);

        pnlLast.add(pnlForm);

        jPanel6.add(pnlLast);

        add(jPanel6);
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCLActionPerformed
        String s = cbxCL.getSelectedItem().toString();
        if (s.equals("Tất cả")) {
            s = "(Tất cả )";

            for (ChatLieu cl : cl_dao.getAllCL()) {
                s += "|(" + cl.getTenChatLieu() + ")";
            }
        }
        if (filters.size() < 3) {
            filters.add(RowFilter.regexFilter(s, 6));
        } else {
            filters.set(2, RowFilter.regexFilter(s, 6));
        }
        // Apply filters
        tr.setRowFilter(RowFilter.andFilter(filters));
    }//GEN-LAST:event_cbxCLActionPerformed

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
                    //                    Thư sửa từ tbl sang pblTab
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

    private void cbxMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMSActionPerformed
        String s = cbxMS.getSelectedItem().toString();
        if (s.equals("Tất cả")) {
            s = "(Tất cả)";

            for (MauSac ms : mau_dao.getAllMau()) {
                s += "|(" + ms.getTenMau() + ")";
            }
        }
        if (filters.size() < 2) {
            filters.add(RowFilter.regexFilter(s, 5));
        } else {
            filters.set(1, RowFilter.regexFilter(s, 5));
        }
        // Apply filters
        tr.setRowFilter(RowFilter.andFilter(filters));
    }//GEN-LAST:event_cbxMSActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if(txtTienKhachDua.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Cần nhập tiền khách đưa","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        }else if(txtSDT.equals("")){
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
                Panel_ChiTietHoaDon.this.remove(this);
            } 
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnXoaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTHDActionPerformed

        int r = tableDonHang.getSelectedRow();
        if(r < 0){
            JOptionPane.showMessageDialog(null,"Chọn sản phẩm cần xóa");
        }else{
            modelDonHang.removeRow(r);
            setTongThanhTien();
        }
    }//GEN-LAST:event_btnXoaCTHDActionPerformed

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

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        tienThoi = 0;
        try {
            tienThoi = ParseDouble(txtTienKhachDua.getText()) - tongThanhTien;
            lblTienThoi.setText(df.format(tienThoi));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void cbxKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKTActionPerformed
          String s = cbxKT.getSelectedItem().toString();
        if (s.equals("Tất cả")) {
            s = "(Tất cả)";

            for (KichThuoc kt : kt_dao.getAllKT()) {
                s += "|(" + kt.getTenKichThuoc() + ")";
            }
        }

        if (filters.size() < 4) {
            filters.add(RowFilter.regexFilter(s, 7));
        } else {
            filters.set(3, RowFilter.regexFilter(s, 7));
        }
        // Apply filters
        tr.setRowFilter(RowFilter.andFilter(filters));
    }//GEN-LAST:event_cbxKTActionPerformed

    private void cbxPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPLActionPerformed
        String s = cbxPL.getSelectedItem().toString();
        if (s.equals("Tất cả")) {
            s = "(Tất cả)";

            for (LoaiSanPham lsp : lsp_dao.getAllLSP()) {
                s += "|(" + lsp.getTenLoaiSP() + ")";
            }
        }

        if (filters.isEmpty()) {
            filters.add(RowFilter.regexFilter(s, 4));
        } else {
            filters.set(0, RowFilter.regexFilter(s, 4));
        }
        // Apply filters
        tr.setRowFilter(RowFilter.andFilter(filters));
    }//GEN-LAST:event_cbxPLActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        String search = txtTim.getText();
        timKiemNhaCC(search);
    }//GEN-LAST:event_txtTimKeyReleased
    public void timKiemNhaCC(String ten) {
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(modolSP);
        jtbSanPham.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(ten));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaCTHD;
    private javax.swing.JComboBox<String> cbxCL;
    private javax.swing.JComboBox<String> cbxKT;
    private javax.swing.JComboBox<String> cbxMS;
    private javax.swing.JComboBox<String> cbxPL;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jspSoLuong;
    private javax.swing.JTable jtbSanPham;
    private javax.swing.JLabel lblTienThoi;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlLast;
    private javax.swing.JPanel pnlTKnSL;
    private javax.swing.JTable tableDonHang;
    private swing.TextField txtTienKhachDua;
    private swing.TextFieldAnimation txtTim;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    Component setPreferredSize(JPanel pnlForm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
