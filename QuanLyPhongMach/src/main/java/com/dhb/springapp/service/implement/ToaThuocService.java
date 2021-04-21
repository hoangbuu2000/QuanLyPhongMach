package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.modelview.AddPrescription;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.IToaThuocRepository;
import com.dhb.springapp.service.IToaThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;

@Service
public class ToaThuocService extends GenericService<ToaThuoc> implements IToaThuocService {
    private IToaThuocRepository toaThuocRepository;

    @Autowired
    public ToaThuocService(@Qualifier("toaThuocRepository")IGenericRepository<ToaThuoc> iGenericRepository) {
        super(iGenericRepository);
        this.toaThuocRepository = (IToaThuocRepository) iGenericRepository;
    }

    @Override
    public AddPrescription castToModelView(ToaThuoc toaThuoc, SimpleDateFormat format) {
        AddPrescription result = new AddPrescription();
        Map<Thuoc, Integer> medicines = new HashMap<>();
        result.setId(toaThuoc.getId());
        result.setDoctor(toaThuoc.getBacSi());
        result.setPatient(toaThuoc.getBenhNhan());
        result.setDate(format.format(toaThuoc.getNgayKeToa()));
        result.setDisease(toaThuoc.getLoaiBenh());
        toaThuoc.getDsChiTietToaThuoc().forEach(t -> {
            medicines.put(t.getThuoc(), t.getSoLuong());
        });
        result.setMedicines(medicines);
        return result;
    }

    @Override
    public void addOrUpdate(String id, AddPrescription addPrescription, SimpleDateFormat format) throws Exception {
        if (id != null && !id.isEmpty()) {
            // update
            ToaThuoc toaThuoc = getById(ToaThuoc.class, id);
            toaThuoc.setBacSi(addPrescription.getDoctor());
            toaThuoc.setBenhNhan(addPrescription.getPatient());
            toaThuoc.setLoaiBenh(addPrescription.getDisease());
            toaThuoc.setNgayKeToa(format.parse(addPrescription.getDate()));

            List<ChiTietToaThuoc> chiTietToaThuocList = setUpChiTietToaThuoc(addPrescription, toaThuoc);
            if (toaThuocRepository.addOrUpdate(id, toaThuoc, chiTietToaThuocList))
                throw new Exception("Giao tac them that bai");

        }
        else {
            // insert
            ToaThuoc toaThuoc = new ToaThuoc();
            toaThuoc.setId(UUID.randomUUID().toString());
            toaThuoc.setBacSi(addPrescription.getDoctor());
            toaThuoc.setBenhNhan(addPrescription.getPatient());
            toaThuoc.setNgayKeToa(format.parse(addPrescription.getDate()));
            toaThuoc.setLoaiBenh(addPrescription.getDisease());

            List<ChiTietToaThuoc> chiTietToaThuocList = setUpChiTietToaThuoc(addPrescription, toaThuoc);
            if (toaThuocRepository.addOrUpdate(id, toaThuoc, chiTietToaThuocList))
                throw new Exception("Giao tac them that bai");
        }
    }

    private List<ChiTietToaThuoc> setUpChiTietToaThuoc(AddPrescription addPrescription, ToaThuoc toaThuoc) {
        List<ChiTietToaThuoc> chiTietToaThuocList = new ArrayList<>();
        addPrescription.getMedicines().forEach((thuoc, soLuong) -> {
            ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
            chiTietToaThuoc.setToaThuoc(toaThuoc);
            chiTietToaThuoc.setThuoc(thuoc);
            chiTietToaThuoc.setDonGia(thuoc.getDonGia());
            chiTietToaThuoc.setSoLuong(soLuong);
            chiTietToaThuoc.setThanhTien(thuoc.getDonGia().multiply(new BigDecimal(soLuong)));

            chiTietToaThuocList.add(chiTietToaThuoc);
        });

        return chiTietToaThuocList;
    }
}
