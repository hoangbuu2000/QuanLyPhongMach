package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.ChiTietCaKhamBenh;
import com.dhb.springapp.modelview.AddSchedule;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.ICaKhamBenhService;
import com.dhb.springapp.service.IChiTietCaKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    IChiTietCaKhamBenhService iChiTietCaKhamBenhService;
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    ICaKhamBenhService iCaKhamBenhService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "active");

        //Chi lay nhung ca kham benh con trong theo ngay
        model.addAttribute("doctors", iBacSiService.getAll(BacSi.class));
        model.addAttribute("shifts", iCaKhamBenhService.getAll(CaKhamBenh.class));
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("schedules", iChiTietCaKhamBenhService
                .getAll(ChiTietCaKhamBenh.class));
        return "schedule.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("schedule", new ChiTietCaKhamBenh());
        return "schedule.add";
    }

    // Neu admin them thi se duoc chon bac si, neu bac si tu them thi khong duoc chon bac si ma chi lay bac si hien tai
    @PostMapping("/add")
    public String addProcess(@ModelAttribute("schedule") @Valid ChiTietCaKhamBenh schedule,
                             BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            ChiTietCaKhamBenh t = iChiTietCaKhamBenhService.insert(schedule);
            if (t != null)
                return "redirect:/schedule";
        }
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
