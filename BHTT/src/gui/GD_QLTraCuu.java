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
        modelDSHoaDon = (DefaultTableModel) tblHoaDonBanHang.getModel();
        modelDSDonHoan = (DefaultTableModel)  tblHoaDonHoan.getModel();
        today = new Date();
        jdcNgayTim.setDate(new Date());
        jdcNgayMuonTim.setDate(new Date());
        nhanvien_dao=new DAO_NhanVien();
        
        DocDSHoaDon();
        DocDanhSachDonHoan();
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
    
    private void XoaHetDuLieuTrenTable(){
        DefaultTableModel dm  = (DefaultTableModel) tblHoaDonBanHang.getModel();
        dm.getDataVector().removeAllElements();
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
        txtTim1 = new swing.TextFieldAnimation();
        btnChiTiet1 = new swing.Button();
        jdcNgayTim1 = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jdcNgayMuonTim1 = new com.toedter.calendar.JDateChooser();
        jspNam1 = new com.toedter.calendar.JYearChooser();
        jLabel21 = new javax.swing.JLabel();
        btnTim1 = new swing.Button();
        btnQuy5 = new swing.Button();
        btnQuy6 = new swing.Button();
        btnQuy7 = new swing.Button();
        btnQuy8 = new swing.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonHoan = new javax.swing.JTable();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnlMain.setBackground(new java.awt.Color(204, 204, 255));
        pnlMain.setForeground(new java.awt.Color(204, 204, 255));
        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));

        pnlLichSuDonHang.setLayout(new javax.swing.BoxLayout(pnlLichSuDonHang, javax.swing.BoxLayout.Y_AXIS));

        pnlDau.setMaximumSize(new java.awt.Dimension(98301, 100));
        pnlDau.setPreferredSize(new java.awt.Dimension(1197, 100));
        pnlDau.setLayout(new javax.swing.BoxLayout(pnlDau, javax.swing.BoxLayout.X_AXIS));

        pnlThongTinCuaHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cửa hàng thời trang BHTT");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setRequestFocusEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Địa chỉ : ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên nhân viên :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, thành phố Hồ Chí Minh  ");

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenNV.setText("Nguyễn Văn A");

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

        txtTim.setHintText("Nhập mã hóa đơn.");
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        btnChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnChiTiet.setText("Xem chi tiết đơn hàng");
        btnChiTiet.setColor1(new java.awt.Color(204, 153, 0));
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Từ ngày:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Đến ngày:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Năm");

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTim.setColor1(new java.awt.Color(255, 153, 153));
        btnTim.setColor2(new java.awt.Color(51, 51, 255));
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnQuy1.setText("Quý 1");
        btnQuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy1ActionPerformed(evt);
            }
        });

        btnQuy2.setText("Quý 2");
        btnQuy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy2ActionPerformed(evt);
            }
        });

        btnQuy3.setText("Quý 3");
        btnQuy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy3ActionPerformed(evt);
            }
        });

        btnQuy4.setText("Quý 4");
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
                "Mã hóa đơn", "Ngày lập", "Tổng số lượng sản phầm", "Tổng thành tiền", "Tên khách hàng", "Tên nhân viên"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDonBanHang);
        if (tblHoaDonBanHang.getColumnModel().getColumnCount() > 0) {
            tblHoaDonBanHang.getColumnModel().getColumn(0).setResizable(false);
        }

        pnlLichSuDonHang.add(jScrollPane1);

        jTabbedPane1.addTab("Lịch sử đơn hàng", pnlLichSuDonHang);

        pnlLichSuDonHoan.setLayout(new javax.swing.BoxLayout(pnlLichSuDonHoan, javax.swing.BoxLayout.Y_AXIS));

        pnlDau2.setMaximumSize(new java.awt.Dimension(98301, 100));
        pnlDau2.setPreferredSize(new java.awt.Dimension(1197, 100));
        pnlDau2.setLayout(new javax.swing.BoxLayout(pnlDau2, javax.swing.BoxLayout.X_AXIS));

        pnlThongTinCuaHang2.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cửa hàng thời trang BHTT");
        jLabel9.setFocusable(false);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setRequestFocusEnabled(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Địa chỉ : ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Tên nhân viên :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, thành phố Hồ Chí Minh  ");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Nguyễn Văn A");

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

        txtTim1.setHintText("Nhập mã đơn hoàn");
        txtTim1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTim1KeyReleased(evt);
            }
        });

        btnChiTiet1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnChiTiet1.setText("Xem chi tiết đơn hàng");
        btnChiTiet1.setColor1(new java.awt.Color(204, 153, 0));
        btnChiTiet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTiet1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Từ ngày:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Đến ngày:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Năm");

        btnTim1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTim1.setColor1(new java.awt.Color(255, 153, 153));
        btnTim1.setColor2(new java.awt.Color(51, 51, 255));
        btnTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim1ActionPerformed(evt);
            }
        });

        btnQuy5.setText("Quý 1");
        btnQuy5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy5ActionPerformed(evt);
            }
        });

        btnQuy6.setText("Quý 2");
        btnQuy6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy6ActionPerformed(evt);
            }
        });

        btnQuy7.setText("Quý 3");
        btnQuy7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy7ActionPerformed(evt);
            }
        });

        btnQuy8.setText("Quý 4");
        btnQuy8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspNam1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuy5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnQuy6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnQuy7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuy8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcNgayTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcNgayMuonTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnChiTiet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(btnTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1386, 1386, 1386))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(btnTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgayTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel20)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jspNam1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnQuy6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuy7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuy8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnChiTiet1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnQuy5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jdcNgayMuonTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                "Mã đơn hoàn", "Ngày hoàn đơn", "Tổng sản phẩm", "Tổng tiền hoàn", "Tên khách hàng", "Tên nhân viên"
            }
        ));
        jScrollPane3.setViewportView(tblHoaDonHoan);
        if (tblHoaDonHoan.getColumnModel().getColumnCount() > 0) {
            tblHoaDonHoan.getColumnModel().getColumn(0).setResizable(false);
        }

        pnlLichSuDonHoan.add(jScrollPane3);

        jTabbedPane1.addTab("Lịch sử đơn hoàn", pnlLichSuDonHoan);

        pnlMain.add(jTabbedPane1);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        int hangChon = tblHoaDonBanHang.getSelectedRow();
        if (hangChon == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xem chi tiết");
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
            JOptionPane.showMessageDialog(null, "Thời gian tìm không lớn hơn thời gian hiện tại");
        } else {
            if (ngayTim.after(ngayCanTim)) {
                JOptionPane.showMessageDialog(null, "Ngày tìm không lớn hơn ngày cần tìm");
            }else{
                ArrayList<ChiTietHoaDon> dsHoaDonTheoNgay = cthd_dao.layDanhSachHoaDonTheoNgay(dayTim, dayCanTim);
                XoaHetDuLieuTrenTable();
                for (ChiTietHoaDon cthd : dsHoaDonTheoNgay) {
                    modelDSHoaDon.addRow(new Object[]{
                        cthd.getHoaDon().getMaHD(), sdf.format(cthd.getHoaDon().getNgayLap()),
                        cthd.getSoLuong(), df.format(cthd.getTongTien()), cthd.getHoaDon().getKhachHang().getTenKH()
                    });
                }
            }
        }
        System.out.println(ngayCanTim);
        System.out.println(ngayTim);
    }//GEN-LAST:event_btnTimActionPerformed

    private void TimKiemTheoQuy(String dauQuy,String cuoiQuy){
        ArrayList<ChiTietHoaDon> dsHoaDonTheoQuy = cthd_dao.layDSHoaDonTheoQuy(dauQuy, cuoiQuy);
        XoaHetDuLieuTrenTable();
        for (ChiTietHoaDon cthd : dsHoaDonTheoQuy) {
            modelDSHoaDon.addRow(new Object[]{
                cthd.getHoaDon().getMaHD(), sdf.format(cthd.getHoaDon().getNgayLap()),
                cthd.getSoLuong(), df.format(cthd.getTongTien()), cthd.getHoaDon().getKhachHang().getTenKH()
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
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            TimKiemTheoQuy(dauQuy4, cuoiQuy4);
        }
    }//GEN-LAST:event_btnQuy4ActionPerformed

    private void txtTim1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTim1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTim1KeyReleased

    private void btnChiTiet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTiet1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTiet1ActionPerformed

    private void btnTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTim1ActionPerformed

    private void btnQuy5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuy5ActionPerformed

    private void btnQuy6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuy6ActionPerformed

    private void btnQuy7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuy7ActionPerformed

    private void btnQuy8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuy8ActionPerformed
    
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
    private swing.Button btnChiTiet1;
    private swing.Button btnQuy1;
    private swing.Button btnQuy2;
    private swing.Button btnQuy3;
    private swing.Button btnQuy4;
    private swing.Button btnQuy5;
    private swing.Button btnQuy6;
    private swing.Button btnQuy7;
    private swing.Button btnQuy8;
    private swing.Button btnTim;
    private swing.Button btnTim1;
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
    private com.toedter.calendar.JDateChooser jdcNgayMuonTim1;
    private com.toedter.calendar.JDateChooser jdcNgayTim;
    private com.toedter.calendar.JDateChooser jdcNgayTim1;
    private com.toedter.calendar.JYearChooser jspNam;
    private com.toedter.calendar.JYearChooser jspNam1;
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
    private swing.TextFieldAnimation txtTim1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {

    }
}
