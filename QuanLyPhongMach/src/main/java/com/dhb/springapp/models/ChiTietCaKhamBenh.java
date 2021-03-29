package com.dhb.springapp.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chitietcakhambenh")
public class ChiTietCaKhamBenh {
    @Id
    @Column(name = "MaCa")
    private int maCa;
    @Id
    @Column(name = "MaBacSi")
    private String maBacSi;
    @Id
    @Column(name = "NgayKhamBenh")
    private Date ngayKhamBenh;
    @ManyToOne
    @MapsId
    @JoinColumn(name = "MaCa")
    private CaKhamBenh caKhamBenh;
    @ManyToOne
    @MapsId
    @JoinColumn(name = "MaBacSi")
    private BacSi bacSi;

    public int getMaCa() {
        return maCa;
    }

    public void setMaCa(int maCa) {
        this.maCa = maCa;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public Date getNgayKhamBenh() {
        return ngayKhamBenh;
    }

    public void setNgayKhamBenh(Date ngayKhamBenh) {
        this.ngayKhamBenh = ngayKhamBenh;
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
