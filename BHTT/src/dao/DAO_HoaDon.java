/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Trinh Cui Bap
 */
public class DAO_HoaDon {

    private ArrayList<HoaDon> dsHD;
    private ArrayList<ChiTietHoaDon> dsCTHD;
    private DAO_KhachHang kh_dao = new DAO_KhachHang();
    private DAO_NhanVien nv_dao = new DAO_NhanVien();

    public ArrayList<HoaDon> getallDSHoaDon() {
        dsHD = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from HoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maHD");
                Date ngayLap = rs.getDate("ngayLap");
                double tienKhachDua = rs.getDouble("tienKhachDua");
                String diaChi = rs.getString("diaChi");
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                dsHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    public ArrayList<HoaDon> getallDSHoaDonConHoan() {
        dsHD = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from HoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maHD");
                Date ngayLap = rs.getDate("ngayLap");
                double tienKhachDua = rs.getDouble("tienKhachDua");
                String diaChi = rs.getString("diaChi");
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                dsHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    public boolean themHoaDon(HoaDon hd) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into HoaDon values(?,?,?,?,?,?)");
            stmt.setString(1, hd.getMaHD());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngayLap = sdf.format(hd.getNgayLap());
            stmt.setString(2, ngayLap);
            stmt.setDouble(3, hd.getTienKhachDua());
            stmt.setString(4, hd.getDiaChi());
            stmt.setString(5, hd.getNhanVien().getMaNV());
            stmt.setString(6, hd.getKhachHang().getMaKH());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }

    public HoaDon getHoaDonMoiNhat() {
        HoaDon hd = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        Statement stmt = null;
        try {
            String sql = "select top 1 * from HoaDon order by maHD DESC";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DAO_NhanVien nv_dao = new DAO_NhanVien();
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                DAO_KhachHang kh_dao = new DAO_KhachHang();
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
//                hd = new HDBanHang(rs.getString("MaHDBH"), nv, kh, rs.getDate("NgayLapHDBH"), rs.getDouble("TongTien"));
                hd = new HoaDon(rs.getString("maHD"), rs.getDate("ngayLap"),
                        rs.getDouble("tienKhachDua"), rs.getString("diaChi"), nv, kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public HoaDon layHoaDonTheoMa(String maHD) {
        try (
                 Connection con = ConnectDB.opConnection();  PreparedStatement pts = con.prepareStatement("Select * from HoaDon where maHD =? ")) {
            pts.setString(1, maHD);
            try ( ResultSet rs = pts.executeQuery()) {
                if (rs.next()) {
                    String maNV = rs.getString("maHD");
                    Date ngayLap = rs.getDate("ngayLap");
                    double tienKhachDua = rs.getDouble("tienKhachDua");
                    String diaChi = rs.getString("diaChi");
                    NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                    KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                    HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                    return hd;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<HoaDon> thongKeDoanhThu(String a, String b) {
        dsHD = new ArrayList<HoaDon>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "select maHD,ngayLap,tienKhachDua,diaChi,maNV,maKH\n"
                    + "from HoaDon\n"
                    + "where ngayLap >= ? and ngayLap <= ?";
            stemnt = con.prepareStatement(sql);
            stemnt.setString(1, a);
            stemnt.setString(2, b);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maHD");
                Date ngayLap = rs.getDate("ngayLap");
                double tienKhachDua = rs.getDouble("tienKhachDua");
                String diaChi = rs.getString("diaChi");
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                dsHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    public ArrayList<HoaDon> thongKeDoanhThuTheoNam(int a) {
        dsHD = new ArrayList<HoaDon>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "select maHD,ngayLap,tienKhachDua,diaChi,maNV,maKH\n"
                    + "from HoaDon\n"
                    + "where year(ngayLap)=?";
            stemnt = con.prepareStatement(sql);
            stemnt.setInt(1, a);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maHD");
                Date ngayLap = rs.getDate("ngayLap");
                double tienKhachDua = rs.getDouble("tienKhachDua");
                String diaChi = rs.getString("diaChi");
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                dsHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    public ArrayList<HoaDon> getAllDSHDtheoMaNV(String a) {
        dsHD = new ArrayList<HoaDon>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "select *from HoaDon where maNV=?";
            stemnt = con.prepareStatement(sql);
            stemnt.setString(1, a);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maHD");
                Date ngayLap = rs.getDate("ngayLap");
                double tienKhachDua = rs.getDouble("tienKhachDua");
                String diaChi = rs.getString("diaChi");
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                dsHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }


    public ArrayList<HoaDon> thongKeDoanhThuTheoNV(String a, String b, String ma) {
        dsHD = new ArrayList<HoaDon>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "select maHD,ngayLap,tienKhachDua,diaChi,maNV,maKH\n"
                    + "                    from HoaDon\n"
                    + "                    where maNV=? and ngayLap >= ? and ngayLap <= ?";
            stemnt = con.prepareStatement(sql);
            stemnt.setString(1, ma);
            stemnt.setString(2, a);
            stemnt.setString(3, b);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maHD");
                Date ngayLap = rs.getDate("ngayLap");
                double tienKhachDua = rs.getDouble("tienKhachDua");
                String diaChi = rs.getString("diaChi");
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                dsHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    public ArrayList<HoaDon> thongKeDoanhThuNVTheoNam(int a, String ma) {
        dsHD = new ArrayList<HoaDon>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "select maHD,ngayLap,tienKhachDua,diaChi,maNV,maKH\n"
                    + "from HoaDon\n"
                    + "where maNV = ? and year(ngayLap)=?";
            stemnt = con.prepareStatement(sql);
            stemnt.setString(1, ma);
            stemnt.setInt(2, a);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maHD");
                Date ngayLap = rs.getDate("ngayLap");
                double tienKhachDua = rs.getDouble("tienKhachDua");
                String diaChi = rs.getString("diaChi");
                NhanVien nv = nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                KhachHang kh = kh_dao.getKHBangMa(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maNV, ngayLap, tienKhachDua, diaChi, nv, kh);
                dsHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }

}
