package com.dhb.springapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "hoadon")
public class HoaDon implements Serializable {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "NgayXuat")
    private Date ngayXuat;
    @Column(name = "TongTien")
    private BigDecimal tongTien;
    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;
    @OneToOne
    @JoinColumn(name = "MaToaThuoc", unique = true)
    private ToaThuoc toaThuoc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ToaThuoc getToaThuoc() {
        return toaThuoc;
    }

    public void setToaThuoc(ToaThuoc toaThuoc) {
        this.toaThuoc = toaThuoc;
    }
}
