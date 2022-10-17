/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import connectDB.ConnectDB;
import entity.ChatLieu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author bohie
 */
public class DAO_ChatLieu {
    public  ArrayList<ChatLieu> getAllCL() {
        ArrayList<ChatLieu> dsCL = new ArrayList<ChatLieu>();
        try{
            ConnectDB.getInstance();
            java.sql.Connection con = ConnectDB.getConnection();
            String sql = "select * from ChatLieu";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ChatLieu cl = new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")) ;
                dsCL.add(cl);
                
            }
        }catch (Exception e) {
            e.printStackTrace();}
        
        return dsCL;
    }
    public ChatLieu layChatLieuBangMa(String maTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("select *from ChatLieu where maChatLieu = ?")){
            pts.setString(1,maTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                        ChatLieu cl = new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu"));
                        return cl;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
}
