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
public class MauSac {
    private String maMau;
    private String tenMau;

    public MauSac() {
    }

    public MauSac(String maMau) {
        this.maMau = maMau;
    }

    public MauSac(String maMau, String tenMau) {
        this.maMau = maMau;
        this.tenMau = tenMau;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    @Override
    public String toString() {
        return "MauSac{" + "maMau=" + maMau + ", tenMau=" + tenMau + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.maMau);
        hash = 47 * hash + Objects.hashCode(this.tenMau);
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
        final MauSac other = (MauSac) obj;
        if (!Objects.equals(this.maMau, other.maMau)) {
            return false;
        }
        return Objects.equals(this.tenMau, other.tenMau);
    }
    
    
}
