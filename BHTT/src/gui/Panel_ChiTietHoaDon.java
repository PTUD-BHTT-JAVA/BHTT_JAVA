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
import entity.ChatLieu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KichThuoc;
import entity.LoaiSanPham;
import entity.MauSac;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

import java.awt.Component;
import java.awt.HeadlessException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JLabel;

/**
 *
 * @author Anh Thu
 */
public class Panel_ChiTietHoaDon extends javax.swing.JPanel {

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
    private final DefaultTableModel modolSP;
    List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
    private TableRowSorter<DefaultTableModel> tr;
    public DefaultTableModel modelDonHang;
    DecimalFormat df = new DecimalFormat("#,##0 VND");
    int soLuongTon;
    private ArrayList<SanPham> dstt = null;
    private ArrayList<SanPham> ListSP;
    private double tongThanhTien;
    private double tienThoi;
    private DAO_KhachHang kh;
    private DAO_LoaiKhachHang lkh;
    private DAO_NhanVien nv;
    private DAO_ChiTietHoaDon cthd_dao;
    public TaiKhoan tkDN;
    private DAO_HoaDon hd_dao;
    private ArrayList<SanPham> dsSPTrongDonHang;
    private String txtSDT, txtTenNV;
    private JLabel lblHT;
    private final double tienDeTangDTL = 20000;
    private final int diemThanhVIP = 1000; 
    private ArrayList<SanPham> ListTimSP;

    public Panel_ChiTietHoaDon(String sdt, String tenNV) {

        txtSDT = sdt;
        txtTenNV = tenNV;
        initComponents();
        pnlTB.setVisible(false);
        this.setFocusable(true);

        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
        }

        modolSP = (DefaultTableModel) jtbSanPham.getModel();
        modelDonHang = (DefaultTableModel) tableDonHang.getModel();
//        DocDuLieuLenTable();
        DocDuLieuVaoCombobox();
//        moKhoaTextfields(false);
        dstt = new ArrayList<SanPham>();
        ListSP = new ArrayList<SanPham>();
        
        tr = new TableRowSorter<DefaultTableModel>(modolSP);
        hd_dao = new DAO_HoaDon();
        cthd_dao = new DAO_ChiTietHoaDon();
        kh = new DAO_KhachHang();
        nv = new DAO_NhanVien();
        jtbSanPham.setRowSorter(tr);
    }

    private void DocDuLieuVaoCombobox() {
        lsp_dao = new DAO_LoaiSP();
        ArrayList<LoaiSanPham> listLSP = lsp_dao.getAllLSP();
        for (LoaiSanPham lsp : listLSP) {
            cbxPL.addItem(lsp.getTenLoaiSP());

        }
        mau_dao = new DAO_MauSac();
        ArrayList<MauSac> listMau = mau_dao.getAllMau();
        for (MauSac m : listMau) {
            cbxMS.addItem(m.getTenMau());
        }
        cl_dao = new DAO_ChatLieu();
        ArrayList<ChatLieu> listCL = cl_dao.getAllCL();
        for (ChatLieu cl : listCL) {
            cbxCL.addItem(cl.getTenChatLieu());
        }
        kt_dao = new DAO_KichThuoc();
        ArrayList<KichThuoc> listKT = kt_dao.getAllKT();
        for (KichThuoc kt : listKT) {
            cbxKT.addItem(kt.getTenKichThuoc());
        }
    }

    private void DocDuLieuLenTable() {
        sp_dao = new DAO_SanPham();
        ListSP = sp_dao.getAllSP();
        for (SanPham sp : ListSP) {
            modolSP.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), df.format(sp.getGiaGoc()),
                sp.getLoaiSanPham().getTenLoaiSP(),
                sp.getMauSac().getTenMau(), sp.getChatLieu().getTenChatLieu(), sp.getKichThuoc().getTenKichThuoc(),});
        }

    }

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

    public double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (NumberFormatException e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }

    private void XoaHetDLTrenTbale(JTable table) {
        DefaultTableModel fm = (DefaultTableModel) table.getModel();
        fm.getDataVector().removeAllElements();
    }

    public void tongTien() {
        tongThanhTien = 0;
        if ("LKH001".equals(kh.layKhachHangBangSDT(txtSDT).getLoaiKhachHang().getMaLoaiKH())) {
            for (SanPham s : dstt) {
                tongThanhTien += (s.getSoLuong() * s.getGiaGoc());
            }
            tongThanhTien = tongThanhTien - (tongThanhTien * 10 / 100) + tongThanhTien * 0.05;
            txtTongTien.setText(df.format(tongThanhTien));
        } else {
            for (SanPham s : dstt) {
                tongThanhTien += (s.getSoLuong() * s.getGiaGoc());
            }
            tongThanhTien += tongThanhTien * 0.05;
            txtTongTien.setText(df.format(tongThanhTien));
        }

    }

    public int vitriSP(SanPham sp) {
        int i = -1;
        if (dstt.contains(sp)) {
            return dstt.indexOf(sp);
        }
        return i;
    }

    public void thanhToan(String ma) {
        if (!dstt.isEmpty()) {
            KhachHang khTT = kh.layKhachHangBangSDT(txtSDT);
            NhanVien nvTT = nv.layNhanVienBangTen(txtTenNV);
            String diaChi = "12 Nguyễn Văn Bảo,phường 4,Gò Vấp,Hồ Chí Minh";
            Date ngayLapHD = new Date();
            HoaDon hd = new HoaDon(ma, ngayLapHD, Double.parseDouble(txtTienKhachDua.getText()),
                    diaChi, new NhanVien(nvTT.getMaNV()), new KhachHang(khTT.getMaKH()));
            hd_dao.themHoaDon(hd);
            HoaDon hoaDonMoiThem = hd_dao.getHoaDonMoiNhat();
            for (SanPham sp : dstt) {
                try {
                    cthd_dao = new DAO_ChiTietHoaDon();
                    ChiTietHoaDon cthd = new ChiTietHoaDon(sp.getSoLuong(), 0.1, sp.getGiaGoc() * sp.getSoLuong(), tienThoi, hoaDonMoiThem, sp);
                    cthd_dao.themCTHD(cthd);
                } catch (Exception e) {
                    return;
                }
            }
            for (SanPham sp : dstt) {
                SanPham spCu = null;
                try {
                    spCu = sp_dao.laySanPhamBangMa(sp.getMaSP());
                } catch (Exception e) {
                    System.out.println("loi sql");
                    e.printStackTrace();
                }
                if (spCu != null) {
                    sp_dao.capNhatSoLuong(spCu.getMaSP(), spCu.getSoLuong() - sp.getSoLuong());
                }
            }
            if (khTT.getLoaiKhachHang().getMaLoaiKH().equals("LKH001")) {
                tongThanhTien = 0;
                for (SanPham sp : dstt) {
                    tongThanhTien += sp.getSoLuong() * sp.getGiaGoc();
                }
                tongThanhTien *= 0.1;
                txtTongTien.setText(df.format(tongThanhTien));
//            Thư khóa
                XoaHetDLTrenTbale(tableDonHang);
                XoaHetDLTrenTbale(jtbSanPham);
                DocDuLieuLenTable();
            } else {
                tongTien();
//            Thư khóa
                XoaHetDLTrenTbale(tableDonHang);
                XoaHetDLTrenTbale(jtbSanPham);
                DocDuLieuLenTable();
            }
            double tongTienThanhToan = 0;
            if (tongThanhTien >= tienDeTangDTL) {
                 for (SanPham sp : dstt) {
                    tongTienThanhToan += sp.getSoLuong() * sp.getGiaGoc();
                }
                kh_dao = new DAO_KhachHang();
                int diemTichLuy = (int) (tongTienThanhToan / tienDeTangDTL);
                khTT.setDiemTichLuy(khTT.getDiemTichLuy() + diemTichLuy);
                kh_dao.capNhatKhachHang(khTT);
                System.out.println(khTT.getMaKH());
            }
            if(khTT.getDiemTichLuy() >= diemThanhVIP){
                khTT.getLoaiKhachHang().setMaLoaiKH("LKH001");
                kh_dao = new DAO_KhachHang();
                kh_dao.capNhatKhachHang(khTT);
            }
        }
    }

    public void themSPVaoDonHang() {
        int r = jtbSanPham.getSelectedRow();
        if (r != -1) {
            soLuongTon = Integer.parseInt(modolSP.getValueAt(r, 2).toString());
            String maSP = (modolSP.getValueAt(r, 0).toString());
            SanPham sp = sp_dao.laySanPhamBangMa(maSP);
            int soluong = 0;
            try {
                soluong = (int) jspSoLuong.getValue();
                sp.getGiaGoc();
            } catch (Exception e) {

            }
            if (soluong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return;
            }
            sp.setSoLuong(soluong);
            int vitri = vitriSP(sp);
            double tien = 0;
            if (soLuongTon < sp.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số Lượng Phải Nhỏ Hơn Hoặc Bằng Số Lượng Tồn");
                return;
            }
            if (vitri > -1) {
                Integer sl = dstt.get(vitri).getSoLuong() + sp.getSoLuong();
                dstt.get(vitri).setSoLuong(sl);
                tien = dstt.get(vitri).getSoLuong() * sp.getGiaGoc();
                modelDonHang.setValueAt(sl, vitri, 2);
                modelDonHang.setValueAt(df.format(tien), vitri, 4);
            } else {
                try {
                    dstt.add(sp);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                modelDonHang = (DefaultTableModel) tableDonHang.getModel();
                tien = sp.getSoLuong() * sp.getGiaGoc();
                modelDonHang.addRow(new Object[]{
                    sp.getMaSP(), sp.getTenSP(),
                    sp.getSoLuong(),
                    df.format(sp.getGiaGoc()), df.format(tien)
                });
            }
            jspSoLuong.setValue(0);
            soLuongTon -= sp.getSoLuong();
            modolSP.setValueAt(soLuongTon, r, 2);

            tongTien();

        } else {
            JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Sản Phẩm Trước Khi Mua");
        }
    }

    public void xoaSPTrongDH(int r) {
        String maSP = dstt.get(r).getMaSP();
        int index = -1;
        for (SanPham s : ListSP) {
            if (s.getMaSP().endsWith(maSP)) {
                index = ListSP.indexOf(s);
                break;
            }
        }
         int soLuong = sp_dao.laySanPhamBangMa(maSP).getSoLuong();
        if (ListTimSP != null && ListTimSP.size() > 0) {
          
            System.out.println(ListTimSP);
            for (SanPham s : ListTimSP) {
                if (s.getMaSP().endsWith(maSP)) {
                    index = ListTimSP.indexOf(s);
                    System.out.println(index);
                    break;
                }
            }
            for (SanPham sp : ListTimSP) {
                if (sp.getMaSP().equalsIgnoreCase(maSP)) {
                    System.out.println(sp.getMaSP());
                    modolSP.setValueAt(soLuong, index, 2);
                    break;
                }
            }
        } else {
            ListSP = sp_dao.getAllSP();
            modolSP.setValueAt(soLuong, index, 2);
        }
        System.out.println(index);
        // xóa trên table model
        modelDonHang.removeRow(r);
        // xóa trên ArrayList
        dstt.remove(r);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlTKnSL = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbxPL = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbxKT = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbxCL = new javax.swing.JComboBox<>();
        btnThemSP = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbxMS = new javax.swing.JComboBox<>();
        jspSoLuong = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        txtTim = new swing.TextFieldAnimation();
        btnTimSP = new swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSanPham = new javax.swing.JTable();
        pnlLast = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDonHang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblKhuyenMai = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnXoaCTHD = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTienKhachDua = new swing.TextField();
        lblTienThoi = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlTB = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setLayout(new javax.swing.OverlayLayout(this));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

        pnlCenter.setMaximumSize(new java.awt.Dimension(2147483647, 450));
        pnlCenter.setPreferredSize(new java.awt.Dimension(780, 370));
        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlTKnSL.setBackground(new java.awt.Color(204, 204, 255));
        pnlTKnSL.setMaximumSize(new java.awt.Dimension(32767, 100));
        pnlTKnSL.setPreferredSize(new java.awt.Dimension(780, 100));
        pnlTKnSL.setRequestFocusEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setText("Phân loại :");

        cbxPL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxPL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setText("Kích thước :");

        cbxKT.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxKT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel18.setText("Chất liệu :");

        cbxCL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        btnThemSP.setBackground(new java.awt.Color(0, 153, 51));
        btnThemSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSP.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add-product.png"))); // NOI18N
        btnThemSP.setText("Thêm sản phẩm vào đơn hàng");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel19.setText("Màu sắc :");

        cbxMS.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jspSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("Nhập số lượng:");

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        btnTimSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTimSP.setColor1(new java.awt.Color(102, 102, 255));
        btnTimSP.setColor2(new java.awt.Color(102, 0, 255));
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTKnSLLayout = new javax.swing.GroupLayout(pnlTKnSL);
        pnlTKnSL.setLayout(pnlTKnSLLayout);
        pnlTKnSLLayout.setHorizontalGroup(
            pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTKnSLLayout.createSequentialGroup()
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxKT, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTKnSLLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTKnSLLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(20, 20, 20)))
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxPL, 0, 175, Short.MAX_VALUE)
                    .addComponent(cbxCL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMS, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemSP))
                .addContainerGap(470, Short.MAX_VALUE))
        );
        pnlTKnSLLayout.setVerticalGroup(
            pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTKnSLLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cbxPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTKnSLLayout.createSequentialGroup()
                        .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTKnSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jspSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCenter.add(pnlTKnSL, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 300));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(16, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        jtbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Giá", "Phân loại", "Màu sắc", "Chất liệu", "Kích thước"
            }
        ));
        jScrollPane1.setViewportView(jtbSanPham);

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(pnlCenter);

        pnlLast.setMaximumSize(new java.awt.Dimension(65534, 500));
        pnlLast.setPreferredSize(new java.awt.Dimension(1122, 400));
        pnlLast.setLayout(new javax.swing.BoxLayout(pnlLast, javax.swing.BoxLayout.Y_AXIS));

        pnlDonHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(800, 350));
        pnlDonHang.setLayout(new java.awt.GridLayout(1, 3));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ĐƠN HÀNG");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlDonHang.add(jLabel5);

        pnlLast.add(pnlDonHang);

        pnlForm.setLayout(new javax.swing.OverlayLayout(pnlForm));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        tableDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tableDonHang);

        jPanel2.add(jScrollPane2);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("TỔNG CỘNG:");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(255, 51, 51));
        txtTongTien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTongTien.setText("0");
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 204, 102)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("VAT:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("5 %");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Khuyễn mãi");

        lblKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblKhuyenMai.setForeground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lblKhuyenMai)
                .addGap(47, 47, 47)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(822, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTien)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(lblKhuyenMai))
                .addContainerGap())
        );

        jPanel2.add(jPanel5);

        jPanel1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setMaximumSize(new java.awt.Dimension(400, 32767));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 300));

        btnThanhToan.setBackground(new java.awt.Color(204, 0, 204));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnXoaCTHD.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnXoaCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xoa_sanpham.png"))); // NOI18N
        btnXoaCTHD.setText("Xóa sản phẩm");
        btnXoaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTHDActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/capnhatKH.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật số lượng");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel4.setText("Tiền thồi:");

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTienKhachDua.setHintText("Tiền khách đưa");
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        lblTienThoi.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblTienThoi.setForeground(new java.awt.Color(255, 51, 51));
        lblTienThoi.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhat))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblTienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        pnlForm.add(jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1700, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        pnlForm.add(jPanel4);

        pnlLast.add(pnlForm);

        jPanel6.add(pnlLast);

        add(jPanel6);

        pnlTB.setMaximumSize(new java.awt.Dimension(999999, 99999999));
        pnlTB.setMinimumSize(new java.awt.Dimension(600, 600));
        pnlTB.setPreferredSize(new java.awt.Dimension(1700, 900));
        pnlTB.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐƠN HÀNG ĐÃ HOÀN THÀNH");
        pnlTB.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(pnlTB);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        themSPVaoDonHang();
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        if (txtTienKhachDua.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Cần nhập tiền khách đưa", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else if (txtSDT.equals("")) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            if (tienThoi < 0) {
                JOptionPane.showMessageDialog(null, "Số tiền không đủ");
            } else {
                String ma = maTuSinh();
                try {
                    thanhToan(ma);
                } catch (Exception ex) {
                    Logger.getLogger(GD_TaoDonHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                tienThoi = 0;
                tongThanhTien = 0;
                lblTienThoi.setText(df.format(tienThoi));
                txtTongTien.setText(df.format(tongThanhTien));
                txtTienKhachDua.setText("");
                XoaHetDLTrenTbale(tableDonHang);
                Form_HoaDon fHD = new Form_HoaDon(ma);
                fHD.setLocationRelativeTo(null);
                fHD.setVisible(true);
                this.remove(jPanel6);
                pnlTB.setVisible(true);

            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnXoaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTHDActionPerformed
        int r = tableDonHang.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(null, "Chọn sản phẩm cần xóa");
        } else {
//            modelDonHang.removeRow(r);
//            dstt.remove(r);
            xoaSPTrongDH(r);
            tongTien();
        }
    }//GEN-LAST:event_btnXoaCTHDActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
       int row = tableDonHang.getSelectedRow();
       String maSP = dstt.get(row).getMaSP();
        int index = -1;
        for (SanPham s : ListSP) {
            if (s.getMaSP().endsWith(maSP)) {
                index = ListSP.indexOf(s);
                break;
            }
        }
        for (SanPham s : ListSP) {
            if (s.getMaSP().endsWith(maSP)) {
                index = ListSP.indexOf(s);
                break;
            }
        }
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn sản phẩm cần cập nhật");
        } else {
            int slCapNhat = (int) jspSoLuong.getValue();
            SanPham spConlai = sp_dao.laySanPhamBangMa(modelDonHang.getValueAt(row, 0).toString()); // Lấy sản phẩm cần cập nhật
            int soLuongConLai = spConlai.getSoLuong();  // Số lượng còn lại của sản phầm cần cập nhật
            for (SanPham sp : dstt) {
                if (slCapNhat > soLuongConLai) {
                    JOptionPane.showMessageDialog(null, "Số lượng cập nhật không được lớn hơn số tồn");
                    break;
                } else {
                    if (slCapNhat <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng cập nhật lớn hơn 0");
                        break;
                    } else {
                        modelDonHang.setValueAt(slCapNhat, row, 2);
                        modelDonHang.setValueAt(df.format(spConlai.getGiaGoc() * slCapNhat), row, 4);
                        int soLuongThayDoi = sp_dao.laySanPhamBangMa(maSP).getSoLuong() - slCapNhat;
                        if (ListTimSP != null && !ListTimSP.isEmpty()) {
                            System.out.println(ListTimSP);
                            for (SanPham s : ListTimSP) {
                                if (s.getMaSP().endsWith(maSP)) {
                                    index = ListTimSP.indexOf(s);
                                    System.out.println(index);
                                    break;
                                }
                            }
                            for (SanPham spCapNhat : ListTimSP) {
                                if (spCapNhat.getMaSP().equalsIgnoreCase(maSP)) {
                                    System.out.println(spCapNhat.getMaSP());
                                    modolSP.setValueAt(soLuongThayDoi, index, 2);
                                    break;
                                }
                            }
                        } else {
                            ListSP = sp_dao.getAllSP();
                            modolSP.setValueAt(soLuongThayDoi, index, 2);
                        }
//                        sp.setSoLuong(slCapNhat);
                        tongTien();
                        break;
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        tienThoi = 0;
        try {
            if (!"".equals(txtTienKhachDua.getText())) {
                if(txtTienKhachDua.getText().matches("^\\d+")){
                    double tien = Double.parseDouble(txtTienKhachDua.getText());
                    tienThoi = tien - tongThanhTien;
                    lblTienThoi.setText(df.format(tienThoi));
                }else{
                    JOptionPane.showMessageDialog(null,"Tiền khách đưa chỉ nhập số");
                }
            } else {
                lblTienThoi.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        try {
              timKiemSanPham();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_txtTimKeyReleased
    
   
//    Tìm sản phẩm với nhiều tiêu chí trên combobox
    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
        
         XoaHetDLTrenTbale(jtbSanPham);
        sp_dao = new DAO_SanPham();
        ListTimSP = new ArrayList<SanPham>();
       
        ListTimSP = sp_dao.timSanPhamNhieuTieuChi(cbxPL.getSelectedItem().toString(), cbxKT.getSelectedItem().toString()
                ,cbxMS.getSelectedItem().toString(), cbxCL.getSelectedItem().toString());
        if(!ListTimSP.isEmpty()){
            for (SanPham sp : ListTimSP) {
               modolSP.addRow(new Object[]{
                   sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(),
                    df.format(sp.getGiaGoc()),
                    sp.getLoaiSanPham().getTenLoaiSP(),
                    sp.getMauSac().getTenMau(),
                    sp.getChatLieu().getTenChatLieu(),
                    sp.getKichThuoc().getTenKichThuoc()
               });
            }
        }else{
            JOptionPane.showMessageDialog(null,"Không có sản phẩm cần tìm");
            cbxCL.setSelectedItem("Tất cả");
            cbxKT.setSelectedItem("Tất cả");
            cbxMS.setSelectedItem("Tất cả");
            cbxPL.setSelectedItem("Tất cả");
            DocDuLieuLenTable();
        }
     
        
    }//GEN-LAST:event_btnTimSPActionPerformed
    
    public void timKiemSanPham() {
        try {
            sp_dao = new DAO_SanPham();
            ArrayList<SanPham> spList = sp_dao.getSPTuongDoi(txtTim.getText());
            XoaHetDLTrenTbale(jtbSanPham);
//            Object[] row = new Object[8];
            for (int i = 0; i < spList.size(); i++) {
//                row[0] = spList.get(i).getMaSP();
//                row[1] = spList.get(i).getTenSP();
//                row[2] = spList.get(i).getSoLuong();
//                row[3] = df.format(spList.get(i).getGiaGoc());
//                row[4] = spList.get(i).getLoaiSanPham().getTenLoaiSP();
//                row[5] = spList.get(i).getMauSac().getTenMau();
//                row[6] = spList.get(i).getChatLieu().getTenChatLieu();
//                row[7] = spList.get(i).getKichThuoc().getTenKichThuoc();
              
                modolSP.addRow(new Object[]{
                    spList.get(i).getMaSP(),
                    spList.get(i).getTenSP(),
                    spList.get(i).getSoLuong(),
                    df.format(spList.get(i).getGiaGoc()),
                    spList.get(i).getLoaiSanPham().getTenLoaiSP(),
                    spList.get(i).getMauSac().getTenMau(),
                    spList.get(i).getChatLieu().getTenChatLieu(),
                    spList.get(i).getKichThuoc().getTenKichThuoc(),
                });
            }
            jtbSanPham.setModel(modolSP);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSP;
    private swing.Button btnTimSP;
    private javax.swing.JButton btnXoaCTHD;
    private javax.swing.JComboBox<String> cbxCL;
    private javax.swing.JComboBox<String> cbxKT;
    private javax.swing.JComboBox<String> cbxMS;
    private javax.swing.JComboBox<String> cbxPL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jspSoLuong;
    private javax.swing.JTable jtbSanPham;
    public javax.swing.JLabel lblKhuyenMai;
    private javax.swing.JLabel lblTienThoi;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlLast;
    private javax.swing.JPanel pnlTB;
    private javax.swing.JPanel pnlTKnSL;
    private javax.swing.JTable tableDonHang;
    private swing.TextField txtTienKhachDua;
    private swing.TextFieldAnimation txtTim;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    Component setPreferredSize(JPanel pnlForm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
