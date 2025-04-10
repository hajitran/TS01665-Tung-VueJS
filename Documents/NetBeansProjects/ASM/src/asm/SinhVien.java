/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm;

/**
 *
 * @author Tung
 */
public class SinhVien {
    private String MaSV,hoTen,diaChi,email,dienthoai,hinh;
    private boolean gioiTinh;

    public SinhVien() {
    }

    public SinhVien(String MaSV, String hoTen, String diaChi, String email, String dienthoai, String hinh, boolean gioiTinh) {
        this.MaSV = MaSV;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.email = email;
        this.dienthoai = dienthoai;
        this.hinh = hinh;
        this.gioiTinh = gioiTinh;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    
}
