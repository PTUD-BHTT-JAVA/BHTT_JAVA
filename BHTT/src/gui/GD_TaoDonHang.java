
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
import entity.HoaDon;
import entity.KhachHang;
import entity.SanPham;
import entity.TaiKhoan;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class GD_TaoDonHang extends javax.swing.JInternalFrame implements Runnable {

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
    private final JInternalFrame gd_KH;

    public void dongPanel(Component c) {
        pnlTab.remove(c);
    }

    public GD_TaoDonHang(String _username, JInternalFrame frame) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        thread.start();
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
        }
        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        gd_KH=frame;
        this.setRootPaneCheckingEnabled(false);
        javax.swing.plaf.InternalFrameUI ui
                = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);
        initComponents();
        this.setFocusable(true);
        username = _username;
        hd_dao = new DAO_HoaDon();
        cthd_dao = new DAO_ChiTietHoaDon();
        kh = new DAO_KhachHang();
        nv = new DAO_NhanVien();
        lblTenNV.setText(nv.layNhanVienBangMa(username).getTenNV());

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
        jPanel16 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        txtSoDon = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txtGio = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        txtNgay = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        pnlThongTinCuaHang = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        pnlKhachHang2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtLoaiKH = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnTimKH = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnTaoDH = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        pnlTab = new javax.swing.JTabbedPane();
        jLabel4 = new javax.swing.JLabel();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        pnlMain.setBackground(new java.awt.Color(204, 204, 255));
        pnlMain.setForeground(new java.awt.Color(204, 204, 255));
        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));

        pnlDau.setMaximumSize(new java.awt.Dimension(3000, 250));
        pnlDau.setMinimumSize(new java.awt.Dimension(196, 50));
        pnlDau.setPreferredSize(new java.awt.Dimension(1197, 230));
        pnlDau.setLayout(new javax.swing.BoxLayout(pnlDau, javax.swing.BoxLayout.X_AXIS));

        pnlDongHo1.setBackground(new java.awt.Color(204, 204, 255));
        pnlDongHo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDongHo1.setPreferredSize(new java.awt.Dimension(227, 200));
        pnlDongHo1.setLayout(new javax.swing.BoxLayout(pnlDongHo1, javax.swing.BoxLayout.Y_AXIS));

        jPanel16.setBackground(new java.awt.Color(204, 204, 255));
        jPanel16.setMaximumSize(new java.awt.Dimension(600, 600));
        jPanel16.setPreferredSize(new java.awt.Dimension(117, 23));
        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.X_AXIS));

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(130, 55));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("????N H??NG :");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel16.add(jPanel14);

        jPanel22.setBackground(new java.awt.Color(204, 204, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.X_AXIS));

        txtSoDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtSoDon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoDon.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSoDon.setEnabled(false);
        txtSoDon.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel22.add(txtSoDon);

        jPanel16.add(jPanel22);

        pnlDongHo1.add(jPanel16);

        jPanel21.setBackground(new java.awt.Color(204, 204, 255));
        jPanel21.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanel21.setPreferredSize(new java.awt.Dimension(367, 5));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        pnlDongHo1.add(jPanel21);

        jPanel17.setBackground(new java.awt.Color(204, 204, 255));
        jPanel17.setMaximumSize(new java.awt.Dimension(600, 600));
        jPanel17.setPreferredSize(new java.awt.Dimension(223, 35));
        jPanel17.setLayout(new javax.swing.BoxLayout(jPanel17, javax.swing.BoxLayout.X_AXIS));

        txtGio.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        txtGio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGio.setEnabled(false);
        txtGio.setMaximumSize(new java.awt.Dimension(600, 600));
        txtGio.setMinimumSize(new java.awt.Dimension(10, 10));
        txtGio.setPreferredSize(new java.awt.Dimension(170, 47));
        txtGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioActionPerformed(evt);
            }
        });
        jPanel17.add(txtGio);

        pnlDongHo1.add(jPanel17);

        jPanel23.setBackground(new java.awt.Color(204, 204, 255));
        jPanel23.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanel23.setPreferredSize(new java.awt.Dimension(367, 5));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        pnlDongHo1.add(jPanel23);

        jPanel18.setBackground(new java.awt.Color(204, 204, 255));
        jPanel18.setMaximumSize(new java.awt.Dimension(600, 600));
        jPanel18.setMinimumSize(new java.awt.Dimension(64, 10));
        jPanel18.setPreferredSize(new java.awt.Dimension(223, 24));
        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.X_AXIS));

        jPanel19.setBackground(new java.awt.Color(204, 204, 255));
        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.Y_AXIS));

        jPanel26.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel26);

        txtNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNgay.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNgay.setEnabled(false);
        jPanel19.add(txtNgay);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel25);

        jPanel18.add(jPanel19);

        pnlDongHo1.add(jPanel18);

        pnlDau.add(pnlDongHo1);

        pnlThongTinCuaHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinCuaHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlThongTinCuaHang.setMaximumSize(new java.awt.Dimension(1000, 300));
        pnlThongTinCuaHang.setPreferredSize(new java.awt.Dimension(600, 150));
        pnlThongTinCuaHang.setLayout(new javax.swing.BoxLayout(pnlThongTinCuaHang, javax.swing.BoxLayout.Y_AXIS));

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));
        jPanel15.setMaximumSize(new java.awt.Dimension(2000, 60));
        jPanel15.setMinimumSize(new java.awt.Dimension(50, 10));
        jPanel15.setPreferredSize(new java.awt.Dimension(963, 150));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.Y_AXIS));

        jPanel30.setBackground(new java.awt.Color(204, 204, 255));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("C???a h??ng th???i trang BHTT");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(183, 40));
        jLabel2.setRequestFocusEnabled(false);
        jPanel30.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel30);

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setLayout(new java.awt.GridLayout(3, 2));

        jLabel3.setBackground(new java.awt.Color(204, 204, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("?????a ch??? : ");
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 16));
        jPanel6.add(jLabel3);

        jLabel11.setBackground(new java.awt.Color(204, 204, 255));
        jLabel11.setText("12 Nguy???n V??n B???o, ph?????ng 4, qu???n G?? V???p,  ");
        jPanel6.add(jLabel11);

        jPanel29.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel29);

        jLabel12.setText("th??nh ph??? H??? Ch?? Minh                      ");
        jLabel12.setPreferredSize(new java.awt.Dimension(130, 16));
        jPanel6.add(jLabel12);

        jLabel10.setBackground(new java.awt.Color(204, 204, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("T??n nh??n vi??n :");
        jPanel6.add(jLabel10);

        lblTenNV.setBackground(new java.awt.Color(204, 204, 255));
        lblTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenNV.setText("jLabel4");
        jPanel6.add(lblTenNV);

        jPanel15.add(jPanel6);

        pnlThongTinCuaHang.add(jPanel15);

        pnlDau.add(pnlThongTinCuaHang);

        pnlKhachHang2.setBackground(new java.awt.Color(204, 204, 255));
        pnlKhachHang2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Th??ng tin kh??ch h??ng"));
        pnlKhachHang2.setMaximumSize(new java.awt.Dimension(900, 900));
        pnlKhachHang2.setPreferredSize(new java.awt.Dimension(450, 210));
        pnlKhachHang2.setLayout(new javax.swing.BoxLayout(pnlKhachHang2, javax.swing.BoxLayout.X_AXIS));

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));
        jPanel13.setMaximumSize(new java.awt.Dimension(30, 600));
        jPanel13.setMinimumSize(new java.awt.Dimension(2, 20));
        jPanel13.setPreferredSize(new java.awt.Dimension(10, 76));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        pnlKhachHang2.add(jPanel13);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel13.setText("S??? ??i???n tho???i :");
        jLabel13.setMaximumSize(new java.awt.Dimension(500, 600));
        jLabel13.setMinimumSize(new java.awt.Dimension(10, 10));
        jLabel13.setPreferredSize(new java.awt.Dimension(180, 15));
        jPanel1.add(jLabel13);

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSDT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSDT.setMaximumSize(new java.awt.Dimension(700, 200));
        txtSDT.setMinimumSize(new java.awt.Dimension(20, 20));
        txtSDT.setPreferredSize(new java.awt.Dimension(250, 17));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        jPanel1.add(txtSDT);

        jPanel5.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setText("T??n kh??ch h??ng :");
        jLabel14.setMaximumSize(new java.awt.Dimension(500, 600));
        jLabel14.setMinimumSize(new java.awt.Dimension(10, 10));
        jLabel14.setPreferredSize(new java.awt.Dimension(180, 15));
        jPanel2.add(jLabel14);

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTenKH.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenKH.setEnabled(false);
        txtTenKH.setMaximumSize(new java.awt.Dimension(700, 200));
        txtTenKH.setMinimumSize(new java.awt.Dimension(20, 20));
        txtTenKH.setPreferredSize(new java.awt.Dimension(250, 17));
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });
        jPanel2.add(txtTenKH);

        jPanel5.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.X_AXIS));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel15.setText("Lo???i kh??ch h??ng:");
        jLabel15.setMaximumSize(new java.awt.Dimension(500, 600));
        jLabel15.setMinimumSize(new java.awt.Dimension(10, 10));
        jLabel15.setPreferredSize(new java.awt.Dimension(180, 15));
        jPanel3.add(jLabel15);

        txtLoaiKH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtLoaiKH.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLoaiKH.setEnabled(false);
        txtLoaiKH.setMaximumSize(new java.awt.Dimension(700, 200));
        txtLoaiKH.setMinimumSize(new java.awt.Dimension(20, 10));
        txtLoaiKH.setPreferredSize(new java.awt.Dimension(250, 17));
        txtLoaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoaiKHActionPerformed(evt);
            }
        });
        jPanel3.add(txtLoaiKH);

        jPanel5.add(jPanel3);

        pnlKhachHang2.add(jPanel5);

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setMaximumSize(new java.awt.Dimension(30, 600));
        jPanel11.setMinimumSize(new java.awt.Dimension(2, 20));
        jPanel11.setPreferredSize(new java.awt.Dimension(10, 76));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        pnlKhachHang2.add(jPanel11);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanel4.setMinimumSize(new java.awt.Dimension(30, 125));
        jPanel4.setPreferredSize(new java.awt.Dimension(80, 186));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setLayout(new java.awt.BorderLayout());

        btnTimKH.setBackground(new java.awt.Color(99, 203, 116));
        btnTimKH.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnTimKH.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTimKH.setMaximumSize(new java.awt.Dimension(600, 600));
        btnTimKH.setMinimumSize(new java.awt.Dimension(10, 10));
        btnTimKH.setPreferredSize(new java.awt.Dimension(120, 17));
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });
        jPanel8.add(btnTimKH, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8);

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(66, 1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel10);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        btnThemKH.setBackground(new java.awt.Color(99, 203, 116));
        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        btnThemKH.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKH.setText("+");
        btnThemKH.setMaximumSize(new java.awt.Dimension(600, 600));
        btnThemKH.setMinimumSize(new java.awt.Dimension(10, 10));
        btnThemKH.setPreferredSize(new java.awt.Dimension(120, 17));
        btnThemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemKHMousePressed(evt);
            }
        });
        jPanel9.add(btnThemKH, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9);

        pnlKhachHang2.add(jPanel4);

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setMaximumSize(new java.awt.Dimension(30, 600));
        jPanel12.setMinimumSize(new java.awt.Dimension(2, 20));
        jPanel12.setPreferredSize(new java.awt.Dimension(10, 76));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        pnlKhachHang2.add(jPanel12);

        pnlDau.add(pnlKhachHang2);

        pnlMain.add(pnlDau);

        jPanel20.setBackground(new java.awt.Color(204, 204, 255));
        jPanel20.setLayout(new javax.swing.BoxLayout(jPanel20, javax.swing.BoxLayout.Y_AXIS));

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));

        btnTaoDH.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTaoDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/donhangmoi.png"))); // NOI18N
        btnTaoDH.setText("????n h??ng m???i");
        btnTaoDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDHActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/huydonhang.png"))); // NOI18N
        btnHuy.setText("????ng ????n h??ng");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1742, Short.MAX_VALUE)
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
            .addGap(0, 35, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnTaoDH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel20.add(jPanel7);

        pnlTab.setBackground(new java.awt.Color(204, 204, 255));
        pnlTab.setMaximumSize(new java.awt.Dimension(2000, 2000));
        pnlTab.setPreferredSize(new java.awt.Dimension(1300, 820));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/AnhGT.png"))); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(1300, 800));
        pnlTab.addTab("Xin ch??o!", jLabel4);

        jPanel20.add(pnlTab);
        pnlTab.getAccessibleContext().setAccessibleName("");

        pnlMain.add(jPanel20);

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
        kh_dao = new DAO_KhachHang();
        String s = txtSDT.getText();
        KhachHang kh = new KhachHang();
        kh = kh_dao.layKhachHangBangSDT(s);
        if (kh != null) {
            txtTenKH.setText(kh.getTenKH());
            txtLoaiKH.setText(kh.getLoaiKhachHang().getTenLoai());
        } else {
            JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y kh??ch h??ng");
            txtTenKH.setText("");
        }
        
        
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void btnTaoDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDHActionPerformed
        if (txtTenKH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "H??y ch???n kh??ch h??ng ????? t???o h??a ????n!");
        } else {

            Panel_ChiTietHoaDon pnl = new Panel_ChiTietHoaDon(txtSDT.getText(), lblTenNV.getText());
            pnlTab.add(txtTenKH.getText()+"-"+kh.layKhachHangBangSDT(txtSDT.getText()).getSoDienThoai(), pnl);
            kh_dao = new DAO_KhachHang();
            String s = txtSDT.getText();
            KhachHang khTim = new KhachHang();
            khTim = kh_dao.layKhachHangBangSDT(s);
            if ("VIP".equals(khTim.getLoaiKhachHang().getTenLoai())) {
                pnl.lblKhuyenMai.setText(10 +  "%");
                System.out.println(khTim.getLoaiKhachHang().getTenLoai());
            } else {
                pnl.lblKhuyenMai.setText( 0 +  "%");
                 System.out.println(khTim.getLoaiKhachHang().getTenLoai());
            }
            pnlTab.setSelectedComponent(pnl);
            txtSDT.setText("");
            txtTenKH.setText("");
            txtLoaiKH.setText("");
            txtSoDon.setText(maTuSinh());
           
        }
    }//GEN-LAST:event_btnTaoDHActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (pnlTab.getSelectedComponent() == null) {
            JOptionPane.showMessageDialog(this, "Kh??ng t???n t???i ????n h??ng n??o ????? h???y!");
        } else if (JOptionPane.showConfirmDialog(this, "B???n ch???c ch???n mu???n ????ng ????n h??ng n??y?", "X??c nh???n", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            pnlTab.remove(pnlTab.getSelectedComponent());

            txtSoDon.setText(maTuSinh());
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemKHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKHMousePressed

        openComponent(gd_KH);
        
        
    }//GEN-LAST:event_btnThemKHMousePressed
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblTenNV;
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
    public javax.swing.JTextField txtSoDon;
    private javax.swing.JTextField txtTenKH;
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
                thoiGianHienTai = new Date(); // l???y th???i gian hi???n t???i
                String ngayTrongTuan = "";
                if (thoiGianHienTai.getDay() == 0) {
                    ngayTrongTuan = "Ch??? nh???t, ";
                } else {
                    ngayTrongTuan = "Th??? " + (thoiGianHienTai.getDay() + 1) + ", ";// do getDay() t??nh t??? 1.
                }
                txtGio.setText(sdf_Gio.format(thoiGianHienTai));
                txtNgay.setText(ngayTrongTuan + sdf_Ngay.format(thoiGianHienTai));
                thread.sleep(1000); // cho ph??p ng??? trong kho???ng 1000 mns =1s
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
