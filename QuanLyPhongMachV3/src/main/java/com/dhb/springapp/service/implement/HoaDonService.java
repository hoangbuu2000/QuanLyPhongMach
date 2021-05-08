package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.models.HoaDon;
import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.modelview.AddInvoice;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.IHoaDonRepository;
import com.dhb.springapp.repository.IThuocRepository;
import com.dhb.springapp.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HoaDonService extends GenericService<HoaDon> implements IHoaDonService {
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    private IThuocRepository iThuocRepository;

    @Autowired
    public HoaDonService(@Qualifier("hoaDonRepository")IGenericRepository<HoaDon> iGenericRepository) {
        super(iGenericRepository);
        this.hoaDonRepository = (IHoaDonRepository) iGenericRepository;
    }

    @Override
    public AddInvoice castToModelView(HoaDon hoaDon, SimpleDateFormat format) {
        AddInvoice addInvoice = new AddInvoice();
        Map<Thuoc, Integer> medicines = new HashMap<>();
        addInvoice.setId(hoaDon.getId());
        addInvoice.setPrescription(hoaDon.getToaThuoc());
        addInvoice.setEmployee(hoaDon.getNhanVien());
        addInvoice.setDate(format.format(hoaDon.getNgayXuat()));
        hoaDon.getToaThuoc().getDsChiTietToaThuoc().forEach(t -> {
            medicines.put(t.getThuoc(), t.getSoLuong());
        });
        addInvoice.setMedicines(medicines);

        return addInvoice;
    }

    @Override
    public void addOrUpdate(String id, AddInvoice addInvoice, SimpleDateFormat format) throws Exception {
        if (id != null && !id.isEmpty()) {
            HoaDon hoaDon = hoaDonRepository.getById(HoaDon.class, id);
            hoaDon.setNhanVien(addInvoice.getEmployee());
            hoaDon.setToaThuoc(addInvoice.getPrescription());
            hoaDon.setNgayXuat(format.parse(addInvoice.getDate()));
            hoaDon.setTongTien(getTongTien(addInvoice.getMedicines()));
            if (!hoaDonRepository.addOrUpdate(id, hoaDon))
                throw new Exception("Giao tac them that bai");

        }
        else {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(UUID.randomUUID().toString());
            hoaDon.setToaThuoc(addInvoice.getPrescription());
            hoaDon.setNhanVien(addInvoice.getEmployee());
            hoaDon.setNgayXuat(format.parse(addInvoice.getDate()));
            hoaDon.setTongTien(getTongTien(addInvoice.getMedicines()));
            if (!hoaDonRepository.addOrUpdate(id, hoaDon))
                throw new Exception("Giao tac them that bai");
        }
    }

    private BigDecimal getTongTien(Map<Thuoc, Integer> medicines) {
        BigDecimal result = new BigDecimal(0);
        for(Map.Entry<Thuoc, Integer> me : medicines.entrySet()) {
            Thuoc thuoc = iThuocRepository.getById(Thuoc.class, me.getKey().getId());
            Integer soLuong = me.getValue();
            result = result.add(thuoc.getDonGia().multiply(new BigDecimal(soLuong)));
        }
        return result;
    }

    @Override
    public HoaDon getHoaDonTheoToaThuoc(String maToaThuoc) {
        return hoaDonRepository.getHoaDonTheoToaThuoc(maToaThuoc);
    }

    @Override
    public List<HoaDon> getHoaDonFromTo(String from, String to) throws ParseException {
        return hoaDonRepository.getHoaDonFromTo(from, to);
    }

    @Override
    public BigDecimal getTotalSales(int year) {
        List<HoaDon> hoaDonList = hoaDonRepository.getAll(HoaDon.class);
        List<HoaDon> result = hoaDonList.stream().filter(h -> h.getNgayXuat().getYear() + 1900 == year)
                .collect(Collectors.toList());
        BigDecimal tong = new BigDecimal(0);
        for(HoaDon h : result) {
            tong = tong.add(h.getTongTien());
        }
        return tong;
    }

    @Override
    public BigDecimal[] getTotalSalesFromTo(String from, String to) {
        int f = Integer.parseInt(from);
        int t = Integer.parseInt(to);
        BigDecimal[] result = new BigDecimal[t - f + 1];
        for(int i = f; i <= t; i++) {
            result[i - f] = getTotalSales(i);
        }
        return result;
    }
}
