package com.dhb.springapp.controllers;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.modelview.AddPatient;
import com.dhb.springapp.service.IBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller("home")
@RequestMapping("/")
public class HomeController {
    @Autowired
    IBenhNhanService iBenhNhanService;

    @RequestMapping()
    public String index(ModelMap model) {
        model.addAttribute("appointment", new AddPatient());
        return "homePage";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("appointment") @Valid AddPatient addPatient,
                             BindingResult result, ModelMap model) throws ParseException {
        if (!result.hasErrors()) {
            try {
                iBenhNhanService.themBenhNhanVaPhieuKhamBenh(addPatient, new SimpleDateFormat("dd/MM/yyyy"));
                model.addAttribute("result", "Thank you for booking!!!");
                return "redirect:/booking";
            }
            catch (Exception e) {
                model.addAttribute("message", e.getMessage());
            }
        }

        return "homePage";
    }

    @GetMapping("/booking")
    public String bookingResult() {
        return "booking.result";
    }

    @GetMapping("/search")
    public String search(@RequestParam("q")String id, ModelMap model) {
        BenhNhan benhNhan = iBenhNhanService.getById(BenhNhan.class, id);
        model.addAttribute("patient", benhNhan);
        model.addAttribute("diseases", iBenhNhanService.getBenhAnTheoBenhNhan(id));
        return "search";
    }

    @GetMapping("/re-examination")
    public String reExam(@RequestParam("id") String id, ModelMap model) {
        if (id != null && !id.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            BenhNhan benhNhan = iBenhNhanService.getById(BenhNhan.class, id);
            if (benhNhan != null) {
                AddPatient addPatient = new AddPatient();
                addPatient.setId(benhNhan.getId());
                addPatient.setTen(benhNhan.getTen());
                addPatient.setHo(benhNhan.getHo());
                addPatient.setGioiTinh(benhNhan.getGioiTinh());
                addPatient.setEmail(benhNhan.getEmail());
                addPatient.setNgaySinh(format.format(benhNhan.getNgaySinh()));
                addPatient.setDienThoai(benhNhan.getDienThoai());
                addPatient.setTuoi(benhNhan.getTuoi());
                addPatient.setBacSi(null);
                addPatient.setCaKham(null);
                addPatient.setNgayKham(null);
                addPatient.setThanhToan(false);
                addPatient.setLoaiBenhList(null);
                model.addAttribute("patient", addPatient);
                return "re-examination";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/re-examination")
    public String reExamProcess(@RequestParam(value = "id", required = false) String id,
                                @ModelAttribute @Valid AddPatient addPatient,
                                BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            if (id != null && !id.isEmpty()) {
                try {
                    iBenhNhanService.themBenhNhanTaiKham(addPatient, new SimpleDateFormat("dd/MM/yyyy"));
                    model.addAttribute("result", "Thank you for booking!!!");
                    return "redirect:/booking";
                }
                catch (Exception e) {
                    model.addAttribute("message", e.getMessage());
                }
            }
        }
        return "re-examination";
    }
}
