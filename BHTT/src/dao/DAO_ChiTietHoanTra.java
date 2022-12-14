/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietHoanTra;
import entity.HoaDon;
import entity.HoaDonHoanTra;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author bohie
 */
public class DAO_ChiTietHoanTra {

    private ArrayList<HoaDonHoanTra> dsHDH;
    private DAO_HoaDonHoan hdht_dao = new DAO_HoaDonHoan();
    private DAO_SanPham sanPham_dao = new DAO_SanPham();
    private ArrayList<ChiTietHoanTra> dsCTHT;

    
    public ArrayList<ChiTietHoanTra> getallDSCTHT() {
        dsCTHT = new ArrayList<ChiTietHoanTra>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from ChiTietHoanTra";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                String lyDo= rs.getString("lyDoHoanTra");
                HoaDonHoanTra hdht = hdht_dao.layHoaDonHoanTheoMa(rs.getString("maHDHT"));
                SanPham maSP = sanPham_dao.laySanPhamBangMa(rs.getString("maSP"));
                ChiTietHoanTra ctht = new ChiTietHoanTra(soLuong,lyDo, maSP, hdht);
                dsCTHT.add(ctht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHT;
    }
    
    public ChiTietHoanTra timCTHoanTraTheoMaLyDo(String maSP,String maHDH, String lydo){
        try (Connection conn = ConnectDB.opConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ChiTietHoanTra WHERE maSP like ? and maHDH like ? and lyDoHoanTra like ? ")) {
                pstmt.setString(1, maSP);
                pstmt.setString(2, maHDH);
                pstmt.setString(3, lydo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    DAO_SanPham dSP=new DAO_SanPham();
                    SanPham sp=dSP.laySanPhamBangMa(rs.getString("maSP"));
                    DAO_HoaDonHoan dCTHD=new DAO_HoaDonHoan();
                    HoaDonHoanTra hdht=dCTHD.layHoaDonHoanTheoMa(rs.getString("maHDHT"));
                    
                    ChiTietHoanTra ctht=new ChiTietHoanTra(rs.getInt("soLuong"), rs.getString("lyDoHoanTra"),sp,hdht);
                    return ctht;
                }
            } catch (Exception e) {
                System.err.println("timCTHoaDonHoan(): get data fail");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("timCTHoaDonHoan(): connect db fail");
        }
        return null;
    }
    
    public ArrayList<ChiTietHoanTra> layTatCaSPLoi() {
        ArrayList<ChiTietHoanTra> ctht = new ArrayList<ChiTietHoanTra>();
        try{           
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * from ChiTietHoanTra where lyDoHoanTra= N'Lỗi nhà sản xuất'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                DAO_SanPham dSP=new DAO_SanPham();
                
                SanPham sp=dSP.laySanPhamBangMa(rs.getString("maSP"));
                DAO_HoaDonHoan dHDH=new DAO_HoaDonHoan();
                HoaDonHoanTra hdht=dHDH.layHoaDonHoanTheoMa(rs.getString("maHDHT"));
                ChiTietHoanTra ct =new ChiTietHoanTra(rs.getInt("soLuong"), rs.getString("LyDoHoanTra"), sp, hdht);
                ctht.add(ct);
                
            }
        }catch (SQLException e) {
        }
        return ctht;
    }
     public ArrayList<ChiTietHoanTra> layTheoMaHDHT(String ma) {
  
        ArrayList<ChiTietHoanTra> dsCTHT = new ArrayList<ChiTietHoanTra>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            PreparedStatement statement = null;
            String sql = "select * from ChiTietHoanTra where maHDHT = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, ma);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                String lyDo= rs.getString("lyDoHoanTra");
                HoaDonHoanTra htdt = hdht_dao.layHoaDonHoanTheoMa(ma);
                SanPham maSP = sanPham_dao.laySanPhamBangMa(rs.getString("maSP"));
                ChiTietHoanTra ctht = new ChiTietHoanTra(soLuong,lyDo, maSP, htdt);
                dsCTHT.add(ctht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHT;
    }
    public ArrayList<ChiTietHoanTra> layCTHTBangMaSP(String m){
        dsCTHT = new ArrayList<ChiTietHoanTra>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from ChiTietHoanTra where maSP = ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, m);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                String lyDo= rs.getString("lyDoHoanTra");
                HoaDonHoanTra htdt = hdht_dao.layHoaDonHoanTheoMa(rs.getString("maHDHT"));
                SanPham maSP = sanPham_dao.laySanPhamBangMa(m);
                ChiTietHoanTra ctht = new ChiTietHoanTra(soLuong,lyDo, maSP, htdt);
                dsCTHT.add(ctht);
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
        return dsCTHT;
        
    }
    
    public ArrayList<ChiTietHoanTra> layCTHTBangMaHD(String m){
        dsCTHT = new ArrayList<ChiTietHoanTra>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from ChiTietHoanTra where maHD = ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, m);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                String lyDo= rs.getString("lyDoHoanTra");
                HoaDonHoanTra htdt = hdht_dao.layHoaDonHoanTheoMa(rs.getString("maHDHT"));
                SanPham maSP = sanPham_dao.laySanPhamBangMa(m);
                ChiTietHoanTra ctht = new ChiTietHoanTra(soLuong,lyDo, maSP, htdt);
                dsCTHT.add(ctht);
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
        return dsCTHT;
        
    }
    
    public ArrayList<ChiTietHoanTra> layDSCTHTBangMa(String maTim) {
        ArrayList<ChiTietHoanTra> dsCTHDHoan = new ArrayList<ChiTietHoanTra>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from ChiTietHoanTra where maHDHT = ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, maTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                String lyDo= rs.getString("lyDoHoanTra");
                HoaDonHoanTra htdt = hdht_dao.layHoaDonHoanTheoMa(maTim);
                SanPham maSP = sanPham_dao.laySanPhamBangMa(rs.getString("maSP"));
                ChiTietHoanTra ctht = new ChiTietHoanTra(soLuong,lyDo, maSP, htdt);
                dsCTHDHoan.add(ctht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return dsCTHDHoan;
    }

    public boolean themCTHoanTra(ChiTietHoanTra ctHoanTra) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into ChiTietHoanTra values(?,?,?,?)");
            stmt.setInt(1, ctHoanTra.getSoLuong());
            stmt.setString(2, ctHoanTra.getSanPham().getMaSP());
            stmt.setString(3, ctHoanTra.getHoaDonHoanTra().getMaHDHT());            
            stmt.setString(4, ctHoanTra.getLyDoHoanTra());
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

}
