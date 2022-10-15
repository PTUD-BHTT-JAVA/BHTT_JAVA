/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Trinh Cui Bap
 */
public class DAO_TaiKhoan {
    public List<TaiKhoan> layTatCaTaiKhoanVaoBang() {
        List<TaiKhoan> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from TaiKhoan";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String tenDN = rs.getString("tenDN");
                String matKhau = rs.getString("matKhau");
                
                TaiKhoan tk = new TaiKhoan(tenDN,matKhau);
                ds.add(tk);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
    public String timTenDangNhap(String tenDN){
       String tenDangNhap = null;
       try (Connection con = ConnectDB.getConnection();
               PreparedStatement pts = con.prepareStatement("Select tenDN from TaiKhoan where tenDN = ?")){
           pts.setString(1,tenDN );
           ResultSet rs = pts.executeQuery();
           
           while(rs.next()){
               tenDangNhap = rs.getString("tenDN");
           }
           return tenDangNhap;
       } catch (Exception e) {
           
       }
       return null;
    }
    
    public String timMatKhau(String tenDN){
       String mk = null;
       try (Connection con = ConnectDB.getConnection();
               PreparedStatement pts = con.prepareStatement("Select matKhau from TaiKhoan where tenDN = ?")){
           pts.setString(1,tenDN );
           ResultSet rs = pts.executeQuery();
           
           while(rs.next()){
               mk = rs.getString("matKhau");
           }
           return mk;
       } catch (Exception e) {
           
       }
       return null;
   }
    
}
