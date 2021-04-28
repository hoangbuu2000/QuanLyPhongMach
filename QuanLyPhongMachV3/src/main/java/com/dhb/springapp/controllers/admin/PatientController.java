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
                return "redirect:/admin/patient";
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
