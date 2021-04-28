package com.dhb.springapp.controllers;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.service.ICaKhamBenhService;
import com.dhb.springapp.service.IToaThuocService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    IToaThuocService iToaThuocService;
    @Autowired
    ICaKhamBenhService iCaKhamBenhService;

    @GetMapping("/ajax")
    public @ResponseBody
    String layBacSi(@RequestParam("date") String date,
                    @RequestParam("shift") int shiftID) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<BacSi> ds = new ArrayList<>();
            iCaKhamBenhService.layBacSiTheoCaKham(shiftID, format.parse(date)).forEach(t -> {
                BacSi b = new BacSi();
                b.setId(t.getId());
                b.setTen(t.getTen());
                b.setHo(t.getHo());
                b.setDienThoai(t.getDienThoai());
                b.setEmail(t.getEmail());
                b.setGioiTinh(t.getGioiTinh());
                b.setNgaySinh(t.getNgaySinh());
                b.setImage(t.getImage());
                b.setQueQuan(t.getQueQuan());
                ds.add(b);
            });
            ajaxResponse = mapper.writeValueAsString(ds);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/ajax1")
    public @ResponseBody String layCaKham(@RequestParam("date") String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<CaKhamBenh> ds = new ArrayList<>();
            iCaKhamBenhService.layCaKhamTheoNgayKham(format.parse(date)).forEach(t -> {
                CaKhamBenh caKhamBenh = new CaKhamBenh();
                caKhamBenh.setId(t.getId());
                caKhamBenh.setTenCa(t.getTenCa());
                caKhamBenh.setMoTa(t.getMoTa());
                ds.add(caKhamBenh);
            });
            ajaxResponse = mapper.writeValueAsString(ds);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/getDiseaseDetails")
    public @ResponseBody String diseaseDetails(@RequestParam("id")String id) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            Map<Thuoc, Integer> medicines = new HashMap<>();
            iToaThuocService.getById(ToaThuoc.class, id).getDsChiTietToaThuoc().forEach(ct -> {
                Thuoc thuoc = new Thuoc();
                thuoc.setId(ct.getThuoc().getId());
                thuoc.setTenThuoc(ct.getThuoc().getTenThuoc());
                thuoc.setDonGia(ct.getDonGia());
                thuoc.setMoTa(ct.getThuoc().getMoTa());
                thuoc.setDonVi(ct.getThuoc().getDonVi());
                Integer soLuong = Integer.valueOf(ct.getSoLuong());

                medicines.put(thuoc, soLuong);
            });
            ajaxResponse = mapper.writeValueAsString(medicines);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
