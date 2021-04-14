package com.dhb.springapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "chitietcakhambenh")
public class ChiTietCaKhamBenh implements Serializable {
    @Id
    @Column(name = "NgayKhamBenh")
    private Date ngayKhamBenh;
    @Id
    @ManyToOne
    @JoinColumn(name = "MaCa")
    private CaKhamBenh caKhamBenh;
    @Id
    @ManyToOne
    @JoinColumn(name = "MaBacSi")
    private BacSi bacSi;

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
