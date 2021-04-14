package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.ChiTietCaKhamBenh;
import com.dhb.springapp.service.IChiTietCaKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    IChiTietCaKhamBenhService iChiTietCaKhamBenhService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "active");
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("schedules", iChiTietCaKhamBenhService
                .getAll(ChiTietCaKhamBenh.class));
        return "schedule.index";
    }

    @GetMapping("/add")
    public String addView() {
        return "schedule.add";
    }

    @PostMapping("/add")
    public String addProcess() {
        return "schedule.add";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable("id") String id) {
        return "schedule.edit";
    }

    @PostMapping("/edit{id}")
    public String editProcess(@PathVariable("id") String id) {
        return "schedule.edit";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") String id) {
        return "schedule.details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        return "redirect:/";
    }
}
