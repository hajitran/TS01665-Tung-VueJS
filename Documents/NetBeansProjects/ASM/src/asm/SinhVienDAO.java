/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tung
 */
public class SinhVienDAO {
    List<SinhVien> ds = new ArrayList<>();

    public SinhVienDAO() {
    ds.add(new SinhVien("PS001", "Nguyễn Văn An", "an@gmail.com", "0901234569", 
           "99 ABC HQK", "C:\\Users\\Tung\\Documents\\NetBeansProjects\\ASM\\src\\images\\minh.jpg", false));
    
    ds.add(new SinhVien("PS002", "Nguyễn Văn Do", "bo@gmail.com", "0901234569", 
           "99 ABC HQK", "C:\\Users\\Tung\\Documents\\NetBeansProjects\\ASM\\src\\images\\obama.png", false));
    
    ds.add(new SinhVien("PS003", "Nguyễn Văn Can", "can@gmail.com", "0901234570", 
           "100 ABC HQK", "C:\\Users\\Tung\\Documents\\NetBeansProjects\\ASM\\src\\images\\obama.png", false));
    }
    
    public List<SinhVien> getList(){
        return ds;
    }
    
    public int add(SinhVien sv){
        for (SinhVien item : ds) {
            if (item.getMaSV().equalsIgnoreCase(sv.getMaSV())) {
                //da ton tai masv
                return -1;
            }
        }
        ds.add(sv);
        return 1;//them vao thanh cong  
    }
    
    public boolean del(String masv){
        boolean  kq = ds.removeIf(item -> item.getMaSV().equalsIgnoreCase(masv));
        return kq;
        //return ds.removeIf(item -> item.getMaSV().equalsIgnoreCase(masv));
    }
    
    public boolean update(SinhVien sv){
        for (SinhVien item : ds) {
            if (item.getMaSV().equalsIgnoreCase(sv.getMaSV())) {
                //neu trung thi tien hanh ghi de
                item.setHoTen(sv.getHoTen());
                item.setDiaChi(item.getDiaChi());
                item.setDienthoai(item.getDienthoai());
                item.setEmail(item.getEmail());
                item.setHinh(item.getHinh());
                item.setGioiTinh(sv.isGioiTinh());
                return true;
            }
        }
        return false;
    }
    
}
