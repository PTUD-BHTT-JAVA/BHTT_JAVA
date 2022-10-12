/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

/**
 *
 * @author ACER
 */
public class GD_QLThongKe extends javax.swing.JInternalFrame {
    private static class RoundedBorder implements Border {
 
    private int radius;
 
 
    RoundedBorder(int radius) {
        this.radius = radius;
    }
 
 
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }
 
 
    public boolean isBorderOpaque() {
        return true;
    }
 
 
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
    private String username;
    /**
     * Creates new form QuanLyHoaDon
     */
    public GD_QLThongKe(String _username) {
        this.setRootPaneCheckingEnabled(false);
        javax.swing.plaf.InternalFrameUI ui
                = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);
        initComponents();
        this.setFocusable(true);
        username=_username;
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
        btnThongKe = new javax.swing.JButton();
        lblNam = new javax.swing.JLabel();
        lblQuy = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnQuy2 = new javax.swing.JButton();
        btnQuy3 = new javax.swing.JButton();
        btnQuy4 = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        tbpThongKe = new javax.swing.JTabbedPane();
        pnlDoanhThu = new javax.swing.JPanel();
        pnlTongHDDB = new javax.swing.JPanel();
        lblTongHDDB = new javax.swing.JLabel();
        pnlTongTienBan = new javax.swing.JPanel();
        lblTongTienBan = new javax.swing.JLabel();
        pnlTongHDTra = new javax.swing.JPanel();
        lblTongHDTra = new javax.swing.JLabel();
        pnlTongTienHoan = new javax.swing.JPanel();
        lblTongTienHoan = new javax.swing.JLabel();
        pnlKetToan = new javax.swing.JPanel();
        lblKetToan = new javax.swing.JLabel();
        pnlTopSP = new javax.swing.JPanel();
        pnlBieuDoTKTopSP = new javax.swing.JPanel();
        scrTKTopSP = new javax.swing.JScrollPane();
        tblTKTopSP = new javax.swing.JTable();
        pnlTopNV = new javax.swing.JPanel();
        pnlTKTopNV = new javax.swing.JPanel();
        scrTKNVTop = new javax.swing.JScrollPane();
        tblTKTopNV = new javax.swing.JTable();
        pnlSPTon = new javax.swing.JPanel();
        lblTieuDeTKSPT = new javax.swing.JLabel();
        scrTKSPT = new javax.swing.JScrollPane();
        tblTKSPT = new javax.swing.JTable();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblTieuDe.setText("THỐNG KÊ");

        pnlTieuChi.setBackground(new java.awt.Color(255, 255, 255));
        pnlTieuChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTuNgay.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblTuNgay.setText("Từ ngày:");

        lblDenNgay.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblDenNgay.setText("Đến ngày: ");

        btnThongKe.setBackground(new java.awt.Color(51, 119, 41));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setText("Thống kê");

        lblNam.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblNam.setText("Năm: ");

        lblQuy.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblQuy.setText("Quý: ");

        jButton1.setBackground(new java.awt.Color(51, 119, 41));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Quý 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnQuy2.setBackground(new java.awt.Color(51, 119, 41));
        btnQuy2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnQuy2.setForeground(new java.awt.Color(255, 255, 255));
        btnQuy2.setText("Quý 2 ");

        btnQuy3.setBackground(new java.awt.Color(51, 119, 41));
        btnQuy3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnQuy3.setForeground(new java.awt.Color(255, 255, 255));
        btnQuy3.setText("Quý 3 ");

        btnQuy4.setBackground(new java.awt.Color(51, 119, 41));
        btnQuy4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnQuy4.setForeground(new java.awt.Color(255, 255, 255));
        btnQuy4.setText("Quý 4 ");

        javax.swing.GroupLayout pnlTieuChiLayout = new javax.swing.GroupLayout(pnlTieuChi);
        pnlTieuChi.setLayout(pnlTieuChiLayout);
        pnlTieuChiLayout.setHorizontalGroup(
            pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuChiLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(216, 216, 216)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(btnQuy2)
                .addGap(13, 13, 13)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTieuChiLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnThongKe))
                    .addGroup(pnlTieuChiLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnQuy3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuy4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTieuChiLayout.setVerticalGroup(
            pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTieuChiLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDenNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQuy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTieuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnQuy4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuy3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnQuy2))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                        .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(530, 530, 530))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                        .addComponent(pnlTieuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTieuChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbpThongKe.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        pnlTongHDDB.setBackground(new java.awt.Color(0, 204, 0));

        lblTongHDDB.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHDDB.setForeground(new java.awt.Color(204, 255, 255));
        lblTongHDDB.setText("Tổng số hóa đơn đã bán");

        javax.swing.GroupLayout pnlTongHDDBLayout = new javax.swing.GroupLayout(pnlTongHDDB);
        pnlTongHDDB.setLayout(pnlTongHDDBLayout);
        pnlTongHDDBLayout.setHorizontalGroup(
            pnlTongHDDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDDBLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTongHDDB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTongHDDBLayout.setVerticalGroup(
            pnlTongHDDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDDBLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTongHDDB, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pnlTongTienBan.setBackground(new java.awt.Color(204, 204, 0));

        lblTongTienBan.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTienBan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienBan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTienBan.setText("Tổng tiền bán đượbc");

        javax.swing.GroupLayout pnlTongTienBanLayout = new javax.swing.GroupLayout(pnlTongTienBan);
        pnlTongTienBan.setLayout(pnlTongTienBanLayout);
        pnlTongTienBanLayout.setHorizontalGroup(
            pnlTongTienBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongTienBanLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(lblTongTienBan)
                .addGap(34, 34, 34))
        );
        pnlTongTienBanLayout.setVerticalGroup(
            pnlTongTienBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongTienBanLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblTongTienBan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTongHDTra.setBackground(new java.awt.Color(102, 102, 255));

        lblTongHDTra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHDTra.setForeground(new java.awt.Color(255, 255, 255));
        lblTongHDTra.setText("Tổng số hóa đơn hoàn trả");

        javax.swing.GroupLayout pnlTongHDTraLayout = new javax.swing.GroupLayout(pnlTongHDTra);
        pnlTongHDTra.setLayout(pnlTongHDTraLayout);
        pnlTongHDTraLayout.setHorizontalGroup(
            pnlTongHDTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongHDTraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTongHDTra)
                .addGap(23, 23, 23))
        );
        pnlTongHDTraLayout.setVerticalGroup(
            pnlTongHDTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDTraLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblTongHDTra)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTongTienHoan.setBackground(new java.awt.Color(255, 0, 153));

        lblTongTienHoan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTienHoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTienHoan.setText("Tổng tiền hoàn lại");

        javax.swing.GroupLayout pnlTongTienHoanLayout = new javax.swing.GroupLayout(pnlTongTienHoan);
        pnlTongTienHoan.setLayout(pnlTongTienHoanLayout);
        pnlTongTienHoanLayout.setHorizontalGroup(
            pnlTongTienHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongTienHoanLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(lblTongTienHoan)
                .addGap(46, 46, 46))
        );
        pnlTongTienHoanLayout.setVerticalGroup(
            pnlTongTienHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongTienHoanLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblTongTienHoan)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        pnlKetToan.setBackground(new java.awt.Color(153, 0, 51));

        lblKetToan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblKetToan.setForeground(new java.awt.Color(255, 255, 255));
        lblKetToan.setText("Kết toán");

        javax.swing.GroupLayout pnlKetToanLayout = new javax.swing.GroupLayout(pnlKetToan);
        pnlKetToan.setLayout(pnlKetToanLayout);
        pnlKetToanLayout.setHorizontalGroup(
            pnlKetToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKetToanLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(lblKetToan)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        pnlKetToanLayout.setVerticalGroup(
            pnlKetToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKetToanLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblKetToan)
                .addContainerGap(150, Short.MAX_VALUE))
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
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTongTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTongTienBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTongHDTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTongTienHoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(pnlKetToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );

        tbpThongKe.addTab("Doanh Thu", pnlDoanhThu);

        pnlTopSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        pnlBieuDoTKTopSP.setBackground(new java.awt.Color(153, 153, 0));

        javax.swing.GroupLayout pnlBieuDoTKTopSPLayout = new javax.swing.GroupLayout(pnlBieuDoTKTopSP);
        pnlBieuDoTKTopSP.setLayout(pnlBieuDoTKTopSPLayout);
        pnlBieuDoTKTopSPLayout.setHorizontalGroup(
            pnlBieuDoTKTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );
        pnlBieuDoTKTopSPLayout.setVerticalGroup(
            pnlBieuDoTKTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblTKTopSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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

        javax.swing.GroupLayout pnlTopSPLayout = new javax.swing.GroupLayout(pnlTopSP);
        pnlTopSP.setLayout(pnlTopSPLayout);
        pnlTopSPLayout.setHorizontalGroup(
            pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBieuDoTKTopSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrTKTopSP, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTopSPLayout.setVerticalGroup(
            pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopSPLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBieuDoTKTopSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrTKTopSP, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tbpThongKe.addTab("Top 10 sản phẩm bán chạy", pnlTopSP);

        pnlTKTopNV.setBackground(new java.awt.Color(153, 153, 0));

        javax.swing.GroupLayout pnlTKTopNVLayout = new javax.swing.GroupLayout(pnlTKTopNV);
        pnlTKTopNV.setLayout(pnlTKTopNVLayout);
        pnlTKTopNVLayout.setHorizontalGroup(
            pnlTKTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
        pnlTKTopNVLayout.setVerticalGroup(
            pnlTKTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblTKTopNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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
                .addGap(27, 27, 27)
                .addComponent(scrTKNVTop, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTopNVLayout.setVerticalGroup(
            pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopNVLayout.createSequentialGroup()
                .addGap(0, 32, Short.MAX_VALUE)
                .addGroup(pnlTopNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTKTopNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrTKNVTop, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        tbpThongKe.addTab("Top 10 nhân viên xuất sắc", pnlTopNV);

        lblTieuDeTKSPT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTieuDeTKSPT.setText("Danh sách sản phẩm không có lượt mua");

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
                        .addGap(366, 366, 366)
                        .addComponent(lblTieuDeTKSPT))
                    .addGroup(pnlSPTonLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(scrTKSPT, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuy2;
    private javax.swing.JButton btnQuy3;
    private javax.swing.JButton btnQuy4;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblDenNgay;
    private javax.swing.JLabel lblKetToan;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblQuy;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTieuDeTKSPT;
    private javax.swing.JLabel lblTongHDDB;
    private javax.swing.JLabel lblTongHDTra;
    private javax.swing.JLabel lblTongTienBan;
    private javax.swing.JLabel lblTongTienHoan;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JPanel pnlBieuDoTKTopSP;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlKetToan;
    private javax.swing.JPanel pnlMain;
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
    private javax.swing.JScrollPane scrTKTopSP;
    private javax.swing.JTable tblTKSPT;
    private javax.swing.JTable tblTKTopNV;
    private javax.swing.JTable tblTKTopSP;
    private javax.swing.JTabbedPane tbpThongKe;
    // End of variables declaration//GEN-END:variables
}
