package gui;

import connectDB.ConnectDB;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoanTra;
import dao.DAO_HoaDon;
import dao.DAO_HoaDonHoan;
import dao.DAO_NhanVien;

import entity.ChiTietHoaDon;
import entity.ChiTietHoanTra;
import entity.HoaDon;
import entity.HoaDonHoanTra;

import entity.SanPham;
import entity.TaiKhoan;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class GD_QLTraCuu extends javax.swing.JInternalFrame implements Runnable {

    private String username;
    List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
    private TableRowSorter<DefaultTableModel> tr;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat fday = new SimpleDateFormat("yyyy-MM-dd");
//    public DefaultTableModel modelDonHang;
    DecimalFormat df = new DecimalFormat("#,##0 VND");
    private Thread thread = new Thread(this);
    private DAO_ChiTietHoaDon cthd_dao;
    public TaiKhoan tkDN;

    private ArrayList<SanPham> dsSPTrongDonHang;
    private  DAO_ChiTietHoanTra chiTietHoanTra;
    private final DefaultTableModel modelDSHoaDon;
    private  DAO_NhanVien nhanvien_dao;
    private DAO_HoaDonHoan hdh_dao;
    private final Date today;
    private final DefaultTableModel modelDSDonHoan;

    public GD_QLTraCuu(String _username) {
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
        username = _username;
        
        cthd_dao = new DAO_ChiTietHoaDon();
        chiTietHoanTra = new DAO_ChiTietHoanTra();
        hdh_dao = new DAO_HoaDonHoan();
        modelDSHoaDon = (DefaultTableModel) tblHoaDonBanHang.getModel();
        modelDSDonHoan = (DefaultTableModel)  tblHoaDonHoan.getModel();
        today = new Date();
        jdcNgayTim.setDate(new Date());
        jdcNgayMuonTim.setDate(new Date());
        jdcNgayTimHoan.setDate(new Date());
        jdcNgayMuonTimHoan.setDate(new Date());
        nhanvien_dao=new DAO_NhanVien();
//        DocDSHoaDon();
//        DocDanhSachDonHoan();
    }

    private void DocDSHoaDon() {
        DAO_HoaDon hd_dao=new DAO_HoaDon();
        for (HoaDon hd : hd_dao.getallDSHoaDon()) {
            double thanhTien=hd.thanhTienThuong();
            int soLuong=0;
            if(hd.getKhachHang().getLoaiKhachHang().getMaLoaiKH().equals("LKH001"))
                thanhTien=hd.thanhTienVIP();
            for (ChiTietHoaDon ct: cthd_dao.layDSHDBangMa(hd.getMaHD()))
                soLuong= soLuong+ct.getSoLuong();
            modelDSHoaDon.addRow(new Object[]{
                hd.getMaHD(), sdf.format(hd.getNgayLap()),
                soLuong, df.format(thanhTien), hd.getKhachHang().getTenKH(),
                hd.getNhanVien().getTenNV(),
            });
        }
    }
    
    private void DocDanhSachDonHoan()
    {
        
        DAO_HoaDonHoan hdh_dao=new DAO_HoaDonHoan();
        for (HoaDonHoanTra hdht : hdh_dao.getallDSHoaDonHoan()) {
            int soLuong=0;
               for (ChiTietHoanTra ct:chiTietHoanTra.layDSCTHTBangMa(hdht.getMaHDHT())){
                   soLuong=soLuong+ct.getSoLuong();
               }
                modelDSDonHoan.addRow(new Object[]{
                    hdht.getMaHDHT(),
                    hdht.getNgayHoanTra(),
                    soLuong,
                    df.format(hdht.tongTienHoan()),
                    hdht.getHoaDon().getKhachHang().getTenKH(),
                    hdht.getNhanVien().getTenNV(),
                });
        }   
    }
    
//    X??a danh s??ch h??a ????n b??n h??ng
    private void XoaHetDuLieuTrenTable(JTable table){
        DefaultTableModel dm  = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlLichSuDonHang = new javax.swing.JPanel();
        pnlDau = new javax.swing.JPanel();
        pnlThongTinCuaHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtTim = new swing.TextFieldAnimation();
        btnChiTiet = new swing.Button();
        jdcNgayTim = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jdcNgayMuonTim = new com.toedter.calendar.JDateChooser();
        jspNam = new com.toedter.calendar.JYearChooser();
        jLabel8 = new javax.swing.JLabel();
        btnTim = new swing.Button();
        btnQuy1 = new swing.Button();
        btnQuy2 = new swing.Button();
        btnQuy3 = new swing.Button();
        btnQuy4 = new swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonBanHang = new javax.swing.JTable();
        pnlLichSuDonHoan = new javax.swing.JPanel();
        pnlDau2 = new javax.swing.JPanel();
        pnlThongTinCuaHang2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtTimDonHoan = new swing.TextFieldAnimation();
        btnChiTietDonHoan = new swing.Button();
        jdcNgayTimHoan = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jdcNgayMuonTimHoan = new com.toedter.calendar.JDateChooser();
        jspNamHoan = new com.toedter.calendar.JYearChooser();
        jLabel21 = new javax.swing.JLabel();
        btnTimDonHoan = new swing.Button();
        btnQuy1Hoan = new swing.Button();
        btnQuy2Hoan = new swing.Button();
        btnQuy3Hoan = new swing.Button();
        btnQuy4Hoan = new swing.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonHoan = new javax.swing.JTable();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnlMain.setBackground(new java.awt.Color(204, 204, 255));
        pnlMain.setForeground(new java.awt.Color(204, 204, 255));
        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));

        jTabbedPane1.setMaximumSize(null);

        pnlLichSuDonHang.setLayout(new javax.swing.BoxLayout(pnlLichSuDonHang, javax.swing.BoxLayout.Y_AXIS));

        pnlDau.setMaximumSize(new java.awt.Dimension(98301, 100));
        pnlDau.setPreferredSize(new java.awt.Dimension(1197, 100));
        pnlDau.setLayout(new javax.swing.BoxLayout(pnlDau, javax.swing.BoxLayout.X_AXIS));

        pnlThongTinCuaHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("C???a h??ng th???i trang BHTT");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setRequestFocusEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("?????a ch??? : ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("T??n nh??n vi??n :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("12 Nguy???n V??n B???o, ph?????ng 4, qu???n G?? V???p, th??nh ph??? H??? Ch?? Minh  ");

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenNV.setText("Nguy???n V??n A");

        javax.swing.GroupLayout pnlThongTinCuaHangLayout = new javax.swing.GroupLayout(pnlThongTinCuaHang);
        pnlThongTinCuaHang.setLayout(pnlThongTinCuaHangLayout);
        pnlThongTinCuaHangLayout.setHorizontalGroup(
            pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinCuaHangLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlThongTinCuaHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1394, Short.MAX_VALUE)
                .addGap(1015, 1015, 1015))
        );
        pnlThongTinCuaHangLayout.setVerticalGroup(
            pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinCuaHangLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinCuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblTenNV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDau.add(pnlThongTinCuaHang);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlDau.add(jPanel6);

        pnlLichSuDonHang.add(pnlDau);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(2522, 100));

        txtTim.setHintText("Nh???p m?? h??a ????n.");
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        btnChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnChiTiet.setText("Xem chi ti???t ????n h??ng");
        btnChiTiet.setColor1(new java.awt.Color(51, 0, 255));
        btnChiTiet.setColor2(new java.awt.Color(204, 0, 204));
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("T??? ng??y:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("?????n ng??y:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("N??m");

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTim.setColor1(new java.awt.Color(51, 0, 255));
        btnTim.setColor2(new java.awt.Color(204, 0, 204));
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnQuy1.setText("Qu?? 1");
        btnQuy1.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy1.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy1ActionPerformed(evt);
            }
        });

        btnQuy2.setText("Qu?? 2");
        btnQuy2.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy2.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy2ActionPerformed(evt);
            }
        });

        btnQuy3.setText("Qu?? 3");
        btnQuy3.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy3.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy3ActionPerformed(evt);
            }
        });

        btnQuy4.setText("Qu?? 4");
        btnQuy4.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy4.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspNam, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnQuy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnQuy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuy4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcNgayTim, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcNgayMuonTim, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1386, 1386, 1386))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgayTim, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jspNam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnQuy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuy4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnQuy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jdcNgayMuonTim, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlLichSuDonHang.add(jPanel4);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 300));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(16, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        tblHoaDonBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? h??a ????n", "Ng??y l???p", "T???ng s??? l?????ng s???n ph???m", "T???ng th??nh ti???n", "T??n kh??ch h??ng", "T??n nh??n vi??n"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDonBanHang);
        if (tblHoaDonBanHang.getColumnModel().getColumnCount() > 0) {
            tblHoaDonBanHang.getColumnModel().getColumn(0).setResizable(false);
        }

        pnlLichSuDonHang.add(jScrollPane1);

        jTabbedPane1.addTab("L???ch s??? ????n h??ng", pnlLichSuDonHang);

        pnlLichSuDonHoan.setLayout(new javax.swing.BoxLayout(pnlLichSuDonHoan, javax.swing.BoxLayout.Y_AXIS));

        pnlDau2.setMaximumSize(new java.awt.Dimension(98301, 100));
        pnlDau2.setPreferredSize(new java.awt.Dimension(1197, 100));
        pnlDau2.setLayout(new javax.swing.BoxLayout(pnlDau2, javax.swing.BoxLayout.X_AXIS));

        pnlThongTinCuaHang2.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("C???a h??ng th???i trang BHTT");
        jLabel9.setFocusable(false);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setRequestFocusEnabled(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("?????a ch??? : ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("T??n nh??n vi??n :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("12 Nguy???n V??n B???o, ph?????ng 4, qu???n G?? V???p, th??nh ph??? H??? Ch?? Minh  ");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Nguy???n V??n A");

        javax.swing.GroupLayout pnlThongTinCuaHang2Layout = new javax.swing.GroupLayout(pnlThongTinCuaHang2);
        pnlThongTinCuaHang2.setLayout(pnlThongTinCuaHang2Layout);
        pnlThongTinCuaHang2Layout.setHorizontalGroup(
            pnlThongTinCuaHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinCuaHang2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlThongTinCuaHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinCuaHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlThongTinCuaHang2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1392, Short.MAX_VALUE)
                .addGap(1015, 1015, 1015))
        );
        pnlThongTinCuaHang2Layout.setVerticalGroup(
            pnlThongTinCuaHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinCuaHang2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinCuaHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinCuaHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDau2.add(pnlThongTinCuaHang2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlDau2.add(jPanel7);

        pnlLichSuDonHoan.add(pnlDau2);

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel8.setPreferredSize(new java.awt.Dimension(2522, 100));

        txtTimDonHoan.setHintText("Nh???p m?? ????n ho??n");
        txtTimDonHoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimDonHoanKeyReleased(evt);
            }
        });

        btnChiTietDonHoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnChiTietDonHoan.setText("Xem chi ti???t ????n ho??n");
        btnChiTietDonHoan.setColor1(new java.awt.Color(51, 0, 255));
        btnChiTietDonHoan.setColor2(new java.awt.Color(204, 0, 204));
        btnChiTietDonHoan.setMaximumSize(null);
        btnChiTietDonHoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietDonHoanActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("T??? ng??y:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("?????n ng??y:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("N??m");

        btnTimDonHoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTimDonHoan.setColor1(new java.awt.Color(51, 0, 255));
        btnTimDonHoan.setColor2(new java.awt.Color(204, 0, 204));
        btnTimDonHoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimDonHoanActionPerformed(evt);
            }
        });

        btnQuy1Hoan.setText("Qu?? 1");
        btnQuy1Hoan.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy1Hoan.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy1Hoan.setMaximumSize(null);
        btnQuy1Hoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy1HoanActionPerformed(evt);
            }
        });

        btnQuy2Hoan.setText("Qu?? 2");
        btnQuy2Hoan.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy2Hoan.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy2Hoan.setMaximumSize(null);
        btnQuy2Hoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy2HoanActionPerformed(evt);
            }
        });

        btnQuy3Hoan.setText("Qu?? 3");
        btnQuy3Hoan.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy3Hoan.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy3Hoan.setMaximumSize(null);
        btnQuy3Hoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy3HoanActionPerformed(evt);
            }
        });

        btnQuy4Hoan.setText("Qu?? 4");
        btnQuy4Hoan.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy4Hoan.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy4Hoan.setMaximumSize(null);
        btnQuy4Hoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy4HoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimDonHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspNamHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuy1Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnQuy2Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnQuy3Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuy4Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcNgayTimHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcNgayMuonTimHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnChiTietDonHoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(btnTimDonHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1386, 1386, 1386))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(btnTimDonHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgayTimHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTimDonHoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel20)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jspNamHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnQuy2Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuy3Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuy4Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnChiTietDonHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnQuy1Hoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jdcNgayMuonTimHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlLichSuDonHoan.add(jPanel8);

        jScrollPane3.setMaximumSize(new java.awt.Dimension(32767, 300));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(16, 100));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 200));

        tblHoaDonHoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? ????n ho??n", "M?? h??a ????n", "Ng??y ho??n ????n", "T???ng s???n ph???m", "T???ng ti???n ho??n", "T??n kh??ch h??ng", "T??n nh??n vi??n"
            }
        ));
        jScrollPane3.setViewportView(tblHoaDonHoan);
        if (tblHoaDonHoan.getColumnModel().getColumnCount() > 0) {
            tblHoaDonHoan.getColumnModel().getColumn(0).setResizable(false);
        }

        pnlLichSuDonHoan.add(jScrollPane3);

        jTabbedPane1.addTab("L???ch s??? ????n ho??n", pnlLichSuDonHoan);

        pnlMain.add(jTabbedPane1);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        int hangChon = tblHoaDonBanHang.getSelectedRow();
        if (hangChon == -1) {
            JOptionPane.showMessageDialog(null, "Vui l??ng ch???n h??a ????n c???n xem chi ti???t");
            return;
        }
        String m = modelDSHoaDon.getValueAt(hangChon, 0).toString();
        DG_CTDH dgCTDH = new DG_CTDH(m);
        dgCTDH.setLocationRelativeTo(null);
        dgCTDH.setVisible(true);
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        
        Date ngayTim = jdcNgayTim.getDate();
        Date ngayCanTim = jdcNgayMuonTim.getDate();
        String dayTim = fday.format(ngayTim);
        String dayCanTim =  fday.format(ngayCanTim);
        if (ngayTim.after(today) || ngayTim.after(today)) {
            JOptionPane.showMessageDialog(null, "Th???i gian t??m kh??ng l???n h??n th???i gian hi???n t???i");
        } else {
            if (ngayTim.after(ngayCanTim)) {
                JOptionPane.showMessageDialog(null, "Ng??y t??m kh??ng l???n h??n ng??y c???n t??m");
            }else{
                ArrayList<ChiTietHoaDon> dsHoaDonTheoNgay = cthd_dao.layDSHDTheoNgayQL(dayTim, dayCanTim);
                XoaHetDuLieuTrenTable(tblHoaDonBanHang);
                for (ChiTietHoaDon cthd : dsHoaDonTheoNgay) {
                    modelDSHoaDon.addRow(new Object[]{
                        cthd.getHoaDon().getMaHD(),
                        sdf.format(cthd.getHoaDon().getNgayLap()),
                        cthd.getSoLuong(), 
                        df.format(cthd.getTongTien()),
                        cthd.getHoaDon().getKhachHang().getTenKH(),
                        cthd.getHoaDon().getNhanVien().getTenNV()
                    });
                }
            }
        }
        System.out.println(ngayCanTim);
        System.out.println(ngayTim);
    }//GEN-LAST:event_btnTimActionPerformed
    
//    T??m ki???m danh s??ch h??a ????n theo qu??
    private void TimKiemTheoQuy(String dauQuy,String cuoiQuy){
//        String maNV = username;
        ArrayList<ChiTietHoaDon> dsHoaDonTheoQuy = cthd_dao.layDSHoaDonTheoQuyQL(dauQuy, cuoiQuy);
        XoaHetDuLieuTrenTable(tblHoaDonBanHang);
        for (ChiTietHoaDon cthd : dsHoaDonTheoQuy) {
            modelDSHoaDon.addRow(new Object[]{
                cthd.getHoaDon().getMaHD(), sdf.format(cthd.getHoaDon().getNgayLap()),
                cthd.getSoLuong(), df.format(cthd.getTongTien()), cthd.getHoaDon().getKhachHang().getTenKH(),
                cthd.getHoaDon().getNhanVien().getTenNV()
            });
        }
    }
    
    private void TimKiemDonHoanTheoQuy(String dauQuy,String cuoiQuy){
        ArrayList<HoaDonHoanTra> dsHoaDonHoanTheoQuy = hdh_dao.getDSHDHoanTheoNgayQL(dauQuy, cuoiQuy);
        XoaHetDuLieuTrenTable(tblHoaDonHoan);
        for (HoaDonHoanTra hdht : dsHoaDonHoanTheoQuy) {
            modelDSDonHoan.addRow(new Object[]{
                hdht.getMaHDHT(), hdht.getHoaDon().getMaHD(),
                sdf.format(hdht.getNgayHoanTra()),
                hdht.getSoLuong(),
                df.format(hdht.tongTienHoan()), hdht.getHoaDon().getKhachHang().getTenKH(),
                hdht.getHoaDon().getNhanVien().getTenNV()
            });
        }
    }
    
    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        String tim = txtTim.getText();
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(modelDSHoaDon);
        tblHoaDonBanHang.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)"+tim));
    }//GEN-LAST:event_txtTimKeyReleased

    private void btnQuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy1ActionPerformed
        int year = jspNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            String dauQuy1 = String.valueOf(year).concat("-01-01");
            String cuoiQuy1 = String.valueOf(year).concat("-03-31");
            TimKiemTheoQuy(dauQuy1, cuoiQuy1);
        }
    }//GEN-LAST:event_btnQuy1ActionPerformed

    private void btnQuy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy2ActionPerformed
         int year = jspNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        String dauQuy2 = String.valueOf(year).concat("-04-01");
        String cuoiQuy2 = String.valueOf(year).concat("-06-30");
         if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            TimKiemTheoQuy(dauQuy2, cuoiQuy2);
        }
    }//GEN-LAST:event_btnQuy2ActionPerformed

    private void btnQuy3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy3ActionPerformed
        int year = jspNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        String dauQuy3 = String.valueOf(year).concat("-07-01");
        String cuoiQuy3 = String.valueOf(year).concat("-09-30");
         if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            TimKiemTheoQuy(dauQuy3, cuoiQuy3);
        }
    }//GEN-LAST:event_btnQuy3ActionPerformed

    private void btnQuy4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy4ActionPerformed
        int year = jspNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        String dauQuy4 = String.valueOf(year).concat("-10-01");
        String cuoiQuy4 = String.valueOf(year).concat("-12-31");
         if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            TimKiemTheoQuy(dauQuy4, cuoiQuy4);
        }
    }//GEN-LAST:event_btnQuy4ActionPerformed

    private void txtTimDonHoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimDonHoanKeyReleased
        String tim = txtTimDonHoan.getText();
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(modelDSDonHoan);
        tblHoaDonHoan.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)"+tim));
    }//GEN-LAST:event_txtTimDonHoanKeyReleased

    private void btnChiTietDonHoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietDonHoanActionPerformed
      int hangChon = tblHoaDonHoan.getSelectedRow();
        if (hangChon == -1) {
            JOptionPane.showMessageDialog(null, "Vui l??ng ch???n h??a ????n c???n xem chi ti???t");
            return;
        }
        String m = modelDSDonHoan.getValueAt(hangChon, 0).toString();
        DG_CTDHHT dgCTDHHT = new DG_CTDHHT(m);
        dgCTDHHT.setLocationRelativeTo(null);
        dgCTDHHT.setVisible(true);
    }//GEN-LAST:event_btnChiTietDonHoanActionPerformed

    private void btnTimDonHoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimDonHoanActionPerformed
        Date ngayTim = jdcNgayTimHoan.getDate();
        Date ngayCanTim = jdcNgayMuonTimHoan.getDate();
        String dayTim = fday.format(ngayTim);
        String dayCanTim =  fday.format(ngayCanTim);
        if (ngayTim.after(today) || ngayTim.after(today)) {
            JOptionPane.showMessageDialog(null, "Th???i gian t??m kh??ng l???n h??n th???i gian hi???n t???i");
        } else {
            if (ngayTim.after(ngayCanTim)) {
                JOptionPane.showMessageDialog(null, "Ng??y t??m kh??ng l???n h??n ng??y c???n t??m");
            }else{
                ArrayList<HoaDonHoanTra> dsHoaDonHoanTheoNgay =  hdh_dao.getDSHDHoanTheoNgayQL(dayTim, dayCanTim);
                XoaHetDuLieuTrenTable(tblHoaDonHoan);
                for (HoaDonHoanTra hdht : dsHoaDonHoanTheoNgay) {
                    modelDSDonHoan.addRow(new Object[]{
                       hdht.getMaHDHT(),hdht.getHoaDon().getMaHD(),
                        sdf.format(   hdht.getNgayHoanTra()),
                        hdht.getSoLuong(),
                        df.format(hdht.tongTienHoan()),hdht.getHoaDon().getKhachHang().getTenKH(),
                        hdht.getHoaDon().getNhanVien().getTenNV()
                    });
                }
            }
        }
    }//GEN-LAST:event_btnTimDonHoanActionPerformed

    private void btnQuy1HoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy1HoanActionPerformed
         int year = jspNamHoan.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            String dauQuy1 = String.valueOf(year).concat("-01-01");
            String cuoiQuy1 = String.valueOf(year).concat("-03-31");
            TimKiemDonHoanTheoQuy(dauQuy1, cuoiQuy1);
        }
    }//GEN-LAST:event_btnQuy1HoanActionPerformed

    private void btnQuy2HoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy2HoanActionPerformed
        int year = jspNamHoan.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            String dauQuy2 = String.valueOf(year).concat("-04-01");
             String cuoiQuy2 = String.valueOf(year).concat("-06-30");
            TimKiemDonHoanTheoQuy(dauQuy2, cuoiQuy2);
        }
    }//GEN-LAST:event_btnQuy2HoanActionPerformed

    private void btnQuy3HoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy3HoanActionPerformed
       int year = jspNamHoan.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        String dauQuy3 = String.valueOf(year).concat("-07-01");
        String cuoiQuy3 = String.valueOf(year).concat("-09-30");
         if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            TimKiemDonHoanTheoQuy(dauQuy3, cuoiQuy3);
        }
    }//GEN-LAST:event_btnQuy3HoanActionPerformed

    private void btnQuy4HoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy4HoanActionPerformed
        int year = jspNamHoan.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        String dauQuy4 = String.valueOf(year).concat("-10-01");
        String cuoiQuy4 = String.valueOf(year).concat("-12-31");
         if (year > namHiemTai) {
            JOptionPane.showMessageDialog(null, "N??m ???????c ch???n kh??ng l???n h??n n??m hi???n t???i", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } else {
            TimKiemDonHoanTheoQuy(dauQuy4, cuoiQuy4);
        }
    }//GEN-LAST:event_btnQuy4HoanActionPerformed
    
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

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnChiTiet;
    private swing.Button btnChiTietDonHoan;
    private swing.Button btnQuy1;
    private swing.Button btnQuy1Hoan;
    private swing.Button btnQuy2;
    private swing.Button btnQuy2Hoan;
    private swing.Button btnQuy3;
    private swing.Button btnQuy3Hoan;
    private swing.Button btnQuy4;
    private swing.Button btnQuy4Hoan;
    private swing.Button btnTim;
    private swing.Button btnTimDonHoan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcNgayMuonTim;
    private com.toedter.calendar.JDateChooser jdcNgayMuonTimHoan;
    private com.toedter.calendar.JDateChooser jdcNgayTim;
    private com.toedter.calendar.JDateChooser jdcNgayTimHoan;
    private com.toedter.calendar.JYearChooser jspNam;
    private com.toedter.calendar.JYearChooser jspNamHoan;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JPanel pnlDau;
    private javax.swing.JPanel pnlDau2;
    private javax.swing.JPanel pnlLichSuDonHang;
    private javax.swing.JPanel pnlLichSuDonHoan;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlThongTinCuaHang;
    private javax.swing.JPanel pnlThongTinCuaHang2;
    private javax.swing.JTable tblHoaDonBanHang;
    private javax.swing.JTable tblHoaDonHoan;
    private swing.TextFieldAnimation txtTim;
    private swing.TextFieldAnimation txtTimDonHoan;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {

    }
}
