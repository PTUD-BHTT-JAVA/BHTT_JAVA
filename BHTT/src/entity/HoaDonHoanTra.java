/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author bohie
 */
public class HoaDonHoanTra {
    private String maHDHT;
    private Date ngayHoanTra;
    private HoaDon hoaDon;

    public HoaDonHoanTra(String maHDHT, Date ngayHoanTra, HoaDon hoaDon) {
        this.maHDHT = maHDHT;
        this.ngayHoanTra = ngayHoanTra;
        this.hoaDon = hoaDon;
    }

    public HoaDonHoanTra(String maHDHT) {
        this.maHDHT = maHDHT;
    }

    public HoaDonHoanTra() {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maHDHT);
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

    @Override
    public String toString() {
        return "HoaDonHoanTra{" + "maHDHT=" + maHDHT + ", ngayHoanTra=" + ngayHoanTra + ", hoaDon=" + hoaDon + '}';
    }
    
}
