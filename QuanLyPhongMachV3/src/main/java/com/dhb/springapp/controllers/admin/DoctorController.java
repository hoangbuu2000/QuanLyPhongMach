package com.dhb.springapp.controllers.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/doctor")
public class DoctorController {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
       "cloud_name", "dk5jgf3xj",
       "api_key", "781767664334152",
       "api_secret", "JmhAHxHXKWsrnrfWnsRiMg_JsSw"
    ));

    @Autowired
    private IBacSiService iBacSiService;
    @Autowired
    private ITaiKhoanService iTaiKhoanService;

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
                        //chi luu duoc anh trong thu muc target tuk a trui
                        MultipartFile img = addDoctor.getImage();
//                        String relativePath = "";
//                        String targetPath;
                        Map uploadResult = new HashMap();
                        if (img != null && !img.isEmpty()) {
                            try{
//                                relativePath = "/resources/images/bacsi/" + addDoctor.getUsername() + ".png";
//                                targetPath = request.getSession().getServletContext()
//                                        .getRealPath(String.format("/resources/images/bacsi/%s.png", addDoctor.getUsername()));
//                                img.transferTo(new File(targetPath));
                                uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                        "public_id", "my_folder/" + addDoctor.getUsername()));
                            }
                            catch (IllegalStateException | IOException ex) {
                                System.err.println(ex.getMessage());
                            }
                        }
                        iTaiKhoanService.themTaiKhoanVaBacSi(uploadResult.get("url").toString(), addDoctor);
                        return "redirect:/admin/doctor";
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
                            TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);
                            MultipartFile img = editedDoctor.getImage();
//                            String relativePath = "/resources/images/bacsi/" + editedDoctor.getUsername() + ".png";
//                            String targetPath = request.getSession().getServletContext()
//                                    .getRealPath(String.format("/resources/images/bacsi/%s.png", editedDoctor.getUsername()));
//                            String oldPath = request.getSession().getServletContext()
//                                    .getRealPath(String.format("/resources/images/bacsi/%s.png", taiKhoan.getUsername()));
                            String path = "";
                            Map uploadResult = new HashMap();
                            if (img != null && !img.isEmpty()) {
                                if (!iTaiKhoanService.checkNoChangeUsername(id, editedDoctor)) {
//                                    File file = new File(oldPath);
//                                    file.delete();
                                    cloudinary.uploader().destroy("my_folder/"+taiKhoan.getUsername(),
                                            ObjectUtils.emptyMap());
                                }
                                try {
                                    uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                            "public_id", "my_folder/" + editedDoctor.getUsername()));
                                    path = uploadResult.get("url").toString();
//                                    img.transferTo(new File(targetPath));
                                }
                                catch (IllegalStateException | IOException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                            else {
                                path = taiKhoan.getBacSi().getImage();
                                if (!iTaiKhoanService.checkNoChangeUsername(id, editedDoctor)) {
                                    uploadResult = cloudinary.uploader().rename("my_folder/"+taiKhoan.getUsername(),
                                            "my_folder/"+editedDoctor.getUsername(),
                                            ObjectUtils.emptyMap());
                                    path = uploadResult.get("url").toString();
//                                    File file = new File(oldPath);
//                                    file.renameTo(new File(targetPath));
                                }
                            }
                            iTaiKhoanService.suaTaiKhoanVaBacSi(id, path, editedDoctor);
                            return "redirect:/admin/doctor";
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
            return "redirect:/admin/doctor";
        }
        return "doctor.index";
    }
}
