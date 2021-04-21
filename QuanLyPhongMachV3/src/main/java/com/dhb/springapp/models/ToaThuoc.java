package com.dhb.springapp.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "toathuoc")
public class ToaThuoc implements Serializable {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "NgayKeToa")
    private Date ngayKeToa;
    @ManyToOne()
    @JoinColumn(name = "MaBacSi")
    private BacSi bacSi;
    @ManyToOne
    @JoinColumn(name = "MaBenhNhan")
    private BenhNhan benhNhan;
    @ManyToOne
    @JoinColumn(name = "MaLoaiBenh")
    private LoaiBenh loaiBenh;
    @OneToMany(mappedBy = "toaThuoc", fetch = FetchType.EAGER)
    private Set<ChiTietToaThuoc> dsChiTietToaThuoc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNgayKeToa() {
        return ngayKeToa;
    }

    public void setNgayKeToa(Date ngayKeToa) {
        this.ngayKeToa = ngayKeToa;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public LoaiBenh getLoaiBenh() {
        return loaiBenh;
    }

    public void setLoaiBenh(LoaiBenh loaiBenh) {
        this.loaiBenh = loaiBenh;
    }

    public Set<ChiTietToaThuoc> getDsChiTietToaThuoc() {
        return dsChiTietToaThuoc;
    }

    public void setDsChiTietToaThuoc(Set<ChiTietToaThuoc> dsChiTietToaThuoc) {
        this.dsChiTietToaThuoc = dsChiTietToaThuoc;
    }
}
