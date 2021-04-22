package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.*;
import com.dhb.springapp.service.IHoaDonService;
import com.dhb.springapp.service.ILoaiBenhService;
import com.dhb.springapp.service.INhanVienService;
import com.dhb.springapp.service.IToaThuocService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@ControllerAdvice
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    IHoaDonService iHoaDonService;
    @Autowired
    IToaThuocService iToaThuocService;
    @Autowired
    INhanVienService iNhanVienService;
    @Autowired
    ILoaiBenhService iLoaiBenhService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        model.addAttribute("prescriptions", iToaThuocService.getAll(ToaThuoc.class));
        model.addAttribute("employees", iNhanVienService.getAll(NhanVien.class));
    }

    @GetMapping()
    public String index() {
        return "invoice.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(ModelMap model) {
        model.addAttribute("invoice", new HoaDon());
        return "invoice.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@ModelAttribute("invoice") @Valid HoaDon hoaDon,
                                     BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            return "redirect:/invoice";
        }
        return "invoice.add";
    }

    @GetMapping("/api/getTienKham")
    public @ResponseBody String getTienKham(@RequestParam("idToaThuoc") String id) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(iToaThuocService.getById(ToaThuoc.class, id).getLoaiBenh().getDonGia());
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
