/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.LoaiNhanVien;
import connectDB.ConnectDB;
import java.sql.SQLException;
/**
 *
 * @author Anh Thu
 */
public class DAO_LoaiNhanVien {
        public List<LoaiNhanVien> layTatCaLoai(){
        List<LoaiNhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from LoaiNhanVien";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maLoaiNV = rs.getString("maLoaiNV");
                String tenLoaiNV = rs.getString("tenLoaiNV");
                LoaiNhanVien lnv = new LoaiNhanVien(maLoaiNV, tenLoaiNV);
                ds.add(lnv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
}
