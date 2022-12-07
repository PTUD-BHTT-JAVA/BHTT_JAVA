/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoanTra;
import entity.HoaDon;
import entity.HoaDonHoanTra;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 *
 * @author bohie
 */
public class DAO_HoaDonHoan {

    private ArrayList<HoaDonHoanTra> dsHDH;
    private DAO_HoaDon hd_dao = new DAO_HoaDon();
    private DAO_NhanVien nv_dao=new DAO_NhanVien();

    public ArrayList<HoaDonHoanTra> getallDSHoaDonHoan() {
        dsHDH = new ArrayList<HoaDonHoanTra>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from HoaDonHoanTra";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHDH = rs.getString("maHDHT");
                Date ngayLap = rs.getDate("ngayHoanTra");
                HoaDon hd = hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                NhanVien nv= nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                HoaDonHoanTra hdht = new HoaDonHoanTra(maHDH, ngayLap, hd,nv);
                dsHDH.add(hdht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHDH;
    }

    public boolean themHoaDonHon(HoaDonHoanTra hdHoan) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into HoaDonHoanTra values(?,?,?,?)");
            stmt.setString(1, hdHoan.getMaHDHT());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngayLap = sdf.format(hdHoan.getNgayHoanTra());
            stmt.setString(2, ngayLap);
            stmt.setString(3, hdHoan.getHoaDon().getMaHD());
            stmt.setString(4, hdHoan.getNhanVien().getMaNV());

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

    public HoaDonHoanTra layHoaDonHoanTheoMa(String maHDH) {
        try (
                 Connection con = ConnectDB.opConnection();  PreparedStatement pts = con.prepareStatement("Select * from HoaDonHoanTra where maHDHT =? ")) {
            pts.setString(1, maHDH);
            try ( ResultSet rs = pts.executeQuery()) {
                if (rs.next()) {
                    Date ngayLap = rs.getDate("ngayHoanTra");
                    HoaDon hd = hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                    String maHDHT  = rs.getString("maHDHT");
                    NhanVien nv= nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                    HoaDonHoanTra hdht = new HoaDonHoanTra(maHDHT, ngayLap, hd,nv);
                    return hdht;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<HoaDonHoanTra> layHoaDonHoanTheoMaHD(String maTim) {
        ArrayList<HoaDonHoanTra> ds = new ArrayList<HoaDonHoanTra>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from HoaDonHoanTra where maHD =? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, maTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date ngayLap = rs.getDate("ngayHoanTra");
                HoaDon hd = hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                NhanVien nv= nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                HoaDonHoanTra hdht = new HoaDonHoanTra(rs.getString("maHDHT"), ngayLap, hd,nv);
                ds.add(hdht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ds;
    }
        public ArrayList<HoaDonHoanTra> layHoaDonHoanTheoMaNV(String maTim) {
        ArrayList<HoaDonHoanTra> ds = new ArrayList<HoaDonHoanTra>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from HoaDonHoanTra where maNV =? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, maTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date ngayLap = rs.getDate("ngayHoanTra");
                HoaDon hd = hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                NhanVien nv= nv_dao.layNhanVienBangMa(rs.getString("maNV"));
                HoaDonHoanTra hdht = new HoaDonHoanTra(rs.getString("maHDHT"), ngayLap, hd,nv);
                ds.add(hdht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ds;
    }

    public ArrayList<HoaDonHoanTra> thongKeDoanhThu(String a, String b) {
        dsHDH = new ArrayList<HoaDonHoanTra>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "select * \n"
                    + " from [HoaDonHoanTra] hdht join [dbo].[HoaDon] hd on hdht.maHD=hd.maHD\n"
                    + "	join [dbo].[KhachHang] kh on kh.maKH=hd.maKH\n"
                    + "join [dbo].[NhanVien] nv on nv.maNV=hd.maNV\n"
                    + "where ngayHoanTra >= ? and ngayHoanTra <= ?";
            stemnt = con.prepareStatement(sql);
            stemnt.setString(1, a);
            stemnt.setString(2, b);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("maHD");
                String maHDH = rs.getString("maHDHT");
                Date ngayHoanTra = rs.getDate("ngayHoanTra");
                NhanVien nv = new NhanVien(rs.getString("maNV"));
                KhachHang kh = new KhachHang(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maHD, rs.getDate("ngayLap"), rs.getDouble("tienKhachDua"), rs.getString("diaChi"), nv, kh);
                
                HoaDonHoanTra hdht = new HoaDonHoanTra(maHDH, ngayHoanTra, hd,nv);
                dsHDH.add(hdht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHDH;
    }
    public ArrayList<HoaDonHoanTra> thongKeDoanhThuTheoNam(int a) {
        dsHDH = new ArrayList<HoaDonHoanTra>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "select * \n"
                    + " from [HoaDonHoanTra] hdht join [dbo].[HoaDon] hd on hdht.maHD=hd.maHD\n"
                    + "	join [dbo].[KhachHang] kh on kh.maKH=hd.maKH\n"
                    + "join [dbo].[NhanVien] nv on nv.maNV=hd.maNV\n"
                    + "where year(ngayHoanTra) =?";
            stemnt = con.prepareStatement(sql);
            stemnt.setInt(1, a);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("maHD");
                String maHDH = rs.getString("maHDHT");
                Date ngayHoanTra = rs.getDate("ngayHoanTra");
                NhanVien nv = new NhanVien(rs.getString("maNV"));
                KhachHang kh = new KhachHang(rs.getString("maKH"));
                HoaDon hd = new HoaDon(maHD, rs.getDate("ngayLap"), rs.getDouble("tienKhachDua"), rs.getString("diaChi"), nv, kh);
                HoaDonHoanTra hdht = new HoaDonHoanTra(maHDH, ngayHoanTra, hd,nv);
                dsHDH.add(hdht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHDH;
    }
}
