package com.dhb.springapp.controllers.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dk5jgf3xj",
            "api_key", "781767664334152",
            "api_secret", "JmhAHxHXKWsrnrfWnsRiMg_JsSw"
    ));

    @Autowired
    INhanVienService iNhanVienService;
    @Autowired
    ITaiKhoanService iTaiKhoanService;

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
                        //chi luu duoc anh trong thu muc target tuk a trui
                        MultipartFile img = addEmployee.getImage();
                        Map uploadResult = new HashMap();
                        if (img != null && !img.isEmpty()) {
                            uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                        "public_id", "my_folder/" + addEmployee.getUsername()));
                        }
                        iTaiKhoanService.themTaiKhoanVaNhanVien(uploadResult.get("url").toString(), addEmployee);
                        return "redirect:/admin/employee";
                    }
                    catch (Exception e) {
                        model.addAttribute("message", e.getMessage());
                    }
//                    try {
//                        //chi luu duoc anh trong thu muc target tuk a trui
//                        MultipartFile img = addEmployee.getImage();
//                        String relativePath = "";
//                        String targetPath;
//                        if (img != null && !img.isEmpty()) {
//                            try{
//                                relativePath = "/resources/images/nhanvien/" + addEmployee.getUsername() + ".png";
//                                targetPath = request.getSession().getServletContext()
//                                        .getRealPath(String.format("/resources/images/nhanvien/%s.png", addEmployee.getUsername()));
//                                img.transferTo(new File(targetPath));
//                            }
//                            catch (IllegalStateException | IOException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//                        }
//                        iTaiKhoanService.themTaiKhoanVaNhanVien(relativePath, addEmployee);
//                        return "redirect:/admin/employee";
//                    }
//                    catch (Exception e) {
//                        model.addAttribute("message", e.getMessage());
//                    }
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
                        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);
                        MultipartFile img = editedEmployee.getImage();
                        String path = "";
                        Map uploadResult = new HashMap();
                        if (img != null && !img.isEmpty()) {
                            if (!iTaiKhoanService.checkNoChangeUsername(id, editedEmployee)) {
                                cloudinary.uploader().destroy("my_folder/"+taiKhoan.getUsername(),
                                        ObjectUtils.emptyMap());
                            }
                            uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                    "public_id", "my_folder/" + editedEmployee.getUsername()));
                            path = uploadResult.get("url").toString();
                        }
                        else {
                            path = taiKhoan.getNhanVien().getImage();
                            if (!iTaiKhoanService.checkNoChangeUsername(id, editedEmployee)) {
                                uploadResult = cloudinary.uploader().rename("my_folder/"+taiKhoan.getUsername(),
                                        "my_folder/"+editedEmployee.getUsername(),
                                        ObjectUtils.emptyMap());
                                path = uploadResult.get("url").toString();
                            }
                        }
                        iTaiKhoanService.suaTaiKhoanVaAdmin(id, path, editedEmployee);
                        return "redirect:/admin/employee";
                    }
                    catch (Exception e) {
                        model.addAttribute("message", e.getMessage());
                    }

//                    try {
//                        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);
//                        MultipartFile img = editedEmployee.getImage();
//                        String relativePath = "/resources/images/nhanvien/" + editedEmployee.getUsername() + ".png";
//                        String targetPath = request.getSession().getServletContext()
//                                .getRealPath(String.format("/resources/images/nhanvien/%s.png", editedEmployee.getUsername()));
//                        if (!iTaiKhoanService.checkNoChangeUsername(id, editedEmployee)) {
//                            relativePath = "/resources/images/nhanvien/" + editedEmployee.getUsername() + ".png";
//                            targetPath = request.getSession().getServletContext()
//                                    .getRealPath(String.format("/resources/images/nhanvien/%s.png", editedEmployee.getUsername()));
//                            String oldPath = request.getSession().getServletContext()
//                                    .getRealPath(String.format("/resources/images/nhanvien/%s.png", taiKhoan.getUsername()));
//                            File file = new File(oldPath);
//                            file.renameTo(new File(targetPath));
//                        }
//                        if (img != null && !img.isEmpty()) {
//                            try {
//                                String oldPath = request.getSession().getServletContext()
//                                        .getRealPath(String.format("/resources/images/nhanvien/%s.png", taiKhoan.getUsername()));
//                                File file = new File(oldPath);
//                                file.delete();
//                                img.transferTo(new File(targetPath));
//                            }
//                            catch (IllegalStateException | IOException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//                        }
//                        iTaiKhoanService.suaTaiKhoanVaNhanVien(id, relativePath, editedEmployee);
//                        return "redirect:/admin/employee";
//                    }
//                    catch (Exception e) {
//                        model.addAttribute("message", e.getMessage());
//                    }
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
        if (id != null && !id.isEmpty()) {
            iNhanVienService.delete(iNhanVienService.getById(NhanVien.class, id));
            return "redirect:/admin/employee";
        }
        return "employee.index";
    }

    @PostMapping("/deleteAjax")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAjax(@RequestParam("id")String id) {
        iNhanVienService.delete(iNhanVienService.getById(NhanVien.class, id));
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
