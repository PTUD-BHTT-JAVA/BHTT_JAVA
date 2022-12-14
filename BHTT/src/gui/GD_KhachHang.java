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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
public class GD_KhachHang extends javax.swing.JInternalFrame {

    private DefaultTableModel modelKhachHang;
    private DAO_KhachHang kh;
    private DAO_LoaiKhachHang lkh;
    private XSSFRow rowCount;

    /**
     * Creates new form QuanLyHoaDon
     */
    public GD_KhachHang() throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            System.out.println(e);
        }
        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        this.setRootPaneCheckingEnabled(false);
        javax.swing.plaf.InternalFrameUI ui
                = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);
        initComponents();
        this.setFocusable(true);

        lkh = new DAO_LoaiKhachHang();
        ArrayList<LoaiKhachHang> listLKH = lkh.getalltbLoaiKhachHang();
        for (LoaiKhachHang loaiKH : listLKH) {
            cboPhanLoai.addItem(loaiKH.getTenLoai());
        }
        modelKhachHang = (DefaultTableModel) tableKhachHang.getModel();
        modelKhachHang.setRowCount(0);
        DocDuLieuLenTable();
    }

    private void importKhachHang() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "C:\\Users\\Trinh Cui Bap\\Desktop";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Chọn file để import");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILE", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelBIS);
                excelImportToJTable = new XSSFWorkbook(excelFIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    XSSFCell excelTen = excelRow.getCell(0);
                    XSSFCell excelSoDienThoai = excelRow.getCell(1);
                    XSSFCell excelEmail = excelRow.getCell(2);
                    XSSFCell excelGioiTinh = excelRow.getCell(3);
                    if (kh.layKhachHangBangSDT(excelSoDienThoai.toString()) == null) {
                        KhachHang khImport = new KhachHang(maTuSinh(), excelTen.toString(), excelSoDienThoai.toString(), 0, excelEmail.toString(), excelGioiTinh.getBooleanCellValue(), new LoaiKhachHang("LKH002"));
                        kh.themKhachHang(khImport);
                        modelKhachHang.addRow(new Object[]{
                            khImport.getMaKH(), khImport.getTenKH(), khImport.getSoDienThoai(), khImport.getEmail(),
                            khImport.isGioiTinh() == true ? "Nam" : "Nữ", "LKH001".equals(khImport.getLoaiKhachHang().getMaLoaiKH()) ? "VIP" : "Thường", khImport.getDiemTichLuy()
                        });
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại");
                    }
                }

            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private void exportKhachHang() {
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
                        if (cboPhanLoai.getSelectedItem().toString().equals("VIP")) {
                            excelCell.setCellValue(modelKhachHang.getValueAt(i, j).toString());
                        } else {
                            excelCell.setCellValue(modelKhachHang.getValueAt(i, j).toString());
                        }
                    }
                }
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                System.out.println(excelFOU.toString());
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Xuất thành công !!!........");

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
        } else {
            JOptionPane.showMessageDialog(null, "Xuất không thành công");
        }
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

    private void xoaTextField() {
        txtTenKH.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtLoaiKH.setText("");
        txtDiemTichLuy.setText("");
    }

    private boolean kiemTraThongTin() {
        String tenKH = txtTenKH.getText();
        String soDienThoai = txtSDT.getText();
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

    private void timKhachHang() {
        try {
            ArrayList<KhachHang> emps = kh.getDSKHTuongDoi(txtTim.getText());
            XoaHetDuLieuTrenTableModel();
            Object[] row = new Object[7];
            modelKhachHang.setRowCount(0);
            for (int i = 0; i < emps.size(); i++) {
                row[0] = emps.get(i).getMaKH();
                row[1] = emps.get(i).getTenKH();
                row[2] = emps.get(i).getSoDienThoai();
                row[3] = emps.get(i).getEmail();
                row[4] = emps.get(i).isGioiTinh() == true ? "Nam" : "Nữ";
                row[5] = emps.get(i).getLoaiKhachHang().getTenLoai();
                row[6] = emps.get(i).getDiemTichLuy();
                modelKhachHang.addRow(row);
            }
            tableKhachHang.setModel(modelKhachHang);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void xoaTrangTextField() {
        txtTenKH.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiemTichLuy.setText("");
        txtLoaiKH.setText("");
        radNu.setSelected(false);

    }

    public boolean kiemTraForm() {
        String email = txtLoaiKH.getText();
        if (email.equals("") || !email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void XoaHetDuLieuTrenTableModel() {
        DefaultTableModel dm = (DefaultTableModel) tableKhachHang.getModel();
        dm.getDataVector().removeAllElements();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlTren = new javax.swing.JPanel();
        pnlThongTin = new javax.swing.JPanel();
        txtTenKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLoaiKH = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiemTichLuy = new javax.swing.JTextField();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        pnlNut = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        pnlGiua = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTim = new swing.TextFieldAnimation();
        jLabel2 = new javax.swing.JLabel();
        cboPhanLoai = new javax.swing.JComboBox<>();
        btnLamMoi = new swing.Button();
        pnlDuoi = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKhachHang = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFrameIcon(null);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        pnlMain.setPreferredSize(new java.awt.Dimension(3666, 855));
        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));

        pnlTren.setBackground(new java.awt.Color(204, 204, 255));
        pnlTren.setMaximumSize(new java.awt.Dimension(2147483647, 280));
        pnlTren.setMinimumSize(new java.awt.Dimension(100, 300));
        pnlTren.setPreferredSize(new java.awt.Dimension(1324, 500));
        pnlTren.setLayout(new java.awt.BorderLayout());

        pnlThongTin.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102)), "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N
        pnlThongTin.setToolTipText("");
        pnlThongTin.setMaximumSize(new java.awt.Dimension(841, 32767));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(980, 280));

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Tên khách hàng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("Số điện thoại: ");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setText("Phân loại:");

        txtLoaiKH.setEditable(false);
        txtLoaiKH.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setText("Điểm tích lũy:");

        txtDiemTichLuy.setEditable(false);
        txtDiemTichLuy.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        radNam.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(radNam);
        radNam.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        radNam.setText("Nam");

        radNu.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(radNu);
        radNu.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        radNu.setText("Nữ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel1.setText("Giới tính");

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
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(68, 68, 68)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail)
                    .addComponent(txtSDT)
                    .addComponent(txtTenKH)
                    .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(txtLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(58, 58, 58)
                        .addComponent(radNam)
                        .addGap(36, 36, 36)
                        .addComponent(radNu)
                        .addGap(84, 84, 84)))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(radNam)
                                .addComponent(radNu)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addGap(46, 46, 46))
        );

        pnlTren.add(pnlThongTin, java.awt.BorderLayout.WEST);

        pnlNut.setBackground(new java.awt.Color(204, 204, 255));
        pnlNut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlNut.setMaximumSize(new java.awt.Dimension(300, 32767));
        pnlNut.setPreferredSize(new java.awt.Dimension(400, 500));

        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/themkhachhang.png"))); // NOI18N
        btnThemKH.setText("Thêm khách hàng");
        btnThemKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThemKH.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/capnhatKH.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật thông tin");
        btnCapNhat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCapNhat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tay.png"))); // NOI18N
        btnXoaTrang.setText("Xóa trắng");
        btnXoaTrang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoaTrang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excel.png"))); // NOI18N
        btnImport.setText("Nhập danh sách khách hàng");
        btnImport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImport.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-32.png"))); // NOI18N
        btnExport.setText("Xuất khách hàng theo loại");
        btnExport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNutLayout = new javax.swing.GroupLayout(pnlNut);
        pnlNut.setLayout(pnlNutLayout);
        pnlNutLayout.setHorizontalGroup(
            pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(2377, Short.MAX_VALUE))
        );
        pnlNutLayout.setVerticalGroup(
            pnlNutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImport)
                .addGap(18, 18, 18)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pnlTren.add(pnlNut, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlTren);

        pnlGiua.setBackground(new java.awt.Color(204, 204, 255));
        pnlGiua.setMaximumSize(new java.awt.Dimension(32767, 50));
        pnlGiua.setPreferredSize(new java.awt.Dimension(3666, 60));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel13.setText("Tìm kiếm :");

        txtTim.setHintText("Nhập tên để tìm kiếm.");
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setText("Phân loại:");

        cboPhanLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhanLoaiItemStateChanged(evt);
            }
        });

        btnLamMoi.setText("Làm mới dữ liệu");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGiuaLayout = new javax.swing.GroupLayout(pnlGiua);
        pnlGiua.setLayout(pnlGiuaLayout);
        pnlGiuaLayout.setHorizontalGroup(
            pnlGiuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiuaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2502, Short.MAX_VALUE))
        );
        pnlGiuaLayout.setVerticalGroup(
            pnlGiuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiuaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGiuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel2)
                    .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.add(pnlGiua);

        pnlDuoi.setBackground(new java.awt.Color(204, 204, 255));
        pnlDuoi.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        pnlDuoi.setPreferredSize(new java.awt.Dimension(1314, 505));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        tableKhachHang.setAutoCreateRowSorter(true);
        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Giới tính", "Phân loại", "Điểm tích lũy"
            }
        ));
        tableKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKhachHang);

        javax.swing.GroupLayout pnlDuoiLayout = new javax.swing.GroupLayout(pnlDuoi);
        pnlDuoi.setLayout(pnlDuoiLayout);
        pnlDuoiLayout.setHorizontalGroup(
            pnlDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuoiLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2352, Short.MAX_VALUE))
        );
        pnlDuoiLayout.setVerticalGroup(
            pnlDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDuoiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.add(pnlDuoi);

        getContentPane().add(pnlMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        xoaTextField();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        String tenKH = txtTenKH.getText();
        String soDienThoai = txtSDT.getText();
        String email = txtEmail.getText();
        boolean gioiTinh = radNam.isSelected();
        String phanloai = "LKH002";
        int diemTichLuy = 0;
        if (tenKH.length() < 0 || soDienThoai.length() < 0 || email.length() < 0 || radNam.isSelected() == false && !radNu.isSelected()) {
            JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            if (kiemTraThongTin()) {
                if (kh.layKhachHangBangSDT(soDienThoai) == null) {
                    KhachHang khachhang = new KhachHang(maTuSinh(), tenKH, soDienThoai, diemTichLuy, email, gioiTinh, new LoaiKhachHang(phanloai));
                    kh.themKhachHang(khachhang);
                    XoaHetDuLieuTrenTableModel();
                    DocDuLieuLenTable();
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    xoaTrangTextField();
                } else {
                    JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng đã tồn tại");
                }
            }
        }
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        int row = tableKhachHang.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn khách hàng cần cần cập nhật", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật khách hàng này ?", "Cập nhật khách hàng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String tenKH = txtTenKH.getText();
                String soDienThoai = txtSDT.getText();
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
                    modelKhachHang.setValueAt(txtSDT.getText(), row, 2);
                    modelKhachHang.setValueAt(txtEmail.getText(), row, 3);
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void tableKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhachHangMouseClicked
        try {
            int r = tableKhachHang.getSelectedRow();
            if (r >= 0) {
                txtTenKH.setText(tableKhachHang.getValueAt(r, 1).toString());
                txtSDT.setText(tableKhachHang.getValueAt(r, 2).toString());
                txtEmail.setText(tableKhachHang.getValueAt(r, 3).toString());
                txtLoaiKH.setText(tableKhachHang.getValueAt(r, 5).toString());
                txtDiemTichLuy.setText(tableKhachHang.getValueAt(r, 6).toString());
                if (tableKhachHang.getValueAt(r, 4).toString().equals("Nam")) {
                    radNam.setSelected(true);
                } else {
                    radNu.setSelected(true);
                }
            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableKhachHangMouseClicked

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        importKhachHang();
    }//GEN-LAST:event_btnImportActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        timKhachHang();

    }//GEN-LAST:event_txtTimKeyReleased

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        exportKhachHang();
    }//GEN-LAST:event_btnExportActionPerformed

    private void cboPhanLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhanLoaiItemStateChanged
        try {
            txtTim.setText("");
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                String selectItem = cboPhanLoai.getSelectedItem().toString();
                if (selectItem.equals("VIP")) {
                    selectItem = "LKH001";
                } else if (selectItem.equals("Thường")) {
                    selectItem = "LKH002";
                }else {
                    
                }
                List<KhachHang> khCanLoc = kh.getKhachHangTheoTenLoai(selectItem);
                System.out.println(khCanLoc.toString());
                if (!khCanLoc.isEmpty()) {
                     XoaHetDuLieuTrenTableModel();
                    for (KhachHang khachhang : khCanLoc) {
                        modelKhachHang.addRow(new Object[]{
                            khachhang.getMaKH(), khachhang.getTenKH(), khachhang.getSoDienThoai(), khachhang.getEmail(), khachhang.isGioiTinh() == true ? "Nam" : "Nữ",
                            "LKH001".equals(khachhang.getLoaiKhachHang().getMaLoaiKH()) ? "VIP" : "Thường", khachhang.getDiemTichLuy()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy loại khách hàng cần tìm");
                    XoaHetDuLieuTrenTableModel();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cboPhanLoaiItemStateChanged

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        XoaHetDuLieuTrenTableModel();
        DocDuLieuLenTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private swing.Button btnLamMoi;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboPhanLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDuoi;
    private javax.swing.JPanel pnlGiua;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNut;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JPanel pnlTren;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    public javax.swing.JTable tableKhachHang;
    private javax.swing.JTextField txtDiemTichLuy;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLoaiKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private swing.TextFieldAnimation txtTim;
    // End of variables declaration//GEN-END:variables

}
