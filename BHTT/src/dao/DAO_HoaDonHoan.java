/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.HoaDonHoanTra;
import entity.KhachHang;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bohie
 */
public class DAO_HoaDonHoan {
    private ArrayList<HoaDonHoanTra> dsHDH;
    private DAO_HoaDon hd_dao = new DAO_HoaDon();
    

    public ArrayList<HoaDonHoanTra> getallDSHoaDonHoan() {
        dsHDH = new ArrayList<HoaDonHoanTra>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from HoaDonHoanTra";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHDH = rs.getString("maHDHT");
                Date ngayLap = rs.getDate("ngayHoanTra");
                HoaDon hd = hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                HoaDonHoanTra hdht = new HoaDonHoanTra(maHDH, ngayLap, hd);
                dsHDH.add(hdht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHDH;
    }

}
