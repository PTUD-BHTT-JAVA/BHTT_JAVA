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
public class LoaiNhanVien {
    private String maLoaiNV;
    private String tenLoaiNV;

    public LoaiNhanVien() {
    }

    public LoaiNhanVien(String maLoaiNV) {
        this.maLoaiNV = maLoaiNV;
    }
    
    
    
    public LoaiNhanVien(String maLoaiNV, String tenLoaiNV) {
        this.maLoaiNV = maLoaiNV;
        this.tenLoaiNV = tenLoaiNV;
    }

    public String getMaLoaiNV() {
        return maLoaiNV;
    }

    public String getTenLoaiNV() {
        return tenLoaiNV;
    }

    public void setMaLoaiNV(String maLoaiNV) {
        this.maLoaiNV = maLoaiNV;
    }

    public void setTenLoaiNV(String tenLoaiNV) {
        this.tenLoaiNV = tenLoaiNV;
    }

    @Override
    public String toString() {
        return "LoaiNhanVien{" + "maLoaiNV=" + maLoaiNV + ", tenLoaiNV=" + tenLoaiNV + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.maLoaiNV);
        hash = 89 * hash + Objects.hashCode(this.tenLoaiNV);
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
        final LoaiNhanVien other = (LoaiNhanVien) obj;
        if (!Objects.equals(this.maLoaiNV, other.maLoaiNV)) {
            return false;
        }
        return Objects.equals(this.tenLoaiNV, other.tenLoaiNV);
    }

    
    
}
