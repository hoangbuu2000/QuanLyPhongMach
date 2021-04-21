package com.dhb.springapp.modelview;

import com.dhb.springapp.models.LoaiBenh;

import java.util.List;
import java.util.Set;

public class Appointment {
    private String appointmentID;
    private String patientName;
    private String age;
    private String doctorName;
    private Set<LoaiBenh> loaiBenhList;
    private String appointmentDate;
    private String appointmentTime;

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Set<LoaiBenh> getLoaiBenhList() {
        return loaiBenhList;
    }

    public void setLoaiBenhList(Set<LoaiBenh> loaiBenhList) {
        this.loaiBenhList = loaiBenhList;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
