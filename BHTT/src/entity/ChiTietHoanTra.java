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
public class ChiTietHoanTra {
    private int soLuong;
    private SanPham sanPham;
    private HoaDonHoanTra hoaDonHoanTra;

    public ChiTietHoanTra(int soLuong, SanPham sanPham, HoaDonHoanTra hoaDonHoanTra) {
        this.soLuong = soLuong;
        this.sanPham = sanPham;
        this.hoaDonHoanTra = hoaDonHoanTra;
    }

    public ChiTietHoanTra() {
    }

    public ChiTietHoanTra(SanPham sanPham, HoaDonHoanTra hoaDonHoanTra) {
        this.sanPham = sanPham;
        this.hoaDonHoanTra = hoaDonHoanTra;
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
        return "ChiTietHoanTra{" + "soLuong=" + soLuong + ", sanPham=" + sanPham + ", hoaDonHoanTra=" + hoaDonHoanTra + '}';
    }
    
}
