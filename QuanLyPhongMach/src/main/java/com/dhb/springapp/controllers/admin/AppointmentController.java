package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.modelview.AddPatient;
import com.dhb.springapp.modelview.Appointment;
import com.dhb.springapp.service.IBenhNhanService;
import com.dhb.springapp.service.IPhieuKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@ControllerAdvice
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IPhieuKhamBenhService iPhieuKhamBenhService;
    @Autowired
    private IBenhNhanService iBenhNhanService;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
        model.addAttribute("appointmentAct", "active");
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("appointments", iPhieuKhamBenhService.getAllAppointmentDESC(format));
        return "appointment.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("patient", new AddPatient());
        return "appointment.add";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("patient") @Valid AddPatient addPatient,
                             BindingResult result, ModelMap model) throws ParseException {
        if (!result.hasErrors()) {
            try {
                iBenhNhanService.themBenhNhanVaPhieuKhamBenh(addPatient, format);
                return "redirect:/appointment";
            }
            catch (Exception e) {
                model.addAttribute("message", e.getMessage());
            }
        }

        return "appointment.add";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable("id") String id) {
        return "appointment.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable("id") String id) {
        return "appointment.edit";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") String id) {
        return "appointment.details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        if (!id.isEmpty() && id != null) {
            PhieuKhamBenh phieuKhamBenh = iPhieuKhamBenhService.getById(PhieuKhamBenh.class, Integer.parseInt(id));
            iPhieuKhamBenhService.delete(phieuKhamBenh);
        }
        return "redirect:/appointment";
    }
}
