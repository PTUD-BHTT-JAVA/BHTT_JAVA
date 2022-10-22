/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;

import entity.NhaCungCap;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Trinh Cui Bap
 */
public class DAO_NhaCungCap {

    private ArrayList<NhaCungCap> dsNCC;

    public ArrayList<NhaCungCap> getalltbNhaCungCap() {
        dsNCC = new ArrayList<NhaCungCap>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhaCungCap";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNCC = rs.getString("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, soDienThoai, email);
                dsNCC.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNCC;
    }

    public boolean themNhaCungCap(NhaCungCap ncc) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into NhaCungCap values(?,?,?,?,?)");
            stmt.setString(1, ncc.getMaNCC());
            stmt.setString(2, ncc.getTenNCC());
            stmt.setString(3, ncc.getDiaChi());
            stmt.setString(4, ncc.getSoDienThoai());
            stmt.setString(5, ncc.getEmail());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }


    public void xoaNhaCungCap(String maNCC) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "delete from NhaCungCap where maNCC = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maNCC);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NhaCungCap getElement(int index) {
        if (index < 0 || index > dsNCC.size()) {
            return null;
        }
        return dsNCC.get(index);
    }

    public boolean capNhaCungCap(NhaCungCap ncc) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update NhaCungCap set tenNCC=?,diaChi=?,soDienThoai=?,email=? where maNCC = ?");
            stmt.setString(1, ncc.getTenNCC());
            stmt.setString(2, ncc.getDiaChi());
            stmt.setString(3, ncc.getSoDienThoai());
            stmt.setString(4, ncc.getEmail());
            stmt.setString(5, ncc.getMaNCC());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }

    public ArrayList<NhaCungCap> getNhaCungCapTheoTen(String tenKH) {
        ArrayList<NhaCungCap> dsNCC = new ArrayList<NhaCungCap>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from NhaCungCap where tenNCC = ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, tenKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                while (rs.next()) {
                    String maNCC = rs.getString("maNCC");
                    String tenNCC = rs.getString("tenNCC");
                    String diaChi = rs.getString("diaChi");
                    String soDienThoai = rs.getString("soDienThoai");
                    String email = rs.getString("email");
                    NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, soDienThoai, email);
                    dsNCC.add(ncc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return dsNCC;
    }
    public NhaCungCap layNhaCungCapBangMa(String maTim) {
      NhaCungCap ncc = new NhaCungCap();
        try(
            Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from NhaCungCap where maNCC =? ")){
            pts.setString(1,maTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                        ncc=new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"), rs.getString("diaChi"), rs.getString("soDienThoai"), rs.getString("email"));
                        return ncc;
                    }
                }
            }catch(Exception e){
           
       }
       return null;
   
    }
   
}
