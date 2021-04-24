package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.modelview.AddEmployee;
import com.dhb.springapp.service.INhanVienService;
import com.dhb.springapp.service.ITaiKhoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    INhanVienService iNhanVienService;
    @Autowired
    ITaiKhoanService iTaiKhoanService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "active");
        model.addAttribute("scheduleAct", "");
        model.addAttribute("diseaseAct", "");
        model.addAttribute("medicineAct", "");
        model.addAttribute("appointmentAct", "");
        model.addAttribute("prescriptionAct", "");
        model.addAttribute("invoiceAct", "");
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("employees", iNhanVienService.getAllOrderBy(NhanVien.class, "ten",
                Order.asc));
        return "employee.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("employee", new AddEmployee());
        return "employee.add";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("employee") @Valid AddEmployee addEmployee,
                             BindingResult result, ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            if(iTaiKhoanService.checkPassword(addEmployee)) {
                if (iTaiKhoanService.checkExistedUsername(addEmployee)) {
                    try {
                        iTaiKhoanService.themTaiKhoanVaNhanVien(addEmployee, request);
                        return "redirect:/employee";
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
        return "employee.add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") String id) {
        return "employee.details";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable("id") String id, ModelMap model) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        NhanVien nhanVien = iNhanVienService.getById(NhanVien.class, id);
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);

        AddEmployee employee = iNhanVienService.castEmployeeToModelView(nhanVien, taiKhoan, format);

        model.addAttribute("employee", employee);
        model.addAttribute("id", id);
        return "employee.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable("id") String id,
                              @ModelAttribute("employee") @Valid AddEmployee editedEmployee,
                              BindingResult result,
                              ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            if(iTaiKhoanService.checkPassword(editedEmployee)) {
                if (iTaiKhoanService.checkNoChangeUsername(id, editedEmployee)
                        || (!iTaiKhoanService.checkNoChangeUsername(id, editedEmployee))
                        && iTaiKhoanService.checkExistedUsername(editedEmployee)) {

                    try {
                        iTaiKhoanService.suaTaiKhoanVaNhanVien(id, editedEmployee, request);
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
        model.addAttribute("employee", editedEmployee);
        model.addAttribute("id", id);
        return "employee.edit";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        return "redirect:/employee";
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "name", required = false) String name) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        List<NhanVien> nhanVienList = new ArrayList<>();
        try {
            if (id != null && !id.isEmpty()) {
                if (name != null && !name.isEmpty()) {
                    nhanVienList = iNhanVienService.castPersistenceToTransient(
                            iNhanVienService.getNhanVienTheoTenVaID(id, name));
                }
                else {
                    nhanVienList.add(iNhanVienService.castPersistenceToTransient(
                            iNhanVienService.getById(NhanVien.class, id)));
                }
            }
            else {
                if (name != null && !name.isEmpty())
                    nhanVienList = iNhanVienService.castPersistenceToTransient(
                            iNhanVienService.getNhanVienTheoTen(name));
                else
                    nhanVienList = iNhanVienService.castPersistenceToTransient(
                            iNhanVienService.getAllOrderBy(NhanVien.class, "ten", Order.asc));
            }
            ajaxResponse = mapper.writeValueAsString(nhanVienList);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
