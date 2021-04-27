package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.*;
import com.dhb.springapp.modelview.AddInvoice;
import com.dhb.springapp.modelview.AddPrescription;
import com.dhb.springapp.modelview.SearchInvoice;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ControllerAdvice
@RequestMapping("/admin/invoice")
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
    public String index(ModelMap model) {
        model.addAttribute("invoices", iHoaDonService.getAll(HoaDon.class));
        return "invoice.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(@RequestParam(value = "id", required = false)String id, ModelMap model) {
        if (id != null && !id.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("invoice", iHoaDonService.castToModelView(
                    iHoaDonService.getById(HoaDon.class, id), format));
        }
        else {
            model.addAttribute("invoice", new AddInvoice());
        }

        return "invoice.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@RequestParam(value = "id", required = false)String id,
                                     @ModelAttribute("invoice") @Valid AddInvoice addInvoice,
                                     BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                iHoaDonService.addOrUpdate(id, addInvoice,
                        new SimpleDateFormat("dd/MM/yyyy"));
                return "redirect:/invoice";
            }
            catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", e.getMessage());
            }
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

    @PostMapping("/delete")
    public String delete(@RequestParam("id")String id) {
        if (id != null && !id.isEmpty()) {
            try {
                iHoaDonService.delete(iHoaDonService.getById(HoaDon.class, id));
                return "redirect:/invoice";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "404";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id")String id, ModelMap model) {
        model.addAttribute("invoice", iHoaDonService.castToModelView(
                iHoaDonService.getById(HoaDon.class, id), new SimpleDateFormat("dd/MM/yyyy")));
        return "invoice.details";
    }

    @GetMapping("/search")
    public @ResponseBody String search(@RequestParam(value = "from", required = false)String from,
                                @RequestParam(value = "to", required = false)String to) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        if (!((from == null || from.isEmpty()) && (to == null || to.isEmpty()))) {
            try {
                List<SearchInvoice> result = new ArrayList<>();
                iHoaDonService.getHoaDonFromTo(from, to).forEach(h -> {
                    SearchInvoice hoaDon = new SearchInvoice();
                    hoaDon.setId(h.getId());
                    hoaDon.setTenBenhNhan(h.getToaThuoc().getBenhNhan().getTen());
                    hoaDon.setNgayXuat(h.getNgayXuat());
                    hoaDon.setTenNhanVien(h.getNhanVien().getTen());
                    hoaDon.setTongTien(h.getTongTien());
                    result.add(hoaDon);
                });
                ajaxResponse = mapper.writeValueAsString(result);
            }
            catch (JsonProcessingException | ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                List<SearchInvoice> result = new ArrayList<>();
                iHoaDonService.getAll(HoaDon.class).forEach(h -> {
                    SearchInvoice hoaDon = new SearchInvoice();
                    hoaDon.setId(h.getId());
                    hoaDon.setTenBenhNhan(h.getToaThuoc().getBenhNhan().getTen());
                    hoaDon.setNgayXuat(h.getNgayXuat());
                    hoaDon.setTenNhanVien(h.getNhanVien().getTen());
                    hoaDon.setTongTien(h.getTongTien());
                    result.add(hoaDon);
                });
                ajaxResponse = mapper.writeValueAsString(result);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ajaxResponse;
    }
}
