/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import entity.LoaiSanPham;
import entity.NhanVien;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
/**
 *
 * @author bohie
 */
public class DAO_LoaiSP {
    public  ArrayList<LoaiSanPham> getAllLSP() {
        ArrayList<LoaiSanPham> dsLSP = new ArrayList<LoaiSanPham>();
        try{
           
            ConnectDB.getInstance();
            java.sql.Connection con = ConnectDB.getConnection();
            String sql = "select * from LoaiSanPham";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                LoaiSanPham lsp = new LoaiSanPham(rs.getString("maLoaiSP"), rs.getString("tenLoaiSP")) ;
                dsLSP.add(lsp);
                
            }
        }catch (Exception e) {
            e.printStackTrace();}
        
        return dsLSP;
    }
     public LoaiSanPham layLoaiSPBangMa(String maTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from LoaiSanPham where maLoaiSP = ?  ")){
            pts.setString(1,maTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                       LoaiSanPham lsp= new LoaiSanPham(rs.getString("maLoaiSP"), rs.getString("tenLoaiSP"));
                        return lsp;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
     public LoaiSanPham layLoaiSPBangTen(String tenTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from LoaiSanPham where tenLoaiSP=?  ")){
            pts.setString(1,tenTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                       LoaiSanPham lsp= new LoaiSanPham(rs.getString("maLoaiSP"), rs.getString("tenLoaiSP"));
                        return lsp;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
}
