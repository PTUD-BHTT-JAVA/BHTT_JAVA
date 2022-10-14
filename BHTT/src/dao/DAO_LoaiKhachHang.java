/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Trinh Cui Bap
 */
public class DAO_LoaiKhachHang {
     private ArrayList<LoaiKhachHang> dsLKH;

    public ArrayList<LoaiKhachHang> getalltbLoaiKhachHang() {
        dsLKH = new ArrayList<LoaiKhachHang>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from LoaiKhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maLoaiKH = rs.getString("maLoaiKH");
                String tenLoaiKH = rs.getString("tenLoaiKH");
                LoaiKhachHang lkh = new LoaiKhachHang(maLoaiKH, tenLoaiKH);
                dsLKH.add(lkh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLKH;
    }
}
