/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Constructor;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.tools.DocumentationTool;
import keeptoo.KGradientPanel;

/**
 *
 * @author ACER
 */
public class QuanLy extends javax.swing.JFrame {
    private static String username;

    /** Creates new form MainEmployee */
    public QuanLy(String _username) {
        username = _username;
        this.setUndecorated(true);
        this.setResizable(true);
        this.setVisible(true);

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        GioiThieu frGioiThieu=new GioiThieu(username);
        openComponent(frGioiThieu);
        

    }
    
   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        pnlNguoiDung = new javax.swing.JPanel();
        lblChucVu = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        pnlSilde = new javax.swing.JPanel();
        pnlGioiThieu = new keeptoo.KGradientPanel();
        lblGioiThieu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlSanPham = new keeptoo.KGradientPanel();
        lblSanPham = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlNhanVien = new keeptoo.KGradientPanel();
        lblNhanVien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlTaiKhoan = new keeptoo.KGradientPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlNhaCungCap = new keeptoo.KGradientPanel();
        lblNhaCungCap = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlThongKe = new keeptoo.KGradientPanel();
        lblThongKe = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlTroGiup = new keeptoo.KGradientPanel();
        lblTroGiup = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(200, 200));

        pnlMain.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, new java.awt.Color(227, 227, 227)));
        pnlForm.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        pnlForm.setLayout(new java.awt.BorderLayout());
        pnlMain.add(pnlForm, java.awt.BorderLayout.CENTER);

        pnlMenu.setBackground(new java.awt.Color(249, 234, 249));
        pnlMenu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMenu.setPreferredSize(new java.awt.Dimension(250, 200));
        pnlMenu.setLayout(new java.awt.BorderLayout());

        pnlNguoiDung.setBackground(new java.awt.Color(255, 255, 255));

        lblChucVu.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblChucVu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblChucVu.setText("Quản lý");

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTen.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblTen.setText("Trần Văn B");

        javax.swing.GroupLayout pnlNguoiDungLayout = new javax.swing.GroupLayout(pnlNguoiDung);
        pnlNguoiDung.setLayout(pnlNguoiDungLayout);
        pnlNguoiDungLayout.setHorizontalGroup(
            pnlNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDungLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGroup(pnlNguoiDungLayout.createSequentialGroup()
                        .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlNguoiDungLayout.setVerticalGroup(
            pnlNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDungLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlNguoiDungLayout.createSequentialGroup()
                        .addComponent(lblChucVu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTen)
                        .addGap(30, 30, 30))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlNguoiDung, java.awt.BorderLayout.PAGE_START);

        pnlSilde.setBackground(new java.awt.Color(255, 255, 255));
        pnlSilde.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlSilde.setLayout(new javax.swing.BoxLayout(pnlSilde, javax.swing.BoxLayout.PAGE_AXIS));

        pnlGioiThieu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pnlGioiThieu.setkEndColor(new java.awt.Color(255, 204, 204));
        pnlGioiThieu.setkStartColor(new java.awt.Color(102, 0, 153));
        pnlGioiThieu.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnlGioiThieu.setPreferredSize(new java.awt.Dimension(270, 40));
        pnlGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlGioiThieuMousePressed(evt);
            }
        });
        pnlGioiThieu.setLayout(new java.awt.BorderLayout());

        lblGioiThieu.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblGioiThieu.setForeground(new java.awt.Color(255, 255, 255));
        lblGioiThieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGioiThieu.setText("GIỚI THIỆU");
        pnlGioiThieu.add(lblGioiThieu, java.awt.BorderLayout.CENTER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/trangChu.png"))); // NOI18N
        pnlGioiThieu.add(jLabel1, java.awt.BorderLayout.LINE_START);

        pnlSilde.add(pnlGioiThieu);

        pnlSanPham.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pnlSanPham.setkEndColor(new java.awt.Color(255, 204, 204));
        pnlSanPham.setkStartColor(new java.awt.Color(102, 0, 153));
        pnlSanPham.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnlSanPham.setPreferredSize(new java.awt.Dimension(270, 40));
        pnlSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlSanPhamMousePressed(evt);
            }
        });
        pnlSanPham.setLayout(new java.awt.BorderLayout());

        lblSanPham.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setText("SẢN PHẨM");
        pnlSanPham.add(lblSanPham, java.awt.BorderLayout.CENTER);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sanPham.png"))); // NOI18N
        pnlSanPham.add(jLabel9, java.awt.BorderLayout.LINE_START);

        pnlSilde.add(pnlSanPham);

        pnlNhanVien.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pnlNhanVien.setkEndColor(new java.awt.Color(255, 204, 204));
        pnlNhanVien.setkStartColor(new java.awt.Color(102, 0, 153));
        pnlNhanVien.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnlNhanVien.setPreferredSize(new java.awt.Dimension(270, 40));
        pnlNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlNhanVienMousePressed(evt);
            }
        });
        pnlNhanVien.setLayout(new java.awt.BorderLayout());

        lblNhanVien.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVien.setText("NHÂN VIÊN");
        pnlNhanVien.add(lblNhanVien, java.awt.BorderLayout.CENTER);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nhanVien.png"))); // NOI18N
        pnlNhanVien.add(jLabel10, java.awt.BorderLayout.LINE_START);

        pnlSilde.add(pnlNhanVien);

        pnlTaiKhoan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pnlTaiKhoan.setkEndColor(new java.awt.Color(255, 204, 204));
        pnlTaiKhoan.setkStartColor(new java.awt.Color(102, 0, 153));
        pnlTaiKhoan.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnlTaiKhoan.setPreferredSize(new java.awt.Dimension(270, 40));
        pnlTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTaiKhoanMousePressed(evt);
            }
        });
        pnlTaiKhoan.setLayout(new java.awt.BorderLayout());

        lblTaiKhoan.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaiKhoan.setText("TÀI KHOẢN");
        pnlTaiKhoan.add(lblTaiKhoan, java.awt.BorderLayout.CENTER);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/taiKhoan.png"))); // NOI18N
        pnlTaiKhoan.add(jLabel11, java.awt.BorderLayout.LINE_START);

        pnlSilde.add(pnlTaiKhoan);

        pnlNhaCungCap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pnlNhaCungCap.setkEndColor(new java.awt.Color(255, 204, 204));
        pnlNhaCungCap.setkStartColor(new java.awt.Color(102, 0, 153));
        pnlNhaCungCap.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnlNhaCungCap.setPreferredSize(new java.awt.Dimension(270, 40));
        pnlNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlNhaCungCapMousePressed(evt);
            }
        });
        pnlNhaCungCap.setLayout(new java.awt.BorderLayout());

        lblNhaCungCap.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        lblNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhaCungCap.setText("NHÀ CUNG CẤP");
        pnlNhaCungCap.add(lblNhaCungCap, java.awt.BorderLayout.CENTER);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nhaCungCap.png"))); // NOI18N
        pnlNhaCungCap.add(jLabel12, java.awt.BorderLayout.LINE_START);

        pnlSilde.add(pnlNhaCungCap);

        pnlThongKe.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pnlThongKe.setkEndColor(new java.awt.Color(255, 204, 204));
        pnlThongKe.setkStartColor(new java.awt.Color(102, 0, 153));
        pnlThongKe.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnlThongKe.setPreferredSize(new java.awt.Dimension(270, 40));
        pnlThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlThongKeMousePressed(evt);
            }
        });
        pnlThongKe.setLayout(new java.awt.BorderLayout());

        lblThongKe.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThongKe.setText("THỐNG KÊ");
        pnlThongKe.add(lblThongKe, java.awt.BorderLayout.CENTER);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/thongKe.png"))); // NOI18N
        pnlThongKe.add(jLabel7, java.awt.BorderLayout.LINE_START);

        pnlSilde.add(pnlThongKe);

        pnlTroGiup.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pnlTroGiup.setkEndColor(new java.awt.Color(255, 204, 204));
        pnlTroGiup.setkStartColor(new java.awt.Color(102, 0, 153));
        pnlTroGiup.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnlTroGiup.setPreferredSize(new java.awt.Dimension(270, 40));
        pnlTroGiup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTroGiupMousePressed(evt);
            }
        });
        pnlTroGiup.setLayout(new java.awt.BorderLayout());

        lblTroGiup.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblTroGiup.setForeground(new java.awt.Color(255, 255, 255));
        lblTroGiup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTroGiup.setText("TRỢ GIÚP");
        pnlTroGiup.add(lblTroGiup, java.awt.BorderLayout.CENTER);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/troGiup.png"))); // NOI18N
        pnlTroGiup.add(jLabel8, java.awt.BorderLayout.LINE_START);

        pnlSilde.add(pnlTroGiup);

        pnlMenu.add(pnlSilde, java.awt.BorderLayout.CENTER);

        btnDangXuat.setBackground(new java.awt.Color(252, 249, 250));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(102, 0, 102));
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dangXuat.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 153), 3, true));
        btnDangXuat.setPreferredSize(new java.awt.Dimension(76, 40));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        pnlMenu.add(btnDangXuat, java.awt.BorderLayout.PAGE_END);

        pnlMain.add(pnlMenu, java.awt.BorderLayout.WEST);
        pnlMenu.getAccessibleContext().setAccessibleParent(this);

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 204, 204));
        kGradientPanel1.setkGradientFocus(2600);
        kGradientPanel1.setkStartColor(new java.awt.Color(102, 0, 153));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1155, 45));

        jLabel3.setFont(new java.awt.Font("MV Boli", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BHTT Store");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        kGradientPanel1.add(jLabel3);

        pnlMain.add(kGradientPanel1, java.awt.BorderLayout.NORTH);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlGioiThieuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGioiThieuMousePressed
        GioiThieu frGioiThieu=new GioiThieu(username);
        openComponent(frGioiThieu);
    }//GEN-LAST:event_pnlGioiThieuMousePressed

    private void pnlSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSanPhamMousePressed
        SanPham fSanPham=new SanPham(username);
        openComponent(fSanPham);
    }//GEN-LAST:event_pnlSanPhamMousePressed

    private void pnlTaiKhoanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaiKhoanMousePressed
        TaiKhoan fTaiKhoan=new TaiKhoan(username);
        openComponent(fTaiKhoan);
    }//GEN-LAST:event_pnlTaiKhoanMousePressed

    private void pnlTroGiupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTroGiupMousePressed
        QLTroGiup fQLTroGiup=new QLTroGiup(username);
        openComponent(fQLTroGiup);
    }//GEN-LAST:event_pnlTroGiupMousePressed

    private void pnlNhaCungCapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhaCungCapMousePressed
        NhaCungCap fNhaCungCap=new NhaCungCap(username);
        openComponent(fNhaCungCap);
    }//GEN-LAST:event_pnlNhaCungCapMousePressed

    private void pnlThongKeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThongKeMousePressed
        QLThongKe fQLThongKe=new QLThongKe(username);
        openComponent(fQLThongKe);
    }//GEN-LAST:event_pnlThongKeMousePressed

    private void pnlNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhanVienMousePressed
        QLNhanVien fLNhanVien=new QLNhanVien(username);
        openComponent(fLNhanVien);
    }//GEN-LAST:event_pnlNhanVienMousePressed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ?", "Thoát", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed
    // open frame when click panel
    void openComponent(JInternalFrame frame) {
        Component[] components = pnlForm.getComponents();
        Component component = null;
        for (int i = 0; i < components.length; i++) {
            component = components[i];
            if (component != null) {
                component.setVisible(false);
            }
        }
        pnlForm.add(frame);
        frame.setVisible(true);
    }
    // set color
    void setColor(JPanel panel, JLabel label) {
        panel.setBackground(new Color(204, 232, 255));
        label.setForeground(new Color(33, 162, 72));
    }

    // rest color
    void resetColor(JPanel panel, JLabel label) {
        panel.setBackground(new Color(255, 255, 255));
        label.setForeground(new Color(0, 0, 0));
    }

   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLy(username);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblGioiThieu;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTroGiup;
    private javax.swing.JPanel pnlForm;
    private keeptoo.KGradientPanel pnlGioiThieu;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNguoiDung;
    private keeptoo.KGradientPanel pnlNhaCungCap;
    private keeptoo.KGradientPanel pnlNhanVien;
    private keeptoo.KGradientPanel pnlSanPham;
    private javax.swing.JPanel pnlSilde;
    private keeptoo.KGradientPanel pnlTaiKhoan;
    private keeptoo.KGradientPanel pnlThongKe;
    private keeptoo.KGradientPanel pnlTroGiup;
    // End of variables declaration//GEN-END:variables

    class RoundedPanel extends JPanel
    {
        private Color backgroundColor;
        private int cornerRadius = 15;
        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }
        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
            
        }
        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//             
        }
    }
    
}

