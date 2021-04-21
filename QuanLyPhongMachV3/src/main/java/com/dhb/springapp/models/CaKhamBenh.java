package com.dhb.springapp.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cakhambenh")
public class CaKhamBenh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "TenCa")
    private String tenCa;
    @Column(name = "MoTa")
    private String moTa;
    @OneToMany(mappedBy = "caKhamBenh", fetch = FetchType.LAZY)
    private Set<PhieuKhamBenh> dsPhieuKhamBenh;
    @OneToMany(mappedBy = "caKhamBenh", fetch = FetchType.LAZY)
    private Set<ChiTietCaKhamBenh> dsChiTietCaKhamBenh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Set<PhieuKhamBenh> getDsPhieuKhamBenh() {
        return dsPhieuKhamBenh;
    }

    public void setDsPhieuKhamBenh(Set<PhieuKhamBenh> dsPhieuKhamBenh) {
        this.dsPhieuKhamBenh = dsPhieuKhamBenh;
    }

    public Set<ChiTietCaKhamBenh> getDsChiTietCaKhamBenh() {
        return dsChiTietCaKhamBenh;
    }

    public void setDsChiTietCaKhamBenh(Set<ChiTietCaKhamBenh> dsChiTietCaKhamBenh) {
        this.dsChiTietCaKhamBenh = dsChiTietCaKhamBenh;
    }
}
