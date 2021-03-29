package com.dhb.springapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "chitiettoathuoc")
public class ChiTietToaThuoc implements Serializable {
    @Id
    @Column(name = "MaToaThuoc", length = 36)
    private String maToaThuoc;
    @Id
    @Column(name = "MaThuoc")
    private int maThuoc;
    @Column(name = "SoLuong")
    private int soLuong;
    @Column(name = "DonGia")
    private BigDecimal donGia;
    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "MaToaThuoc")
    private ToaThuoc toaThuoc;
    @ManyToOne
    @MapsId
    @JoinColumn(name = "MaThuoc")
    private Thuoc thuoc;

    public String getMaToaThuoc() {
        return maToaThuoc;
    }

    public void setMaToaThuoc(String maToaThuoc) {
        this.maToaThuoc = maToaThuoc;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public ToaThuoc getToaThuoc() {
        return toaThuoc;
    }

    public void setToaThuoc(ToaThuoc toathuoc) {
        this.toaThuoc = toathuoc;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }
}
