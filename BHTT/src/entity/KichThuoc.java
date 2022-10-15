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
    private String moTa;
    public KichThuoc() {
    }

    public KichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public KichThuoc(String maKichThuoc, String tenKichThuoc, String moTa) {
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
        this.moTa = moTa;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.maKichThuoc);
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
        return Objects.equals(this.maKichThuoc, other.maKichThuoc);
    }

    @Override
    public String toString() {
        return "KichThuoc{" + "maKichThuoc=" + maKichThuoc + ", tenKichThuoc=" + tenKichThuoc + ", moTa=" + moTa + '}';
    }
    
    
    
}
