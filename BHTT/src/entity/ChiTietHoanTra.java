/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.DAO_ChiTietHoanTra;
import java.util.Objects;

/**
 *
 * @author bohie
 */
public class ChiTietHoanTra {
    private int soLuong;
    private String lyDoHoanTra;
    private SanPham sanPham;
    private HoaDonHoanTra hoaDonHoanTra;

    public ChiTietHoanTra(int soLuong, String lyDoHoanTra, SanPham sanPham, HoaDonHoanTra hoaDonHoanTra) {
        this.soLuong = soLuong;
        this.lyDoHoanTra = lyDoHoanTra;
        this.sanPham = sanPham;
        this.hoaDonHoanTra = hoaDonHoanTra;
    }

    

    public ChiTietHoanTra() {
    }

    public ChiTietHoanTra(SanPham sanPham, HoaDonHoanTra hoaDonHoanTra) {
        this.sanPham = sanPham;
        this.hoaDonHoanTra = hoaDonHoanTra;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public HoaDonHoanTra getHoaDonHoanTra() {
        return hoaDonHoanTra;
    }

    public void setHoaDonHoanTra(HoaDonHoanTra hoaDonHoanTra) {
        this.hoaDonHoanTra = hoaDonHoanTra;
    }

    public String getLyDoHoanTra() {
        return lyDoHoanTra;
    }

    public void setLyDoHoanTra(String lyDoHoanTra) {
        this.lyDoHoanTra = lyDoHoanTra;
    }
    
    public double tinhThanhTien(){
        double tt=0 ;
        tt=getSanPham().getGiaGoc() * getSoLuong();
        
        return tt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.sanPham);
        hash = 37 * hash + Objects.hashCode(this.hoaDonHoanTra);
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
        final ChiTietHoanTra other = (ChiTietHoanTra) obj;
        if (!Objects.equals(this.sanPham, other.sanPham)) {
            return false;
        }
        return Objects.equals(this.hoaDonHoanTra, other.hoaDonHoanTra);
    }

    @Override
    public String toString() {
        return "ChiTietHoanTra{" + "soLuong=" + soLuong + ", lyDoHoanTra=" + lyDoHoanTra + ", sanPham=" + sanPham + ", hoaDonHoanTra=" + hoaDonHoanTra + '}';
    }

    
    
}
