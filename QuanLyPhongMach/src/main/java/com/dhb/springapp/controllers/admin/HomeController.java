package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.Utils.PatientUtil;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.modelview.NewPatient;
import com.dhb.springapp.repository.implement.BacSiRepository;
import com.dhb.springapp.repository.implement.BenhNhanRepository;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.IBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller("admin.home")
@RequestMapping("/admin")
public class HomeController {
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    IBenhNhanService iBenhNhanService;

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("soLuongBacSi", iBacSiService.getAll(BacSi.class).size());
        model.addAttribute("soLuongBenhNhan", iBenhNhanService.getAll(BenhNhan.class).size());
        model.addAttribute("danhSachBacSi", iBacSiService.getAll(BacSi.class));
        model.addAttribute("danhSachBenhNhanMoi", PatientUtil.getNewPatients(iBenhNhanService.getNewBenhNhan()));
        return "admin.index";
    }
}
