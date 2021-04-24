package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.service.ILoaiBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/disease")
public class DiseaseController {
    @Autowired
    ILoaiBenhService iLoaiBenhService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
        model.addAttribute("diseaseAct", "active");
        model.addAttribute("medicineAct", "");
        model.addAttribute("prescriptionAct", "");
        model.addAttribute("invoiceAct", "");
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("diseases", iLoaiBenhService.getAll(LoaiBenh.class));
        return "disease.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(@RequestParam(value = "id", required = false)String id,
                                  ModelMap model) {
        if (id != null && !id.isEmpty()) {
            model.addAttribute("disease", iLoaiBenhService.getById(LoaiBenh.class, Integer.parseInt(id)));
        }
        else {
            model.addAttribute("disease", new LoaiBenh());
        }
        return "disease.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@RequestParam(value = "id", required = false)String id,@ModelAttribute("disease") @Valid LoaiBenh loaiBenh,
                                     BindingResult result) {
        if (!result.hasErrors()) {
            try {
                if (id != null && !id.isEmpty())
                    iLoaiBenhService.update(loaiBenh);
                else
                    iLoaiBenhService.insert(loaiBenh);
                return "redirect:/disease";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "disease.add";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        if (id != null && !id.isEmpty()) {
            try {
                iLoaiBenhService.delete(iLoaiBenhService.getById(LoaiBenh.class, Integer.parseInt(id)));
                return "redirect:/disease";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "404";
    }
}
