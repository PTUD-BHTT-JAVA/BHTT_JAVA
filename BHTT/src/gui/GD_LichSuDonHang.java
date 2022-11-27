package gui;

import connectDB.ConnectDB;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;

import entity.ChiTietHoaDon;
import entity.ChiTietHoanTra;
import entity.HoaDon;

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

import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class GD_LichSuDonHang extends javax.swing.JInternalFrame implements Runnable {

    private String username;
    List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
    private TableRowSorter<DefaultTableModel> tr;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat fday = new SimpleDateFormat("yyyy-MM-dd");
    public DefaultTableModel modelDonHang;
    DecimalFormat df = new DecimalFormat("#,##0 VND");
    private Thread thread = new Thread(this);
    private DAO_ChiTietHoaDon cthd_dao;
    public TaiKhoan tkDN;
    private DAO_HoaDon hd_dao;
    private ArrayList<SanPham> dsSPTrongDonHang;
    private final DefaultTableModel modelDSHoaDon;
    private final Date today;

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
        username = _username;
        cthd_dao = new DAO_ChiTietHoaDon();
        modelDSHoaDon = (DefaultTableModel) tblHoaDonBanHang.getModel();
        today = new Date();
        jdcNgayTim.setDate(new Date());
        jdcNgayMuonTim.setDate(new Date());
        DocDSHoaDon();
    }

    private void DocDSHoaDon() {
        List<ChiTietHoaDon> dsHD = cthd_dao.layDSHoaDonLenBang();
        for (ChiTietHoaDon cthd : dsHD) {
            modelDSHoaDon.addRow(new Object[]{
                cthd.getHoaDon().getMaHD(), sdf.format(cthd.getHoaDon().getNgayLap()),
                cthd.getSoLuong(), df.format(cthd.getTongTien()), cthd.getHoaDon().getKhachHang().getTenKH()
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
        jLabel1 = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        pnlDau1 = new javax.swing.JPanel();
        pnlThongTinCuaHang1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTenNV1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        textFieldAnimation2 = new swing.TextFieldAnimation();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbSanPham1 = new javax.swing.JTable();

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nguyễn Văn A");

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlThongTinCuaHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
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
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDau.add(pnlThongTinCuaHang);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
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
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGap(0, 5, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jdcNgayTim, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlLichSuDonHang.add(jPanel4);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 300));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(16, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        tblHoaDonBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày lập", "Tổng số lượng sản phầm", "Tổng thành tiền", "Tên khách hàng"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDonBanHang);
        if (tblHoaDonBanHang.getColumnModel().getColumnCount() > 0) {
            tblHoaDonBanHang.getColumnModel().getColumn(0).setResizable(false);
        }

        pnlLichSuDonHang.add(jScrollPane1);

        jTabbedPane1.addTab("Lịch sử đơn hàng", pnlLichSuDonHang);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        pnlDau1.setMaximumSize(new java.awt.Dimension(98301, 100));
        pnlDau1.setPreferredSize(new java.awt.Dimension(1197, 100));
        pnlDau1.setLayout(new javax.swing.BoxLayout(pnlDau1, javax.swing.BoxLayout.X_AXIS));

        pnlThongTinCuaHang1.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cửa hàng thời trang BHTT");
        jLabel4.setFocusable(false);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setRequestFocusEnabled(false);

        jLabel5.setText("Địa chỉ : ");

        jLabel13.setText("Tên nhân viên :");

        txtTenNV1.setText("Nguyễn Văn A");

        jLabel14.setText("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp,  ");

        jLabel15.setText("thành phố Hồ Chí Minh");

        javax.swing.GroupLayout pnlThongTinCuaHang1Layout = new javax.swing.GroupLayout(pnlThongTinCuaHang1);
        pnlThongTinCuaHang1.setLayout(pnlThongTinCuaHang1Layout);
        pnlThongTinCuaHang1Layout.setHorizontalGroup(
            pnlThongTinCuaHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinCuaHang1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlThongTinCuaHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 542, Short.MAX_VALUE)
                .addGroup(pnlThongTinCuaHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongTinCuaHang1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1506, Short.MAX_VALUE)
                        .addGroup(pnlThongTinCuaHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(txtTenNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
        );
        pnlThongTinCuaHang1Layout.setVerticalGroup(
            pnlThongTinCuaHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinCuaHang1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinCuaHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinCuaHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDau1.add(pnlThongTinCuaHang1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlDau1.add(jPanel5);

        jPanel2.add(pnlDau1);

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(2522, 100));

        textFieldAnimation2.setText("textFieldAnimation1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(textFieldAnimation2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2245, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(textFieldAnimation2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(32767, 300));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(16, 100));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 200));

        jtbSanPham1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Giá", "Phân loại", "Màu sắc", "Chất liệu", "Kích thước"
            }
        ));
        jScrollPane2.setViewportView(jtbSanPham1);

        jPanel2.add(jScrollPane2);

        jTabbedPane1.addTab("Lịch sử đơn hoàn", jPanel2);

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
    private swing.Button btnQuy1;
    private swing.Button btnQuy2;
    private swing.Button btnQuy3;
    private swing.Button btnQuy4;
    private swing.Button btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcNgayMuonTim;
    private com.toedter.calendar.JDateChooser jdcNgayTim;
    private com.toedter.calendar.JYearChooser jspNam;
    private javax.swing.JTable jtbSanPham1;
    private javax.swing.JPanel pnlDau;
    private javax.swing.JPanel pnlDau1;
    private javax.swing.JPanel pnlLichSuDonHang;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlThongTinCuaHang;
    private javax.swing.JPanel pnlThongTinCuaHang1;
    private javax.swing.JTable tblHoaDonBanHang;
    private swing.TextFieldAnimation textFieldAnimation2;
    private javax.swing.JTextField txtTenNV1;
    private swing.TextFieldAnimation txtTim;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {

    }
}
