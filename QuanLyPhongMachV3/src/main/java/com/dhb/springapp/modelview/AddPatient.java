package com.dhb.springapp.modelview;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.LoaiBenh;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Set;

public class AddPatient {
    @NotEmpty(message = "{validation.last}")
    private String ho;
    @NotEmpty(message = "{validation.first}")
    private String ten;
    @Min(value = 1, message = "{validation.age.min}")
    @Max(value = 100, message = "{validation.age.max}")
    private int tuoi;
    @NotEmpty(message = "{validation.email}")
    @Email(message = "{validation.email.syntax}")
    private String email;
    @Length(min = 10, max = 10, message = "{validation.phone.length}")
    private String dienThoai;
    @NotEmpty(message = "{validation.dob}")
    private String ngaySinh;
    @NotEmpty(message = "{validation.gender}")
    private String gioiTinh;
    @NotNull(message = "{validation.shift}")
    private CaKhamBenh caKham;
    private BacSi bacSi;
    @Size(min = 1, message = "{validation.disease.size}")
    private Set<LoaiBenh> loaiBenhList;
    @NotEmpty(message = "{validation.appointment}")
    private String ngayKham;
    @NotNull(message = "{validation.payment}")
    private boolean thanhToan;

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public CaKhamBenh getCaKham() {
        return caKham;
    }

    public void setCaKham(CaKhamBenh caKham) {
        this.caKham = caKham;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public Set<LoaiBenh> getLoaiBenhList() {
        return loaiBenhList;
    }

    public void setLoaiBenhList(Set<LoaiBenh> loaiBenhList) {
        this.loaiBenhList = loaiBenhList;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }
}
