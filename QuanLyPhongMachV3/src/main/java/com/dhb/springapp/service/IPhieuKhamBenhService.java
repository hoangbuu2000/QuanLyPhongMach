package com.dhb.springapp.service;

import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.modelview.Appointment;

import java.text.SimpleDateFormat;
import java.util.List;

public interface IPhieuKhamBenhService extends IGenericService<PhieuKhamBenh> {
    List<Appointment> getAllAppointmentDESC(SimpleDateFormat format);
}
