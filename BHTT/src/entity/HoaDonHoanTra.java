/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoanTra;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bohie
 */
public class HoaDonHoanTra {
    private String maHDHT;
    private Date ngayHoanTra;
    private HoaDon hoaDon;
    private NhanVien nhanVien;

    public HoaDonHoanTra() {
    }

    public HoaDonHoanTra(String maHDHT, Date ngayHoanTra, HoaDon hoaDon, NhanVien nhanVien) {
        this.maHDHT = maHDHT;
        this.ngayHoanTra = ngayHoanTra;
        this.hoaDon = hoaDon;
        this.nhanVien = nhanVien;
    }

    public HoaDonHoanTra(String maHDHT) {
        this.maHDHT = maHDHT;
    }

    public String getMaHDHT() {
        return maHDHT;
    }

    public void setMaHDHT(String maHDHT) {
        this.maHDHT = maHDHT;
    }

    public Date getNgayHoanTra() {
        return ngayHoanTra;
    }

    public void setNgayHoanTra(Date ngayHoanTra) {
        this.ngayHoanTra = ngayHoanTra;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.maHDHT);
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
        final HoaDonHoanTra other = (HoaDonHoanTra) obj;
        return Objects.equals(this.maHDHT, other.maHDHT);
    }
    
    public double tongTienHoan(){
        double tt=0;
        DAO_ChiTietHoanTra ct_dao=new DAO_ChiTietHoanTra();
        List<ChiTietHoanTra> list= ct_dao.layDSCTHTBangMa(getMaHDHT().toString());
        for (ChiTietHoanTra ct:list){
            tt= tt+ct.tinhThanhTien();
        }
        return tt;
        
    }




    @Override
    public String toString() {
        return "HoaDonHoanTra{" + "maHDHT=" + maHDHT + ", ngayHoanTra=" + ngayHoanTra + ", hoaDon=" + hoaDon + ", nhanVien=" + nhanVien +'}';
    }
    
}
