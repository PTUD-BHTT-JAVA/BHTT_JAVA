package gui;

import connectDB.ConnectDB;
import dao.DAO_KhachHang;
import dao.DAO_LoaiKhachHang;

import entity.KhachHang;
import entity.LoaiKhachHang;

import java.awt.event.ItemEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
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


public class GD_KhachHang extends javax.swing.JInternalFrame {

    private DefaultTableModel modelKhachHang;
    private String username;
    private DAO_KhachHang kh;
    private final DAO_LoaiKhachHang lkh;
    private XSSFRow rowCount;

    /**
     * Creates new form QuanLyHoaDon
     */
    public GD_KhachHang(String _username) {
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setRootPaneCheckingEnabled(false);
        javax.swing.plaf.InternalFrameUI ui = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);
        initComponents();
        this.setFocusable(true);
        username = _username;
        lkh = new DAO_LoaiKhachHang();
        ArrayList<LoaiKhachHang> listLKH = lkh.getalltbLoaiKhachHang();
        for (LoaiKhachHang loaiKH : listLKH) {
            cboLoaiKH.addItem(loaiKH.getMaLoaiKH());
        }
        modelKhachHang = (DefaultTableModel) tableKhachHang.getModel();
        modelKhachHang.setRowCount(0);
        DocDuLieuLenTable();
    }

    private String maTuSinh() {
        String ma = "KH";
        int tachMa;
        int i = 0, j = 1;
        int[] dem = new int[999];
        String id;
        for (KhachHang kh : kh.getalltbKhachHang()) {
            id = kh.getMaKH();
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

    private void XoaHetDuLieuTrenTableModel() {
        DefaultTableModel dm = (DefaultTableModel) tableKhachHang.getModel();
        dm.getDataVector().removeAllElements();
    }

    private void xoaTrangTextField() {
        txtTenKH.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtDiemTichLuy.setText("");
        txtPhanLoai.setText("");
        radNu.setSelected(false);

    }

    private void DocDuLieuLenTable() {
        kh = new DAO_KhachHang();
        List<KhachHang> list = kh.getalltbKhachHang();
        for (KhachHang khDoc : list) {
            modelKhachHang.addRow(new Object[]{
                khDoc.getMaKH(), khDoc.getTenKH(), khDoc.getSoDienThoai(), khDoc.getEmail(), khDoc.isGioiTinh() == true ? "Nam" : "Nữ", !"LKH002".equals(khDoc.getLoaiKhachHang().getMaLoaiKH()) ? "VIP" : "Thường", khDoc.getDiemTichLuy()
            });
        }
    }
    
    private void timKiemKhachHang(String ten){
        modelKhachHang = (DefaultTableModel) tableKhachHang.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelKhachHang);
        tableKhachHang.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)"+ten));
    }
    
    private boolean kiemTraThongTin() {
        String tenKH = txtTenKH.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String email = txtEmail.getText();
        String firstLetter = "[A-EGHIK-VXYÂĐỔÔÚỨ]";
        String otherLetters = "[a-eghik-vxyàáâãèéêìíòóôõùúýỳỹỷỵựửữừứưụủũợởỡờớơộổỗồốọỏịỉĩệểễềếẹẻẽặẳẵằắăậẩẫầấạảđ₫]";
        String regexString = "^"
                + firstLetter + otherLetters + "+\\s"
                + "(" + firstLetter + otherLetters + "+\\s)*"
                + firstLetter + otherLetters + "+$";
        if (!tenKH.matches(regexString)) {
            JOptionPane.showMessageDialog(null, "Tên không chứa ký tự đặt biệt", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!soDienThoai.matches("^0{1}[1-9]{9}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại chỉ chứa 10 ký số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;

    }
    
    private void importKhachHang(){
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
                for(int row = 1;row <= excelSheet.getLastRowNum();row++){
                    XSSFRow excelRow = excelSheet.getRow(row);
                    XSSFCell excelTen = excelRow.getCell(0);
                    XSSFCell excelSoDienThoai = excelRow.getCell(1);
                    XSSFCell excelEmail = excelRow.getCell(2);
                    XSSFCell excelGioiTinh =  excelRow.getCell(3);
                    KhachHang khImport = new KhachHang(maTuSinh(), excelTen.toString(), excelSoDienThoai.toString(), 0, excelEmail.toString(),excelGioiTinh.getBooleanCellValue(),new LoaiKhachHang("LKH002"));
                    kh.themKhachHang(khImport);
                    modelKhachHang.addRow(new Object[]{
                       khImport.getMaKH(),khImport.getTenKH(),khImport.getSoDienThoai(),khImport.getEmail(),
                       khImport.isGioiTinh()==true ? "Nam" : "Nữ",khImport.getLoaiKhachHang().getMaLoaiKH(),khImport.getDiemTichLuy()
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
                    System.out.println(ex);
                }
            }
        }
    }

    private void exportKhachHang(){
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;
       
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\Trinh Cui Bap\\Desktop");
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                //Import excel poi libraries to netbeans
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");
                rowCount = excelSheet.createRow(0);

                for (int i = 0; i < modelKhachHang.getColumnCount(); i++) {
                    XSSFCell cell = rowCount.createCell(i);
                    cell.setCellValue(modelKhachHang.getColumnName(i));
                }
                for (int i = 0; i < modelKhachHang.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i + 1);
                    for (int j = 0; j < modelKhachHang.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(modelKhachHang.getValueAt(i, j).toString());
                    }
                }
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Export thành công !!!........");
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            } finally {
                try {
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelFOU != null) {
                        excelFOU.close();
                    }
                    if (excelJTableExporter != null) {
                        excelJTableExporter.close();
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Expor không thành công");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlTop = new javax.swing.JPanel();
        pnlThongTin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhanLoai = new javax.swing.JTextField();
        txtDiemTichLuy = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        pnlButton = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnGuiMail = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKhachHang = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cboLoaiKH = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTim = new swing.TextFieldAnimation();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);

        pnlThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tên khách hàng");

        jLabel2.setFont(new java.awt.Font("Zilla Slab Medium", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Phân loại");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Điểm tích lũy");

        txtPhanLoai.setEditable(false);

        txtDiemTichLuy.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Giới tính");

        buttonGroup1.add(radNam);
        radNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radNam.setText("Nam");

        buttonGroup1.add(radNu);
        radNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radNu.setText("Nữ");

        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/themkhachhang.png"))); // NOI18N
        btnThemKH.setText("Thêm khách hàng");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/capnhatKH.png"))); // NOI18N
        btnCapNhat.setText("Cật nhập thông tin");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnGuiMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/email.png"))); // NOI18N
        btnGuiMail.setText("   Gửi mail");
        btnGuiMail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuiMail.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuiMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMailActionPerformed(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excel.png"))); // NOI18N
        btnImport.setText("Import khách hàng");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-32.png"))); // NOI18N
        btnExport.setText("Export khách hàng");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnXoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tay.png"))); // NOI18N
        btnXoaTrang.setText("    Xóa trắng");
        btnXoaTrang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonLayout.createSequentialGroup()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlButtonLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlButtonLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuiMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(55, 55, 55))
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                                        .addComponent(txtPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel7)
                                        .addGap(39, 39, 39)
                                        .addComponent(radNu)
                                        .addGap(18, 18, 18)
                                        .addComponent(radNam)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7))
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radNu)
                                .addComponent(radNam)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(pnlTop, java.awt.BorderLayout.PAGE_START);

        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên khách hàng", "Số điện thoại", "Email", "Giới tính", "Phân loại", "Điểm tích lũy"
            }
        ));
        tableKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKhachHang);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Phân loại");

        cboLoaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboLoaiKH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiKHItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Tìm kiếm");

        txtTim.setHintText("Nhập tên để tìm kiếm");
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addGap(52, 52, 52)
                .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(267, 267, 267)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
       String tenKH = txtTenKH.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String email = txtEmail.getText();
        boolean gioiTinh = radNam.isSelected();
        String phanloai = "LKH002";
        int diemTichLuy = 0;
        if (tenKH.length() < 0 || soDienThoai.length() < 0 || email.length() < 0 || radNam.isSelected() == false && !radNu.isSelected()) {
            JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            if (kiemTraThongTin()) {
                KhachHang khachhang = new KhachHang(maTuSinh(), tenKH, soDienThoai, diemTichLuy, email, gioiTinh, new LoaiKhachHang(phanloai));
                kh.themKhachHang(khachhang);
                modelKhachHang.addRow(new Object[]{
                    khachhang.getMaKH(), khachhang.getTenKH(), khachhang.getSoDienThoai(), khachhang.getEmail(), khachhang.isGioiTinh() == true ? "Nam" : "Nữ",
                    khachhang.getLoaiKhachHang().getMaLoaiKH(), khachhang.getDiemTichLuy()
                });
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                xoaTrangTextField();
            }
        }
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        xoaTrangTextField();  
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tableKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhachHangMouseClicked
        try {
            int r = tableKhachHang.getSelectedRow();
            txtTenKH.setText(modelKhachHang.getValueAt(r, 1).toString());
            txtSoDienThoai.setText(modelKhachHang.getValueAt(r, 2).toString());
            txtEmail.setText(modelKhachHang.getValueAt(r, 3).toString());
            txtPhanLoai.setText(modelKhachHang.getValueAt(r, 5).toString());
            txtDiemTichLuy.setText(modelKhachHang.getValueAt(r, 6).toString());
            if (modelKhachHang.getValueAt(r, 4).toString().equals("Nam")) {
                radNam.setSelected(true);
            } else {
                radNu.setSelected(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableKhachHangMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed

        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật khách hàng này ?", "Cập nhật khách hàng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int row = tableKhachHang.getSelectedRow();
            String tenKH = txtTenKH.getText();
            String soDienThoai = txtSoDienThoai.getText();
            String email = txtEmail.getText();
            boolean gioiTinh = radNam.isSelected();
            String phanloai = "LKH002";
            int diemTichLuy = 0;
            if (!kiemTraThongTin()) {
                JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
            } else {
                KhachHang khNew = new KhachHang(modelKhachHang.getValueAt(row, 0).toString(), tenKH, soDienThoai, diemTichLuy, email, gioiTinh, new LoaiKhachHang(phanloai));
                kh.capNhatKhachHang(khNew);
                modelKhachHang.setValueAt(txtTenKH.getText(), row, 1);
                modelKhachHang.setValueAt(txtSoDienThoai.getText(), row, 2);
                modelKhachHang.setValueAt(txtEmail.getText(), row, 3);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            }
        }


    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void cboLoaiKHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiKHItemStateChanged
        try {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selectItem = cboLoaiKH.getSelectedItem().toString();
            List<KhachHang> khCanLoc = kh.getKhachHangTheoMaLoai(selectItem);
            if (!khCanLoc.isEmpty()) {
                XoaHetDuLieuTrenTableModel();
                for (KhachHang khachhang : khCanLoc) {
                    modelKhachHang.addRow(new Object[]{
                        khachhang.getMaKH(), khachhang.getTenKH(), khachhang.getSoDienThoai(), khachhang.getEmail(), khachhang.isGioiTinh() == true ? "Nam" : "Nữ",
                        "LKH001".equals(khachhang.getLoaiKhachHang().getMaLoaiKH()) ? "VIP" : "Thường", khachhang.getDiemTichLuy()
                    });
                }      
            } else {
                XoaHetDuLieuTrenTableModel();
                DocDuLieuLenTable();
                return;
            }
        }
        } catch (Exception e) {
           
        }
    }//GEN-LAST:event_cboLoaiKHItemStateChanged

    private void btnGuiMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMailActionPerformed
        
    }//GEN-LAST:event_btnGuiMailActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        String search = txtTim.getText();
        timKiemKhachHang(search);
    }//GEN-LAST:event_txtTimKeyReleased

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
      importKhachHang();
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
       exportKhachHang();
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
      xoaTrangTextField();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnGuiMail;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoaiKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JTable tableKhachHang;
    private javax.swing.JTextField txtDiemTichLuy;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPhanLoai;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKH;
    private swing.TextFieldAnimation txtTim;
    // End of variables declaration//GEN-END:variables
}
