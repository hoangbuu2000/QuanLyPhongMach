package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.service.ILoaiBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/chart")
public class ChartController {
    @Autowired
    ILoaiBenhService iLoaiBenhService;

    @GetMapping("/patients")
    public String patients() {
        return "chart.patients";
    }

    @GetMapping("/doctors")
    public String doctors(ModelMap model) {
        List<LoaiBenh> loaiBenhs = iLoaiBenhService.getAll(LoaiBenh.class);
        Map<Integer, String> diseases = new HashMap<>();
        loaiBenhs.forEach(l -> {
            diseases.put(l.getId(), l.getTenBenh());
        });
        model.addAttribute("diseasesKK", diseases);
        return "chart.doctors";
    }

    @GetMapping("/profit")
    public String profit() {
        return "chart.profit";
    }
}
