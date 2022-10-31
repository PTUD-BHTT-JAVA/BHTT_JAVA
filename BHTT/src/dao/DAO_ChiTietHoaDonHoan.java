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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Trinh Cui Bap
 */
public class DAO_ChiTietHoaDonHoan {
    private  ArrayList<ChiTietHoanTra> dsCTDonHoan;
    private DAO_SanPham sp_dao = new DAO_SanPham();
    private DAO_HoaDonHoan hdh_dao = new DAO_HoaDonHoan();
    
    public ArrayList<ChiTietHoanTra> getallDSHoaDon() {
          dsCTDonHoan = new ArrayList<ChiTietHoanTra>();
          try {
              ConnectDB.getInstance();
              Connection con = ConnectDB.getConnection();
              String sql = "select * from ChiTietHoanTra";
              Statement statement = con.createStatement();
              ResultSet rs = statement.executeQuery(sql);
              while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                HoaDonHoanTra hdht = hdh_dao.layHoaDonHoanTheoMa(rs.getString("maHDHT"));
                SanPham maSP = sp_dao.laySanPhamBangMa(rs.getString("maSP"));
                ChiTietHoanTra ctht = new ChiTietHoanTra(soLuong, maSP, hdht);
                dsCTDonHoan.add(ctht);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return dsCTDonHoan;
      }
    
}
