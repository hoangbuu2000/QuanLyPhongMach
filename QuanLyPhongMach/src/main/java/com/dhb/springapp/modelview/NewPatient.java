package com.dhb.springapp.modelview;

public class NewPatient {
    private String tenBenhNhan;
    private String dienThoai;
    private Object[] loaiBenh;

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public Object[] getLoaiBenh() {
        return loaiBenh;
    }

    public void setLoaiBenh(Object[] loaiBenh) {
        this.loaiBenh = loaiBenh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
