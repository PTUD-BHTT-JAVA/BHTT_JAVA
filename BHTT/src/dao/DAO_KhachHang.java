package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO_KhachHang {
    private ArrayList<KhachHang> dsKH;

    public ArrayList<KhachHang> getalltbKhachHang() {
        dsKH = new ArrayList<KhachHang>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                String soDienThoai  = rs.getString("soDienThoai");
                int diemTichLuy = rs.getInt("diemTichLuy");
                String email = rs.getString("email");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(rs.getString("maLoaiKH"));
                KhachHang kh = new KhachHang(maNV, tenKH, soDienThoai, diemTichLuy, email, gioiTinh, loaiKhachHang);
                dsKH.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKH;
    }
    /*
        Thêm khách hàng
    */
    public boolean themKhachHang(KhachHang kh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into KhachHang values(?,?,?,?,?,?,?)");
            stmt.setString(1,kh.getMaKH());
            stmt.setString(2,kh.getTenKH());
            stmt.setString(3,kh.getSoDienThoai());
            stmt.setInt(4,kh.getDiemTichLuy());
            stmt.setString(5,kh.getEmail());
            stmt.setBoolean(6,kh.isGioiTinh());
            stmt.setString(7,kh.getLoaiKhachHang().getMaLoaiKH());
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
//    Xóa khách hàng
      public void xoaKhachHang(String maKH) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "delete from KhachHang where maKH = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,maKH);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      public KhachHang getElement(int index) {
        if (index < 0 || index > dsKH.size()) {
            return null;
        }
        return dsKH.get(index);
    }
    
      
     public boolean capNhatKhachHang(KhachHang kh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update KhachHang set tenKH=?,soDienThoai=?,diemTichLuy=?,email=?,gioiTinh=?,maLoaiKH=? where maKH = ?");
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getSoDienThoai());
            stmt.setInt(3, kh.getDiemTichLuy());
            stmt.setString(4,kh.getEmail());
            stmt.setBoolean(5,kh.isGioiTinh());
            stmt.setString(6,kh.getLoaiKhachHang().getMaLoaiKH());
            stmt.setString(7,kh.getMaKH());
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
     
     public ArrayList<KhachHang> getKhachHangTheoMaLoai(String maLKH) {
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from KhachHang where maLoaiKH = ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1,maLKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maKH");
                String ten = rs.getString("tenKH");
                String soDienThoai  = rs.getString("soDienThoai");
                int diemTichLuy = rs.getInt("diemTichLuy");
                String email = rs.getString("email");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(rs.getString("maLoaiKH"));
                KhachHang kh = new KhachHang(maNV, ten, soDienThoai, diemTichLuy, email, gioiTinh, loaiKhachHang);
                dsKH.add(kh);
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
        return dsKH;
    }
         
}
