/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.LoaiNhanVien;
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
public class DAO_NhanVien {
    private final DAO_LoaiNhanVien lnvDAO = new DAO_LoaiNhanVien();
    private DAO_TaiKhoan tkDAO;
    public List<NhanVien> layTatCaNhanVienVaoBang() {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhanVien";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,lnv);
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
    
    public List<NhanVien> layNhanVienVaoBang() {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhanVien  where maLoaiNV= 'LNV002'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,lnv);
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
        public List<NhanVien> layQuanLyVaoBang() {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhanVien where maLoaiNV= 'LNV001'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,lnv);
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
    
    public NhanVien layNhanVienBangMa(String maNV) {
        try(
            Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from NhanVien where maNV =? ")){
            pts.setString(1,maNV );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                        LoaiNhanVien lnv= lnvDAO.timLoaiNVBangMa(rs.getString("maLoaiNV"));
                        NhanVien nv=new NhanVien(rs.getString("maNV"),rs.getString("tenNV"),rs.getString("CMND"),rs.getString("soDienThoai"),rs.getBoolean("gioiTinh"),rs.getDouble("luongCoBan"),lnv);
                        return nv;
                    }
                }
            }catch(Exception e){
           
       }
       return null; 
    }
        public NhanVien layNhanVienBangCMND(String maNV) {
        try(
            Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from NhanVien where CMND =? ")){
            pts.setString(1,maNV );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                        LoaiNhanVien lnv= lnvDAO.timLoaiNVBangMa(rs.getString("maLoaiNV"));
                        NhanVien nv=new NhanVien(rs.getString("maNV"),rs.getString("tenNV"),rs.getString("CMND"),rs.getString("soDienThoai"),rs.getBoolean("gioiTinh"),rs.getDouble("luongCoBan"),lnv);
                        return nv;
                    }
                }
            }catch(Exception e){
           
       }
       return null; 
    }
    public boolean themNV(NhanVien nv) {
        try(
            Connection conn = ConnectDB.opConnection();  PreparedStatement pstmt = conn.prepareStatement("INSERT INTO NHANVIEN VALUES (?,?,?,?,?,?,?)");)
            {
                pstmt.setString(1, nv.getMaNV());
                pstmt.setString(2, nv.getTenNV());
                pstmt.setString(3, nv.getCMND());
                pstmt.setString(4, nv.getSoDienThoai());
                pstmt.setBoolean(5, nv.isGioiTinh());               
                pstmt.setDouble(6, nv.getLuongCoBan());
                pstmt.setString(7, nv.getLoaiNhanVien().getMaLoaiNV());
                return  pstmt.executeUpdate()>0;
            } catch (Exception e) {
            System.err.println("themNV(): connect db fail");
            e.printStackTrace();
        }
        return false;
    }

        
        
    
    
    public boolean capNhatNV(NhanVien nv) {
        try(
            Connection conn = ConnectDB.opConnection();  
                PreparedStatement pstmt = conn.prepareStatement("UPDATE NHANVIEN SET CMND=?, SODIENTHOAI=?, GIOITINH=?, LUONGCOBAN=? ,MALOAINV=? WHERE MANV=?");)
            {
                pstmt.setString(1, nv.getTenNV());
                pstmt.setString(3, nv.getCMND());
                pstmt.setString(4, nv.getSoDienThoai());
                pstmt.setInt(5, nv.isGioiTinh() ==true ? 1:0);               
                pstmt.setFloat(6, (float)nv.getLuongCoBan());
                pstmt.setString(7, nv.getLoaiNhanVien().getMaLoaiNV());
                
                pstmt.setString(8, nv.getMaNV());
                return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("capnhatNV(): connect db fail");
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean xoaNV(NhanVien nv) {
        try (Connection conn = ConnectDB.opConnection();
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM NHANVIEN WHERE MANV=?;")) {
            pstmt.setString(1, nv.getMaNV());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}

