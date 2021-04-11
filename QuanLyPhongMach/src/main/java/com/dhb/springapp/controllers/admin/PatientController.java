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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("patientAct", "active");
        model.addAttribute("doctorAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
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
                             ModelMap model,
                             BindingResult result) throws ParseException {
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
}
