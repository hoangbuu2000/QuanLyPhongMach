package com.dhb.springapp.modelview;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddSchedule {
    @NotNull
    private BacSi bacSi;
    @NotNull
    private CaKhamBenh caKhamBenh;
    @NotEmpty
    private String ngayKhamBenh;

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public CaKhamBenh getCaKhamBenh() {
        return caKhamBenh;
    }

    public void setCaKhamBenh(CaKhamBenh caKhamBenh) {
        this.caKhamBenh = caKhamBenh;
    }

    public String getNgayKhamBenh() {
        return ngayKhamBenh;
    }

    public void setNgayKhamBenh(String ngayKhamBenh) {
        this.ngayKhamBenh = ngayKhamBenh;
    }
}
