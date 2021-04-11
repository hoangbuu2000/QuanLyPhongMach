package com.dhb.springapp.models;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "loaibenh")
public class LoaiBenh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "TenBenh", length = 45, nullable = false)
    private String tenBenh;
    @Column(name = "MoTa", nullable = false)
    private String moTa;
    @Column(name = "DonGia", nullable = false)
    private BigDecimal donGia;
    @OneToMany(mappedBy = "loaiBenh", fetch = FetchType.LAZY)
    private Set<ToaThuoc> dsToaThuoc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBenh() {
        return tenBenh;
    }

    public void setTenBenh(String tenBenh) {
        this.tenBenh = tenBenh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Set<ToaThuoc> getDsToaThuoc() {
        return dsToaThuoc;
    }

    public void setDsToaThuoc(Set<ToaThuoc> dsToaThuoc) {
        this.dsToaThuoc = dsToaThuoc;
    }
}
