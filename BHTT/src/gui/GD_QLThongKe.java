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
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
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
    SimpleDateFormat fday=new SimpleDateFormat("yyyy-MM-dd");

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
        pnlTongHDDB = new javax.swing.JPanel();
        lblTongHDDB = new javax.swing.JLabel();
        lblTongHD = new javax.swing.JLabel();
        pnlTongTienBan = new javax.swing.JPanel();
        lblTongTienBan = new javax.swing.JLabel();
        lblTongTienBanD = new javax.swing.JLabel();
        pnlTongHDTra = new javax.swing.JPanel();
        lblTongHDTra = new javax.swing.JLabel();
        lblSoHDH = new javax.swing.JLabel();
        pnlTongTienHoan = new javax.swing.JPanel();
        lblTongTienHoan = new javax.swing.JLabel();
        tongTienHoann = new javax.swing.JLabel();
        pnlKetToan = new javax.swing.JPanel();
        lblKetToan = new javax.swing.JLabel();
        lblKetToann = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jSpTopNV = new javax.swing.JSpinner();
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
                        .addContainerGap(14, Short.MAX_VALUE))
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

        pnlTongHDDB.setBackground(new java.awt.Color(0, 204, 0));

        lblTongHDDB.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHDDB.setForeground(new java.awt.Color(204, 255, 255));
        lblTongHDDB.setText("Tổng số hóa đơn đã bán");

        lblTongHD.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHD.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTongHDDBLayout = new javax.swing.GroupLayout(pnlTongHDDB);
        pnlTongHDDB.setLayout(pnlTongHDDBLayout);
        pnlTongHDDBLayout.setHorizontalGroup(
            pnlTongHDDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDDBLayout.createSequentialGroup()
                .addGroup(pnlTongHDDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTongHDDBLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblTongHDDB))
                    .addGroup(pnlTongHDDBLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(lblTongHD)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTongHDDBLayout.setVerticalGroup(
            pnlTongHDDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDDBLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTongHDDB, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTongHD)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pnlTongTienBan.setBackground(new java.awt.Color(204, 204, 0));

        lblTongTienBan.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTienBan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienBan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTienBan.setText("Tổng tiền bán được");

        lblTongTienBanD.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienBanD.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTongTienBanLayout = new javax.swing.GroupLayout(pnlTongTienBan);
        pnlTongTienBan.setLayout(pnlTongTienBanLayout);
        pnlTongTienBanLayout.setHorizontalGroup(
            pnlTongTienBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongTienBanLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(lblTongTienBan)
                .addGap(34, 34, 34))
            .addGroup(pnlTongTienBanLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(lblTongTienBanD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTongTienBanLayout.setVerticalGroup(
            pnlTongTienBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongTienBanLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblTongTienBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongTienBanD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTongHDTra.setBackground(new java.awt.Color(102, 102, 255));

        lblTongHDTra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHDTra.setForeground(new java.awt.Color(255, 255, 255));
        lblTongHDTra.setText("Tổng số hóa đơn hoàn trả");

        lblSoHDH.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSoHDH.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTongHDTraLayout = new javax.swing.GroupLayout(pnlTongHDTra);
        pnlTongHDTra.setLayout(pnlTongHDTraLayout);
        pnlTongHDTraLayout.setHorizontalGroup(
            pnlTongHDTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongHDTraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTongHDTra)
                .addGap(23, 23, 23))
            .addGroup(pnlTongHDTraLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(lblSoHDH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTongHDTraLayout.setVerticalGroup(
            pnlTongHDTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDTraLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblTongHDTra)
                .addGap(18, 18, 18)
                .addComponent(lblSoHDH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTongTienHoan.setBackground(new java.awt.Color(255, 0, 153));

        lblTongTienHoan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienHoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTienHoan.setText("Tổng tiền hoàn lại");

        tongTienHoann.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tongTienHoann.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTongTienHoanLayout = new javax.swing.GroupLayout(pnlTongTienHoan);
        pnlTongTienHoan.setLayout(pnlTongTienHoanLayout);
        pnlTongTienHoanLayout.setHorizontalGroup(
            pnlTongTienHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongTienHoanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTongTienHoan)
                .addGap(46, 46, 46))
            .addGroup(pnlTongTienHoanLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(tongTienHoann)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTongTienHoanLayout.setVerticalGroup(
            pnlTongTienHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongTienHoanLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblTongTienHoan)
                .addGap(18, 18, 18)
                .addComponent(tongTienHoann)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pnlKetToan.setBackground(new java.awt.Color(153, 0, 51));

        lblKetToan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblKetToan.setForeground(new java.awt.Color(255, 255, 255));
        lblKetToan.setText("Kết toán");

        lblKetToann.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblKetToann.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlKetToanLayout = new javax.swing.GroupLayout(pnlKetToan);
        pnlKetToan.setLayout(pnlKetToanLayout);
        pnlKetToanLayout.setHorizontalGroup(
            pnlKetToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKetToanLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(pnlKetToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKetToanLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblKetToann))
                    .addComponent(lblKetToan))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        pnlKetToanLayout.setVerticalGroup(
            pnlKetToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKetToanLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblKetToan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblKetToann)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTongHDTra, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTongHDDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(106, 106, 106)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTongTienBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTongTienHoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(pnlKetToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTongTienBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTongHDDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTongHDTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTongTienHoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlKetToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );

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

        tbpThongKe.addTab("Top 10 sản phẩm bán chạy", pnlTopSP);

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Top:");

        javax.swing.GroupLayout pnlTopNVLayout = new javax.swing.GroupLayout(pnlTopNV);
        pnlTopNV.setLayout(pnlTopNVLayout);
        pnlTopNVLayout.setHorizontalGroup(
            pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopNVLayout.createSequentialGroup()
                .addGroup(pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopNVLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpTopNV, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTopNVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlTKTopNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(scrTKNVTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTopNVLayout.setVerticalGroup(
            pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopNVLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrTKNVTop, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTopNVLayout.createSequentialGroup()
                        .addGroup(pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSpTopNV, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTKTopNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        tbpThongKe.addTab("Top 10 nhân viên xuất sắc", pnlTopNV);

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
        String tu=sdf.format(tuNgay);
        String den=sdf.format(denNgay);
        if (ngayTim.after(today) || ngayCanTim.after(today)) {
            JOptionPane.showMessageDialog(null, "Thời gian tìm không lớn hơn thời gian hiện tại");
        } else {
            if (ngayTim.after(ngayCanTim)) {
                JOptionPane.showMessageDialog(null, "Ngày tìm không lớn hơn ngày cần tìm");
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
                lblTongTienBanD.setText(String.format("%d", tongThanhTien) + " VND");

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
                tongThanhTienHoanVIP = tongThanhTienHoanVIP + tongThanhTienHoanVIP * 0.15;
                tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
                tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
                tongTienHoann.setText(String.format("%d", tongThanhTienHoan) + " VND");
                String s = "";
                long thu = 0;
                long chi = 0;
                if (tongThanhTien > tongThanhTienHoan) {
                    thu = tongThanhTien - tongThanhTienHoan;
                    s += "Thu: " + thu + " VND";
                } else {
                    chi = tongThanhTienHoan - tongThanhTien;
                    s += "Chi: " + chi + " VND";
                }
                lblKetToann.setText(s);
                ThongKeSPBanChay(tu, den);

            }
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnQuy4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy4ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
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
        lblTongTienBanD.setText(String.format("%d", tongThanhTien) + " VND");
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
        tongThanhTienHoanVIP = tongThanhTienHoanVIP + tongThanhTienHoanVIP * 0.15;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(String.format("%d", tongThanhTienHoan) + " VND");
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + thu + " VND";
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + chi + " VND";
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
    }//GEN-LAST:event_btnQuy4ActionPerformed

    private void btnQuy3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy3ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
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
        lblTongTienBanD.setText(String.format("%d", tongThanhTien) + " VND");
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
        tongThanhTienHoanVIP = tongThanhTienHoanVIP + tongThanhTienHoanVIP * 0.15;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(String.format("%d", tongThanhTienHoan) + " VND");
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + thu + " VND";
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + chi + " VND";
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
    }//GEN-LAST:event_btnQuy3ActionPerformed

    private void btnQuy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy2ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
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
        lblTongTienBanD.setText(String.format("%d", tongThanhTien) + " VND");
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
        tongThanhTienHoanVIP = tongThanhTienHoanVIP + tongThanhTienHoanVIP * 0.15;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(String.format("%d", tongThanhTienHoan) + " VND");
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + thu + " VND";
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + chi + " VND";
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
    }//GEN-LAST:event_btnQuy2ActionPerformed

    private void btnQuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuy1ActionPerformed
        DefaultTableModel fm = (DefaultTableModel) tblTKTopSP.getModel();
        fm.setRowCount(0);
        int a = jycNam.getYear();
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
        lblTongTienBanD.setText(String.format("%d", tongThanhTien) + " VND");
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
        tongThanhTienHoanVIP = tongThanhTienHoanVIP + tongThanhTienHoanVIP * 0.15;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(String.format("%d", tongThanhTienHoan) + " VND");
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + thu + " VND";
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + chi + " VND";
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
        lblTongTienBanD.setText(String.format("%d", tongThanhTien) + " VND");

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
        tongThanhTienHoanVIP = tongThanhTienHoanVIP + tongThanhTienHoanVIP * 0.15;
        tongThanhTienHoanThuong = tongThanhTienHoanThuong + tongThanhTienHoanThuong * 0.05;
        tongThanhTienHoan = (long) tongThanhTienHoanVIP + (long) tongThanhTienHoanThuong;
        tongTienHoann.setText(String.format("%d", tongThanhTienHoan) + " VND");
        String s = "";
        long thu = 0;
        long chi = 0;
        if (tongThanhTien > tongThanhTienHoan) {
            thu = tongThanhTien - tongThanhTienHoan;
            s += "Thu: " + thu + " VND";
        } else {
            chi = tongThanhTienHoan - tongThanhTien;
            s += "Chi: " + chi + " VND";
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
    }//GEN-LAST:event_btnCaNamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tu,den;
        tu=fday.format(tuNgay.getDate());
        den=fday.format(denNgay.getDate());
        modelTopNV.setRowCount(0);
        List<NhanVien> dsNV = nv_dao.layNhanVienCoHoaDonTheoNgay(tu, den);
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        ArrayList<HoaDon> dsHD;
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        long tongThanhTien = 0;
        double tongThanhTienVIP = 0;
        double tongThanhTienThuong = 0;
        int i=1;


        dsNV.sort(Comparator.comparing(NhanVien::getDoanhThu).reversed());
        System.out.println(dsNV);
        for (NhanVien nv : dsNV) {
                modelTopNV.addRow(new Object[]{i++, nv.getMaNV(), nv.getTenNV(), nv.getDoanhThu()});
        }
        ThongKeSPNV();

    }//GEN-LAST:event_jButton1ActionPerformed
    public void ThongKeSPNV() {
        int c = (int) jSpTopNV.getValue();
        if (c <0) {
            JOptionPane.showMessageDialog(null, "Top phải lớn hơn hoặc bằng 0.");
            return;
        }
        if(c >modelTopNV.getRowCount()){
            JOptionPane.showMessageDialog(null, "Hệ thống không đủ nhân viên.");
            return;
        }
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int j = 0; j < c; j++) {
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner jSpTopNV;
    private javax.swing.JSpinner jSpTopSP;
    private com.toedter.calendar.JYearChooser jycNam;
    private javax.swing.JLabel lblDenNgay;
    private javax.swing.JLabel lblKetToan;
    private javax.swing.JLabel lblKetToann;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblSoHDH;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTieuDeTKSPT;
    private javax.swing.JLabel lblTieuDeTKSPT1;
    private javax.swing.JLabel lblTongHD;
    private javax.swing.JLabel lblTongHDDB;
    private javax.swing.JLabel lblTongHDTra;
    private javax.swing.JLabel lblTongTienBan;
    private javax.swing.JLabel lblTongTienBanD;
    private javax.swing.JLabel lblTongTienHoan;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JPanel pnlBieuDoTKTopSP;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlKetToan;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSPLoi;
    private javax.swing.JPanel pnlSPTon;
    private javax.swing.JPanel pnlTKTopNV;
    private javax.swing.JPanel pnlTieuChi;
    private javax.swing.JPanel pnlTongHDDB;
    private javax.swing.JPanel pnlTongHDTra;
    private javax.swing.JPanel pnlTongTienBan;
    private javax.swing.JPanel pnlTongTienHoan;
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
