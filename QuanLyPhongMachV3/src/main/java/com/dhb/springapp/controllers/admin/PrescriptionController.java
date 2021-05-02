package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.*;
import com.dhb.springapp.modelview.AddPrescription;
import com.dhb.springapp.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/admin/prescription")
public class PrescriptionController {
    @Autowired
    IToaThuocService iToaThuocService;
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    IBenhNhanService iBenhNhanService;
    @Autowired
    ILoaiBenhService iLoaiBenhService;
    @Autowired
    IThuocService iThuocService;
    @Autowired
    IHoaDonService iHoaDonService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
//        model.addAttribute("doctors", iBacSiService.getAll(BacSi.class));
        // Nen chi hien thi nhung benh nhan co lich kham trong ca kham do, khi nao can thi moi cho xem het benh nhan
//        model.addAttribute("patients", iBenhNhanService.getBenhNhanCoLichKhamTheoThoiGianChoTruoc(new Date()));
        model.addAttribute("patients", iBenhNhanService.getAll(BenhNhan.class));
        model.addAttribute("diseases", iLoaiBenhService.getAll(LoaiBenh.class));
        model.addAttribute("medicines", iThuocService.getAll(Thuoc.class));
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("prescriptions", iToaThuocService.getAll(ToaThuoc.class));
        return "prescription.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(@RequestParam(value = "id", required = false)String id,
                                  ModelMap model) {
        if (id != null && !id.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("prescription", iToaThuocService.castToModelView(
                    iToaThuocService.getById(ToaThuoc.class, id), format));
        }
        else {
            model.addAttribute("prescription", new AddPrescription());
        }

        return "prescription.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@RequestParam(value = "id", required = false)String id,
                                     @ModelAttribute("prescription") @Valid AddPrescription addPrescription,
                                     BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                iToaThuocService.addOrUpdate(id, addPrescription,
                        new SimpleDateFormat("dd/MM/yyyy"));
                return "redirect:/admin/prescription";
            }
            catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", e.getMessage());
            }
        }
        return "prescription.add";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id")String id, ModelMap model) {
        model.addAttribute("prescription", iToaThuocService.castToModelView(
                iToaThuocService.getById(ToaThuoc.class, id), new SimpleDateFormat("dd/MM/yyyy")));
        return "prescription.details";
    }

    @GetMapping("/api/getunit")
    public @ResponseBody String getUnit(@RequestParam("id")String id) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            String unit = iThuocService.getById(Thuoc.class, Integer.parseInt(id)).getDonVi();
            ajaxResponse = mapper.writeValueAsString(unit);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/api/getprice")
    public @ResponseBody String getPrice(@RequestParam("id")String id) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            BigDecimal price = iThuocService.getById(Thuoc.class, Integer.parseInt(id)).getDonGia();
            ajaxResponse = mapper.writeValueAsString(price);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id")String id) {
        if (id != null && !id.isEmpty()) {
            try {
                ToaThuoc toaThuoc = iToaThuocService.getById(ToaThuoc.class, id);
                HoaDon hoaDon = iHoaDonService.getHoaDonTheoToaThuoc(id);
                iHoaDonService.delete(hoaDon);
                iToaThuocService.delete(toaThuoc);
                return "redirect:/admin/prescription";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "404";
    }
}
