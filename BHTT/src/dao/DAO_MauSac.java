/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.MauSac;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author bohie
 */
public class DAO_MauSac {
    public  ArrayList<MauSac> getAllMau() {
        ArrayList<MauSac> dsM = new ArrayList<MauSac>();
        try{
           
            ConnectDB.getInstance();
            java.sql.Connection con = ConnectDB.getConnection();
            String sql = "select * from MauSac";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                MauSac m = new MauSac(rs.getString("maMau"), rs.getString("tenMau")) ;
                dsM.add(m);
                
            }
        }catch (Exception e) {
            e.printStackTrace();}
        
        return dsM;
    }
    public MauSac layMauSacBangMa(String maTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from MauSac where maMau = ?  ")){
            pts.setString(1,maTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                       MauSac ms = new MauSac(rs.getString("maMau"), rs.getString("tenMau"));
                        return ms;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
    public MauSac layMauSacBangTen(String tenTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from MauSac where tenMau=?  ")){
            pts.setNString(1,tenTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                       MauSac ms = new MauSac(rs.getString("maMau"), rs.getString("tenMau"));
                        return ms;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
}
