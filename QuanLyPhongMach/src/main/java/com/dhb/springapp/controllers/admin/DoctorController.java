package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.ITaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@ControllerAdvice
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private IBacSiService iBacSiService;
    @Autowired
    private ITaiKhoanService iTaiKhoanService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "active");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
        model.addAttribute("appointment", "");
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("danhSachBacSi", iBacSiService.getAll(BacSi.class));
        return "doctor.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("doctor", new AddDoctor());
        return "doctor.add";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("doctor") @Valid AddDoctor addDoctor,
                             BindingResult result, ModelMap model,
                             HttpServletRequest request) throws ParseException {
        if (!result.hasErrors()) {
            if(iTaiKhoanService.checkPassword(addDoctor)) {
                if (iTaiKhoanService.checkExistedUsername(addDoctor)) {
                    try {
                        iTaiKhoanService.themTaiKhoanVaBacSi(addDoctor, request);
                        return "redirect:/doctor";
                    }
                    catch (Exception e) {
                        model.addAttribute("message", e.getMessage());
                    }
                }
                else {
                    model.addAttribute("message", "Tai khoan da ton tai");
                }
            }
            else {
                model.addAttribute("message", "Xac nhan mat khau khong dung");
            }
        }

        return "doctor.add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(name = "id") String id, ModelMap model) {
        BacSi bacSi = iBacSiService.getById(BacSi.class, id);
        return "doctor.details";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") String id, ModelMap model) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        BacSi bacSi = iBacSiService.getById(BacSi.class, id);
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);

        AddDoctor doctor = iBacSiService.castDoctorToModelView(bacSi, taiKhoan, format);

        model.addAttribute("doctor", doctor);
        model.addAttribute("id", id);
        return "doctor.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable(name = "id") String id,
                              @ModelAttribute("doctor") @Valid AddDoctor editedDoctor,
                              BindingResult result,
                              ModelMap model,
                              HttpServletRequest request) {
        if (!result.hasErrors()) {
                if(iTaiKhoanService.checkPassword(editedDoctor)) {
                    if (iTaiKhoanService.checkNoChangeUsername(id, editedDoctor)
                            || (!iTaiKhoanService.checkNoChangeUsername(id, editedDoctor))
                            && iTaiKhoanService.checkExistedUsername(editedDoctor)) {

                        try {
                            iTaiKhoanService.suaTaiKhoanVaBacSi(id, editedDoctor, request);
                            return "redirect:/doctor";
                        }
                        catch (Exception e) {
                            model.addAttribute("message", e.getMessage());
                        }
                    }
                    else {
                        model.addAttribute("messages", "Tai khoan da ton tai");
                    }
                }
                else {
                    model.addAttribute("message", "Xac nhan mat khau khong dung");
                }
        }
        model.addAttribute("doctor", editedDoctor);
        model.addAttribute("id", id);
        return "doctor.edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable(name = "id") String id) {
        if (id != null && !id.isEmpty()) {
            iBacSiService.delete(iBacSiService.getById(BacSi.class, id));
            return "redirect:/doctor";
        }
        return "doctor.index";
    }
}
