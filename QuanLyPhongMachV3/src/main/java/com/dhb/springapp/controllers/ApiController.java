package com.dhb.springapp.controllers;

import com.dhb.springapp.models.*;
import com.dhb.springapp.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    IToaThuocService iToaThuocService;
    @Autowired
    ICaKhamBenhService iCaKhamBenhService;
    @Autowired
    IBenhNhanService iBenhNhanService;
    @Autowired
    ILoaiBenhService iLoaiBenhService;
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    IHoaDonService iHoaDonService;

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

    @GetMapping("/getTotalPatient")
    public @ResponseBody String getTotalPatient() {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        int[] result = iBenhNhanService.getTotalPatientInMonthOfYear(new Date().getYear());
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/getTypeOfDisease")
    public @ResponseBody String getTypeOfDisease() {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        List<LoaiBenh> loaiBenhs = iLoaiBenhService.getAll(LoaiBenh.class);
        Map<Integer, String> diseases = new HashMap<>();
        loaiBenhs.forEach(l -> {
            diseases.put(l.getId(), l.getTenBenh());
        });

        try {
            ajaxResponse = mapper.writeValueAsString(diseases);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/getTotalPatientOnDisease")
    public @ResponseBody String getTotalPatientOnDisease(@RequestParam("id")String id,
                                                         @RequestParam("year")String year) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        int result = iBenhNhanService.getTotalPatientOnDisease(Integer.parseInt(id), Integer.parseInt(year));
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/getTotalDoctors")
    public @ResponseBody String getTotalDoctors() {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        List<BacSi> result  = new ArrayList<>();
        iBacSiService.getAll(BacSi.class).forEach(b -> {
            BacSi bacSi = new BacSi();
            bacSi.setId(b.getId());
            bacSi.setHo(b.getHo());
            bacSi.setTen(b.getTen());
            bacSi.setGioiTinh(b.getGioiTinh());
            bacSi.setNgaySinh(b.getNgaySinh());
            bacSi.setDienThoai(b.getDienThoai());
            bacSi.setImage(b.getImage());
            bacSi.setQueQuan(b.getQueQuan());
            bacSi.setEmail(b.getEmail());

            result.add(bacSi);
        });

        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/getTotalPrescriptionOfDoctor")
    public @ResponseBody String getTotalPatientOfDoctor(@RequestParam(value = "filter", required = false)
                                                                    String filter) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        int[] result = iBacSiService.getTotalPrescriptionOfDoctor(filter);
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/getTotalSales")
    public @ResponseBody String getTotalSales(@RequestParam("from")String from,
                                              @RequestParam("to")String to) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        BigDecimal[] result = iHoaDonService.getTotalSalesFromTo(from, to);
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }
}
