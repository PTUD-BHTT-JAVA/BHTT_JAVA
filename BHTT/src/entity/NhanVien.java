/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Anh Thu
 */
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String CMND;
    private String soDienThoai;
    private boolean gioiTinh;
    private double luongCoBan;
    private LoaiNhanVien loaiNhanVien;

    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }
    

    public NhanVien(String maNV, String tenNV, String CMND, String soDienThoai, boolean gioiTinh, double luongCoBan, LoaiNhanVien loaiNhanVien) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.CMND = CMND;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.luongCoBan = luongCoBan;
        this.loaiNhanVien = loaiNhanVien;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public LoaiNhanVien getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", CMND=" + CMND + ", soDienThoai=" + soDienThoai + ", gioiTinh=" + gioiTinh + ", luongCoBan=" + luongCoBan + ", loaiNhanVien=" + loaiNhanVien + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.maNV);
        hash = 83 * hash + Objects.hashCode(this.tenNV);
        hash = 83 * hash + Objects.hashCode(this.CMND);
        hash = 83 * hash + Objects.hashCode(this.soDienThoai);
        hash = 83 * hash + (this.gioiTinh ? 1 : 0);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.luongCoBan) ^ (Double.doubleToLongBits(this.luongCoBan) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.loaiNhanVien);
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
        final NhanVien other = (NhanVien) obj;
        if (this.gioiTinh != other.gioiTinh) {
            return false;
        }
        if (Double.doubleToLongBits(this.luongCoBan) != Double.doubleToLongBits(other.luongCoBan)) {
            return false;
        }
        if (!Objects.equals(this.maNV, other.maNV)) {
            return false;
        }
        if (!Objects.equals(this.tenNV, other.tenNV)) {
            return false;
        }
        if (!Objects.equals(this.CMND, other.CMND)) {
            return false;
        }
        if (!Objects.equals(this.soDienThoai, other.soDienThoai)) {
            return false;
        }
        return Objects.equals(this.loaiNhanVien, other.loaiNhanVien);
    }
    
    
    
}
