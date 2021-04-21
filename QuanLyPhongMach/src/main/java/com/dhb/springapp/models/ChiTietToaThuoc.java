package com.dhb.springapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "chitiettoathuoc")
public class ChiTietToaThuoc implements Serializable {
    @Column(name = "SoLuong")
    private int soLuong;
    @Column(name = "DonGia")
    private BigDecimal donGia;
    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;

    @Id
    @ManyToOne
    @JoinColumn(name = "MaToaThuoc")
    private ToaThuoc toaThuoc;
    @Id
    @ManyToOne
    @JoinColumn(name = "MaThuoc")
    private Thuoc thuoc;

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
