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
public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String email;
    private String soDienThoai;
    private String diaChi;
    public NhaCungCap() {
        
    }

    public NhaCungCap(String maNCC, String tenNCC, String email, String soDienThoai, String diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public NhaCungCap(String maNCC) {
        this.maNCC = maNCC;
    }
    
    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", email=" + email + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.maNCC);
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
        final NhaCungCap other = (NhaCungCap) obj;
        return Objects.equals(this.maNCC, other.maNCC);
    }
    
    
}
