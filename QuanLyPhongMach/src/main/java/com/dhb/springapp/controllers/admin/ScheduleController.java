package com.dhb.springapp.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@RequestMapping("/schedule")
public class ScheduleController {
    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "active");
    }

    @GetMapping()
    public String index() {
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
