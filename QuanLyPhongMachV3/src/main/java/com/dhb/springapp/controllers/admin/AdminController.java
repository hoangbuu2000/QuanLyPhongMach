package com.dhb.springapp.controllers.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddEmployee;
import com.dhb.springapp.service.IAdminService;
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
@ControllerAdvice
@RequestMapping("/admin/admin-management")
public class AdminController {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dk5jgf3xj",
            "api_key", "781767664334152",
            "api_secret", "JmhAHxHXKWsrnrfWnsRiMg_JsSw"
    ));

    @Autowired
    IAdminService iAdminService;
    @Autowired
    ITaiKhoanService iTaiKhoanService;

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("admins", iAdminService.getAll(Admin.class));
        return "admin.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("admin", new AddEmployee());
        return "admin.add";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("admin") @Valid AddEmployee admin,
                             BindingResult result,
                             ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            if(iTaiKhoanService.checkPassword(admin)) {
                if (iTaiKhoanService.checkExistedUsername(admin)) {
                    try {
                        //chi luu duoc anh trong thu muc target tuk a trui
                        MultipartFile img = admin.getImage();
                        Map uploadResult = new HashMap();
                        if (img != null && !img.isEmpty()) {
                            uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                        "public_id", "my_folder/" + admin.getUsername()));
                        }
                        iTaiKhoanService.themTaiKhoanVaAdmin(uploadResult.get("url").toString(), admin);
                        return "redirect:/admin/admin-management";
                    }
                    catch (Exception e) {
                        model.addAttribute("message", e.getMessage());
                    }
//                    try {
//                        //chi luu duoc anh trong thu muc target tuk a trui
//                        MultipartFile img = admin.getImage();
//                        String relativePath = "";
//                        String targetPath;
//                        if (img != null && !img.isEmpty()) {
//                            try{
//                                relativePath = "/resources/images/admin/" + admin.getUsername() + ".png";
//                                targetPath = request.getSession().getServletContext()
//                                        .getRealPath(String.format("/resources/images/admin/%s.png", admin.getUsername()));
//                                img.transferTo(new File(targetPath));
//                            }
//                            catch (IllegalStateException | IOException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//                        }
//                        iTaiKhoanService.themTaiKhoanVaAdmin(relativePath, admin);
//                        return "redirect:/admin/admin-management";
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
        return "admin.add";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable("id") String id, ModelMap model) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Admin admin = iAdminService.getById(Admin.class, id);
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);

        AddEmployee addAdmin = iAdminService.castAdminToModelView(admin, taiKhoan, format);

        model.addAttribute("admin", addAdmin);
        model.addAttribute("id", id);
        return "admin.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable("id") String id,
                              @ModelAttribute("admin") @Valid AddEmployee editedAdmin,
                              BindingResult result,
                              ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            if(iTaiKhoanService.checkPassword(editedAdmin)) {
                if (iTaiKhoanService.checkNoChangeUsername(id, editedAdmin)
                        || (!iTaiKhoanService.checkNoChangeUsername(id, editedAdmin))
                        && iTaiKhoanService.checkExistedUsername(editedAdmin)) {

                    try {
                        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);
                        MultipartFile img = editedAdmin.getImage();
                        String path = "";
                        Map uploadResult = new HashMap();
                        if (img != null && !img.isEmpty()) {
                            if (!iTaiKhoanService.checkNoChangeUsername(id, editedAdmin)) {
                                cloudinary.uploader().destroy("my_folder/"+taiKhoan.getUsername(),
                                        ObjectUtils.emptyMap());
                            }
                            uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                        "public_id", "my_folder/" + editedAdmin.getUsername()));
                            path = uploadResult.get("url").toString();
                        }
                        else {
                            path = taiKhoan.getAdmin().getImage();
                            if (!iTaiKhoanService.checkNoChangeUsername(id, editedAdmin)) {
                                uploadResult = cloudinary.uploader().rename("my_folder/"+taiKhoan.getUsername(),
                                        "my_folder/"+editedAdmin.getUsername(),
                                        ObjectUtils.emptyMap());
                                path = uploadResult.get("url").toString();
                            }
                        }
                        iTaiKhoanService.suaTaiKhoanVaAdmin(id, path, editedAdmin);
                        return "redirect:/admin/admin-management";
                    }
                    catch (Exception e) {
                        model.addAttribute("message", e.getMessage());
                    }
//                    try {
//                        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);
//                        MultipartFile img = editedAdmin.getImage();
//                        String relativePath = "/resources/images/admin/" + editedAdmin.getUsername() + ".png";
//                        String targetPath = request.getSession().getServletContext()
//                                .getRealPath(String.format("/resources/images/admin/%s.png", editedAdmin.getUsername()));
//                        if (!iTaiKhoanService.checkNoChangeUsername(id, editedAdmin)) {
//                            relativePath = "/resources/images/admin/" + editedAdmin.getUsername() + ".png";
//                            targetPath = request.getSession().getServletContext()
//                                    .getRealPath(String.format("/resources/images/admin/%s.png", editedAdmin.getUsername()));
//                            String oldPath = request.getSession().getServletContext()
//                                    .getRealPath(String.format("/resources/images/admin/%s.png", taiKhoan.getUsername()));
//                            File file = new File(oldPath);
//                            file.renameTo(new File(targetPath));
//                        }
//                        if (img != null && !img.isEmpty()) {
//                            try {
//                                String oldPath = request.getSession().getServletContext()
//                                        .getRealPath(String.format("/resources/images/admin/%s.png", taiKhoan.getUsername()));
//                                File file = new File(oldPath);
//                                file.delete();
//                                img.transferTo(new File(targetPath));
//                            }
//                            catch (IllegalStateException | IOException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//                        }
//                        iTaiKhoanService.suaTaiKhoanVaAdmin(id, relativePath, editedAdmin);
//                        return "redirect:/admin/admin-management";
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
        model.addAttribute("admin", editedAdmin);
        model.addAttribute("id", id);
        return "admin.edit";
    }

    @PostMapping("/deleteAjax")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAjax(@RequestParam("id")String id) {
        iAdminService.delete(iAdminService.getById(Admin.class, id));
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "name", required = false) String name) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        List<Admin> adminList = new ArrayList<>();
        try {
            if (id != null && !id.isEmpty()) {
                if (name != null && !name.isEmpty()) {
                    adminList = iAdminService.castPersistenceToTransient(
                            iAdminService.getAdminTheoTenVaID(id, name));
                }
                else {
                    adminList.add(iAdminService.castPersistenceToTransient(
                            iAdminService.getById(Admin.class, id)));
                }
            }
            else {
                if (name != null && !name.isEmpty())
                    adminList = iAdminService.castPersistenceToTransient(
                            iAdminService.getAdminTheoTen(name));
                else
                    adminList = iAdminService.castPersistenceToTransient(
                            iAdminService.getAllOrderBy(Admin.class, "ten", Order.asc));
            }
            ajaxResponse = mapper.writeValueAsString(adminList);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
