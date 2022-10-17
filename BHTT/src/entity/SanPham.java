/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author bohie
 */
public class SanPham {
    private String maSP;
    private String tenSP;
    private double giaGoc;
    private int soLuong;
    private byte[] hinhAnh;
    private String moTa;
    private Date ngayNhap;
    private Date hanSD;
    private NhaCungCap nhaCungCap;
    private ChatLieu chatLieu;
    private MauSac mauSac;
    private KichThuoc kichThuoc;
    private LoaiSanPham loaiSanPham;

    public SanPham(String maSP, String tenSP, double giaGoc, int soLuong, byte[] hinhAnh, String moTa, Date ngayNhap, Date hanSD, NhaCungCap nhaCungCap, ChatLieu chatLieu, MauSac mauSac, KichThuoc kichThuoc, LoaiSanPham loaiSanPham) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaGoc = giaGoc;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.ngayNhap = ngayNhap;
        this.hanSD = hanSD;
        this.nhaCungCap = nhaCungCap;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.loaiSanPham = loaiSanPham;
    }

    public SanPham() {
    }

    public SanPham(String maSP) {
        this.maSP = maSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(double giaGoc) {
        this.giaGoc = giaGoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Date getHanSD() {
        return hanSD;
    }

    public void setHanSD(Date hanSD) {
        this.hanSD = hanSD;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maSP);
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
        final SanPham other = (SanPham) obj;
        return Objects.equals(this.maSP, other.maSP);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "SanPham{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", giaGoc=" + giaGoc + ", soLuong=" + soLuong + ", hinhAnh=" + hinhAnh + ", moTa=" + moTa + ", ngayNhap=" + sdf.format(ngayNhap) + ", hanSD=" + sdf.format(hanSD) + ", nhaCungCap=" + nhaCungCap + ", chatLieu=" + chatLieu + ", mauSac=" + mauSac + ", kichThuoc=" + kichThuoc + ", loaiSanPham=" + loaiSanPham + '}';
    }
    
}
