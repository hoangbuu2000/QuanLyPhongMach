package com.dhb.springapp.service.implement;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.modelview.Appointment;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.ILoaiBenhRepository;
import com.dhb.springapp.repository.IPhieuKhamBenhRepository;
import com.dhb.springapp.service.IPhieuKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhieuKhamBenhService extends GenericService<PhieuKhamBenh> implements IPhieuKhamBenhService {
    private IPhieuKhamBenhRepository phieuKhamBenhRepository;

    @Autowired
    public PhieuKhamBenhService(@Qualifier("phieuKhamBenhRepository") IGenericRepository<PhieuKhamBenh> iGenericRepository) {
        super(iGenericRepository);
        this.phieuKhamBenhRepository = (IPhieuKhamBenhRepository) iGenericRepository;
    }

    @Override
    public List<Appointment> getAllAppointmentDESC(SimpleDateFormat format) {
        List<Appointment> appointmentList = new ArrayList<>();
        getAllOrderBy(PhieuKhamBenh.class, "id", Order.desc)
                .forEach(p -> {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentID(String.valueOf(p.getId()));
                    appointment.setPatientName(String.format("%s %s", p.getBenhNhan().getHo(), p.getBenhNhan().getTen()));
                    appointment.setDoctorName(String.format("%s %s", p.getBacSi().getHo(), p.getBacSi().getTen()));
                    appointment.setAge(String.valueOf(p.getBenhNhan().getTuoi()));
                    appointment.setAppointmentDate(format.format(p.getNgayKham()));
                    appointment.setAppointmentTime(p.getCaKhamBenh().getMoTa());
                    appointment.setLoaiBenhList(p.getDsLoaiBenh());

                    appointmentList.add(appointment);
                });
        return appointmentList;
    }
}
