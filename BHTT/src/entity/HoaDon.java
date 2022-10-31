/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.DAO_ChiTietHoaDon;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bohie
 */
public class HoaDon {
    private String maHD;
    private Date ngayLap;
    private double tienKhachDua;
    private String diaChi;
    private NhanVien nhanVien;
    private KhachHang khachHang;

    public HoaDon(String maHD, Date ngayLap, double tienKhachDua, String diaChi, NhanVien nhanVien, KhachHang khachHang) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tienKhachDua = tienKhachDua;
        this.diaChi = diaChi;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
    }

    public HoaDon() {
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHD() {
        return maHD;
    }
    
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayLap=" + ngayLap + ", tienKhachDua=" + tienKhachDua + ", diaChi=" + diaChi + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.maHD);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHD, other.maHD);
    }
    
    public double thanhTienVIP(HoaDon hd){
        double tt=0;
        DAO_ChiTietHoaDon dao_cthd = new DAO_ChiTietHoaDon();
        List<ChiTietHoaDon> list=dao_cthd.layDSHDBangMa(hd.getMaHD());
        if (hd.getKhachHang().getLoaiKhachHang().getMaLoaiKH().equals("LKH001")){
                for (ChiTietHoaDon ct:list){
                    tt+=ct.getTongTien();
            }   
        }
            tt=tt-(tt*10/100) + tt*0.05;
        return tt;
    }
    
    public double thanhTienThuong(HoaDon hd){
        double tt=0;
        DAO_ChiTietHoaDon dao_cthd = new DAO_ChiTietHoaDon();
        List<ChiTietHoaDon> list=dao_cthd.layDSHDBangMa(hd.getMaHD());
        if (hd.getKhachHang().getLoaiKhachHang().getMaLoaiKH().equals("LKH002")){
                for (ChiTietHoaDon ct:list){
                    tt+=ct.getTongTien();
            }

        }
        tt += tt*0.05;
        return tt;
    }

    
}
