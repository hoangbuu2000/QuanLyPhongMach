package com.dhb.springapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "phieukhambenh")
public class PhieuKhamBenh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "NgayKham")
    private Date ngayKham;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "ThanhToan")
    private boolean thanhToan;
    @ManyToOne
    @JoinColumn(name = "MaBenhNhan")
    private BenhNhan benhNhan;
    @ManyToOne
    @JoinColumn(name = "MaBacSi")
    private BacSi bacSi;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "chitietphieukhambenh",
            joinColumns = {
                    @JoinColumn(name = "MaPhieuKhamBenh")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "MaLoaiBenh")
            }
    )
    private Set<LoaiBenh> dsLoaiBenh;
    @ManyToOne
    @JoinColumn(name = "MaCaKham")
    private CaKhamBenh caKhamBenh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public Set<LoaiBenh> getDsLoaiBenh() {
        return dsLoaiBenh;
    }

    public void setDsLoaiBenh(Set<LoaiBenh> dsLoaiBenh) {
        this.dsLoaiBenh = dsLoaiBenh;
    }

    public CaKhamBenh getCaKhamBenh() {
        return caKhamBenh;
    }

    public void setCaKhamBenh(CaKhamBenh caKhamBenh) {
        this.caKhamBenh = caKhamBenh;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }
}
