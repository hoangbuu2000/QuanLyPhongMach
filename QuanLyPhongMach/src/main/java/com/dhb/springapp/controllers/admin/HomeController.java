package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.Utils.PatientUtil;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.IBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin.home")
@ControllerAdvice
@RequestMapping("/admin")
public class HomeController {
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    IBenhNhanService iBenhNhanService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("dashboard", "active");
        model.addAttribute("patientAct", "");
        model.addAttribute("doctorAct", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
        model.addAttribute("appointmentAct", "");
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("soLuongBacSi", iBacSiService.getAll(BacSi.class).size());
        model.addAttribute("soLuongBenhNhan", iBenhNhanService.getAll(BenhNhan.class).size());
        model.addAttribute("danhSachBacSi", iBacSiService.getAll(BacSi.class));
        model.addAttribute("danhSachBenhNhanMoi", PatientUtil.getNewPatients(iBenhNhanService.getTopNewBenhNhan(10)));
        return "admin.index";
    }
}
