package dao;

import connectDB.ConnectDB;
import entity.ChatLieu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KichThuoc;
import entity.LoaiSanPham;
import entity.MauSac;
import entity.NhaCungCap;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;

public class DAO_ChiTietHoaDon {

    private DAO_NhaCungCap dao_ncc = new DAO_NhaCungCap();
    private DAO_LoaiSP dao_lsp = new DAO_LoaiSP();
    private DAO_MauSac dao_ms = new DAO_MauSac();
    private DAO_ChatLieu dao_cl = new DAO_ChatLieu();
    private DAO_KichThuoc dao_kt = new DAO_KichThuoc();
    private DAO_HoaDon hd_dao = new DAO_HoaDon();
    private DAO_SanPham sp_dao = new DAO_SanPham();
    private ArrayList<ChiTietHoaDon> dsCTHD;
   
    public ArrayList<ChiTietHoaDon> getallDSHoaDon() {
        dsCTHD = new ArrayList<ChiTietHoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from ChiTietHoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                double VAT = rs.getDouble("VAT");
                HoaDon maHD =  hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                SanPham maSP = sp_dao.laySanPhamBangMa(rs.getString("maSP"));
                double tongTien = rs.getDouble("tongTien");
                double tienThoi = rs.getDouble("tienThoi");
                ChiTietHoaDon cthd = new ChiTietHoaDon(soLuong, VAT, tongTien, tienThoi, maHD, maSP);
                dsCTHD.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHD;
    }
    
    public ArrayList<ChiTietHoaDon> layDSHoaDonLenBang() {
      ArrayList<ChiTietHoaDon> dsCTHDX = new ArrayList<ChiTietHoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = """
                         select hd.maHD,hd.ngayLap,SUM(cthd.soLuong) as TongSoLuong,SUM(cthd.tongTien) as TongThanhTien, nv.tenNV
                                                  from HoaDon hd
                                                  join ChiTietHoaDon cthd on cthd.maHD = hd.maHD
                                                  join NhanVien nv on nv.maNV = hd.maNV
                                                  group by hd.ngayLap,hd.maHD,nv.tenNV""";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int soLuong = rs.getInt("TongSoLuong");
                double VAT = 0.1;
                HoaDon maHD = hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                SanPham maSP = new SanPham("SP001");
                double tongTien = rs.getDouble("TongThanhTien");
                double tienThoi = 1000;
                ChiTietHoaDon cthd = new ChiTietHoaDon(soLuong, VAT, tongTien, tienThoi, maHD, maSP);
                dsCTHDX.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHDX;
    }
   
    public boolean themCTHD(ChiTietHoaDon cthd) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?,?)");
            stmt.setInt(1, cthd.getSoLuong());
            stmt.setDouble(2, cthd.getVAT());
            stmt.setString(3, cthd.getHoaDon().getMaHD());
            stmt.setString(4, cthd.getSanPham().getMaSP());
            stmt.setDouble(5, cthd.getTongTien());
            stmt.setDouble(6, cthd.getTienThoi());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
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

    public SanPham laySanPhamBangMa(String maTim) {
        try (
            java.sql.Connection con = ConnectDB.opConnection();  PreparedStatement pts = con.prepareStatement("Select * from SanPham where maSP = ?  ")) {
            pts.setString(1, maTim);
            try ( ResultSet rs = pts.executeQuery()) {
                if (rs.next()) {
                    String maSP = rs.getString("maSP");
                    String tenSP = rs.getString("tenSP");
                    double gia = rs.getDouble("giaGoc");
                    int sl = rs.getInt("soLuong");
                    byte[] hinhanh = rs.getBytes("hinhAnh");
                    String moTa = rs.getString("moTa");
                    java.sql.Date ngayN = rs.getDate("ngayNhap");
                    Date ngayNhap = new Date(ngayN.getTime());
                    java.sql.Date han = rs.getDate("hanSD");
                    Date hanSD = new Date(han.getTime());
                    String maNCC = rs.getString("maNCC");
                    String mau = rs.getString("maMau");
                    String kt = rs.getString("maKichThuoc");
                    String loiSP = rs.getString("maLoaiSP");
                    NhaCungCap ncc = dao_ncc.layNhaCungCapBangMa(maNCC);
                    LoaiSanPham lsp = dao_lsp.layLoaiSPBangMa(loiSP);
                    ChatLieu chatl = dao_cl.layChatLieuBangMa(rs.getString("maChatLieu"));
                    MauSac ms = dao_ms.layMauSacBangMa(rs.getString("maMau"));
                    KichThuoc kicht = dao_kt.layKichThuocBangMa(kt);
                    LoaiSanPham loaiSP = new LoaiSanPham(loiSP);
                    SanPham sp = new SanPham(maSP, tenSP, gia, sl, hinhanh, moTa, ngayNhap, hanSD, ncc, chatl, ms, kicht, lsp);
                    return sp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<ChiTietHoaDon> layDSHDBangMa(String maTim){
        ArrayList<ChiTietHoaDon> dsHDHoan = new ArrayList<ChiTietHoaDon>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from ChiTietHoaDon where maHD = ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, maTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                double VAT = rs.getDouble("VAT");
                HoaDon maHD =  hd_dao.layHoaDonTheoMa(rs.getString("maHD"));
                SanPham maSP = sp_dao.laySanPhamBangMa(rs.getString("maSP"));
                double tongTien = rs.getDouble("tongTien");
                double tienThoi = rs.getDouble("tienThoi");
                ChiTietHoaDon cthd = new ChiTietHoaDon(soLuong, VAT, tongTien, tienThoi, maHD, maSP);
                dsHDHoan.add(cthd);  
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
        return dsHDHoan;
    }

}
