package com.dhb.springapp.modelview;

import java.util.Set;

public class NewPatient {
    private String tenBenhNhan;
    private String dienThoai;
    private String email;
    private Set<String> loaiBenh;

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public Set<String> getLoaiBenh() {
        return loaiBenh;
    }

    public void setLoaiBenh(Set<String> loaiBenh) {
        this.loaiBenh = loaiBenh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
