package com.dhb.springapp.Utils;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.modelview.NewPatient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PatientUtil {
    public static List<NewPatient> getNewPatients(List<BenhNhan> danhSachBenhNhan) {
        List<NewPatient> result = new ArrayList<>();
        List<String> loaiBenh = new ArrayList<>();
        danhSachBenhNhan.forEach(b -> {
            NewPatient newPatient = new NewPatient();
            newPatient.setTenBenhNhan(b.getTen());
            newPatient.setDienThoai(b.getDienThoai());
            newPatient.setEmail(b.getEmail());
            b.getDsPhieuKhamBenh().stream().reduce((first, second) -> second).get().getDsLoaiBenh().forEach(l -> {
                loaiBenh.add(l.getTenBenh());
            });
            newPatient.setLoaiBenh(new HashSet<>(loaiBenh));
            result.add(newPatient);
        });
        return result;
    }
}
