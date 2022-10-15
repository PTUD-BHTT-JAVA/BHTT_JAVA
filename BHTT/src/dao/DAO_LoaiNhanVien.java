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
import entity.NhanVien;
import java.sql.PreparedStatement;
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

    public LoaiNhanVien timLoaiNVBangMa(String ma) {
        try(
            Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from LoaiNhanVien where maLoaiNV = ? ")){
            pts.setString(1,ma );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                        LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"),rs.getString("tenLoaiNV"));
                        return lnv;
                    }
                }
        }catch(Exception e){
           e.printStackTrace();
       }
       return null;
    }
}
