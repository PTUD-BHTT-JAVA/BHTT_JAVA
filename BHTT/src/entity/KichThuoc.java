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
public class KichThuoc {
    private String maKichThuoc;
    private String tenKichThuoc;

    public KichThuoc() {
    }

    public KichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }
    
     
    public KichThuoc(String maKichThuoc, String tenKichThuoc) {
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
    }

    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    @Override
    public String toString() {
        return "KichThuoc{" + "maKichThuoc=" + maKichThuoc + ", tenKichThuoc=" + tenKichThuoc + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.maKichThuoc);
        hash = 89 * hash + Objects.hashCode(this.tenKichThuoc);
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
        final KichThuoc other = (KichThuoc) obj;
        if (!Objects.equals(this.maKichThuoc, other.maKichThuoc)) {
            return false;
        }
        return Objects.equals(this.tenKichThuoc, other.tenKichThuoc);
    }
    
    
}
