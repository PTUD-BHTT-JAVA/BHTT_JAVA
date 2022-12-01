/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 *
 * @author bohie
 */
public class DAO_SanPham {
   
    private DAO_NhaCungCap dao_ncc= new DAO_NhaCungCap();
    
    private DAO_LoaiSP dao_lsp= new DAO_LoaiSP();
    
    private DAO_MauSac dao_ms= new DAO_MauSac();
    
    private DAO_ChatLieu dao_cl= new DAO_ChatLieu();
    
    private DAO_KichThuoc dao_kt= new DAO_KichThuoc();
    private ArrayList<ChiTietHoaDon> dsCTHD ;
    private ArrayList<thongKeSPBanChay> tkSPBC ;
    public  ArrayList<SanPham> getAllSP() {
        ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
        try{
           
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "select * from SanPham";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maSP= rs.getString("maSP");
                String tenSP= rs.getString("tenSP"); 
                double gia= rs.getDouble("giaGoc");
                int sl= rs.getInt("soLuong");
                byte[] hinhanh= rs.getBytes("hinhAnh");
                String moTa= rs.getString("moTa");
                java.sql.Date ngayN= rs.getDate("ngayNhap");
                Date ngayNhap = new Date(ngayN.getTime());
                
                 java.sql.Date han= rs.getDate("hanSD");
                Date hanSD = new Date(han.getTime());
                String maNCC= rs.getString("maNCC");
                String mau= rs.getString("maMau");
                String kt= rs.getString("maKichThuoc");
                String loiSP= rs.getString("maLoaiSP");
                NhaCungCap ncc = dao_ncc.layNhaCungCapBangMa(maNCC);
                LoaiSanPham lsp = dao_lsp.layLoaiSPBangMa(loiSP);
                ChatLieu chatl= dao_cl.layChatLieuBangMa(rs.getString("maChatLieu"));
                MauSac ms= dao_ms.layMauSacBangMa(rs.getString("maMau"));
                KichThuoc kicht = dao_kt.layKichThuocBangMa(kt);
                LoaiSanPham loaiSP= new LoaiSanPham(loiSP);
                SanPham sp = new SanPham(maSP, tenSP, gia, sl, hinhanh, moTa, ngayNhap, hanSD, ncc, chatl, ms, kicht, lsp)
                        ;dsSP.add(sp);
                
            }
        }catch (SQLException e) {
        }
        return dsSP;
    }
    
    public SanPham laySanPhamBangMa(String maTim) {
        
        try(
             java.sql.Connection con = ConnectDB.opConnection();
            PreparedStatement pts = con.prepareStatement("Select * from SanPham where maSP = ?  ")){
            pts.setString(1,maTim );
                try(ResultSet rs = pts.executeQuery()){
                    if (rs.next()){
                     String maSP= rs.getString("maSP");
                String tenSP= rs.getString("tenSP"); 
                double gia= rs.getDouble("giaGoc");
                int sl= rs.getInt("soLuong");
                byte[] hinhanh= rs.getBytes("hinhAnh");
                String moTa= rs.getString("moTa");
                java.sql.Date ngayN= rs.getDate("ngayNhap");
                Date ngayNhap = new Date(ngayN.getTime());
                
                java.sql.Date han= rs.getDate("hanSD");
                Date hanSD = new Date(han.getTime());
                String maNCC= rs.getString("maNCC");
                String mau= rs.getString("maMau");
                String kt= rs.getString("maKichThuoc");
                String loiSP= rs.getString("maLoaiSP");
                NhaCungCap ncc = dao_ncc.layNhaCungCapBangMa(maNCC);
                LoaiSanPham lsp = dao_lsp.layLoaiSPBangMa(loiSP);
                ChatLieu chatl= dao_cl.layChatLieuBangMa(rs.getString("maChatLieu"));
                MauSac ms= dao_ms.layMauSacBangMa(rs.getString("maMau"));
                KichThuoc kicht = dao_kt.layKichThuocBangMa(kt);
                LoaiSanPham loaiSP= new LoaiSanPham(loiSP);
                SanPham sp = new SanPham(maSP, tenSP, gia, sl, hinhanh, moTa, ngayNhap, hanSD, ncc, chatl, ms, kicht, lsp);
                        return sp;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
       }
       return null;
     }
    
    public boolean themSP(SanPham spNew){
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n=0;
        try {
            ConnectDB.getInstance().connect();
            String sql= "insert into SanPham values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            
            stemnt = con.prepareStatement(sql);
            stemnt.setString(1, spNew.getMaSP());
            stemnt.setString(2, spNew.getTenSP());
            stemnt.setDouble(3, spNew.getGiaGoc());
            stemnt.setInt(4 , spNew.getSoLuong());
            stemnt.setBytes(5, spNew.getHinhAnh());
            stemnt.setString(6, spNew.getMoTa());
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngayNhap= sdf.format(spNew.getNgayNhap());
            stemnt.setString(7,  ngayNhap);
            String hanSD= sdf.format( spNew.getNgayNhap());
            stemnt.setString(8, hanSD);
            stemnt.setString(9, spNew.getNhaCungCap().getMaNCC());
            stemnt.setString(10, spNew.getChatLieu().getMaChatLieu());
            stemnt.setString(11, spNew.getMauSac().getMaMau());
            stemnt.setString(12, spNew.getKichThuoc().getMaKichThuoc());
              stemnt.setString(13, spNew.getLoaiSanPham().getMaLoaiSP());
            n = stemnt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                stemnt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return n>0;
    }
    
    public boolean XoaSP(String ma){
         Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n=0;
        String sql= "Delete SanPham Where maSP = ?";
        try {
            stemnt = con.prepareStatement(sql);
			stemnt.setString(1, ma);
			n = stemnt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                stemnt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return n>0;
    }
    
    public void CapNhatSP(SanPham spNew){
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        String sql= "update SanPham set tenSP=?, giaGoc=?,soLuong=?,hinhAnh=?,moTa=?,ngayNhap=?,hanSD=?,maNCC=?,maChatLieu=?,maMau=?,maKichThuoc=?,maLoaiSP=? Where maSP = ?";
        try {
            stemnt = con.prepareStatement(sql);
           
            stemnt.setString(1, spNew.getTenSP());
            stemnt.setDouble(2, spNew.getGiaGoc());
            stemnt.setInt(3 , spNew.getSoLuong());
            stemnt.setBytes(4, spNew.getHinhAnh());
            stemnt.setString(5, spNew.getMoTa());
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngayNhap= sdf.format(spNew.getNgayNhap());
            stemnt.setString(6,  ngayNhap);
            String hanSD= sdf.format( spNew.getNgayNhap());
            stemnt.setString(7, hanSD);
            stemnt.setString(8, spNew.getNhaCungCap().getMaNCC());
            stemnt.setString(9, spNew.getChatLieu().getMaChatLieu());
            stemnt.setString(10, spNew.getMauSac().getMaMau());
            stemnt.setString(11, spNew.getKichThuoc().getMaKichThuoc());
              stemnt.setString(12, spNew.getLoaiSanPham().getMaLoaiSP());
               stemnt.setString(13, spNew.getMaSP());
            stemnt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                stemnt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<SanPham> layDSSPBangMa(String maTim){
        ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from SanPham where maSP = ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, maTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
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
                dsSP.add(sp);
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
        return dsSP;
    }
    
    
    public  ArrayList<SanPham> getSPTuongDoi(String search) {
        ArrayList<SanPham> spList = new ArrayList<SanPham>();
        try{
            Connection con = ConnectDB.getConnection();
            String sql = "select * from SanPham where CONCAT(maSP,tenSP,giaGoc,soLuong) LIKE  '%" + search + "%' ";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maSP= rs.getString("maSP");
                String tenSP= rs.getString("tenSP"); 
                double gia= rs.getDouble("giaGoc");
                int sl= rs.getInt("soLuong");
                byte[] hinhanh= rs.getBytes("hinhAnh");
                String moTa= rs.getString("moTa");
                java.sql.Date ngayN= rs.getDate("ngayNhap");
                Date ngayNhap = new Date(ngayN.getTime());
                 java.sql.Date han= rs.getDate("hanSD");
                Date hanSD = new Date(han.getTime());
                String maNCC= rs.getString("maNCC");
                String kt= rs.getString("maKichThuoc");
                String loiSP= rs.getString("maLoaiSP");
                NhaCungCap ncc = dao_ncc.layNhaCungCapBangMa(maNCC);
                LoaiSanPham lsp = dao_lsp.layLoaiSPBangMa(loiSP);
                ChatLieu chatl= dao_cl.layChatLieuBangMa(rs.getString("maChatLieu"));
                MauSac ms= dao_ms.layMauSacBangMa(rs.getString("maMau"));
                KichThuoc kicht = dao_kt.layKichThuocBangMa(kt);
                LoaiSanPham loaiSP= new LoaiSanPham(loiSP);
                
                SanPham sp = new SanPham(maSP, tenSP, gia, sl, 
                        hinhanh, moTa, ngayNhap, hanSD, ncc, chatl, ms, kicht, lsp);
                spList.add(sp);
                
            }
        }catch (SQLException e) {
        }
        return spList;
    }
    
    public ArrayList<SanPham> timSanPhamNhieuTieuChi(String loai,String kichThuoc, String mauSac,String chatLieu){
        ArrayList<SanPham> spCanTim =  new ArrayList<SanPham>();
        DAO_LoaiSP dao_loaisp = new DAO_LoaiSP();
        DAO_KichThuoc dao_kichthuoc =  new DAO_KichThuoc();
        DAO_MauSac dao_mausac = new DAO_MauSac();
        DAO_ChatLieu dao_chatlieu = new DAO_ChatLieu();
        String txtLoaiSP = dao_loaisp.layLoaiSPBangTen(loai) == null ? "" : ("" + dao_loaisp.layLoaiSPBangTen(loai).getMaLoaiSP());
        String txtKichThuoc = dao_kichthuoc.layKichThuocBangTen(kichThuoc) == null ? "" : (""+ dao_kichthuoc.layKichThuocBangTen(kichThuoc).getMaKichThuoc());
        String txtMauSac = dao_mausac.layMauSacBangTen(mauSac) == null ? "" : ("" + dao_mausac.layMauSacBangTen(mauSac).getMaMau());
        String txtChatLieu = dao_chatlieu.layChatLieuBangTen(chatLieu) == null ? "" : (""+dao_chatlieu.layChatLieuBangTen(chatLieu).getMaChatLieu());
         String sql = "select * from SanPham where maLoaiSP like N'%" + txtLoaiSP + "%' and maKichThuoc like N'%" 
                + txtKichThuoc + "%' and maMau like N'%" + txtMauSac +  "%' and maChatLieu like N'%" + txtChatLieu + "%'";
        try {
            ConnectDB.getInstance();
            Connection con  = ConnectDB.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setGiaGoc(rs.getDouble("giaGoc"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setHinhAnh(rs.getBytes("hinhAnh"));
                sp.setNgayNhap(rs.getDate("ngayNhap"));
                sp.setHanSD(rs.getDate("hanSD"));
                NhaCungCap ncc = dao_ncc.layNhaCungCapBangMa(rs.getString("maNCC"));
                sp.setNhaCungCap(ncc);
                ChatLieu cl =  dao_chatlieu.layChatLieuBangMa(rs.getString("maChatLieu"));
                sp.setChatLieu(cl);
                MauSac ms =  dao_mausac.layMauSacBangMa(rs.getString("maMau"));
                sp.setMauSac(ms);
                KichThuoc kt = dao_kichthuoc.layKichThuocBangMa(rs.getString("maKichThuoc"));
                sp.setKichThuoc(kt);
                LoaiSanPham lsp = dao_loaisp.layLoaiSPBangMa(rs.getString("maLoaiSP"));
                sp.setLoaiSanPham(lsp);
                spCanTim.add(sp);
            }            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return spCanTim;
        
    }
    
     public boolean capNhatSoLuong(String maSP, int soLuong) {
        int n = -1;
        String sql = "update SanPham  set soLuong = ? where maSP = ?";
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement pstmt = con.prepareCall(sql);
            pstmt.setInt(1, soLuong);
            pstmt.setString(2, maSP);
            n = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }
     
     public class thongKeSPBanChay{
         private int slBan ;
         private String maSP;

        public thongKeSPBanChay(int slBan, String maSP) {
            this.slBan = slBan;
            this.maSP = maSP;
        }
            public thongKeSPBanChay() {
        }

        public int getSlBan() {
            return slBan;
        }

        public void setSlBan(int slBan) {
            this.slBan = slBan;
        }

        public String getMaSP() {
            return maSP;
        }

        public void setMaSP(String maSP) {
            this.maSP = maSP;
        }
            
     }
     public ArrayList<thongKeSPBanChay> thongKeSPBanChay(String a, String b) {
        tkSPBC = new ArrayList<thongKeSPBanChay>();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stemnt = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            String sql = "SELECT Sum(soLuong) as slBan\n"
                    + "      ,maSP\n"
                    + "  FROM [BHTT].[dbo].[ChiTietHoaDon]\n"
                    + "  where   maHD in   ( select maHD\n"
                    + "                    from HoaDon\n"
                    + "                    where HoaDon.ngayLap  >= ? and HoaDon.ngayLap  <= ?)\n"
                    + "  Group by maSP\n"
                    + "  Order by slBan desc	";
            stemnt = con.prepareStatement(sql);
            stemnt.setString(1, a);
            stemnt.setString(2, b);

            ResultSet rs = stemnt.executeQuery();
            while (rs.next()) {
                int slBan = rs.getInt("slBan");
                String maSP = rs.getString("maSP");
                thongKeSPBanChay spbc= new thongKeSPBanChay(slBan, maSP);
                tkSPBC.add(spbc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tkSPBC;
    }
     
     
     
     
     
     
     
     
     
//    public ArrayList<SanPham> LocSP() {
//        ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
//         try{
//           
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "select * from SanPham";
//            java.sql.Statement statement = con.createStatement();
//            java.sql.ResultSet rs = statement.executeQuery(sql);
//        
//        } catch (Exception e) {
//            e.printStackTrace();
//        
//        }
//        return null;
//        
//    }
    ///
//    public ArrayList<SanPham> getDSTheoLoc(String maMau, String maChatLieu, String maKichThuoc,String maloaiSP) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		ArrayList<SanPham> ds = new ArrayList<SanPham>();
//		PreparedStatement pstm = null;
//		try {
//			pstm = con.prepareStatement("select * from SanPham where maMau = ? and maKichThuoc=? and maLoaiSP =? and maChatLieu=?");
//			pstm.setString(1, maMau);
//                        pstm.setString(2, maKichThuoc);
//			pstm.setString(3, maloaiSP);
//			pstm.setString(4, maChatLieu);
//			ResultSet rs = pstm.executeQuery();
//			while (rs.next()) {
//                        String maSP= rs.getString("maSP");
//                String tenSP= rs.getString("tenSP"); 
//                double gia= rs.getDouble("giaGoc");
//                int sl= rs.getInt("soLuong");
//                byte[] hinhanh= rs.getBytes("hinhAnh");
//                String moTa= rs.getString("moTa");
//                java.sql.Date ngayN= rs.getDate("ngayNhap");
//                Date ngayNhap = new Date(ngayN.getTime());
//                
//                java.sql.Date han= rs.getDate("hanSD");
//                Date hanSD = new Date(han.getTime());
//                String maNCC= rs.getString("maNCC");
//                String mau= rs.getString("maMau");
//                String kt= rs.getString("maKichThuoc");
//                String loiSP= rs.getString("maLoaiSP");
//                NhaCungCap ncc = dao_ncc.layNhaCungCapBangMa(maNCC);
//                LoaiSanPham lsp = dao_lsp.layLoaiSPBangMa(loiSP);
//                ChatLieu chatl= dao_cl.layChatLieuBangMa(rs.getString("maChatLieu"));
//                MauSac ms= dao_ms.layMauSacBangMa(rs.getString("maMau"));
//                KichThuoc kicht = dao_kt.layKichThuocBangMa(kt);
//                LoaiSanPham loaiSP= new LoaiSanPham(loiSP);
//                SanPham sp = new SanPham(maSP, tenSP, gia, sl, hinhanh, moTa, ngayNhap, hanSD, ncc, chatl, ms, kicht, lsp);
//		ds.add(sp);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pstm.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
//		}
//		return ds;
//	}
}
