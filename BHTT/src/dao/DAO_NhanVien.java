/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.LoaiNhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Trinh Cui Bap
 */
public class DAO_NhanVien {
    public List<NhanVien> layTatCaNhanVienVaoBang() {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhanVien";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,lnv);
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
    
}
