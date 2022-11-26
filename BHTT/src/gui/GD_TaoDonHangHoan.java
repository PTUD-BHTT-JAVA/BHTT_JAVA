package gui;

import connectDB.ConnectDB;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoanTra;
import dao.DAO_HoaDon;
import dao.DAO_HoaDonHoan;
import dao.DAO_KhachHang;

import dao.DAO_NhanVien;
import dao.DAO_SanPham;
import entity.ChiTietHoaDon;
import entity.ChiTietHoanTra;
import entity.HoaDon;
import entity.HoaDonHoanTra;
import entity.KhachHang;
import entity.SanPham;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class GD_TaoDonHangHoan extends javax.swing.JInternalFrame implements Runnable {

    private String username;
    private DefaultTableModel modelHoaDon;
    private DefaultTableModel modelCanHoan;
    private DefaultTableModel modelDonHoan;
    private DAO_ChiTietHoaDon cthd_dao;
    private DAO_ChiTietHoanTra ctht_dao = new DAO_ChiTietHoanTra();
    private DAO_SanPham sp_dao = new DAO_SanPham();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final DAO_HoaDon hd_dao;
    private final DAO_NhanVien nv_dao;
    private final DAO_KhachHang kh_dao = new DAO_KhachHang();
    private dao.DAO_HoaDonHoan hdh_dao = new DAO_HoaDonHoan();
    private double tongThanhTien;
    DecimalFormat df = new DecimalFormat("#,##0 VND");
    private Thread thread = new Thread(this);

    public GD_TaoDonHangHoan(String _username) {
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
        modelHoaDon = (DefaultTableModel) tblDSHD.getModel();
        modelCanHoan = (DefaultTableModel) tblCanHoan.getModel();
        modelDonHoan = (DefaultTableModel) tblDonHoan.getModel();
        hd_dao = new DAO_HoaDon();
        nv_dao = new DAO_NhanVien();
        cthd_dao = new DAO_ChiTietHoaDon();
        DocDSHoaDon();
        Date date = new Date();
        moKhoaControls(false);
        txtMaDHHT.setText(maTuSinh());

        lblTenNV.setText(nv_dao.layNhanVienBangMa(username).getTenNV());
    }

    private void moKhoaControls(boolean b) {
        btnHuy.setEnabled(b);
        btnHoan.setEnabled(b);
        spnSoLuong.setEnabled(b);
        btnSua.setEnabled(b);
        btnTaoHDHT.setEnabled(b);
        btnXoaSPDH.setEnabled(b);
    }

    private void DocDSHoaDon() {

        List<ChiTietHoaDon> dsHD = cthd_dao.layDSHoaDonLenBang();
        for (ChiTietHoaDon cthd : dsHD) {
            modelHoaDon.addRow(new Object[]{
                cthd.getHoaDon().getMaHD(), sdf.format(cthd.getHoaDon().getNgayLap()),
                cthd.getSoLuong(), cthd.getTongTien(), cthd.getHoaDon().getKhachHang().getTenKH()
            });
        }
    }

    private void setTongThanhTien() {
        tongThanhTien = 0;
        for (int i = 0; i < modelCanHoan.getRowCount(); i++) {
            tongThanhTien += Double.parseDouble(modelCanHoan.getValueAt(i, 3).toString());
        }
        txtTTDonHang.setText(df.format(tongThanhTien));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlDau = new javax.swing.JPanel();
        pnlDongHo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaDHHT = new javax.swing.JTextField();
        txtGio = new javax.swing.JTextField();
        txtNgay = new javax.swing.JTextField();
        pnlThongTinCuaHang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        pnlTimXem = new javax.swing.JPanel();
        btnChiTiet = new javax.swing.JButton();
        txtTim = new swing.TextFieldAnimation();
        btnThem = new javax.swing.JButton();
        pnlDanhSachHoaDon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSHD = new javax.swing.JTable();
        pnlHoan = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tblDonHang = new javax.swing.JScrollPane();
        tblCanHoan = new javax.swing.JTable();
        pnlThanhTienDonHang = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTTDonHang = new javax.swing.JTextField();
        pnlNut = new javax.swing.JPanel();
        btnHoan = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        btnXoaSPDH = new javax.swing.JButton();
        pnlDonHoan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        t = new javax.swing.JScrollPane();
        tblDonHoan = new javax.swing.JTable();
        pnlThanhTienDonHoan = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTTDonHoan = new javax.swing.JTextField();
        pnlNutTao = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnTaoHDHT = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));

        pnlDau.setBackground(new java.awt.Color(204, 204, 255));
        pnlDau.setPreferredSize(new java.awt.Dimension(1197, 100));
        pnlDau.setLayout(new javax.swing.BoxLayout(pnlDau, javax.swing.BoxLayout.X_AXIS));

        pnlDongHo.setBackground(new java.awt.Color(204, 204, 255));
        pnlDongHo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDongHo.setPreferredSize(new java.awt.Dimension(227, 200));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("ĐƠN HOÀN:");

        txtMaDHHT.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtMaDHHT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaDHHT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaDHHT.setEnabled(false);

        txtGio.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
        txtGio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGio.setEnabled(false);
        txtGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioActionPerformed(evt);
            }
        });

        txtNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNgay.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNgay.setEnabled(false);

        javax.swing.GroupLayout pnlDongHoLayout = new javax.swing.GroupLayout(pnlDongHo);
        pnlDongHo.setLayout(pnlDongHoLayout);
        pnlDongHoLayout.setHorizontalGroup(
            pnlDongHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDongHoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDongHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGio)
                    .addComponent(txtNgay)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDongHoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                        .addComponent(txtMaDHHT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDongHoLayout.setVerticalGroup(
            pnlDongHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDongHoLayout.createSequentialGroup()
                .addGroup(pnlDongHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaDHHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlDau.add(pnlDongHo);

        pnlThongTinCuaHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlThongTinCuaHang.setLayout(new javax.swing.BoxLayout(pnlThongTinCuaHang, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cửa hàng thời trang BHTT");
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setRequestFocusEnabled(false);
        pnlThongTinCuaHang.add(jLabel1);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Địa chỉ : ");
        jLabel2.setPreferredSize(new java.awt.Dimension(300, 0));
        jPanel5.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp,  thành phố Hồ Chí Minh");
        jPanel5.add(jLabel3);

        pnlThongTinCuaHang.add(jPanel5);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(400, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(50, 30));
        jPanel4.setPreferredSize(new java.awt.Dimension(120, 78));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Tên nhân viên :");
        jLabel4.setPreferredSize(new java.awt.Dimension(300, 0));

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlThongTinCuaHang.add(jPanel4);

        pnlDau.add(pnlThongTinCuaHang);

        pnlMain.add(pnlDau);

        pnlTimXem.setBackground(new java.awt.Color(204, 204, 255));

        btnChiTiet.setBackground(new java.awt.Color(0, 153, 51));
        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTiet.setText("Xem chi tiết đơn hàng");
        btnChiTiet.setFocusPainted(false);
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        txtTim.setHintText("Tìm theo mã hóa đơn / tên khách hàng");
        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 153, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnThem.setText("Hoàn đơn");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimXemLayout = new javax.swing.GroupLayout(pnlTimXem);
        pnlTimXem.setLayout(pnlTimXemLayout);
        pnlTimXemLayout.setHorizontalGroup(
            pnlTimXemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimXemLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        pnlTimXemLayout.setVerticalGroup(
            pnlTimXemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimXemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimXemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlTimXemLayout.createSequentialGroup()
                        .addGroup(pnlTimXemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addComponent(txtTim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.add(pnlTimXem);

        pnlDanhSachHoaDon.setBackground(new java.awt.Color(204, 204, 255));
        pnlDanhSachHoaDon.setMinimumSize(new java.awt.Dimension(100, 16));
        pnlDanhSachHoaDon.setLayout(new javax.swing.BoxLayout(pnlDanhSachHoaDon, javax.swing.BoxLayout.X_AXIS));

        tblDSHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày lập", "Tổng số lượng sản phẩm", "Tổng thành tiền", "Tên khách hàng"
            }
        ));
        tblDSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSHD);

        pnlDanhSachHoaDon.add(jScrollPane1);

        pnlMain.add(pnlDanhSachHoaDon);

        pnlHoan.setBackground(new java.awt.Color(204, 204, 255));
        pnlHoan.setMaximumSize(new java.awt.Dimension(98301, 517));
        pnlHoan.setMinimumSize(new java.awt.Dimension(355, 300));
        pnlHoan.setPreferredSize(new java.awt.Dimension(1210, 480));
        pnlHoan.setLayout(new javax.swing.BoxLayout(pnlHoan, javax.swing.BoxLayout.X_AXIS));

        pnlDonHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(505, 363));
        pnlDonHang.setLayout(new javax.swing.BoxLayout(pnlDonHang, javax.swing.BoxLayout.Y_AXIS));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DANH SÁCH SẢN PHẨM CÓ THỂ HOÀN TRẢ");
        jLabel11.setAlignmentX(0.5F);
        jLabel11.setDisplayedMnemonicIndex(0);
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setIconTextGap(0);
        jLabel11.setMaximumSize(new java.awt.Dimension(600, 20));
        jLabel11.setMinimumSize(new java.awt.Dimension(300, 20));
        jLabel11.setPreferredSize(new java.awt.Dimension(600, 20));
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnlDonHang.add(jLabel11);

        tblCanHoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên sản phẩm", "Số lượng", "Thành tiền"
            }
        ));
        tblDonHang.setViewportView(tblCanHoan);

        pnlDonHang.add(tblDonHang);

        pnlThanhTienDonHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlThanhTienDonHang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("TỔNG CỘNG:");

        txtTTDonHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTTDonHang.setForeground(new java.awt.Color(255, 0, 0));
        txtTTDonHang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTTDonHang.setText("0");
        txtTTDonHang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 204, 102)));
        txtTTDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTTDonHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThanhTienDonHangLayout = new javax.swing.GroupLayout(pnlThanhTienDonHang);
        pnlThanhTienDonHang.setLayout(pnlThanhTienDonHangLayout);
        pnlThanhTienDonHangLayout.setHorizontalGroup(
            pnlThanhTienDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhTienDonHangLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTTDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
        );
        pnlThanhTienDonHangLayout.setVerticalGroup(
            pnlThanhTienDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhTienDonHangLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnlThanhTienDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTTDonHang)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        pnlDonHang.add(pnlThanhTienDonHang);

        pnlHoan.add(pnlDonHang);

        pnlNut.setBackground(new java.awt.Color(204, 204, 255));
        pnlNut.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlNut.setPreferredSize(new java.awt.Dimension(200, 300));

        btnHoan.setBackground(new java.awt.Color(94, 153, 94));
        btnHoan.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnHoan.setForeground(new java.awt.Color(255, 255, 255));
        btnHoan.setText("Hoàn");
        btnHoan.setFocusPainted(false);
        btnHoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(94, 153, 94));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa số lượng hoàn");
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(224, 83, 83));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.setFocusPainted(false);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        spnSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Số lượng:");

        btnXoaSPDH.setBackground(new java.awt.Color(94, 153, 94));
        btnXoaSPDH.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnXoaSPDH.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSPDH.setText("Xóa");
        btnXoaSPDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPDHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNutLayout = new javax.swing.GroupLayout(pnlNut);
        pnlNut.setLayout(pnlNutLayout);
        pnlNutLayout.setHorizontalGroup(
            pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNutLayout.createSequentialGroup()
                .addGroup(pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNutLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlNutLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlNutLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlNutLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnHoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlNutLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnXoaSPDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlNutLayout.setVerticalGroup(
            pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNutLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(btnHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSPDH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        pnlHoan.add(pnlNut);

        pnlDonHoan.setBackground(new java.awt.Color(204, 204, 255));
        pnlDonHoan.setLayout(new javax.swing.BoxLayout(pnlDonHoan, javax.swing.BoxLayout.Y_AXIS));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ĐƠN HOÀN");
        jLabel7.setAlignmentX(0.5F);
        jLabel7.setMaximumSize(new java.awt.Dimension(360, 20));
        jLabel7.setMinimumSize(new java.awt.Dimension(250, 20));
        pnlDonHoan.add(jLabel7);

        t.setForeground(new java.awt.Color(255, 255, 255));

        tblDonHoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên sản phẩm", "Số lượng", "Thành tiền"
            }
        ));
        t.setViewportView(tblDonHoan);

        pnlDonHoan.add(t);

        pnlThanhTienDonHoan.setBackground(new java.awt.Color(204, 204, 255));
        pnlThanhTienDonHoan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("TỔNG CỘNG:");

        txtTTDonHoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTTDonHoan.setForeground(new java.awt.Color(255, 0, 0));
        txtTTDonHoan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTTDonHoan.setText("0");
        txtTTDonHoan.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 204, 102)));
        txtTTDonHoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTTDonHoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThanhTienDonHoanLayout = new javax.swing.GroupLayout(pnlThanhTienDonHoan);
        pnlThanhTienDonHoan.setLayout(pnlThanhTienDonHoanLayout);
        pnlThanhTienDonHoanLayout.setHorizontalGroup(
            pnlThanhTienDonHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhTienDonHoanLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTTDonHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        pnlThanhTienDonHoanLayout.setVerticalGroup(
            pnlThanhTienDonHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhTienDonHoanLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnlThanhTienDonHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTTDonHoan)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        pnlDonHoan.add(pnlThanhTienDonHoan);

        pnlHoan.add(pnlDonHoan);

        pnlMain.add(pnlHoan);

        pnlNutTao.setBackground(new java.awt.Color(204, 204, 255));
        pnlNutTao.setLayout(new java.awt.GridLayout(1, 3));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        pnlNutTao.add(jPanel2);

        btnTaoHDHT.setBackground(new java.awt.Color(255, 153, 0));
        btnTaoHDHT.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnTaoHDHT.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHDHT.setText("Tạo hóa đơn hoàn trả");
        btnTaoHDHT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(251, 255, 235), 4, true));
        btnTaoHDHT.setFocusPainted(false);
        btnTaoHDHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDHTActionPerformed(evt);
            }
        });
        pnlNutTao.add(btnTaoHDHT);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        pnlNutTao.add(jPanel1);

        pnlMain.add(pnlNutTao);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void filter(String s) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelHoaDon);
        tblDSHD.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + s));

    }
    private void btnHoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanActionPerformed
        int r = tblCanHoan.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần hoàn");
            return;
        }
        String ma;
        String ten;
        int sl;
        double tt;
        double tongTienHoan = 0;
        int soLuongHoan = (int) spnSoLuong.getValue();
        SanPham sp;
        if (soLuongHoan <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng hoàn lớn hơn 0");
        } else {
            ma = modelCanHoan.getValueAt(r, 0).toString();
            ten = modelCanHoan.getValueAt(r, 1).toString();
            sl = (int) modelCanHoan.getValueAt(r, 2);
            tt = (double) modelCanHoan.getValueAt(r, 3);
            if (soLuongHoan > sl) {
                JOptionPane.showMessageDialog(null, "Số lượng hoàn không được lớn hơn số lượng bán");
                return;
            }
            sp = sp_dao.laySanPhamBangMa(ma);
            boolean kiemTra = false;
            for (int i = 0; i < modelDonHoan.getRowCount(); i++) {
                if (modelDonHoan.getValueAt(i, 0).equals(ma)) {
                    int slMoi = soLuongHoan + (int) modelDonHoan.getValueAt(i, 2);
                    if (slMoi > sl) {
                        JOptionPane.showMessageDialog(null, "Số lượng hoàn không được lớn hơn số lượng bán");
                        kiemTra = true;
                    } else {
                        modelDonHoan.setValueAt(slMoi, i, 2);
                        modelDonHoan.setValueAt(slMoi * sp.getGiaGoc(), i, 3);
                        tinhTongCong();
                        kiemTra = true;
                    }
                }
            }
            if (!kiemTra) {
                modelDonHoan.addRow(new Object[]{ma, ten, soLuongHoan, sp.getGiaGoc() * soLuongHoan});
                tinhTongCong();
                // tongTienHoan += (double)modelDonHoan.getValueAt(this, 3);
            }
        }
    }//GEN-LAST:event_btnHoanActionPerformed
    public void tinhTongCong() {
        double sum = 0;
        for (int i = 0; i < modelDonHoan.getRowCount(); i++) {
            sum = sum + (double) modelDonHoan.getValueAt(i, 3);
        }
        txtTTDonHoan.setText(String.format("%,.1f", sum) + " VND");
    }

    private String maTuSinh() {
        String ma = "HDHT";
        int tachMa;
        int i = 0, j = 1;
        int[] dem = new int[999];
        String id;

        for (HoaDonHoanTra hdht : hdh_dao.getallDSHoaDonHoan()) {
            id = hdht.getMaHDHT();

            tachMa = Integer.parseInt(id.substring(4, 7));
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
    private void btnTaoHDHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDHTActionPerformed
        int row = tblDSHD.getSelectedRow();
        if (tblDonHoan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Đơn hàng trống");
            return;
        }
        btnThem.setEnabled(true);
        String maHDHT = maTuSinh();
        String maHD = modelHoaDon.getValueAt(row, 0).toString();
        Date ngayHT = new Date();
        //lưu vào sql hóa đơn hoàn trả
        HoaDonHoanTra hDHT = new HoaDonHoanTra();
        HoaDon hd = hd_dao.layHoaDonTheoMa(maHD);
        KhachHang kh = kh_dao.getKHBangMa(hd.getKhachHang().getMaKH());
        double tienHoanTra = 0;
        double tienHoanHT = 0;
        for (int i = 0; i < modelDonHoan.getRowCount(); i++) {
            tienHoanHT = (Double) modelDonHoan.getValueAt(i, 3);
        }
        System.out.println(tienHoanHT);
        System.out.println(kh.getLoaiKhachHang().getTenLoai().toString());
        if (kh.getLoaiKhachHang().getTenLoai().equals("VIP")) {
            tienHoanTra = tienHoanHT + tienHoanHT * 0.15;
            hDHT = new HoaDonHoanTra(maHDHT, ngayHT, tienHoanTra, hd_dao.layHoaDonTheoMa(maHD));
            System.out.println(tienHoanHT);
        } else {
            tienHoanTra = tienHoanHT - tienHoanHT * 0.05;
            hDHT = new HoaDonHoanTra(maHDHT, ngayHT, tienHoanTra, hd_dao.layHoaDonTheoMa(maHD));
        }

        hdh_dao.themHoaDonHon(hDHT);
        //lưu vào sql chi tiết hoàn trả
        for (int i = 0; i < modelDonHoan.getRowCount(); i++) {
            SanPham sp = sp_dao.laySanPhamBangMa(modelDonHoan.getValueAt(i, 0).toString());
            int soLuongHT = Integer.parseInt(modelDonHoan.getValueAt(i, 2).toString());
            ChiTietHoanTra ctHT = new ChiTietHoanTra(soLuongHT, sp, hDHT);
            //cập nhâp chi tiết sản phẩm
            ctht_dao.themCTHoanTra(ctHT);
            // cập nhập số lượng sản phẩm
            sp_dao.capNhatSoLuong(sp.getMaSP(), soLuongHT + sp.getSoLuong());
        }
        btnThem.setEnabled(true);
        //xóa 2 bảng con
        DefaultTableModel fm = (DefaultTableModel) tblCanHoan.getModel();
        fm.setRowCount(0);
        DefaultTableModel fmm = (DefaultTableModel) tblDonHoan.getModel();
        fmm.setRowCount(0);
        moKhoaControls(false);
        //đọc bảng  danh sách đơn hàng
        DefaultTableModel fmmn = (DefaultTableModel) tblDSHD.getModel();
        fmmn.setRowCount(0);
        DocDSHoaDon();
        txtTTDonHang.setText(df.format(0));
        txtTTDonHoan.setText(df.format(0));
        JOptionPane.showMessageDialog(null, "Hoàn đơn thành công");
        Form_HoaDonHoan fHDH = new Form_HoaDonHoan(maHDHT, maHD);
        fHDH.setLocationRelativeTo(null);
        fHDH.setVisible(true);

    }//GEN-LAST:event_btnTaoHDHTActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int hangChon;
        hangChon = tblDonHoan.getSelectedRow();
        int r = tblCanHoan.getSelectedRow();
        int soLuongCoSan = (int) modelCanHoan.getValueAt(r, 2);
        int slSua = (int) spnSoLuong.getValue();
        if (hangChon == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa số lượng");
            return;
        }
        if (slSua <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng sửa phải lớn hơn 0.");
            return;
        }
        if (slSua > soLuongCoSan) {
            JOptionPane.showMessageDialog(null, "Số lượng không được vượt quá số lượng bán.");
            return;
        }
        modelDonHoan.setValueAt(slSua, hangChon, 2);
        SanPham sp;
        sp = sp_dao.laySanPhamBangMa((String) modelDonHoan.getValueAt(hangChon, 0));
        modelDonHoan.setValueAt(sp.getGiaGoc() * slSua, hangChon, 3);
        tinhTongCong();
        System.out.println(hangChon);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTTDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTTDonHangActionPerformed

    }//GEN-LAST:event_txtTTDonHangActionPerformed

    private void txtTTDonHoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTTDonHoanActionPerformed

    }//GEN-LAST:event_txtTTDonHoanActionPerformed

    private void txtGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioActionPerformed

    }//GEN-LAST:event_txtGioActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//
//        int row = tblDSHD.getSelectedRow();
//        if (row < 0) {
//            JOptionPane.showMessageDialog(null, "Chọn đơn hàng cần hoàn", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
//        } else {
//            int sl;
//            int slDaHoan = 0;
//            ArrayList<ChiTietHoanTra> dsctht = new ArrayList<ChiTietHoanTra>();
//            ArrayList<HoaDonHoanTra> dsHDHT = new ArrayList<HoaDonHoanTra>();
//            dsHDHT = hdh_dao.layHoaDonHoanTheoMaHD(modelHoaDon.getValueAt(row, 0).toString());
//          
//            moKhoaControls(true);
//            List<ChiTietHoaDon> hdCanHoan = cthd_dao.layDSHDBangMa(modelHoaDon.getValueAt(row, 0).toString());
//            for (ChiTietHoaDon cthd : hdCanHoan) {
//                dsctht = ctht_dao.layCTHTBangMaSP(cthd.getSanPham().getMaSP());
//                for(ChiTietHoanTra ctht: dsctht){
//                    slDaHoan= slDaHoan+ctht.getSoLuong();
//                }
//                
//                sl = cthd.getSoLuong()-slDaHoan;
//                modelCanHoan.addRow(new Object[]{
//                    cthd.getSanPham().getMaSP(),
//                    cthd.getSanPham().getTenSP(),
//                    sl,sl*
//                    cthd.getSanPham().getGiaGoc()
//                });
//                slDaHoan=0;
//                sl=0;
//            }
//
//           
//
//                setTongThanhTien();
//                btnThem.setEnabled(false);
//            }
        int row = tblDSHD.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn đơn hàng cần hoàn", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            int sl;
            int slDaHoan = 0;
            ArrayList<ChiTietHoanTra> dsctht = new ArrayList<ChiTietHoanTra>();
            ArrayList<HoaDonHoanTra> dsHDHT = new ArrayList<HoaDonHoanTra>();
            dsHDHT = hdh_dao.layHoaDonHoanTheoMaHD(modelHoaDon.getValueAt(row, 0).toString());
            moKhoaControls(true);
            List<ChiTietHoaDon> hdCanHoan = cthd_dao.layDSHDBangMa(modelHoaDon.getValueAt(row, 0).toString());
            List<ChiTietHoaDon> ctHD = cthd_dao.layDSHDBangMa(modelHoaDon.getValueAt(row, 0).toString());
            for (ChiTietHoaDon cthd : hdCanHoan) {
                sl = cthd.getSoLuong();

                for (HoaDonHoanTra hdht : dsHDHT) {
                    // System.out.println(hdht);
                    dsctht = ctht_dao.layDSCTHTBangMa(hdht.getMaHDHT());
                    for (ChiTietHoanTra ctht : dsctht) {
                        //  System.out.println(ctht);
                        //nếu là đúng mã sản phẩm thì trừ
                        if (ctht.getSanPham().getMaSP().equals(cthd.getSanPham().getMaSP())) {
                            sl = sl - ctht.getSoLuong();
                        }
                    }
                }

                modelCanHoan.addRow(new Object[]{
                    cthd.getSanPham().getMaSP(),
                    cthd.getSanPham().getTenSP(),
                    sl, sl
                    * cthd.getSanPham().getGiaGoc()
                });
            }

            setTongThanhTien();
            btnThem.setEnabled(false);
    }//GEN-LAST:event_btnThemActionPerformed
    }
    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        int hangChon = tblDSHD.getSelectedRow();
        if (hangChon == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xem chi tiết");
            return;
        }
        String m = modelHoaDon.getValueAt(hangChon, 0).toString();
        DG_CTDH dgCTDH = new DG_CTDH(m);
        dgCTDH.setLocationRelativeTo(null);
        dgCTDH.setVisible(true);
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Xác nhận", "Bạn muốn hủy?", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            btnThem.setEnabled(true);
            DefaultTableModel fm = (DefaultTableModel) tblCanHoan.getModel();
            fm.setRowCount(0);
            DefaultTableModel fmm = (DefaultTableModel) tblDonHoan.getModel();
            fmm.setRowCount(0);
            moKhoaControls(false);
            txtTTDonHang.setText(df.format(0));
            txtTTDonHoan.setText(df.format(0));
        }

    }//GEN-LAST:event_btnHuyActionPerformed

    private void tblDSHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSHDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDSHDMouseClicked

    private void btnXoaSPDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPDHActionPerformed
        int hangChon = tblDonHoan.getSelectedRow();
        if (hangChon == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa");
            return;
        }
        modelDonHoan.removeRow(hangChon);
        tinhTongCong();
    }//GEN-LAST:event_btnXoaSPDHActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnHoan;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoHDHT;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoaSPDH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JPanel pnlDanhSachHoaDon;
    private javax.swing.JPanel pnlDau;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlDonHoan;
    private javax.swing.JPanel pnlDongHo;
    private javax.swing.JPanel pnlHoan;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNut;
    private javax.swing.JPanel pnlNutTao;
    private javax.swing.JPanel pnlThanhTienDonHang;
    private javax.swing.JPanel pnlThanhTienDonHoan;
    private javax.swing.JPanel pnlThongTinCuaHang;
    private javax.swing.JPanel pnlTimXem;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JScrollPane t;
    private javax.swing.JTable tblCanHoan;
    private javax.swing.JTable tblDSHD;
    private javax.swing.JScrollPane tblDonHang;
    private javax.swing.JTable tblDonHoan;
    private javax.swing.JTextField txtGio;
    private javax.swing.JTextField txtMaDHHT;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtTTDonHang;
    private javax.swing.JTextField txtTTDonHoan;
    private swing.TextFieldAnimation txtTim;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            txtGio = new JTextField();
            txtNgay = new JTextField();
            Date thoiGianHienTai = new Date();
            SimpleDateFormat sdf_Gio = new SimpleDateFormat("hh:mm:ss");
            SimpleDateFormat sdf_Ngay = new SimpleDateFormat("dd/MM/yyyy");
            while (true) {
                thoiGianHienTai = new Date(); // lấy thời gian hiện tại
                String ngayTrongTuan = "";
                if (thoiGianHienTai.getDay() == 0) {
                    ngayTrongTuan = "Chủ nhật, ";
                } else {
                    ngayTrongTuan = "Thứ " + (thoiGianHienTai.getDay() + 1) + ", ";// do getDay() tính từ 1.
                }
                txtGio.setText(sdf_Gio.format(thoiGianHienTai));
                txtNgay.setText(ngayTrongTuan + sdf_Ngay.format(thoiGianHienTai));
                thread.sleep(1000); // cho phép ngủ trong khoảng 1000 mns =1s
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
