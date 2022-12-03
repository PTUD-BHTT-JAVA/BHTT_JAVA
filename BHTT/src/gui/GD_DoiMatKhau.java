package gui;


import dao.DAO_TaiKhoan;
import javax.swing.JOptionPane;

public class GD_DoiMatKhau extends javax.swing.JFrame {

    String username;
    DAO_TaiKhoan dTK;

    public GD_DoiMatKhau(String _username) {
        username = _username;
        this.setAlwaysOnTop(true);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        lblTitile = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtPassOld = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNewPass = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAgainPass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlTitle.setLayout(new java.awt.BorderLayout());

        lblTitile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitile.setText("ĐỔI MẬT KHẨU");
        pnlTitle.add(lblTitile, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        txtPassOld.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mật khẩu cũ: ");

        txtNewPass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nhập mật khẩu mới: ");

        txtAgainPass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Nhập lại mật khẩu: ");

        btnThoat.setBackground(new java.awt.Color(255, 102, 102));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnDoiMatKhau.setBackground(new java.awt.Color(51, 204, 0));
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setText("ĐỔI MẬT KHẨU");
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(txtPassOld, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtNewPass))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addComponent(txtAgainPass)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAgainPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        dTK = new DAO_TaiKhoan();
        String passOld = dTK.timTaiKhoan(username).getMatKhau();
        String passNew = txtNewPass.getText();
        if ( !txtPassOld.getText().equals("")) {
            if ( !txtNewPass.getText().equals("")) {
                if ( !txtAgainPass.getText().equals("")) {
                    if (passOld.equals(txtPassOld.getText())) {
                        if (passNew.equals(txtAgainPass.getText())) {
                            dTK.capNhatTaiKhoan(passNew, username);
                            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                            this.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng mật khẩu mới");
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Bạn nhập sai mật khẩu cũ");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập lại mật khẩu mới");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu cũ");
        }

    }//GEN-LAST:event_btnChangePassActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        dTK = new DAO_TaiKhoan();
        String passOld = dTK.timTaiKhoan(username).getMatKhau();
        String passNew = txtNewPass.getText();
        if ( !txtPassOld.getText().equals("")) {
            if ( !txtNewPass.getText().equals("")) {
                if ( !txtAgainPass.getText().equals("")) {
                    if (passOld.equals(txtPassOld.getText())) {
                        if (passNew.equals(txtAgainPass.getText())) {
                            dTK.capNhatTaiKhoan(passNew, username);
                            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                            this.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng mật khẩu mới");
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Bạn nhập sai mật khẩu cũ");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập lại mật khẩu mới");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu cũ");
        }
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnThoatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblTitile;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTextField txtAgainPass;
    private javax.swing.JTextField txtNewPass;
    private javax.swing.JTextField txtPassOld;
    // End of variables declaration//GEN-END:variables
}
