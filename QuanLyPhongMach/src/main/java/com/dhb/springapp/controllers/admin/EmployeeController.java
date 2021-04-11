package com.dhb.springapp.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@RequestMapping("/employee")
public class EmployeeController {
    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "active");
        model.addAttribute("scheduleAct", "");
    }

    @GetMapping()
    public String index() {
        return "employee.index";
    }

    @GetMapping("/add")
    public String addView() {
        return "employee.add";
    }

    @PostMapping("/add")
    public String addProcess() {
        return "employee.add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") String id) {
        return "employee.details";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable("id") String id) {
        return "employee.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable("id") String id) {
        return "employee.edit";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        return "redirect:/employee";
    }
}
