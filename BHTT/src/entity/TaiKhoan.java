/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Anh Thu
 */
public class TaiKhoan {
    private String tenDN;
    private String matKhau;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDN, String matKhau) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
