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
    private TableRowSorter<DefaultTableModel> tr, tr1;
    public DefaultTableModel modelDonHang;
    DecimalFormat df = new DecimalFormat("#,##0 VND");
    DecimalFormat pmTT = new DecimalFormat("###.###");
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
        DocDuLieuLenTable();
        DocDuLieuVaoCombobox();
//        moKhoaTextfields(false);
        dstt = new ArrayList<SanPham>();
        ListSP = new ArrayList<SanPham>();

        tr = new TableRowSorter<DefaultTableModel>(modolSP);
        tr1 = new TableRowSorter<DefaultTableModel>(modolSP);
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
    
    
    private void capNhatSP(int row){
        if(row < 0 ){
            JOptionPane.showMessageDialog(null, "Chọn sản phẩm cần cập nhật");
        }else{
            String maSP = dstt.get(row).getMaSP();
//            int index = -1;
            int slCapNhat = (int) jspSoLuong.getValue();
            SanPham spConlai = sp_dao.laySanPhamBangMa(modelDonHang.getValueAt(row, 0).toString()); // Lấy sản phẩm cần cập nhật
             int soLuongConLai = spConlai.getSoLuong();
             if(slCapNhat > soLuongConLai){
                 JOptionPane.showMessageDialog(null, "Số lượng cập nhật không lớn hơn số lượng tồn");
             }else{
                 if(slCapNhat <= 0){
                     JOptionPane.showMessageDialog(null, "Số lượng cập nhất phải lớn hơn 0");
                 }else{
                     modelDonHang.setValueAt(slCapNhat, row, 2);
                     modelDonHang.setValueAt(df.format(spConlai.getGiaGoc() * slCapNhat), row, 4);
                     int soLuongThayDoi = sp_dao.laySanPhamBangMa(maSP).getSoLuong() - slCapNhat;
                     if (ListTimSP != null && !ListTimSP.isEmpty()) {
//                            System.out.println(ListTimSP);
//                            for (SanPham s : ListTimSP) {
//                                if (s.getMaSP().endsWith(maSP)) {
//                                    index = ListTimSP.indexOf(s);
//                                    System.out.println(index);
//                                    break;
//                                }
//                            }
//                                Thay đổi số lượng  trên table
//                            for (SanPham spCapNhat : ListTimSP) {
//                                if (spCapNhat.getMaSP().equalsIgnoreCase(maSP)) {
//                                    System.out.println(spCapNhat.getMaSP());
//                                    jtbSanPham.setValueAt(soLuongThayDoi, index, 2);
//                                    System.out.println(index);
//                                    break;
//                                }
//                            }
                        }else{
                         ListSP = sp_dao.getAllSP();
//                                modolSP.setValueAt(soLuongThayDoi, index, 2);
//                            System.out.println(index);
                            tongThanhTien = 0;
                            for (SanPham spCTT : dstt) {
//                                    Cập nhật số lượng sản phẩm khi kiếm được
                                if (spCTT.getMaSP().equals(tableDonHang.getValueAt(row, 0).toString())) {
                                    spCTT.setSoLuong(slCapNhat);
                                    if (kh.layKhachHangBangSDT(txtSDT).getLoaiKhachHang().getMaLoaiKH().equals("LKH001")) {
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
                           }
                     }
                 }
             }
           tongTien();
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
                    double km;
                    if(khTT.getLoaiKhachHang().getMaLoaiKH().equals("LKH001"))
                        km=0.1;
                    else
                        km=0;
                    ChiTietHoaDon cthd = new ChiTietHoaDon(sp.getSoLuong(), 0.05, sp.getGiaGoc() * sp.getSoLuong() + sp.getGiaGoc() * sp.getSoLuong()*0.05 - sp.getGiaGoc()* sp.getSoLuong()*km, tienThoi, hoaDonMoiThem, sp);
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
            if (khTT.getDiemTichLuy() >= diemThanhVIP) {
                khTT.getLoaiKhachHang().setMaLoaiKH("LKH001");
                kh_dao = new DAO_KhachHang();
                kh_dao.capNhatKhachHang(khTT);
            }
        }
    }

    public void themSPVaoDonHang() {
        int r = jtbSanPham.getSelectedRow();
        if (r != -1) {
            soLuongTon = Integer.parseInt(jtbSanPham.getValueAt(r, 2).toString());
            String maSP = (jtbSanPham.getValueAt(r, 0).toString());
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
//            soLuongTon -= sp.getSoLuong();
//            modolSP.setValueAt(soLuongTon, r, 2);

            tongTien();

        } else {
            JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Sản Phẩm Trước Khi Mua");
        }
    }

    public void xoaSPTrongDH(int r) {
//        String maSP = dstt.get(r).getMaSP();
//        int index = -1;
//        for (SanPham s : ListSP) {
//            if (s.getMaSP().endsWith(maSP)) {
//                index = ListSP.indexOf(s);
//                break;
//            }
//        }
//        int soLuong = sp_dao.laySanPhamBangMa(maSP).getSoLuong();
//        if (ListTimSP != null && ListTimSP.size() > 0) {
//
//            System.out.println(ListTimSP);
//            for (SanPham s : ListTimSP) {
//                if (s.getMaSP().endsWith(maSP)) {
//                    index = ListTimSP.indexOf(s);
//                    System.out.println(index);
//                    break;
//                }
//            }
//            for (SanPham sp : ListTimSP) {
//                if (sp.getMaSP().equalsIgnoreCase(maSP)) {
//                    System.out.println(sp.getMaSP());
//                    modolSP.setValueAt(soLuong, index, 2);
//                    break;
//                }
//            }
//        } else {
//            ListSP = sp_dao.getAllSP();
//            modolSP.setValueAt(soLuong, index, 2);
//        }
//        System.out.println(index);
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
        jPanel7 = new javax.swing.JPanel();
        txtTim = new swing.TextFieldAnimation();
        jPanel23 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbxPL = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cbxMS = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cbxCL = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbxKT = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        btnTimSP = new swing.Button();
        jPanel20 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jspSoLuong = new javax.swing.JSpinner();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnThemSP = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
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
        setMaximumSize(new java.awt.Dimension(2000, 2000));
        setPreferredSize(new java.awt.Dimension(1700, 890));
        setLayout(new javax.swing.OverlayLayout(this));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

        pnlCenter.setMaximumSize(new java.awt.Dimension(2147483647, 450));
        pnlCenter.setPreferredSize(new java.awt.Dimension(780, 370));
        pnlCenter.setLayout(new javax.swing.BoxLayout(pnlCenter, javax.swing.BoxLayout.Y_AXIS));

        pnlTKnSL.setBackground(new java.awt.Color(204, 204, 255));
        pnlTKnSL.setMaximumSize(new java.awt.Dimension(32767, 100));
        pnlTKnSL.setPreferredSize(new java.awt.Dimension(780, 100));
        pnlTKnSL.setRequestFocusEnabled(false);
        pnlTKnSL.setLayout(new javax.swing.BoxLayout(pnlTKnSL, javax.swing.BoxLayout.X_AXIS));

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)), "Tìm theo tên sản phẩm"));
        jPanel7.setMaximumSize(new java.awt.Dimension(600, 32767));
        jPanel7.setMinimumSize(new java.awt.Dimension(150, 72));
        jPanel7.setPreferredSize(new java.awt.Dimension(200, 140));

        txtTim.setPreferredSize(new java.awt.Dimension(250, 39));
        txtTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimMouseClicked(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTim, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlTKnSL.add(jPanel7);

        jPanel23.setBackground(new java.awt.Color(204, 204, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)), "Lọc sản phẩm"));
        jPanel23.setLayout(new javax.swing.BoxLayout(jPanel23, javax.swing.BoxLayout.X_AXIS));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setMaximumSize(new java.awt.Dimension(800, 32767));
        jPanel8.setPreferredSize(new java.awt.Dimension(570, 83));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.Y_AXIS));

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.X_AXIS));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setText("Phân loại :");
        jLabel16.setAlignmentX(1.0F);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel16.setIconTextGap(20);
        jLabel16.setMaximumSize(new java.awt.Dimension(200, 60));
        jLabel16.setMinimumSize(new java.awt.Dimension(60, 20));
        jLabel16.setPreferredSize(new java.awt.Dimension(85, 22));
        jPanel14.add(jLabel16);

        cbxPL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxPL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbxPL.setMaximumSize(new java.awt.Dimension(200, 50));
        cbxPL.setMinimumSize(new java.awt.Dimension(79, 20));
        cbxPL.setPreferredSize(new java.awt.Dimension(170, 25));
        cbxPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPLActionPerformed(evt);
            }
        });
        jPanel14.add(cbxPL);

        jPanel17.setBackground(new java.awt.Color(204, 204, 255));
        jPanel17.setMaximumSize(new java.awt.Dimension(200, 60));
        jPanel17.setMinimumSize(new java.awt.Dimension(20, 10));
        jPanel17.setPreferredSize(new java.awt.Dimension(30, 25));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel17);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel19.setText("Màu sắc :");
        jLabel19.setAlignmentX(1.0F);
        jLabel19.setMaximumSize(new java.awt.Dimension(200, 60));
        jLabel19.setMinimumSize(new java.awt.Dimension(60, 20));
        jLabel19.setPreferredSize(new java.awt.Dimension(85, 22));
        jPanel14.add(jLabel19);

        cbxMS.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbxMS.setMaximumSize(new java.awt.Dimension(200, 50));
        cbxMS.setMinimumSize(new java.awt.Dimension(79, 20));
        cbxMS.setPreferredSize(new java.awt.Dimension(170, 25));
        cbxMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMSActionPerformed(evt);
            }
        });
        jPanel14.add(cbxMS);

        jPanel8.add(jPanel14);

        jPanel16.setBackground(new java.awt.Color(204, 204, 255));
        jPanel16.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel16.setPreferredSize(new java.awt.Dimension(800, 15));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel16);

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.X_AXIS));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel18.setText("Chất liệu :");
        jLabel18.setAlignmentX(1.0F);
        jLabel18.setMaximumSize(new java.awt.Dimension(200, 60));
        jLabel18.setMinimumSize(new java.awt.Dimension(60, 20));
        jLabel18.setPreferredSize(new java.awt.Dimension(85, 22));
        jPanel15.add(jLabel18);

        cbxCL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbxCL.setMaximumSize(new java.awt.Dimension(200, 50));
        cbxCL.setMinimumSize(new java.awt.Dimension(79, 20));
        cbxCL.setPreferredSize(new java.awt.Dimension(170, 25));
        cbxCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCLActionPerformed(evt);
            }
        });
        jPanel15.add(cbxCL);

        jPanel18.setBackground(new java.awt.Color(204, 204, 255));
        jPanel18.setMaximumSize(new java.awt.Dimension(200, 60));
        jPanel18.setMinimumSize(new java.awt.Dimension(20, 10));
        jPanel18.setPreferredSize(new java.awt.Dimension(30, 25));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jPanel15.add(jPanel18);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setText("Kích thước :");
        jLabel17.setAlignmentX(1.0F);
        jLabel17.setMaximumSize(new java.awt.Dimension(200, 60));
        jLabel17.setMinimumSize(new java.awt.Dimension(60, 20));
        jLabel17.setPreferredSize(new java.awt.Dimension(85, 22));
        jPanel15.add(jLabel17);

        cbxKT.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbxKT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbxKT.setMaximumSize(new java.awt.Dimension(200, 50));
        cbxKT.setMinimumSize(new java.awt.Dimension(79, 20));
        cbxKT.setPreferredSize(new java.awt.Dimension(170, 25));
        cbxKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKTActionPerformed(evt);
            }
        });
        jPanel15.add(cbxKT);

        jPanel8.add(jPanel15);

        jPanel23.add(jPanel8);

        jPanel22.setBackground(new java.awt.Color(204, 204, 255));
        jPanel22.setMaximumSize(new java.awt.Dimension(100, 32767));
        jPanel22.setPreferredSize(new java.awt.Dimension(80, 100));
        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.Y_AXIS));

        jPanel19.setBackground(new java.awt.Color(204, 204, 255));
        jPanel19.setMaximumSize(new java.awt.Dimension(200, 60));
        jPanel19.setMinimumSize(new java.awt.Dimension(30, 10));
        jPanel19.setPreferredSize(new java.awt.Dimension(0, 15));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel22.add(jPanel19);

        jPanel21.setBackground(new java.awt.Color(204, 204, 255));

        btnTimSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        btnTimSP.setColor1(new java.awt.Color(102, 102, 255));
        btnTimSP.setColor2(new java.awt.Color(102, 0, 255));
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel22.add(jPanel21);

        jPanel20.setBackground(new java.awt.Color(204, 204, 255));
        jPanel20.setMaximumSize(new java.awt.Dimension(200, 60));
        jPanel20.setMinimumSize(new java.awt.Dimension(30, 10));
        jPanel20.setPreferredSize(new java.awt.Dimension(0, 15));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel22.add(jPanel20);

        jPanel23.add(jPanel22);

        pnlTKnSL.add(jPanel23);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)), "Thêm sản phẩm vào đơn hàng"));
        jPanel9.setMaximumSize(new java.awt.Dimension(800, 200));
        jPanel9.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel9.setPreferredSize(new java.awt.Dimension(200, 112));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(779, 30));
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(50, 100));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel12);

        jLabel20.setBackground(new java.awt.Color(204, 204, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("Nhập số lượng:");
        jPanel10.add(jLabel20);

        jspSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jspSoLuong.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(jspSoLuong);

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(50, 100));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel11);

        jPanel9.add(jPanel10);

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

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
        jPanel13.add(btnThemSP);

        jPanel9.add(jPanel13);

        pnlTKnSL.add(jPanel9);

        pnlCenter.add(pnlTKnSL);

        jPanel24.setMaximumSize(new java.awt.Dimension(2000, 1000));
        jPanel24.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setMaximumSize(new java.awt.Dimension(2000, 300));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(500, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 200));

        jtbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Giá", "Phân loại", "Màu sắc", "Chất liệu", "Kích thước"
            }
        ));
        jtbSanPham.setMaximumSize(new java.awt.Dimension(2000, 1000));
        jtbSanPham.setMinimumSize(new java.awt.Dimension(300, 200));
        jtbSanPham.setPreferredSize(new java.awt.Dimension(600, 300));
        jScrollPane1.setViewportView(jtbSanPham);

        jPanel24.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlCenter.add(jPanel24);

        jPanel6.add(pnlCenter);

        pnlLast.setMaximumSize(new java.awt.Dimension(65534, 500));
        pnlLast.setPreferredSize(new java.awt.Dimension(1122, 400));
        pnlLast.setLayout(new javax.swing.BoxLayout(pnlLast, javax.swing.BoxLayout.Y_AXIS));

        pnlDonHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlDonHang.setMaximumSize(new java.awt.Dimension(2000, 300));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(800, 80));
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
        jPanel5.setPreferredSize(new java.awt.Dimension(1424, 50));

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
        jLabel6.setText("Khuyễn mãi:");

        lblKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblKhuyenMai.setForeground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(549, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTien)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(lblKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(lblTienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
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
            .addGap(0, 1122, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
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
                    Logger.getLogger(GD_TaoDonHang.class
                            .getName()).log(Level.SEVERE, null, ex);
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
        capNhatSP(row);
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        tienThoi = 0;
        try {
            if (!"".equals(txtTienKhachDua.getText())) {
                if (txtTienKhachDua.getText().matches("^\\d+")) {
                    double tien = Double.parseDouble(txtTienKhachDua.getText());
//                    double tongTienTT = Double.parseDouble(txtTongTien.getText());
                    tienThoi = tien - tongThanhTien;
                    lblTienThoi.setText(df.format(tienThoi));
                } else {
                    JOptionPane.showMessageDialog(null, "Tiền khách đưa chỉ nhập số");
                }
            } else {
                lblTienThoi.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        String s = txtTim.getText();
        filter(s);
    }//GEN-LAST:event_txtTimKeyReleased

    private void filter(String s) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modolSP);
        jtbSanPham.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + s, 1));

    }
//    Tìm sản phẩm với nhiều tiêu chí trên combobox
    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
//        txtTim.setText("");
//        XoaHetDLTrenTbale(jtbSanPham);
//        sp_dao = new DAO_SanPham();
//        ListTimSP = new ArrayList<SanPham>();
//        
//        ListTimSP = sp_dao.timSanPhamNhieuTieuChi(cbxPL.getSelectedItem().toString(), cbxKT.getSelectedItem().toString(),
//                 cbxMS.getSelectedItem().toString(), cbxCL.getSelectedItem().toString());
//        if (!ListTimSP.isEmpty()) {
//            for (SanPham sp : ListTimSP) {
//                modolSP.addRow(new Object[]{
//                    sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(),
//                    df.format(sp.getGiaGoc()),
//                    sp.getLoaiSanPham().getTenLoaiSP(),
//                    sp.getMauSac().getTenMau(),
//                    sp.getChatLieu().getTenChatLieu(),
//                    sp.getKichThuoc().getTenKichThuoc()
//                });
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Không có sản phẩm cần tìm");
//            cbxCL.setSelectedIndex(0);
//            cbxKT.setSelectedIndex(0);
//            cbxMS.setSelectedIndex(0);
//            cbxPL.setSelectedIndex(0);
//            DocDuLieuLenTable();
//        }

        //Thu
        jtbSanPham.setRowSorter(tr1);
        String fCL, fKT, fPL, fMS;
        if (cbxCL.getSelectedItem().toString().equals("Tất cả")) {
            fCL = "";
        } else {
            fCL = cbxCL.getSelectedItem().toString();
        }
        if (cbxMS.getSelectedItem().toString().equals("Tất cả")) {
            fMS = "";
        } else {
            fMS = cbxMS.getSelectedItem().toString();
        }
        if (cbxKT.getSelectedItem().toString().equals("Tất cả")) {
            fKT = "";
        } else {
            fKT = cbxKT.getSelectedItem().toString();
        }
        if (cbxPL.getSelectedItem().toString().equals("Tất cả")) {
            fPL = "";
        } else {
            fPL = cbxPL.getSelectedItem().toString();
        }

        List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(4);
        filters.add(RowFilter.regexFilter(fCL, 6));
        filters.add(RowFilter.regexFilter(fMS, 5));
        filters.add(RowFilter.regexFilter(fKT, 7));
        filters.add(RowFilter.regexFilter(fPL, 4));
        RowFilter<Object, Object> fooBarFilter = RowFilter.andFilter(filters);

        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modolSP);
        jtbSanPham.setRowSorter(tr1);
        tr1.setRowFilter(fooBarFilter);

    }//GEN-LAST:event_btnTimSPActionPerformed

    private void txtTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseClicked
        jtbSanPham.setRowSorter(tr);
        cbxCL.setSelectedIndex(0);
        cbxKT.setSelectedIndex(0);
        cbxMS.setSelectedIndex(0);
        cbxPL.setSelectedIndex(0);
    }//GEN-LAST:event_txtTimMouseClicked

    private void cbxPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPLActionPerformed

        if (!txtTim.getText().equals("")) {
            txtTim.setText("");
            tr1 = new TableRowSorter<DefaultTableModel>(modolSP);
            jtbSanPham.setRowSorter(tr1);
        }

    }//GEN-LAST:event_cbxPLActionPerformed

    private void cbxMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMSActionPerformed
        if (!txtTim.getText().equals("")) {
            txtTim.setText("");
            tr1 = new TableRowSorter<DefaultTableModel>(modolSP);
            jtbSanPham.setRowSorter(tr1);
        }
    }//GEN-LAST:event_cbxMSActionPerformed

    private void cbxCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCLActionPerformed
        if (!txtTim.getText().equals("")) {
            txtTim.setText("");
            tr1 = new TableRowSorter<DefaultTableModel>(modolSP);
            jtbSanPham.setRowSorter(tr1);
        }
    }//GEN-LAST:event_cbxCLActionPerformed

    private void cbxKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKTActionPerformed
        if (!txtTim.getText().equals("")) {
            txtTim.setText("");
            tr1 = new TableRowSorter<DefaultTableModel>(modolSP);
            jtbSanPham.setRowSorter(tr1);
        }
    }//GEN-LAST:event_cbxKTActionPerformed

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
                    spList.get(i).getKichThuoc().getTenKichThuoc(),});
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
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
