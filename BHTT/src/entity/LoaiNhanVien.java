/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Anh Thu
 */
public class LoaiNhanVien {
    private String maLoaiNV;
    private String tenLoaiNV;

    public LoaiNhanVien() {
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

    
    
}
