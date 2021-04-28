package com.dhb.springapp.controllers;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.modelview.AddPatient;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.IBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller("home")
@RequestMapping("/")
public class HomeController {
    @Autowired
    IBenhNhanService iBenhNhanService;

    @RequestMapping()
    public String index(ModelMap model) {
        model.addAttribute("appointment", new AddPatient());

        return "homePage";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("appointment") @Valid AddPatient addPatient,
                             BindingResult result, ModelMap model) throws ParseException {
        if (!result.hasErrors()) {
            try {
                iBenhNhanService.themBenhNhanVaPhieuKhamBenh(addPatient, new SimpleDateFormat("dd/MM/yyyy"));
                return "redirect:/booking";
            }
            catch (Exception e) {
                model.addAttribute("message", e.getMessage());
            }
        }

        return "homePage";
    }

    @GetMapping("/booking")
    public String bookingResult() {
        return "booking.result";
    }
}
