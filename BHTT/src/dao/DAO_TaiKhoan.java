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

    private String SELECT_USERNAME= "select tenDN from TaiKhoan where tenDN = ?";
    private String SELECT_PASSWORD ="select matKhau from TaiKhoan where tenDN = ?";

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

   
   public TaiKhoan timTaiKhoan(String ID) {
        try (Connection conn = ConnectDB.opConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TAIKHOAN WHERE TENDN like ?")) {
            pstmt.setString(1, ID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    TaiKhoan taiKhoan=new TaiKhoan(rs.getString("tendn"), rs.getString("matkhau"));
                    return taiKhoan;
                }
            } catch (Exception e) {
                System.err.println("timTaiKhoan(): get data fail");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("timTaiKhoan(): connect db fail");
        }
        return null;
    }
   

}
