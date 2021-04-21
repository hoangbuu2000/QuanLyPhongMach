package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.*;
import com.dhb.springapp.modelview.AddPatient;
import com.dhb.springapp.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ControllerAdvice
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    IBenhNhanService iBenhNhanService;
    @Autowired
    IPhieuKhamBenhService iPhieuKhamBenhService;
    @Autowired
    ILoaiBenhService iLoaiBenhService;
    @Autowired
    ICaKhamBenhService iCaKhamBenhService;
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    IToaThuocService iToaThuocService;
    @Autowired
    IChiTietToaThuocService iChiTietToaThuocService;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("patientAct", "active");
        model.addAttribute("doctorAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
        model.addAttribute("appointmentAct", "");
        model.addAttribute("diseaseAct", "");
        model.addAttribute("medicineAct", "");

        model.addAttribute("danhSachLoaiBenh", iLoaiBenhService.getAll(LoaiBenh.class));
        model.addAttribute("danhSachCaKhamBenh", iCaKhamBenhService.getAll(CaKhamBenh.class));
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("patient", iBenhNhanService.getAll(BenhNhan.class));
        return "patient.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("patient", new AddPatient());
        return "patient.add";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("patient") @Valid AddPatient addPatient,
                             BindingResult result, ModelMap model) throws ParseException {
        if (!result.hasErrors()) {
            try {
                iBenhNhanService.themBenhNhanVaPhieuKhamBenh(addPatient, format);
                return "redirect:/patient";
            }
            catch (Exception e) {
                model.addAttribute("message", e.getMessage());
            }
        }

        return "patient.add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(name = "id") String id, ModelMap model) {
        BenhNhan benhNhan = iBenhNhanService.getById(BenhNhan.class, id);
        model.addAttribute("patient", benhNhan);
        model.addAttribute("diseases", iBenhNhanService.getBenhAnTheoBenhNhan(id));
        return "patient.details";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") String id, ModelMap model) {
        BenhNhan benhNhan = iBenhNhanService.getById(BenhNhan.class, id);
        model.addAttribute("patient", benhNhan);
        return "patient.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable(name = "id") String id,
                              @ModelAttribute("patient") BenhNhan editedPatient) {
        try {
            iBenhNhanService.update(editedPatient);
            return String.format("redirect:/patient/details/{%s}", id);
        }
        catch (HibernateError e) {
            return "patient.edit";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable(name = "id") String id) {
        if (id != null && !id.isEmpty()) {
            iBenhNhanService.delete(iBenhNhanService.getById(BenhNhan.class, id));
            return "redirect:/patient";
        }
        return "doctor.index";
    }

    @GetMapping("/ajax")
    public @ResponseBody String layBacSi(@RequestParam("date") String date,
                                       @RequestParam("shift") int shiftID) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<BacSi> ds = new ArrayList<>();
            iCaKhamBenhService.layBacSiTheoCaKham(shiftID, format.parse(date)).forEach(t -> {
                BacSi b = new BacSi();
                b.setId(t.getId());
                b.setTen(t.getTen());
                b.setHo(t.getHo());
                b.setDienThoai(t.getDienThoai());
                b.setEmail(t.getEmail());
                b.setGioiTinh(t.getGioiTinh());
                b.setNgaySinh(t.getNgaySinh());
                b.setImage(t.getImage());
                b.setQueQuan(t.getQueQuan());
                ds.add(b);
            });
            ajaxResponse = mapper.writeValueAsString(ds);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/ajax1")
    public @ResponseBody String layCaKham(@RequestParam("date") String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<CaKhamBenh> ds = new ArrayList<>();
            iCaKhamBenhService.layCaKhamTheoNgayKham(format.parse(date)).forEach(t -> {
                CaKhamBenh caKhamBenh = new CaKhamBenh();
                caKhamBenh.setId(t.getId());
                caKhamBenh.setTenCa(t.getTenCa());
                caKhamBenh.setMoTa(t.getMoTa());
                ds.add(caKhamBenh);
            });
            ajaxResponse = mapper.writeValueAsString(ds);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/api/getDiseaseDetails")
    public @ResponseBody String diseaseDetails(@RequestParam("id")String id) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            Map<Thuoc, Integer> medicines = new HashMap<>();
            iToaThuocService.getById(ToaThuoc.class, id).getDsChiTietToaThuoc().forEach(ct -> {
                Thuoc thuoc = new Thuoc();
                thuoc.setId(ct.getThuoc().getId());
                thuoc.setTenThuoc(ct.getThuoc().getTenThuoc());
                thuoc.setDonGia(ct.getDonGia());
                thuoc.setMoTa(ct.getThuoc().getMoTa());
                thuoc.setDonVi(ct.getThuoc().getDonVi());
                Integer soLuong = Integer.valueOf(ct.getSoLuong());

                medicines.put(thuoc, soLuong);
            });
//            List<ChiTietToaThuoc> result = new ArrayList<>();
//            iToaThuocService.getById(ToaThuoc.class, id).getDsChiTietToaThuoc().forEach(ct -> {
//                ToaThuoc toaThuoc = new ToaThuoc();
//                toaThuoc.setId(ct.getToaThuoc().getId());
//                toaThuoc.setNgayKeToa(ct.getToaThuoc().getNgayKeToa());
//                toaThuoc.setLoaiBenh(ct.getToaThuoc().getLoaiBenh());
//                toaThuoc.setBenhNhan(ct.getToaThuoc().getBenhNhan());
//                toaThuoc.setBacSi(ct.getToaThuoc().getBacSi());
//
//                Thuoc thuoc = new Thuoc();
//                thuoc.setId(ct.getThuoc().getId());
//                thuoc.setTenThuoc(ct.getThuoc().getTenThuoc());
//                thuoc.setMoTa(ct.getThuoc().getMoTa());
//                thuoc.setDonGia(ct.getThuoc().getDonGia());
//                thuoc.setDonVi(ct.getThuoc().getDonVi());
//
//                ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
//                chiTietToaThuoc.setToaThuoc(toaThuoc);
//                chiTietToaThuoc.setThuoc(thuoc);
//                chiTietToaThuoc.setSoLuong(ct.getSoLuong());
//                chiTietToaThuoc.setDonGia(ct.getDonGia());
//                chiTietToaThuoc.setThanhTien(ct.getThanhTien());
//
//                result.a
//            });
            ajaxResponse = mapper.writeValueAsString(medicines);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
