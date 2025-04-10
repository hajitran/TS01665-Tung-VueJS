/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm;

/**
 *
 * @author Tung
 */
public class Student {
    public String Masv;
    public String name;
    public double Tienganh;
    public double Tinhoc;
    public double GDTC;
    public double DiemTb;
    
    public Student() {
    }

    public String getMasv() {
        return Masv;
    }

    public void setMasv(String Masv) {
        this.Masv = Masv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTienganh() {
        return Tienganh;
    }

    public void setTienganh(double Tienganh) {
        this.Tienganh = Tienganh;
    }

    public double getTinhoc() {
        return Tinhoc;
    }

    public void setTinhoc(double Tinhoc) {
        this.Tinhoc = Tinhoc;
    }

    public double getGDTC() {
        return GDTC;
    }

    public void setGDTC(double GDTC) {
        this.GDTC = GDTC;
    }

    public double getDiemTb() {
        return (Tienganh + Tinhoc + GDTC)/3;
    }

    public void setDiemTb(double DiemTb) {
        this.DiemTb = DiemTb;
    }

}
