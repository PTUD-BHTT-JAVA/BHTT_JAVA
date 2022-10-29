/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author bohie
 */
public class ChiTietHoaDon {
    private int soLuong;
    private double VAT;
    private double tongTien;
    private double tienThoi;
    private HoaDon hoaDon;
    private SanPham sanPham;

    public ChiTietHoaDon(int soLuong, double VAT, double tongTien, double tienThoi, HoaDon hoaDon, SanPham sanPham) {
        this.soLuong = soLuong;
        this.VAT = VAT;
        this.tongTien = tongTien;
        this.tienThoi = tienThoi;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }

    public ChiTietHoaDon() {
    }

    public double getTienThoi() {
        return tienThoi;
    }

    public void setTienThoi(double tienThoi) {
        this.tienThoi = tienThoi;
    }

   
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.hoaDon);
        hash = 17 * hash + Objects.hashCode(this.sanPham);
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
        final ChiTietHoaDon other = (ChiTietHoaDon) obj;
        if (!Objects.equals(this.hoaDon, other.hoaDon)) {
            return false;
        }
        return Objects.equals(this.sanPham, other.sanPham);
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "soLuong=" + soLuong + ", VAT=" + VAT + ", tongTien=" + tongTien + ", tienThoi=" + tienThoi + ", hoaDon=" + hoaDon + ", sanPham=" + sanPham + '}';
    }
    
   
    
}
