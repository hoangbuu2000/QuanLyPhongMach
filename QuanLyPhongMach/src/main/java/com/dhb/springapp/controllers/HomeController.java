package com.dhb.springapp.controllers;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.service.IBacSiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("home")
@RequestMapping("/")
public class HomeController {
//    @Autowired
//    IBacSiService iBacSiService;

    @RequestMapping()
    public String index(ModelMap model) {
//        model.addAttribute("bacsi", iBacSiService.getAll(BacSi.class));

//            Query query = session.createSQLQuery(
//                    "CALL getBacSiById(:id)")
//                    .addEntity(BacSi.class)
//                    .setParameter("id", "1851050169");
//            Query query1 = session.createSQLQuery(
//                    "CALL getBenhNhanTheoNgayKhamBenh(:date)")
//                    .addEntity(BenhNhan.class)
//                    .setParameter("date", "2021-03-18");

//            model.addAttribute("bacsi", query.list());
//            model.addAttribute("benhnhan", query1.list());

//            Query query = session.createQuery("From TaiKhoan");
//            model.addAttribute("taikhoan", query.list());

//            Query q = session.createQuery("From ToaThuoc");
//            model.addAttribute("toathuoc", q.getResultList());

//            Query q = session.createQuery("From HoaDon");
//            model.addAttribute("hoadon", q.getResultList());

            return "homePage";

    }
}
