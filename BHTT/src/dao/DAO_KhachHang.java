package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.NhanVien;
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
        //    Thêm khách hàng trên database
}
