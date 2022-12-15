/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connectDB.ConnectDB;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoanTra;
import dao.DAO_HoaDon;
import dao.DAO_HoaDonHoan;
import dao.DAO_KhachHang;
import dao.DAO_NhanVien;
import dao.DAO_SanPham;
import dao.DAO_SanPham.thongKeSPBanChay;
import entity.ChiTietHoaDon;
import entity.ChiTietHoanTra;
import entity.HoaDon;
import entity.HoaDonHoanTra;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ACER
 */
public class GD_QLThongKe extends javax.swing.JInternalFrame {

    private DAO_HoaDon hd_dao = new DAO_HoaDon();
    private DAO_NhanVien nv_dao = new DAO_NhanVien();
    private DAO_KhachHang kh_dao = new DAO_KhachHang();
    private dao.DAO_HoaDonHoan hdh_dao = new DAO_HoaDonHoan();
    private DAO_ChiTietHoaDon cthd_dao = new DAO_ChiTietHoaDon();
    private DAO_ChiTietHoanTra ctht_dao = new DAO_ChiTietHoanTra();
    private DAO_SanPham sp_dao = new DAO_SanPham();
    private DefaultTableModel modelTKTopSP, modelTKSPT, modelTKSPL, modelTopNV;
    private final Date today;
    private Object sdf;
    SimpleDateFormat fday = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("#,##0 VND");

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
    private String username;

    /**
     * Creates new form QuanLyHoaDon
     */
    public GD_QLThongKe(String _username) {
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
        modelTKTopSP = (DefaultTableModel) tblTKTopSP.getModel();
        modelTKSPT = (DefaultTableModel) tblTKSPT.getModel();
        modelTKSPL = (DefaultTableModel) tblTKSPL.getModel();
        modelTopNV = (DefaultTableModel) tblTKTopNV.getModel();

        //Thong ke san pham ton
        DefaultTableModel fm1 = (DefaultTableModel) tblTKSPT.getModel();
        fm1.setRowCount(0);
        ArrayList<SanPham> listSPTon = sp_dao.layTatCaSPTon();
        int stt = 1;
        for (SanPham spT : listSPTon) {

            modelTKSPT.addRow(new Object[]{stt++, spT.getMaSP(), spT.getTenSP(),
                spT.getSoLuong(),});
        }
        //Thong ke san pham loi
        DefaultTableModel fm2 = (DefaultTableModel) tblTKSPL.getModel();
        fm2.setRowCount(0);
        ArrayList<ChiTietHoanTra> listSPLoi = ctht_dao.layTatCaSPLoi();
        int sttL = 1;
        int tongSL;
        for (ChiTietHoanTra ctht : listSPLoi) {
            boolean kt = false;
            DAO_ChiTietHoanTra daoctht = new DAO_ChiTietHoanTra();
            for (int i = 0; i < modelTKSPL.getRowCount(); i++) {

                int soLuong = ctht.getSoLuong();
                if (ctht.getSanPham().getMaSP().equals(modelTKSPL.getValueAt(i, 1))) {
                    modelTKSPL.setValueAt(++soLuong, i, 3);
                    kt = true;
                }
            }
            if (!kt) {
                modelTKSPL.addRow(new Object[]{sttL++, ctht.getSanPham().getMaSP(), ctht.getSanPham().getTenSP(),
                    ctht.getSoLuong(), ctht.getSanPham().getNhaCungCap().getTenNCC()});
            }
        }
        today = new Date();
        tuNgay.setDate(new Date());
        denNgay.setDate(new Date());
        jSpTopSP.setValue(5);
//        tu = sdf.format(tuNgay.getDate());
//        den = sdf.format(denNgay.getDate());
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
        pnlTop = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        pnlTieuChi = new javax.swing.JPanel();
        lblTuNgay = new javax.swing.JLabel();
        lblDenNgay = new javax.swing.JLabel();
        lblNam = new javax.swing.JLabel();
        tuNgay = new com.toedter.calendar.JDateChooser();
        denNgay = new com.toedter.calendar.JDateChooser();
        jycNam = new com.toedter.calendar.JYearChooser();
        btnThongKe = new swing.Button();
        btnQuy4 = new swing.Button();
        btnQuy3 = new swing.Button();
        btnQuy2 = new swing.Button();
        btnQuy1 = new swing.Button();
        btnCaNam = new swing.Button();
        jButton1 = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        tbpThongKe = new javax.swing.JTabbedPane();
        pnlDoanhThu = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        pnlTongHDDB1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTongHDDB1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblTongHD = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        pnlTongTienBan1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblTongTienBan1 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        lblTongTienBanD = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        pnlTongHDTra1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTongHDTra1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblSoHDH = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        pnlTongTienHoan1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblTongTienHoan1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        tongTienHoann = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblKetToan1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblKetToann = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        pnlTopSP = new javax.swing.JPanel();
        pnlBieuDoTKTopSP = new javax.swing.JPanel();
        scrTKTopSP = new javax.swing.JScrollPane();
        tblTKTopSP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSpTopSP = new javax.swing.JSpinner();
        pnlTopNV = new javax.swing.JPanel();
        pnlTKTopNV = new javax.swing.JPanel();
        scrTKNVTop = new javax.swing.JScrollPane();
        tblTKTopNV = new javax.swing.JTable();
        pnlSPTon = new javax.swing.JPanel();
        lblTieuDeTKSPT = new javax.swing.JLabel();
        scrTKSPT = new javax.swing.JScrollPane();
        tblTKSPT = new javax.swing.JTable();
        pnlSPLoi = new javax.swing.JPanel();
        lblTieuDeTKSPT1 = new javax.swing.JLabel();
        scrTKSPT1 = new javax.swing.JScrollPane();
        tblTKSPL = new javax.swing.JTable();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);

        pnlMain.setBackground(new java.awt.Color(204, 204, 255));

        pnlTop.setBackground(new java.awt.Color(204, 204, 255));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblTieuDe.setText("THỐNG KÊ");

        pnlTieuChi.setBackground(new java.awt.Color(204, 204, 255));
        pnlTieuChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTuNgay.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblTuNgay.setText("Từ ngày:");

        lblDenNgay.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblDenNgay.setText("Đến ngày: ");

        lblNam.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblNam.setText("Năm: ");

        btnThongKe.setText("Thống kê");
        btnThongKe.setColor1(new java.awt.Color(51, 0, 255));
        btnThongKe.setColor2(new java.awt.Color(204, 0, 204));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnQuy4.setText("Quý 4");
        btnQuy4.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy4.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy4ActionPerformed(evt);
            }
        });

        btnQuy3.setText("Quý 3");
        btnQuy3.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy3.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy3ActionPerformed(evt);
            }
        });

        btnQuy2.setText("Quý 2");
        btnQuy2.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy2.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy2ActionPerformed(evt);
            }
        });

        btnQuy1.setText("Quý 1");
        btnQuy1.setColor1(new java.awt.Color(51, 0, 255));
        btnQuy1.setColor2(new java.awt.Color(204, 0, 204));
        btnQuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuy1ActionPerformed(evt);
            }
        });

        btnCaNam.setText("Cả năm");
        btnCaNam.setColor1(new java.awt.Color(51, 0, 255));
        btnCaNam.setColor2(new java.awt.Color(204, 0, 204));
        btnCaNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTieuChiLayout = new javax.swing.GroupLayout(pnlTieuChi);
        pnlTieuChi.setLayout(pnlTieuChiLayout);
        pnlTieuChiLayout.setHorizontalGroup(
            pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuChiLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTieuChiLayout.createSequentialGroup()
                        .addComponent(tuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTieuChiLayout.createSequentialGroup()
                        .addComponent(jycNam, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnCaNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTieuChiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnQuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuy2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(denNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTieuChiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnQuy3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuy4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTieuChiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        pnlTieuChiLayout.setVerticalGroup(
            pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTieuChiLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblDenNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(lblTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(denNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jycNam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnQuy4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnQuy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnQuy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnQuy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCaNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                        .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(309, 309, 309)
                        .addComponent(jButton1)
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                        .addComponent(pnlTieuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTieuChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlBottom.setBackground(new java.awt.Color(204, 204, 255));

        tbpThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbpThongKe.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        pnlDoanhThu.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlDoanhThuComponentShown(evt);
            }
        });
        pnlDoanhThu.setLayout(new javax.swing.BoxLayout(pnlDoanhThu, javax.swing.BoxLayout.X_AXIS));

        jPanel11.setMaximumSize(new java.awt.Dimension(4000, 4000));
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.Y_AXIS));

        jPanel17.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel17.setMinimumSize(new java.awt.Dimension(10, 10));
        jPanel17.setPreferredSize(new java.awt.Dimension(823, 50));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel17);

        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.X_AXIS));

        jPanel20.setMaximumSize(new java.awt.Dimension(2000, 2000));
        jPanel20.setMinimumSize(new java.awt.Dimension(50, 50));
        jPanel20.setPreferredSize(new java.awt.Dimension(50, 100));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        jPanel18.add(jPanel20);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.Y_AXIS));

        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.X_AXIS));

        pnlTongHDDB1.setBackground(new java.awt.Color(0, 204, 0));
        pnlTongHDDB1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        pnlTongHDDB1.setMinimumSize(new java.awt.Dimension(100, 100));
        pnlTongHDDB1.setPreferredSize(new java.awt.Dimension(400, 300));
        pnlTongHDDB1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel4.setBackground(new java.awt.Color(0, 204, 0));
        jPanel4.setLayout(new java.awt.BorderLayout());

        lblTongHDDB1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHDDB1.setForeground(new java.awt.Color(204, 255, 255));
        lblTongHDDB1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongHDDB1.setText("Tổng số hóa đơn đã bán");
        jPanel4.add(lblTongHDDB1, java.awt.BorderLayout.CENTER);

        pnlTongHDDB1.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 204, 0));
        jPanel5.setLayout(new java.awt.BorderLayout());

        lblTongHD.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHD.setForeground(new java.awt.Color(255, 255, 255));
        lblTongHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(lblTongHD, java.awt.BorderLayout.CENTER);

        pnlTongHDDB1.add(jPanel5);

        jPanel22.add(pnlTongHDDB1);

        jPanel23.setMaximumSize(new java.awt.Dimension(2000, 2000));
        jPanel23.setMinimumSize(new java.awt.Dimension(20, 20));
        jPanel23.setPreferredSize(new java.awt.Dimension(50, 30));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        jPanel22.add(jPanel23);

        pnlTongTienBan1.setBackground(new java.awt.Color(204, 204, 0));
        pnlTongTienBan1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        pnlTongTienBan1.setMinimumSize(new java.awt.Dimension(100, 100));
        pnlTongTienBan1.setPreferredSize(new java.awt.Dimension(400, 300));
        pnlTongTienBan1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel10.setBackground(new java.awt.Color(204, 204, 0));
        jPanel10.setLayout(new java.awt.BorderLayout());

        lblTongTienBan1.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTienBan1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienBan1.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTienBan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongTienBan1.setText("Tổng tiền bán được");
        jPanel10.add(lblTongTienBan1, java.awt.BorderLayout.CENTER);

        pnlTongTienBan1.add(jPanel10);

        jPanel29.setBackground(new java.awt.Color(204, 204, 0));
        jPanel29.setLayout(new java.awt.BorderLayout());

        lblTongTienBanD.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienBanD.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTienBanD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel29.add(lblTongTienBanD, java.awt.BorderLayout.CENTER);

        pnlTongTienBan1.add(jPanel29);

        jPanel22.add(pnlTongTienBan1);

        jPanel15.add(jPanel22);

        jPanel24.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel24.setPreferredSize(new java.awt.Dimension(30, 50));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel15.add(jPanel24);

        jPanel21.setLayout(new javax.swing.BoxLayout(jPanel21, javax.swing.BoxLayout.X_AXIS));

        pnlTongHDTra1.setBackground(new java.awt.Color(102, 102, 255));
        pnlTongHDTra1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        pnlTongHDTra1.setMinimumSize(new java.awt.Dimension(100, 100));
        pnlTongHDTra1.setPreferredSize(new java.awt.Dimension(400, 300));
        pnlTongHDTra1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));
        jPanel6.setLayout(new java.awt.BorderLayout());

        lblTongHDTra1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHDTra1.setForeground(new java.awt.Color(255, 255, 255));
        lblTongHDTra1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongHDTra1.setText("Tổng số hóa đơn hoàn trả");
        jPanel6.add(lblTongHDTra1, java.awt.BorderLayout.CENTER);

        pnlTongHDTra1.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        lblSoHDH.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSoHDH.setForeground(new java.awt.Color(255, 255, 255));
        lblSoHDH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(lblSoHDH, java.awt.BorderLayout.CENTER);

        pnlTongHDTra1.add(jPanel7);

        jPanel21.add(pnlTongHDTra1);

        jPanel25.setMaximumSize(new java.awt.Dimension(2000, 2000));
        jPanel25.setMinimumSize(new java.awt.Dimension(20, 20));
        jPanel25.setPreferredSize(new java.awt.Dimension(50, 30));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        jPanel21.add(jPanel25);

        pnlTongTienHoan1.setBackground(new java.awt.Color(255, 0, 153));
        pnlTongTienHoan1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        pnlTongTienHoan1.setMinimumSize(new java.awt.Dimension(100, 100));
        pnlTongTienHoan1.setPreferredSize(new java.awt.Dimension(400, 300));
        pnlTongTienHoan1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel8.setBackground(new java.awt.Color(255, 0, 153));
        jPanel8.setLayout(new java.awt.BorderLayout());

        lblTongTienHoan1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienHoan1.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTienHoan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongTienHoan1.setText("Tổng tiền hoàn lại");
        jPanel8.add(lblTongTienHoan1, java.awt.BorderLayout.CENTER);

        pnlTongTienHoan1.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 0, 153));
        jPanel9.setLayout(new java.awt.BorderLayout());

        tongTienHoann.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tongTienHoann.setForeground(new java.awt.Color(255, 255, 255));
        tongTienHoann.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(tongTienHoann, java.awt.BorderLayout.CENTER);

        pnlTongTienHoan1.add(jPanel9);

        jPanel21.add(pnlTongTienHoan1);

        jPanel15.add(jPanel21);

        jPanel18.add(jPanel15);

        jPanel19.setMaximumSize(new java.awt.Dimension(2000, 2000));
        jPanel19.setMinimumSize(new java.awt.Dimension(50, 50));
        jPanel19.setPreferredSize(new java.awt.Dimension(50, 100));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        jPanel18.add(jPanel19);

        jPanel11.add(jPanel18);

        jPanel16.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel16.setMinimumSize(new java.awt.Dimension(10, 10));
        jPanel16.setPreferredSize(new java.awt.Dimension(823, 50));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel16);

        pnlDoanhThu.add(jPanel11);

        jPanel12.setLayout(new java.awt.GridLayout(3, 1));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel13);

        jPanel26.setLayout(new javax.swing.BoxLayout(jPanel26, javax.swing.BoxLayout.X_AXIS));

        jPanel27.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel27.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel27.setPreferredSize(new java.awt.Dimension(20, 10));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel26.add(jPanel27);

        jPanel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel2.setLayout(new java.awt.GridLayout(2, 1));

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));
        jPanel1.setLayout(new java.awt.BorderLayout());

        lblKetToan1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblKetToan1.setForeground(new java.awt.Color(255, 255, 255));
        lblKetToan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKetToan1.setText("Kết toán");
        lblKetToan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblKetToan1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel1);

        jPanel3.setBackground(new java.awt.Color(153, 0, 51));
        jPanel3.setLayout(new java.awt.BorderLayout());

        lblKetToann.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblKetToann.setForeground(new java.awt.Color(255, 255, 255));
        lblKetToann.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lblKetToann, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel26.add(jPanel2);

        jPanel28.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel28.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel28.setPreferredSize(new java.awt.Dimension(70, 30));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel26.add(jPanel28);

        jPanel12.add(jPanel26);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel14);

        pnlDoanhThu.add(jPanel12);

        tbpThongKe.addTab("Doanh Thu", pnlDoanhThu);

        pnlTopSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pnlTopSP.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlTopSPComponentShown(evt);
            }
        });

        pnlBieuDoTKTopSP.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlBieuDoTKTopSPLayout = new javax.swing.GroupLayout(pnlBieuDoTKTopSP);
        pnlBieuDoTKTopSP.setLayout(pnlBieuDoTKTopSPLayout);
        pnlBieuDoTKTopSPLayout.setHorizontalGroup(
            pnlBieuDoTKTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        pnlBieuDoTKTopSPLayout.setVerticalGroup(
            pnlBieuDoTKTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblTKTopSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Top", "Mã", "Tên sản phẩm ", "Số lượng bán", "Số lượng tồn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrTKTopSP.setViewportView(tblTKTopSP);
        if (tblTKTopSP.getColumnModel().getColumnCount() > 0) {
            tblTKTopSP.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblTKTopSP.getColumnModel().getColumn(1).setPreferredWidth(23);
            tblTKTopSP.getColumnModel().getColumn(2).setPreferredWidth(45);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Top:");

        javax.swing.GroupLayout pnlTopSPLayout = new javax.swing.GroupLayout(pnlTopSP);
        pnlTopSP.setLayout(pnlTopSPLayout);
        pnlTopSPLayout.setHorizontalGroup(
            pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopSPLayout.createSequentialGroup()
                .addGroup(pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlBieuDoTKTopSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(pnlTopSPLayout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpTopSP, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(scrTKTopSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTopSPLayout.setVerticalGroup(
            pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopSPLayout.createSequentialGroup()
                .addGroup(pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlTopSPLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(scrTKTopSP, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTopSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpTopSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBieuDoTKTopSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tbpThongKe.addTab("Top sản phẩm bán chạy", pnlTopSP);

        pnlTopNV.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlTopNVComponentShown(evt);
            }
        });

        pnlTKTopNV.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTKTopNVLayout = new javax.swing.GroupLayout(pnlTKTopNV);
        pnlTKTopNV.setLayout(pnlTKTopNVLayout);
        pnlTKTopNVLayout.setHorizontalGroup(
            pnlTKTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
        );
        pnlTKTopNVLayout.setVerticalGroup(
            pnlTKTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        tblTKTopNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Top", "Mã", "Tên Nhân Viên", "Doanh Thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrTKNVTop.setViewportView(tblTKTopNV);

        javax.swing.GroupLayout pnlTopNVLayout = new javax.swing.GroupLayout(pnlTopNV);
        pnlTopNV.setLayout(pnlTopNVLayout);
        pnlTopNVLayout.setHorizontalGroup(
            pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTKTopNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrTKNVTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTopNVLayout.setVerticalGroup(
            pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopNVLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addGroup(pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrTKNVTop, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTKTopNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        tbpThongKe.addTab("Top nhân viên xuất sắc", pnlTopNV);

        pnlSPTon.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlSPTonComponentShown(evt);
            }
        });

        lblTieuDeTKSPT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTieuDeTKSPT.setText("Danh sách sản phẩm không có lượt mua đến hiện tại");

        tblTKSPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblTKSPT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên Sản Phẩm", "Số lượng tồn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrTKSPT.setViewportView(tblTKSPT);
        if (tblTKSPT.getColumnModel().getColumnCount() > 0) {
            tblTKSPT.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        javax.swing.GroupLayout pnlSPTonLayout = new javax.swing.GroupLayout(pnlSPTon);
        pnlSPTon.setLayout(pnlSPTonLayout);
        pnlSPTonLayout.setHorizontalGroup(
            pnlSPTonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPTonLayout.createSequentialGroup()
                .addGroup(pnlSPTonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSPTonLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(scrTKSPT, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSPTonLayout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(lblTieuDeTKSPT)))
                .addContainerGap(316, Short.MAX_VALUE))
        );
        pnlSPTonLayout.setVerticalGroup(
            pnlSPTonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPTonLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTieuDeTKSPT)
                .addGap(38, 38, 38)
                .addComponent(scrTKSPT, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tbpThongKe.addTab("Sản phẩm tồn", pnlSPTon);

        pnlSPLoi.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlSPLoiComponentShown(evt);
            }
        });

        lblTieuDeTKSPT1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTieuDeTKSPT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDeTKSPT1.setText("Danh sách sản phẩm trả hàng vì lỗi nhà sản xuất");
        lblTieuDeTKSPT1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tblTKSPL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblTKSPL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên Sản Phẩm", "Số lượng", "Nhà cung cấp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrTKSPT1.setViewportView(tblTKSPL);
        if (tblTKSPL.getColumnModel().getColumnCount() > 0) {
            tblTKSPL.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        javax.swing.GroupLayout pnlSPLoiLayout = new javax.swing.GroupLayout(pnlSPLoi);
        pnlSPLoi.setLayout(pnlSPLoiLayout);
        pnlSPLoiLayout.setHorizontalGroup(
            pnlSPLoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPLoiLayout.createSequentialGroup()
                .addGroup(pnlSPLoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSPLoiLayout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(lblTieuDeTKSPT1))
                    .addGroup(pnlSPLoiLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(scrTKSPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        pnlSPLoiLayout.setVerticalGroup(
            pnlSPLoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPLoiLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblTieuDeTKSPT1)
                .addGap(18, 18, 18)
                .addComponent(scrTKSPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tbpThongKe.addTab("Sản phẩm lỗi", pnlSPLoi);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(tbpThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 1224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addComponent(tbpThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlDoanhThuComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlDoanhThuComponentShown
        tuNgay.setEnabled(true);
        denNgay.setEnabled(true);
        jycNam.setEnabled(true);
        btnThongKe.setEnabled(true);
        btnQuy1.setEnabled(true);
        btnQuy2.setEnabled(true);
        btnQuy3.setEnabled(true);
        btnQuy4.setEnabled(true);
    }//GEN-LAST:event_pnlDoanhThuComponentShown

    private void pnlSPTonComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlSPTonComponentShown
        tuNgay.setEnabled(false);
        denNgay.setEnabled(false);
        jycNam.setEnabled(false);
        btnThongKe.setEnabled(false);
        btnQuy1.setEnabled(false);
        btnQuy2.setEnabled(false);
        btnQuy3.setEnabled(false);
        btnQuy4.setEnabled(false);
    }//GEN-LAST:event_pnlSPTonComponentShown

    private void pnlTopSPComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlTopSPComponentShown
        tuNgay.setEnabled(true);
        denNgay.setEnabled(true);
        jycNam.setEnabled(true);
        btnThongKe.setEnabled(true);
        btnQuy1.setEnabled(true);
        btnQuy2.setEnabled(true);
        btnQuy3.setEnabled(true);
        btnQuy4.setEnabled(true);
    }//GEN-LAST:event_pnlTopSPComponentShown

    private void pnlTopNVComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlTopNVComponentShown
        tuNgay.setEnabled(true);
        denNgay.setEnabled(true);
        jycNam.setEnabled(true);
        btnThongKe.setEnabled(true);
        btnQuy1.setEnabled(true);
        btnQuy2.setEnabled(true);
        btnQuy3.setEnabled(true);
        btnQuy4.setEnabled(true);
    }//GEN-LAST:event_pnlTopNVComponentShown
    public void ThongKeSPBanChayTheoNam(int a) {
        int c = (int) jSpTopSP.getValue();
        if (c <= 0) {
            JOptionPane.showMessageDialog(null, "Top phải lớn hơn 0.");
            return;
        }
        ArrayList<thongKeSPBanChay> dsTKSPBC = sp_dao.thongKeSPBanChayTheoNam(c, a);
        SanPham sp;
        if (dsTKSPBC != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (thongKeSPBanChay tkspbc : dsTKSPBC) {
                sp = sp_dao.laySanPhamBangMa(tkspbc.getMaSP());
                dataset.addValue(tkspbc.getSlBan(), "Chiếc", "" + sp.getTenSP());

            }
            JFreeChart chart = ChartFactory.createBarChart("Thống Kê Sản Phẩm Bán Chạy", "Tên sản phẩm", "Số ", dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(pnlBieuDoTKTopSP.getWidth(), 200));
            pnlBieuDoTKTopSP.removeAll();
            pnlBieuDoTKTopSP.setLayout(new CardLayout());
            pnlBieuDoTKTopSP.add(chartPanel);
            pnlBieuDoTKTopSP.validate();
            pnlBieuDoTKTopSP.repaint();
        }
    }
    private void pnlSPLoiComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlSPLoiComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlSPLoiComponentShown

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed

        Date ngayTim = tuNgay.getDate();
        Date ngayCanTim = denNgay.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tu = sdf.format(tuNgay.getDate());
        String den = sdf.format(denNgay.getDate());
        if (ngayTim.after(today)) {
            JOptionPane.showMessageDialog(null, "Từ ngày không lớn hơn ngày hiện tại");
        } else {
            if (ngayTim.after(ngayCanTim)) {
                JOptionPane.showMessageDialog(null, "Từ ngày không lớn hơn đến ngày");
            } else {

                DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
                fm.setRowCount(0);
                //Thống kê doanh thu

                ArrayList<HoaDon> dsHD = hd_dao.thongKeDoanhThu(tu, den);
                ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
                lblTongHD.setText(dsHD.size() + "");
                long tongThanhTien = 0;
                double tongThanhTienVIP = 0;
                double tongThanhTienThuong = 0;

                for (HoaDon hd : dsHD) {
                    dsCTHD = cthd_dao.layDSHDBangMa(hd.getMaHD());
                    for (ChiTietHoaDon cthd : dsCTHD) {
                        if (hd.getKhachHang().getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                            tongThanhTienVIP = tongThanhTienVIP + cthd.getTongTien();
                        } else {
                            tongThanhTienThuong = tongThanhTienVIP + cthd.getTongTien();
                        }

                    }

                }
                tongThanhTienVIP = tongThanhTienVIP - tongThanhTienVIP * 0.05;
                tongThanhTienThuong = tongThanhTienThuong + tongThanhTienThuong * 0.05;
                tongThanhTien = (long) tongThanhTienVIP + (long) tongThanhTienThuong;
                lblTongTienBanD.setText(df.format(tongThanhTien));

                //Thống kê sản phẩm
                ArrayList<thongKeSPBanChay> dsSPBC = sp_dao.thongKeSPBanChay(tu, den);
                int i = 1;
                SanPham sp;
                for (thongKeSPBanChay spbc : dsSPBC) {
                    sp = sp_dao.laySanPhamBangMa(spbc.getMaSP());
                    modelTKTopSP.addRow(new Object[]{i++, spbc.getMaSP(), sp.getTenSP(),
                        spbc.getSlBan(), sp.getSoLuong(),});
                }
                //Thống kê hoàn
                ArrayList<HoaDonHoanTra> dsHDHT = hdh_dao.thongKeDoanhThu(tu, den);
                ArrayList<ChiTietHoanTra> dsCTHT;
                lblSoHDH.setText(dsHDHT.size() + "");
                long tongThanhTienHoan = 0;
                double tongThanhTienHoanVIP = 0;
                double tongThanhTienHoanThuong = 0;
                KhachHang kh;

                for (HoaDonHoanTra hdht : dsHDHT) {
                    dsCTHT = ctht_dao.layDSCTHTBangMa(hdht.getMaHDHT());
                    for (ChiTietHoanTra ctht : dsCTHT) {
                        kh = kh_dao.getKHBangMa(hdht.getHoaDon().getKhachHang().getMaKH());
                        if (kh.getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                            tongThanhTienHoanVIP = tongThanhTienHoanVIP + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                        } else {
                            tongThanhTienHoanThuong = tongThanhTienHoanThuong + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                        }
                    }
                }
                tongThanhTienHoanVIP = tongThanhTienHoanVIP - tongThanhTienHoanVIP * 0.05;
                tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
                tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
                tongTienHoann.setText(df.format(tongThanhTienHoan));
                String s = "";
                long thu = 0;
                long chi = 0;
                if (tongThanhTien > tongThanhTienHoan) {
                    thu = tongThanhTien - tongThanhTienHoan;
                    s += "Thu: " + df.format(thu);
                } else {
                    chi = tongThanhTienHoan - tongThanhTien;
                    s += "Chi: " + df.format(chi);
                }
                lblKetToann.setText(s);
                ThongKeSPBanChay(tu, den);
                //Thống kê nv
                modelTopNV.setRowCount(0);
                List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(tu, den);
                int j = 1;

                dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
                for (NhanVien nv : dsNV) {
                    modelTopNV.addRow(new Object[]{j++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
                }
                ThongKeSPNV();
            }
        }

    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnQuy4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy4ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (a > namHiemTai) {
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String b = a + "-10-01";
        String c = a + "-12-31";
        ArrayList<HoaDon> dsHD = hd_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        lblTongHD.setText(dsHD.size() + "");
        long tongThanhTien = 0;
        double tongThanhTienVIP = 0;
        double tongThanhTienThuong = 0;

        for (HoaDon hd : dsHD) {
            dsCTHD = cthd_dao.layDSHDBangMa(hd.getMaHD());
            for (ChiTietHoaDon cthd : dsCTHD) {
                if (hd.getKhachHang().getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienVIP = tongThanhTienVIP + cthd.getTongTien();
                } else {
                    tongThanhTienThuong = tongThanhTienThuong + cthd.getTongTien();
                }

            }

        }
        tongThanhTienVIP = tongThanhTienVIP - tongThanhTienVIP * 0.05;
        tongThanhTienThuong = tongThanhTienThuong + tongThanhTienThuong * 0.05;
        tongThanhTien = (long) tongThanhTienVIP + (long) tongThanhTienThuong;
        lblTongTienBanD.setText(df.format(tongThanhTien));
        //hoàn
        ArrayList<HoaDonHoanTra> dsHDHT = hdh_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoanTra> dsCTHT;
        lblSoHDH.setText(dsHDHT.size() + "");
        long tongThanhTienHoan = 0;
        double tongThanhTienHoanVIP = 0;
        double tongThanhTienHoanThuong = 0;
        KhachHang kh;

        for (HoaDonHoanTra hdht : dsHDHT) {
            dsCTHT = ctht_dao.layDSCTHTBangMa(hdht.getMaHDHT());
            for (ChiTietHoanTra ctht : dsCTHT) {
                kh = kh_dao.getKHBangMa(hdht.getHoaDon().getKhachHang().getMaKH());
                if (kh.getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienHoanVIP = tongThanhTienHoanVIP + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                } else {
                    tongThanhTienHoanThuong = tongThanhTienHoanThuong + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                }
            }
        }
        tongThanhTienHoanVIP = tongThanhTienHoanVIP - tongThanhTienHoanVIP * 0.05;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(df.format(tongThanhTienHoan));
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + df.format(thu);
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + df.format(chi);
        }
        lblKetToann.setText(s);
        //Thống kê sản phẩm
        ArrayList<thongKeSPBanChay> dsSPBC = sp_dao.thongKeSPBanChay(b, c);
        int i = 1;
        SanPham sp;
        for (thongKeSPBanChay spbc : dsSPBC) {
            sp = sp_dao.laySanPhamBangMa(spbc.getMaSP());
            modelTKTopSP.addRow(new Object[]{i++, spbc.getMaSP(), sp.getTenSP(),
                spbc.getSlBan(), sp.getSoLuong(),});
        }
        ThongKeSPBanChay(b, c);
        //thống kê nv
        modelTopNV.setRowCount(0);
        List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(b, c);
        int j = 1;

        dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
        for (NhanVien nv : dsNV) {
            modelTopNV.addRow(new Object[]{j++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
        }
        ThongKeSPNV();
    }//GEN-LAST:event_btnQuy4ActionPerformed

    private void btnQuy3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy3ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (a > namHiemTai) {
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String b = a + "-07-01";
        String c = a + "-09-30";
        ArrayList<HoaDon> dsHD = hd_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        lblTongHD.setText(dsHD.size() + "");
        long tongThanhTien = 0;
        double tongThanhTienVIP = 0;
        double tongThanhTienThuong = 0;

        for (HoaDon hd : dsHD) {
            dsCTHD = cthd_dao.layDSHDBangMa(hd.getMaHD());
            for (ChiTietHoaDon cthd : dsCTHD) {
                if (hd.getKhachHang().getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienVIP = tongThanhTienVIP + cthd.getTongTien();
                } else {
                    tongThanhTienThuong = tongThanhTienThuong + cthd.getTongTien();
                }

            }

        }
        tongThanhTienVIP = tongThanhTienVIP - tongThanhTienVIP * 0.05;
        tongThanhTienThuong = tongThanhTienThuong + tongThanhTienThuong * 0.05;
        tongThanhTien = (long) tongThanhTienVIP + (long) tongThanhTienThuong;
        lblTongTienBanD.setText(df.format(tongThanhTien));
        //hoàn
        ArrayList<HoaDonHoanTra> dsHDHT = hdh_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoanTra> dsCTHT;
        lblSoHDH.setText(dsHDHT.size() + "");
        long tongThanhTienHoan = 0;
        double tongThanhTienHoanVIP = 0;
        double tongThanhTienHoanThuong = 0;
        KhachHang kh;

        for (HoaDonHoanTra hdht : dsHDHT) {
            dsCTHT = ctht_dao.layDSCTHTBangMa(hdht.getMaHDHT());
            for (ChiTietHoanTra ctht : dsCTHT) {
                kh = kh_dao.getKHBangMa(hdht.getHoaDon().getKhachHang().getMaKH());
                if (kh.getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienHoanVIP = tongThanhTienHoanVIP + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                } else {
                    tongThanhTienHoanThuong = tongThanhTienHoanThuong + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                }
            }
        }
        tongThanhTienHoanVIP = tongThanhTienHoanVIP - tongThanhTienHoanVIP * 0.05;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(df.format(tongThanhTienHoan));
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + df.format(thu);
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + df.format(chi);
        }
        lblKetToann.setText(s);
        //Thống kê sản phẩm
        ArrayList<thongKeSPBanChay> dsSPBC = sp_dao.thongKeSPBanChay(b, c);
        int i = 1;
        SanPham sp;
        for (thongKeSPBanChay spbc : dsSPBC) {
            sp = sp_dao.laySanPhamBangMa(spbc.getMaSP());
            modelTKTopSP.addRow(new Object[]{i++, spbc.getMaSP(), sp.getTenSP(),
                spbc.getSlBan(), sp.getSoLuong(),});
        }
        ThongKeSPBanChay(b, c);
        //thống kê nv
        modelTopNV.setRowCount(0);
        List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(b, c);
        int j = 1;

        dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
        for (NhanVien nv : dsNV) {
            modelTopNV.addRow(new Object[]{j++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
        }
        ThongKeSPNV();
    }//GEN-LAST:event_btnQuy3ActionPerformed

    private void btnQuy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy2ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (a > namHiemTai) {
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String b = a + "-04-01";
        String c = a + "-06-30";
        ArrayList<HoaDon> dsHD = hd_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        lblTongHD.setText(dsHD.size() + "");
        long tongThanhTien = 0;
        double tongThanhTienVIP = 0;
        double tongThanhTienThuong = 0;

        for (HoaDon hd : dsHD) {
            dsCTHD = cthd_dao.layDSHDBangMa(hd.getMaHD());
            for (ChiTietHoaDon cthd : dsCTHD) {
                if (hd.getKhachHang().getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienVIP = tongThanhTienVIP + cthd.getTongTien();
                } else {
                    tongThanhTienThuong = tongThanhTienThuong + cthd.getTongTien();
                }

            }

        }
        tongThanhTienVIP = tongThanhTienVIP - tongThanhTienVIP * 0.05;
        tongThanhTienThuong = tongThanhTienThuong + tongThanhTienThuong * 0.05;
        tongThanhTien = (long) tongThanhTienVIP + (long) tongThanhTienThuong;
        lblTongTienBanD.setText(df.format(tongThanhTien));
        //hoàn
        ArrayList<HoaDonHoanTra> dsHDHT = hdh_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoanTra> dsCTHT;
        lblSoHDH.setText(dsHDHT.size() + "");
        long tongThanhTienHoan = 0;
        double tongThanhTienHoanVIP = 0;
        double tongThanhTienHoanThuong = 0;
        KhachHang kh;

        for (HoaDonHoanTra hdht : dsHDHT) {
            dsCTHT = ctht_dao.layDSCTHTBangMa(hdht.getMaHDHT());
            for (ChiTietHoanTra ctht : dsCTHT) {
                kh = kh_dao.getKHBangMa(hdht.getHoaDon().getKhachHang().getMaKH());
                if (kh.getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienHoanVIP = tongThanhTienHoanVIP + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                } else {
                    tongThanhTienHoanThuong = tongThanhTienHoanThuong + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                }
            }
        }
        tongThanhTienHoanVIP = tongThanhTienHoanVIP - tongThanhTienHoanVIP * 0.05;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(df.format(tongThanhTienHoan));
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + df.format(thu);
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + df.format(chi);
        }
        lblKetToann.setText(s);
        //Thống kê sản phẩm
        ArrayList<thongKeSPBanChay> dsSPBC = sp_dao.thongKeSPBanChay(b, c);
        int i = 1;
        SanPham sp;
        for (thongKeSPBanChay spbc : dsSPBC) {
            sp = sp_dao.laySanPhamBangMa(spbc.getMaSP());
            modelTKTopSP.addRow(new Object[]{i++, spbc.getMaSP(), sp.getTenSP(),
                spbc.getSlBan(), sp.getSoLuong(),});
        }
        ThongKeSPBanChay(b, c);
        //thống kê nv
        modelTopNV.setRowCount(0);
        List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(b, c);
        int j = 1;

        dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
        for (NhanVien nv : dsNV) {
            modelTopNV.addRow(new Object[]{j++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
        }
        ThongKeSPNV();
    }//GEN-LAST:event_btnQuy2ActionPerformed

    private void btnQuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy1ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (a > namHiemTai) {
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String b = a + "-01-01";
        String c = a + "-03-31";
        ArrayList<HoaDon> dsHD = hd_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        lblTongHD.setText(dsHD.size() + "");
        long tongThanhTien = 0;
        double tongThanhTienVIP = 0;
        double tongThanhTienThuong = 0;

        for (HoaDon hd : dsHD) {
            dsCTHD = cthd_dao.layDSHDBangMa(hd.getMaHD());
            for (ChiTietHoaDon cthd : dsCTHD) {
                if (hd.getKhachHang().getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienVIP = tongThanhTienVIP + cthd.getTongTien();
                } else {
                    tongThanhTienThuong = tongThanhTienThuong + cthd.getTongTien();
                }

            }

        }
        tongThanhTienVIP = tongThanhTienVIP - tongThanhTienVIP * 0.05;
        tongThanhTienThuong = tongThanhTienThuong + tongThanhTienThuong * 0.05;
        tongThanhTien = (long) tongThanhTienVIP + (long) tongThanhTienThuong;
        lblTongTienBanD.setText(df.format(tongThanhTien));
        //hoàn
        ArrayList<HoaDonHoanTra> dsHDHT = hdh_dao.thongKeDoanhThu(b, c);
        ArrayList<ChiTietHoanTra> dsCTHT;
        lblSoHDH.setText(dsHDHT.size() + "");
        long tongThanhTienHoan = 0;
        double tongThanhTienHoanVIP = 0;
        double tongThanhTienHoanThuong = 0;
        KhachHang kh;

        for (HoaDonHoanTra hdht : dsHDHT) {
            dsCTHT = ctht_dao.layDSCTHTBangMa(hdht.getMaHDHT());
            for (ChiTietHoanTra ctht : dsCTHT) {
                kh = kh_dao.getKHBangMa(hdht.getHoaDon().getKhachHang().getMaKH());
                if (kh.getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienHoanVIP = tongThanhTienHoanVIP + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                } else {
                    tongThanhTienHoanThuong = tongThanhTienHoanThuong + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                }
            }
        }
        tongThanhTienHoanVIP = tongThanhTienHoanVIP - tongThanhTienHoanVIP * 0.05;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(df.format(tongThanhTienHoan));
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + df.format(thu);
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + df.format(chi);
        }
        lblKetToann.setText(s);
        //Thống kê sản phẩm.thongKeSPBanChay
        ArrayList<thongKeSPBanChay> dsSPBC = sp_dao.thongKeSPBanChay(b, c);
        int i = 1;
        SanPham sp;
        for (thongKeSPBanChay spbc : dsSPBC) {
            sp = sp_dao.laySanPhamBangMa(spbc.getMaSP());
            modelTKTopSP.addRow(new Object[]{i++, spbc.getMaSP(), sp.getTenSP(),
                spbc.getSlBan(), sp.getSoLuong(),});
        }
        ThongKeSPBanChay(b, c);
        //thống kê nv
        modelTopNV.setRowCount(0);
        List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(b, c);
        int j = 1;

        dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
        for (NhanVien nv : dsNV) {
            modelTopNV.addRow(new Object[]{i++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
        }
        ThongKeSPNV();
    }//GEN-LAST:event_btnQuy1ActionPerformed

    private void btnCaNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaNamActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int c = (int) jSpTopSP.getValue();
        if (c <= 0) {
            JOptionPane.showMessageDialog(null, "Top phải lớn hơn 0.");
            return;
        }
        //Thống ê theo năm
        int a = jycNam.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int namHiemTai = cal.get(Calendar.YEAR);
        if (a > namHiemTai) {
            JOptionPane.showMessageDialog(null, "Năm được chọn không lớn hơn năm hiện tại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<HoaDon> dsHD = hd_dao.thongKeDoanhThuTheoNam(a);
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        lblTongHD.setText(dsHD.size() + "");
        long tongThanhTien = 0;
        double tongThanhTienVIP = 0;
        double tongThanhTienThuong = 0;

        for (HoaDon hd : dsHD) {
            dsCTHD = cthd_dao.layDSHDBangMa(hd.getMaHD());
            for (ChiTietHoaDon cthd : dsCTHD) {
                if (hd.getKhachHang().getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienVIP = tongThanhTienVIP + cthd.getTongTien();
                } else {
                    tongThanhTienThuong = tongThanhTienThuong + cthd.getTongTien();
                }
            }
        }
        tongThanhTienVIP = tongThanhTienVIP - tongThanhTienVIP * 0.05;
        tongThanhTienThuong = tongThanhTienThuong + tongThanhTienThuong * 0.05;
        tongThanhTien = (long) tongThanhTienVIP + (long) tongThanhTienThuong;
        lblTongTienBanD.setText(df.format(tongThanhTien));

        //Thống kê hoàn
        ArrayList<HoaDonHoanTra> dsHDHT = hdh_dao.thongKeDoanhThuTheoNam(a);
        ArrayList<ChiTietHoanTra> dsCTHT;
        lblSoHDH.setText(dsHDHT.size() + "");
        long tongThanhTienHoan = 0;
        double tongThanhTienHoanVIP = 0;
        double tongThanhTienHoanThuong = 0;
        KhachHang kh;

        for (HoaDonHoanTra hdht : dsHDHT) {
            dsCTHT = ctht_dao.layDSCTHTBangMa(hdht.getMaHDHT());
            for (ChiTietHoanTra ctht : dsCTHT) {
                kh = kh_dao.getKHBangMa(hdht.getHoaDon().getKhachHang().getMaKH());
                if (kh.getLoaiKhachHang().getTenLoai().equalsIgnoreCase("VIP")) {
                    tongThanhTienHoanVIP = tongThanhTienHoanVIP + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                } else {
                    tongThanhTienHoanThuong = tongThanhTienHoanThuong + ctht.getSoLuong() * ctht.getSanPham().getGiaGoc();
                }
            }
        }
        tongThanhTienHoanVIP = tongThanhTienHoanVIP - tongThanhTienHoanVIP * 0.05;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(df.format(tongThanhTienHoan));
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + df.format(thu);
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + df.format(chi);
        }
        lblKetToann.setText(s);
        //Thống kê sản phẩm
        ArrayList<SanPham> dsSP = sp_dao.getAllSP();
        ArrayList<thongKeSPBanChay> dsSPBC = sp_dao.thongKeSPBanChayTheoNam(dsSP.size(), a);
        int i = 1;
        SanPham sp;
        for (thongKeSPBanChay spbc : dsSPBC) {
            sp = sp_dao.laySanPhamBangMa(spbc.getMaSP());
            modelTKTopSP.addRow(new Object[]{i++, spbc.getMaSP(), sp.getTenSP(),
                spbc.getSlBan(), sp.getSoLuong(),});
        }

        ThongKeSPBanChayTheoNam(a);
        //thống kê nv
        String tu = a + "-01-01";
        String den = a + "-12-31";
        modelTopNV.setRowCount(0);
        List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(tu, den);
        int j = 1;

        dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
        for (NhanVien nv : dsNV) {
            modelTopNV.addRow(new Object[]{j++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
        }
        ThongKeSPNV();

    }//GEN-LAST:event_btnCaNamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tu, den;
        tu = fday.format(tuNgay.getDate());
        den = fday.format(denNgay.getDate());
        modelTopNV.setRowCount(0);
        List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(tu, den);
        int i = 1;

        dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
        for (NhanVien nv : dsNV) {
            modelTopNV.addRow(new Object[]{i++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
        }
        ThongKeSPNV();

    }//GEN-LAST:event_jButton1ActionPerformed
    public void ThongKeSPNV() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int j = 0; j < modelTopNV.getRowCount(); j++) {
            dataset.addValue((double) modelTopNV.getValueAt(j, 3), "VND", "" + modelTopNV.getValueAt(j, 2));
        }

        JFreeChart chart = ChartFactory.createBarChart("Thống Kê Nhân Viên Suất Xắc", "Tên sản phẩm", "VND ", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(pnlTKTopNV.getWidth(), pnlTKTopNV.getHeight()));
        pnlTKTopNV.removeAll();
        pnlTKTopNV.setLayout(new CardLayout());
        pnlTKTopNV.add(chartPanel);
        pnlTKTopNV.validate();
        pnlTKTopNV.repaint();
    }

    public void ThongKeSPBanChay(String a, String b) {
        int c = (int) jSpTopSP.getValue();
        if (c <= 0) {
            JOptionPane.showMessageDialog(null, "Top phải lớn hơn 0.");
            return;
        }
        ArrayList<thongKeSPBanChay> dsTKSPBC = sp_dao.thongKeSPBanChayTheoTop(c, a, b);
        SanPham sp;
        if (dsTKSPBC != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (thongKeSPBanChay tkspbc : dsTKSPBC) {
                sp = sp_dao.laySanPhamBangMa(tkspbc.getMaSP());
                dataset.addValue(tkspbc.getSlBan(), "Chiếc", "" + sp.getTenSP());

            }
            JFreeChart chart = ChartFactory.createBarChart("Thống Kê Sản Phẩm Bán Chạy", "Tên sản phẩm", "Số ", dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(pnlBieuDoTKTopSP.getWidth(), 200));
            pnlBieuDoTKTopSP.removeAll();
            pnlBieuDoTKTopSP.setLayout(new CardLayout());
            pnlBieuDoTKTopSP.add(chartPanel);
            pnlBieuDoTKTopSP.validate();
            pnlBieuDoTKTopSP.repaint();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnCaNam;
    private swing.Button btnQuy1;
    private swing.Button btnQuy2;
    private swing.Button btnQuy3;
    private swing.Button btnQuy4;
    private swing.Button btnThongKe;
    private com.toedter.calendar.JDateChooser denNgay;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSpinner jSpTopSP;
    private com.toedter.calendar.JYearChooser jycNam;
    private javax.swing.JLabel lblDenNgay;
    private javax.swing.JLabel lblKetToan1;
    private javax.swing.JLabel lblKetToann;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblSoHDH;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTieuDeTKSPT;
    private javax.swing.JLabel lblTieuDeTKSPT1;
    private javax.swing.JLabel lblTongHD;
    private javax.swing.JLabel lblTongHDDB1;
    private javax.swing.JLabel lblTongHDTra1;
    private javax.swing.JLabel lblTongTienBan1;
    private javax.swing.JLabel lblTongTienBanD;
    private javax.swing.JLabel lblTongTienHoan1;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JPanel pnlBieuDoTKTopSP;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSPLoi;
    private javax.swing.JPanel pnlSPTon;
    private javax.swing.JPanel pnlTKTopNV;
    private javax.swing.JPanel pnlTieuChi;
    private javax.swing.JPanel pnlTongHDDB1;
    private javax.swing.JPanel pnlTongHDTra1;
    private javax.swing.JPanel pnlTongTienBan1;
    private javax.swing.JPanel pnlTongTienHoan1;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlTopNV;
    private javax.swing.JPanel pnlTopSP;
    private javax.swing.JScrollPane scrTKNVTop;
    private javax.swing.JScrollPane scrTKSPT;
    private javax.swing.JScrollPane scrTKSPT1;
    private javax.swing.JScrollPane scrTKTopSP;
    private javax.swing.JTable tblTKSPL;
    private javax.swing.JTable tblTKSPT;
    private javax.swing.JTable tblTKTopNV;
    private javax.swing.JTable tblTKTopSP;
    private javax.swing.JTabbedPane tbpThongKe;
    private javax.swing.JLabel tongTienHoann;
    private com.toedter.calendar.JDateChooser tuNgay;
    // End of variables declaration//GEN-END:variables
}
