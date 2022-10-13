/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.NhaCungCap;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Trinh Cui Bap
 */
public class DAO_NhaCungCap {
    private ArrayList<NhaCungCap> dsNCC;

    public ArrayList<NhaCungCap> getalltbNhaCungCap() {
        dsNCC = new ArrayList<NhaCungCap>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhaCungCap";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNCC");
                String tenKH = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String soDienThoai  = rs.getString("soDienThoai");
                String email = rs.getString("email");
                NhaCungCap ncc = new NhaCungCap(maNV, tenKH, email, soDienThoai, diaChi);
                dsNCC.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNCC;
    }
}
