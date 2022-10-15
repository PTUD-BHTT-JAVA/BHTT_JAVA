/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author bohie
 */
public class HoaDon {
    private String maHD;
    private LocalDate ngayLap;
    private double tienKhachDua;
    private String diaChi;
    private NhanVien nhanVien;
    private KhachHang khachHang;

    public HoaDon(String maHD, LocalDate ngayLap, double tienKhachDua, String diaChi, NhanVien nhanVien, KhachHang khachHang) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tienKhachDua = tienKhachDua;
        this.diaChi = diaChi;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
    }
    public HoaDon(String maHD) {
        this.maHD = maHD;
      
    }
    public HoaDon() {
      
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
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
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.maHD);
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

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayLap=" + ngayLap + ", tienKhachDua=" + tienKhachDua + ", diaChi=" + diaChi + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + '}';
    }
    
}
