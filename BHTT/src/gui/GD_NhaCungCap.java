/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connectDB.ConnectDB;
import dao.DAO_NhaCungCap;
import entity.NhaCungCap;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;



import java.util.List;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author ACER
 */
public class GD_NhaCungCap extends javax.swing.JInternalFrame {
    private DefaultTableModel modelNhaCungCap;
    private String username;
    private DAO_NhaCungCap nhacc;
    /**
     * Creates new form QuanLyHoaDon
     */
    public GD_NhaCungCap(String _username) {
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
        username=_username;
        modelNhaCungCap = (DefaultTableModel) tableNhaCC.getModel();
        DocDuLieuDatabaseVaoTable();
    }
    private void importFileExcel(){
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable  = null;
        String defaultCurrentDirectoryPath = "C:\\Users\\Trinh Cui Bap\\Desktop";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Chọn file để import");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILE", "xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if(excelChooser == JFileChooser.APPROVE_OPTION){
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelBIS);
                excelImportToJTable = new XSSFWorkbook(excelFIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);
                for(int row = 1;row < excelSheet.getLastRowNum();row++){
                    XSSFRow excelRow = excelSheet.getRow(row);
                    XSSFCell excelTen = excelRow.getCell(0);
                    XSSFCell excelDiaChi = excelRow.getCell(1);
                    XSSFCell excelSoDienThoai = excelRow.getCell(2);
                    XSSFCell excelEmail =  excelRow.getCell(3);
                    NhaCungCap nccImport = new NhaCungCap(maTuSinh(),excelTen.toString(),excelDiaChi.toString(),excelSoDienThoai.toString(),excelEmail.toString());
                    nhacc.themNhaCungCap(nccImport);
                    modelNhaCungCap.addRow(new Object[]{
                        nccImport.getMaNCC(),nccImport.getTenNCC(),nccImport.getDiaChi(),nccImport.getSoDienThoai(),nccImport.getEmail()
                    });
                    
                }
                JOptionPane.showMessageDialog(null,"Import thành công");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null,iOException.getMessage());
            }finally{
                try {
                    if(excelFIS != null){
                        excelFIS.close();
                    }
                    if(excelBIS  != null){
                        excelBIS.close();
                    }
                    if(excelImportToJTable != null){
                        excelImportToJTable.close();
                    }
                }catch (IOException ex) {
//                    Logger.getLogger(GD_NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private String maTuSinh(){
        String ma="NCC";
        int tachMa;
        int i=0,j=1;
        int[] dem=new int[999];
        String id;
        for (NhaCungCap ncc : nhacc.getalltbNhaCungCap()) {
            id = ncc.getMaNCC();
            tachMa=Integer.parseInt(id.substring(3, 6));
            dem[i] =tachMa;
            i++;
        }
        i=0;
        while (j<999){
            
            if(dem[i]<j){
                if (j <= 9) {
                    ma +=  "00" + (j);
                } else {
                    ma += "0" + (j);
                }
                break;
            } else if(dem[i]>j){
                j=dem[i];
            }else{
                i++;
                j++;
            }
            
        }    
        return ma;
    }
    private void xoaTextField(){
       txtTenNCC.setText("");
       txtSoDienThoai.setText("");
       txtDiaChi.setText("");
       txtEmail.setText("");
    }
    public void timKiemNhaCC(String ten){
        modelNhaCungCap = (DefaultTableModel) tableNhaCC.getModel();
        TableRowSorter<DefaultTableModel> trs =  new TableRowSorter<>(modelNhaCungCap);
        tableNhaCC.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(ten));
    }
    public boolean kiemTraForm(){
        String email = txtEmail.getText();
        if(email.equals("") || !email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng","Cảnh báo",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    private void DocDuLieuDatabaseVaoTable() {
        nhacc = new DAO_NhaCungCap();
        List<NhaCungCap> list = nhacc.getalltbNhaCungCap();
        for (NhaCungCap ncc : list) {
            modelNhaCungCap.addRow(new Object[]{
                ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai(), ncc.getEmail()
            });
        }
    }
    
//    Import file excel
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlTren = new javax.swing.JPanel();
        pnlThongTin = new javax.swing.JPanel();
        txtTenNCC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlNut = new javax.swing.JPanel();
        btnThemNCC = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        pnlGiua = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTim = new swing.TextFieldAnimation();
        pnlDuoi = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNhaCC = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));

        pnlTren.setMaximumSize(new java.awt.Dimension(2147483647, 280));
        pnlTren.setMinimumSize(new java.awt.Dimension(100, 300));
        pnlTren.setPreferredSize(new java.awt.Dimension(1324, 290));
        pnlTren.setLayout(new java.awt.BorderLayout());

        pnlThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102)), "Thông tin nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N
        pnlThongTin.setToolTipText("");
        pnlThongTin.setMaximumSize(new java.awt.Dimension(841, 32767));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(980, 280));

        txtTenNCC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Tên nhà cung cấp");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("Địa chỉ ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("Số điện thoại :");

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setText("Email");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(68, 68, 68)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                    .addComponent(txtSoDienThoai)
                    .addComponent(txtDiaChi)
                    .addComponent(txtTenNCC))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pnlTren.add(pnlThongTin, java.awt.BorderLayout.WEST);

        pnlNut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlNut.setMaximumSize(new java.awt.Dimension(300, 32767));
        pnlNut.setPreferredSize(new java.awt.Dimension(300, 300));

        btnThemNCC.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnThemNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/themNCC.png"))); // NOI18N
        btnThemNCC.setText("Thêm nhà cung cấp");
        btnThemNCC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThemNCC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });

        btnSuaNCC.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnSuaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/capnhatKH.png"))); // NOI18N
        btnSuaNCC.setText("Cập nhật thông tin");
        btnSuaNCC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSuaNCC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu thông tin");
        btnLuu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLuu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excel.png"))); // NOI18N
        btnImport.setText("Import (Excel)");
        btnImport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImport.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNutLayout = new javax.swing.GroupLayout(pnlNut);
        pnlNut.setLayout(pnlNutLayout);
        pnlNutLayout.setHorizontalGroup(
            pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNutLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        pnlNutLayout.setVerticalGroup(
            pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );

        pnlTren.add(pnlNut, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlTren);

        pnlGiua.setMaximumSize(new java.awt.Dimension(32767, 50));
        pnlGiua.setPreferredSize(new java.awt.Dimension(3666, 60));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel13.setText("Tìm kiếm :");

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlGiuaLayout = new javax.swing.GroupLayout(pnlGiua);
        pnlGiua.setLayout(pnlGiuaLayout);
        pnlGiuaLayout.setHorizontalGroup(
            pnlGiuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiuaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(884, Short.MAX_VALUE))
        );
        pnlGiuaLayout.setVerticalGroup(
            pnlGiuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiuaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlGiuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.add(pnlGiua);

        pnlDuoi.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        pnlDuoi.setPreferredSize(new java.awt.Dimension(1314, 505));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        tableNhaCC.setAutoCreateRowSorter(true);
        tableNhaCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Email"
            }
        ));
        tableNhaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhaCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNhaCC);

        javax.swing.GroupLayout pnlDuoiLayout = new javax.swing.GroupLayout(pnlDuoi);
        pnlDuoi.setLayout(pnlDuoiLayout);
        pnlDuoiLayout.setHorizontalGroup(
            pnlDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1314, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlDuoiLayout.setVerticalGroup(
            pnlDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlMain.add(pnlDuoi);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
       String tenNCC = txtTenNCC.getText();
        String diaChi = txtDiaChi.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String email = txtEmail.getText();
        if (tenNCC.equals("") || diaChi.equals("")|| soDienThoai.equals("") || email.equals("")) {
            JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        } else {
            if(kiemTraForm()){
                NhaCungCap ncc = new NhaCungCap(maTuSinh(), tenNCC, diaChi, soDienThoai, email);
                nhacc.themNhaCungCap(ncc);
                modelNhaCungCap.addRow(new Object[]{
                    ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai(), ncc.getEmail()
                });
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                xoaTextField();
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        xoaTextField();
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật khách hàng này ?", "Cập nhật khách hàng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int row = tableNhaCC.getSelectedRow();
            String tenNCC = txtTenNCC.getText();
            String diaChi = txtDiaChi.getText();
            String soDienThoai = txtSoDienThoai.getText();
            String email = txtEmail.getText();
            if (!kiemTraForm()) {
                JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
            } else {
                NhaCungCap nccNew = new NhaCungCap((String) modelNhaCungCap.getValueAt(row, 0), tenNCC, diaChi, soDienThoai, email);
                nhacc.capNhaCungCap(nccNew);
                modelNhaCungCap.setValueAt(txtTenNCC.getText(), row, 1);
                modelNhaCungCap.setValueAt(txtDiaChi.getText(), row, 2);
                modelNhaCungCap.setValueAt(txtSoDienThoai.getText(), row, 3);
                modelNhaCungCap.setValueAt(txtEmail.getText(), row, 4);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            }
        }

    }//GEN-LAST:event_btnSuaNCCActionPerformed

    private void tableNhaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhaCCMouseClicked
      int r = tableNhaCC.getSelectedRow();
      txtTenNCC.setText(modelNhaCungCap.getValueAt(r, 1).toString());
      txtDiaChi.setText(modelNhaCungCap.getValueAt(r, 2).toString());
      txtSoDienThoai.setText(modelNhaCungCap.getValueAt(r, 3).toString());
      txtEmail.setText(modelNhaCungCap.getValueAt(r, 4).toString());
    }//GEN-LAST:event_tableNhaCCMouseClicked

    
    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
       importFileExcel();
    }//GEN-LAST:event_btnImportActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
       String search = txtTim.getText();
       timKiemNhaCC(search);
    }//GEN-LAST:event_txtTimKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDuoi;
    private javax.swing.JPanel pnlGiua;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNut;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JPanel pnlTren;
    private javax.swing.JTable tableNhaCC;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNCC;
    private swing.TextFieldAnimation txtTim;
    // End of variables declaration//GEN-END:variables
}
