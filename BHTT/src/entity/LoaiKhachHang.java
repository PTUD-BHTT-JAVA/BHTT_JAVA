/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Trinh Cui Bap
 */
public class LoaiKhachHang {
    private String maLoaiKH;
    private String tenLoaiKH;

    public LoaiKhachHang() {
    }

    public LoaiKhachHang(String maLoaiKH, String tenLoaiKH) {
        this.maLoaiKH = maLoaiKH;
        this.tenLoaiKH = tenLoaiKH;
    }

    public LoaiKhachHang(String maLoaiKH) {
        this.maLoaiKH = maLoaiKH;
    }

    public String getMaLoaiKH() {
        return maLoaiKH;
    }

    public void setMaLoaiKH(String maLoaiKH) {
        this.maLoaiKH = maLoaiKH;
    }

    public String getTenLoai() {
        return tenLoaiKH;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoaiKH = tenLoai;
    }

    @Override
    public String toString() {
        return "LoaiKhachHang{" + "maLoaiKH=" + maLoaiKH + ", tenLoai=" + tenLoaiKH + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.maLoaiKH);
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
        final LoaiKhachHang other = (LoaiKhachHang) obj;
        return Objects.equals(this.maLoaiKH, other.maLoaiKH);
    }
    
}
