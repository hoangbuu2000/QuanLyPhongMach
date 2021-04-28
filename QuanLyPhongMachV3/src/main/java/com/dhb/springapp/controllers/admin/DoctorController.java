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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/admin/doctor")
public class DoctorController {
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
                        String relativePath = "";
                        String targetPath;
                        if (img != null && !img.isEmpty()) {
                            try{
                                relativePath = "/resources/images/bacsi/" + addDoctor.getUsername() + ".png";
                                targetPath = request.getSession().getServletContext()
                                        .getRealPath(String.format("/resources/images/bacsi/%s.png", addDoctor.getUsername()));
                                img.transferTo(new File(targetPath));
                            }
                            catch (IllegalStateException | IOException ex) {
                                System.err.println(ex.getMessage());
                            }
                        }
                        iTaiKhoanService.themTaiKhoanVaBacSi(relativePath, addDoctor);
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
                            String relativePath = "/resources/images/bacsi/" + editedDoctor.getUsername() + ".png";
                            String targetPath = request.getSession().getServletContext()
                                    .getRealPath(String.format("/resources/images/bacsi/%s.png", editedDoctor.getUsername()));
                            if (!iTaiKhoanService.checkNoChangeUsername(id, editedDoctor)) {
                                relativePath = "/resources/images/bacsi/" + editedDoctor.getUsername() + ".png";
                                targetPath = request.getSession().getServletContext()
                                        .getRealPath(String.format("/resources/images/bacsi/%s.png", editedDoctor.getUsername()));
                                String oldPath = request.getSession().getServletContext()
                                        .getRealPath(String.format("/resources/images/bacsi/%s.png", taiKhoan.getUsername()));
                                File file = new File(oldPath);
                                file.renameTo(new File(targetPath));
                            }
                            if (img != null && !img.isEmpty()) {
                                try {
                                    String oldPath = request.getSession().getServletContext()
                                            .getRealPath(String.format("/resources/images/bacsi/%s.png", taiKhoan.getUsername()));
                                    File file = new File(oldPath);
                                    file.delete();
                                    img.transferTo(new File(targetPath));
                                }
                                catch (IllegalStateException | IOException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                            iTaiKhoanService.suaTaiKhoanVaBacSi(id, relativePath, editedDoctor);
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
