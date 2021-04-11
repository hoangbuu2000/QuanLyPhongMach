package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.Role;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.IGenericService;
import com.dhb.springapp.service.ITaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
import java.util.UUID;

@Controller
@ControllerAdvice
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private Environment env;
    @Autowired
    private IBacSiService iBacSiService;
    @Autowired
    private ITaiKhoanService iTaiKhoanService;
    @Autowired
    @Qualifier("roleService")
    private IGenericService roleService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("doctorAct", "active");
        model.addAttribute("patientAct", "");
        model.addAttribute("dashboard", "");
        model.addAttribute("employeeAct", "");
        model.addAttribute("scheduleAct", "");
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
    public String addProcess(@ModelAttribute("doctor") @Valid AddDoctor addDoctor, ModelMap model,
                             BindingResult result,
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        BacSi bacSi = iBacSiService.getById(BacSi.class, id);
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);

        AddDoctor doctor = iBacSiService.castDoctorToModelView(bacSi, taiKhoan, format);

        model.addAttribute("doctor", doctor);
        model.addAttribute("id", id);
        return "doctor.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable(name = "id") String id,
                              @ModelAttribute("doctor") AddDoctor editedDoctor,
                              HttpServletRequest request) {
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);
        BacSi bacSi = iBacSiService.getById(BacSi.class, id);
        if (editedDoctor != null) {
            if(editedDoctor.getPassword().equals(editedDoctor.getConfirmPassword())) {
                if (taiKhoan.getUsername().equals(editedDoctor.getUsername())
                        || (!taiKhoan.getUsername().equals(editedDoctor.getUsername())
                        && iTaiKhoanService.getTaiKhoanByUsername(editedDoctor.getUsername()) == null)) {

                    MultipartFile img = editedDoctor.getImage();
                    String relativePath = "/resources/images/bacsi/" + editedDoctor.getUsername() + ".png";
                    String targetPath = request.getSession().getServletContext()
                            .getRealPath(String.format("/resources/images/bacsi/%s.png", editedDoctor.getUsername()));
                    if (img != null && !img.isEmpty()) {
                        try {
                            String oldPath = request.getSession().getServletContext()
                                    .getRealPath(String.format("/resources/images/bacsi/%s.png", taiKhoan.getUsername()));
                            File file = new File(oldPath);
                            file.delete();
                            img.transferTo(new File(targetPath));
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                            bacSi.setTen(editedDoctor.getTen());
                            bacSi.setHo(editedDoctor.getHo());
                            bacSi.setEmail(editedDoctor.getEmail());
                            bacSi.setGioiTinh(editedDoctor.getGioiTinh());
                            bacSi.setNgaySinh(format.parse(editedDoctor.getNgaySinh()));
                            bacSi.setQueQuan(editedDoctor.getQueQuan());
                            bacSi.setDienThoai(editedDoctor.getDienThoai());
                            bacSi.setImage(relativePath);

                            taiKhoan.setUsername(editedDoctor.getUsername());
                            taiKhoan.setPassword(editedDoctor.getPassword().isEmpty() ? taiKhoan.getPassword() : editedDoctor.getPassword());
                            taiKhoan.setActive(editedDoctor.isActive());

                            iTaiKhoanService.update(taiKhoan);
                            iBacSiService.update(bacSi);

                            return "redirect:/doctor";
                        }
                        catch (IllegalStateException | IOException | ParseException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }
                }
            }
        }
        return "redirect:/doctor/edit/" + id;
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
