package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.service.ILoaiBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/disease")
public class DiseaseController {
    @Autowired
    ILoaiBenhService iLoaiBenhService;

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("diseases", iLoaiBenhService.getAll(LoaiBenh.class));
        return "disease.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(@RequestParam(value = "id", required = false)String id,
                                  ModelMap model) {
        if (id != null && !id.isEmpty()) {
            model.addAttribute("disease", iLoaiBenhService.getById(LoaiBenh.class, Integer.parseInt(id)));
        }
        else {
            model.addAttribute("disease", new LoaiBenh());
        }
        return "disease.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@RequestParam(value = "id", required = false)String id,@ModelAttribute("disease") @Valid LoaiBenh loaiBenh,
                                     BindingResult result) {
        if (!result.hasErrors()) {
            try {
                if (id != null && !id.isEmpty())
                    iLoaiBenhService.update(loaiBenh);
                else
                    iLoaiBenhService.insert(loaiBenh);
                return "redirect:/admin/disease";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "disease.add";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        if (id != null && !id.isEmpty()) {
            try {
                iLoaiBenhService.delete(iLoaiBenhService.getById(LoaiBenh.class, Integer.parseInt(id)));
                return "redirect:/admin/disease";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "404";
    }
}
