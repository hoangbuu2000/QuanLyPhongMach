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
@RequestMapping("/admin/patient")
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
        model.addAttribute("danhSachLoaiBenh", iLoaiBenhService.getAll(LoaiBenh.class));
        model.addAttribute("danhSachCaKhamBenh", iCaKhamBenhService.getAll(CaKhamBenh.class));
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("patient", iBenhNhanService.getAll(BenhNhan.class));
        return "patient.index";
    }

    @GetMapping("/add")
    public String addView(@RequestParam(value = "oldPatient", required = false)String id,
                          ModelMap model) {
        if (id != null && !id.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            BenhNhan benhNhan = iBenhNhanService.getById(BenhNhan.class, id);
            AddPatient addPatient = new AddPatient();
            addPatient.setId(benhNhan.getId());
            addPatient.setTen(benhNhan.getTen());
            addPatient.setHo(benhNhan.getHo());
            addPatient.setGioiTinh(benhNhan.getGioiTinh());
            addPatient.setEmail(benhNhan.getEmail());
            addPatient.setNgaySinh(format.format(benhNhan.getNgaySinh()));
            addPatient.setDienThoai(benhNhan.getDienThoai());
            addPatient.setTuoi(benhNhan.getTuoi());
            addPatient.setBacSi(null);
            addPatient.setCaKham(null);
            addPatient.setNgayKham(null);
            addPatient.setThanhToan(false);
            addPatient.setLoaiBenhList(null);

            model.addAttribute("patient", addPatient);
        }
        else
            model.addAttribute("patient", new AddPatient());
        return "patient.add";
    }

    @PostMapping("/add")
    public String addProcess(@RequestParam(value = "oldPatient", required = false) String id,
                             @ModelAttribute("patient") @Valid AddPatient addPatient,
                             BindingResult result, ModelMap model) throws ParseException {
        if (!result.hasErrors()) {
            if (id != null && !id.isEmpty()) {
                try {
                    iBenhNhanService.themBenhNhanTaiKham(addPatient, format);
                    return "redirect:/admin/patient";
                }
                catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("message", e.getMessage());
                }
            }
            else {
                try {
                    iBenhNhanService.themBenhNhanVaPhieuKhamBenh(addPatient, format);
                    return "redirect:/admin/patient";
                } catch (Exception e) {
                    model.addAttribute("message", e.getMessage());
                }
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
            return String.format("redirect:/admin/patient/details/%s", id);
        }
        catch (HibernateError e) {
            return "patient.edit";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable(name = "id") String id) {
        if (id != null && !id.isEmpty()) {
            iBenhNhanService.delete(iBenhNhanService.getById(BenhNhan.class, id));
            return "redirect:/admin/patient";
        }
        return "patient.index";
    }
}
