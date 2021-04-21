package com.dhb.springapp.service;

import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.modelview.AddPrescription;

import java.text.SimpleDateFormat;

public interface IToaThuocService extends IGenericService<ToaThuoc> {
    AddPrescription castToModelView(ToaThuoc toaThuoc, SimpleDateFormat format);
    void addOrUpdate(String id, AddPrescription addPrescription, SimpleDateFormat format) throws Exception;
}
