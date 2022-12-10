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
    public List<NhanVien> layTatCaNhanVienDangLamVaoBang() {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhanVien where trangThai=1";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                boolean trangThai = rs.getBoolean("trangThai");
                LoaiNhanVien lnv = new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,trangThai,lnv);
               
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
    
    public List<NhanVien> layTatCaNhanVienDaNghiVaoBang() {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhanVien where trangthai=0";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                boolean trangThai = rs.getBoolean("trangThai");
                LoaiNhanVien lnv = new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,trangThai,lnv);
               
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
    public List<NhanVien> layTatCa() {
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
                boolean trangThai = rs.getBoolean("trangThai");
                LoaiNhanVien lnv = lnvDAO.timLoaiNVBangMa(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,trangThai,lnv);
               
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
        public List<NhanVien> layNhanVienCoHoaDonTheoNgay(String tuNgay,String denNgay) {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select nv.maNV,nv.tenNV, nv.CMND,nv.soDienThoai,nv.gioiTinh,nv.luongCoBan,nv.maLoaiNV,nv.trangThai from NhanVien as nv\n" +
"                    join HoaDon as hd on hd.maNV=nv.maNV\n" +
"                    where hd.ngayLap >= ? and hd.ngayLap <= ?\n" +
"					group by nv.maNV,nv.tenNV, nv.CMND,nv.soDienThoai,nv.gioiTinh,nv.luongCoBan,nv.maLoaiNV,nv.trangThai";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, tuNgay);
            statement.setString(2, denNgay);
            ResultSet rs=statement.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                boolean trangThai = rs.getBoolean("trangThai");
                LoaiNhanVien lnv = lnvDAO.timLoaiNVBangMa(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,trangThai,lnv);
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
                boolean trangThai = rs.getBoolean("trangThai");
                LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,trangThai,lnv);
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }

        public List<NhanVien> layTheoGioiTinh(String s) {
        List<NhanVien> ds = new ArrayList<>();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NhanVien where gioitinh= '"+s+"'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String CMND = rs.getString("CMND");
                String SDT = rs.getString("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                double luongCoBan = rs.getDouble("luongCoBan");
                boolean trangThai = rs.getBoolean("trangThai");
                LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,trangThai,lnv);
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
                        NhanVien nv=new NhanVien(rs.getString("maNV"),rs.getString("tenNV"),rs.getString("CMND"),rs.getString("soDienThoai"),rs.getBoolean("gioiTinh"),rs.getDouble("luongCoBan"),rs.getBoolean("trangThai"),lnv);
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
                        NhanVien nv=new NhanVien(rs.getString("maNV"),rs.getString("tenNV"),rs.getString("CMND"),rs.getString("soDienThoai"),rs.getBoolean("gioiTinh"),rs.getDouble("luongCoBan"),rs.getBoolean("trangThai"),lnv);
                        return nv;
                    }
                }
            }catch(Exception e){
           
       }
       return null; 
    }
    public boolean themNV(NhanVien nv) {
        int n = 0;
        try(
            Connection conn = ConnectDB.opConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO NHANVIEN VALUES (?,?,?,?,?,?,?,?)");)
            
            {
                pstmt.setString(1, nv.getMaNV());
                pstmt.setString(2, nv.getTenNV());
                pstmt.setString(3, nv.getCMND());
                pstmt.setString(4, nv.getSoDienThoai());
                pstmt.setBoolean(5, nv.isGioiTinh());               
                pstmt.setDouble(6, nv.getLuongCoBan());    
                pstmt.setString(7, nv.getLoaiNhanVien().getMaLoaiNV());
                pstmt.setBoolean(8, nv.isTrangThai());
                n = pstmt.executeUpdate();
            } catch (Exception e) {
            System.err.println("themNV(): connect db fail");
            e.printStackTrace();
        }
        return n > 0;
    }

        
        
    
    
    public boolean capNhatNV(NhanVien nv) {
        try(
            Connection conn = ConnectDB.opConnection();  
                PreparedStatement pstmt = conn.prepareStatement("UPDATE NHANVIEN SET CMND=?, SODIENTHOAI=?, GIOITINH=?, LUONGCOBAN=? ,MALOAINV=? WHERE MANV=?");)
            {

                pstmt.setString(1, nv.getCMND());
                pstmt.setString(2, nv.getSoDienThoai());
                pstmt.setInt(3,nv.isGioiTinh()==true?1:0);               
                pstmt.setFloat(4, (float)nv.getLuongCoBan()); 
                pstmt.setString(5, nv.getLoaiNhanVien().getMaLoaiNV());            
                pstmt.setString(6, nv.getMaNV());
                return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("capnhatNV(): connect db fail");
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean xoaNV(NhanVien nv) {
        try(
            Connection conn = ConnectDB.opConnection();  
                PreparedStatement pstmt = conn.prepareStatement("UPDATE NHANVIEN SET TRANGTHAI=? WHERE MANV=?");)
            {

                pstmt.setInt(1, nv.isTrangThai()==true?0:1);         
                pstmt.setString(2, nv.getMaNV());
                return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("choThoiViecNV(): connect db fail");
            e.printStackTrace();
        }
        return false;
    }

    public List<NhanVien> layQuanLyVaoBang() {List<NhanVien> ds = new ArrayList<>();
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
                boolean trangThai = rs.getBoolean("trangThai");
                LoaiNhanVien lnv=new LoaiNhanVien(rs.getString("maLoaiNV"));
                NhanVien nv = new NhanVien(maNV, tenNV,CMND,SDT,gioiTinh,luongCoBan,trangThai,lnv);
                ds.add(nv);
            }
        }catch (SQLException e) {
        }
        return ds;
    }
    
    
    public NhanVien layNhanVienBangTen(String tenNV) {
        try (
            Connection con = ConnectDB.opConnection();  PreparedStatement pts = con.prepareStatement("select * from NhanVien where tenNV = ?")) {
            pts.setNString(1, tenNV);
            try ( ResultSet rs = pts.executeQuery()) {
                if (rs.next()) {
                    LoaiNhanVien lnv = lnvDAO.timLoaiNVBangMa(rs.getString("maLoaiNV"));
                    NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getString("CMND"), rs.getString("soDienThoai"), rs.getBoolean("gioiTinh"), rs.getDouble("luongCoBan"), rs.getBoolean("trangThai"), lnv);
                    return nv;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }
    
}

