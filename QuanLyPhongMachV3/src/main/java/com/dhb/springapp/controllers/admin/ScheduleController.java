package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.ChiTietCaKhamBenh;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.ICaKhamBenhService;
import com.dhb.springapp.service.IChiTietCaKhamBenhService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@ControllerAdvice
@RequestMapping("/admin/schedule")
public class ScheduleController {
    @Autowired
    IChiTietCaKhamBenhService iChiTietCaKhamBenhService;
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    ICaKhamBenhService iCaKhamBenhService;

    @ModelAttribute
    public void modelAttribute(ModelMap model) {
        //Chi lay nhung ca kham benh con trong theo ngay
        model.addAttribute("doctors", iBacSiService.getAll(BacSi.class));
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("schedules", iChiTietCaKhamBenhService
                .getAllOrderBy(ChiTietCaKhamBenh.class, "ngayKhamBenh", Order.desc));
        return "schedule.index";
    }

    @GetMapping("/api/shifts")
    @ResponseBody
    // lay ca kham hien tai cua bac si bo vo select de ngan truong hop ca do da day
    // chu y khi ca kham hien tai cua bac si la ca kham con trong co the bi trung lap du lieu
    public String getShifts(@RequestParam("date") String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        try {
            List<CaKhamBenh> shifts = new ArrayList<>();
            iCaKhamBenhService.layCaKhamConTrong(format.parse(date)).forEach(c -> {
                CaKhamBenh caKhamBenh = new CaKhamBenh();
                caKhamBenh.setId(c.getId());
                caKhamBenh.setTenCa(c.getTenCa());
                caKhamBenh.setMoTa(c.getMoTa());
                shifts.add(caKhamBenh);
            });
            ajaxResponse = mapper.writeValueAsString(shifts);
        }
        catch (JsonProcessingException | ParseException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("schedule", new ChiTietCaKhamBenh());
        return "schedule.add";
    }

    // Neu admin them thi se duoc chon bac si, neu bac si tu them thi khong duoc chon bac si ma chi lay bac si hien tai
    // Luc them kiem tra xem bac si do da dang ky ca lam do truoc do hay chua, neu roi chan khong cho them nua
    @PostMapping("/add")
    public String addProcess(@ModelAttribute("schedule") @Valid ChiTietCaKhamBenh schedule,
                             BindingResult result, ModelMap model) throws ParseException {
        if (!result.hasErrors()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            schedule.setNgayKhamBenh(format.parse(schedule.getNgayKham()));

            if (!iChiTietCaKhamBenhService.checkExistedSchedule(schedule.getBacSi(),
                    schedule.getCaKhamBenh(),
                    schedule.getNgayKhamBenh())) {
                ChiTietCaKhamBenh t = iChiTietCaKhamBenhService.insert(schedule);
                if (t != null)
                    return "redirect:/admin/schedule";
                else
                    model.addAttribute("message", "Giao tac them that bai");
            }
            else {
                model.addAttribute("message", "Lich lam viec nay da ton tai");
            }

        }
        return "schedule.add";
    }

    @GetMapping("/edit/{idD}/{idS}/{date}")
    public String editView(@PathVariable("idD") String maBacSi, @PathVariable("idS") String maCaKham,
                           @PathVariable("date") String date, ModelMap model) throws ParseException {
        BacSi bacSi = iBacSiService.getById(BacSi.class, maBacSi);
        CaKhamBenh caKhamBenh = iCaKhamBenhService.getById(CaKhamBenh.class, Integer.parseInt(maCaKham));
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        ChiTietCaKhamBenh schedule = iChiTietCaKhamBenhService.getExistedSchedule(bacSi,
                caKhamBenh, format.parse(date));
        model.addAttribute("schedule", schedule);
        model.addAttribute("ngayKham", date);
        return "schedule.edit";
    }

    @PostMapping("/edit")
    public String editProcess(@ModelAttribute("schedule") @Valid ChiTietCaKhamBenh schedule,
                              BindingResult result, ModelMap model) throws ParseException {
        if (!result.hasErrors()) {
            System.out.println(schedule.getNgayKham());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            schedule.setNgayKhamBenh(format.parse(schedule.getNgayKham()));

            if (iChiTietCaKhamBenhService.checkExistedSchedule(schedule.getBacSi(),
                    schedule.getCaKhamBenh(),
                    format.parse(schedule.getNgayKham()))) {
                ChiTietCaKhamBenh t = iChiTietCaKhamBenhService.update(schedule);
                if (t != null)
                    return "redirect:/admin/schedule";
                else
                    model.addAttribute("message", "Giao tac sua that bai");
            }
            else {
                model.addAttribute("message", "Khong ton tai lich lam viec");
            }
        }
        return "schedule.edit";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") String id) {
        return "schedule.details";
    }

    //Viet api
    @PostMapping("/delete/{idD}/{idS}/{date}")
    public String delete(@PathVariable("idD") String maBacSi, @PathVariable("idS") String maCa,
                         @PathVariable("date") String date, ModelMap model) {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            ChiTietCaKhamBenh chiTietCaKhamBenh = iChiTietCaKhamBenhService.getAll(ChiTietCaKhamBenh.class).stream()
                    .filter(t -> {
                        try {
                            return t.getBacSi().getId().equals(maBacSi)
                                    && t.getCaKhamBenh().getId() == Integer.parseInt(maCa)
                                    && t.getNgayKhamBenh().getYear() == format.parse(date).getYear()
                                    && t.getNgayKhamBenh().getMonth() == format.parse(date).getMonth()
                                    && t.getNgayKhamBenh().getDate() == format.parse(date).getDate();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).findFirst().orElse(null);
            iChiTietCaKhamBenhService.delete(chiTietCaKhamBenh);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("message", "Xoa that bai");
            return "forward:/admin/schedule";
        }

        return "redirect:/admin/schedule";
    }
}
