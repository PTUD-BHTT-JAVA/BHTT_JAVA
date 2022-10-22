/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KichThuoc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author bohie
 */
public class DAO_KichThuoc {
    public  ArrayList<KichThuoc> getAllKT() {
        ArrayList<KichThuoc> dsLSP = new ArrayList<KichThuoc>();
        try{
           
            ConnectDB.getInstance();
            java.sql.Connection con = ConnectDB.getConnection();
            String sql = "select * from KichThuoc";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                KichThuoc lsp = new KichThuoc(rs.getString("maKichThuoc"), rs.getString("tenKichThuoc"),rs.getString("moTa")) ;
                dsLSP.add(lsp);
                
            }
        }catch (Exception e) {
            e.printStackTrace();}
        
        return dsLSP;
    }
    public KichThuoc layKichThuocBangMa(String maTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from KichThuoc where maKichThuoc = ?  ")){
            pts.setString(1,maTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                       KichThuoc kt = new KichThuoc(rs.getString("maKichThuoc"), rs.getString("tenKichThuoc"), rs.getString("moTa"));
                        return kt;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
    public KichThuoc layKichThuocBangTen(String tenTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from KichThuoc where tenKichThuoc=?  ")){
            pts.setString(1,tenTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                       KichThuoc kt = new KichThuoc(rs.getString("maKichThuoc"), rs.getString("tenKichThuoc"), rs.getString("moTa"));
                        return kt;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
}
