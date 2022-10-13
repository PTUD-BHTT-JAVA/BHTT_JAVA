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
public class LoaiSanPham {
    private String maLoaiSP;
    private String tenLoaiSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public LoaiSanPham(String maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" + "maLoaiSP=" + maLoaiSP + ", tenLoaiSP=" + tenLoaiSP + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.maLoaiSP);
        hash = 31 * hash + Objects.hashCode(this.tenLoaiSP);
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
        final LoaiSanPham other = (LoaiSanPham) obj;
        if (!Objects.equals(this.maLoaiSP, other.maLoaiSP)) {
            return false;
        }
        return Objects.equals(this.tenLoaiSP, other.tenLoaiSP);
    }
    
    
}
