package com.dhb.springapp.modelview;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.models.Thuoc;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class AddPrescription {
    private String id;
    @NotEmpty(message = "validation.date.not.empty")
    private String date;
    @NotNull(message = "validation.doctor.not.null")
    private BacSi doctor;
    @NotNull(message = "validation.patient.not.null")
    private BenhNhan patient;
    @NotNull(message = "validation.disease.not.null")
    private LoaiBenh disease;
    private Map<Thuoc, Integer> medicines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BacSi getDoctor() {
        return doctor;
    }

    public void setDoctor(BacSi doctor) {
        this.doctor = doctor;
    }

    public BenhNhan getPatient() {
        return patient;
    }

    public void setPatient(BenhNhan patient) {
        this.patient = patient;
    }

    public LoaiBenh getDisease() {
        return disease;
    }

    public void setDisease(LoaiBenh disease) {
        this.disease = disease;
    }

    public Map<Thuoc, Integer> getMedicines() {
        return medicines;
    }

    public void setMedicines(Map<Thuoc, Integer> medicines) {
        this.medicines = medicines;
    }
}
