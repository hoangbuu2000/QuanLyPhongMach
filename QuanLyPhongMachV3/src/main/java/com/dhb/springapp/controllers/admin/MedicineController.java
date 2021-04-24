package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.service.IThuocService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    IThuocService iThuocService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
        model.addAttribute("appointmentAct", "");
        model.addAttribute("diseaseAct", "");
        model.addAttribute("medicineAct", "active");
        model.addAttribute("prescriptionAct", "");
        model.addAttribute("invoiceAct", "");
    }

    @GetMapping("/api/getAll")
    public @ResponseBody String getMedicines() {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<Thuoc> result = new ArrayList<>();
            iThuocService.getAll(Thuoc.class).forEach(t -> {
                Thuoc thuoc = new Thuoc();
                thuoc.setId(t.getId());
                thuoc.setTenThuoc(t.getTenThuoc());
                thuoc.setMoTa(t.getMoTa());
                thuoc.setDonVi(t.getDonVi());
                thuoc.setDonGia(t.getDonGia());

                result.add(thuoc);
            });
            ajaxResponse = mapper.writeValueAsString(result);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("medicines", iThuocService.getAll(Thuoc.class));
        return "medicine.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(@RequestParam(value = "id", required = false) String id,
                                  ModelMap model) {
        if (id != null && !id.isEmpty()) {
            model.addAttribute("medicine", iThuocService.getById(Thuoc.class, Integer.parseInt(id)));
        }
        else
            model.addAttribute("medicine", new Thuoc());
        return "medicine.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@RequestParam(value = "id", required = false)String id,@ModelAttribute("medicine") @Valid Thuoc thuoc,
                                     BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                if (id != null && !id.isEmpty())
                    iThuocService.update(thuoc);
                else
                    iThuocService.insert(thuoc);
                return "redirect:/medicine";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "medicine.add";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id")String id) {
        if (id != null && !id.isEmpty()) {
            try {
                iThuocService.delete(iThuocService.getById(Thuoc.class, Integer.parseInt(id)));
                return "redirect:/medicine";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "404";
    }
}
