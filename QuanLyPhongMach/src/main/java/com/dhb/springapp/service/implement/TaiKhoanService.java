package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.Role;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.ITaiKhoanRepository;
import com.dhb.springapp.service.IRoleService;
import com.dhb.springapp.service.ITaiKhoanService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TaiKhoanService extends GenericService<TaiKhoan> implements ITaiKhoanService {
    private ITaiKhoanRepository taiKhoanRepository;
    @Autowired
    private IRoleService roleService;

    @Autowired
    public TaiKhoanService(@Qualifier("taiKhoanRepository")IGenericRepository<TaiKhoan> iGenericRepository) {
        super(iGenericRepository);
        this.taiKhoanRepository = (ITaiKhoanRepository) iGenericRepository;
    }

    @Override
    public TaiKhoan getTaiKhoanByUsername(String username) {
        return taiKhoanRepository.getTaiKhoanByUsername(username);
    }

    @Override
    public List<TaiKhoan> getTaiKhoanTheoChucVu(String kw) {
        return taiKhoanRepository.getTaiKhoanTheoChucVu(kw);
    }

    @Override
    public void themTaiKhoanVaBacSi(AddDoctor addDoctor, HttpServletRequest request) throws Exception {
        //chi luu duoc anh trong thu muc target tuk a trui
        MultipartFile img = addDoctor.getImage();
        String relativePath = "/resources/images/bacsi/" + addDoctor.getUsername() + ".png";
        String targetPath = request.getSession().getServletContext()
                .getRealPath(String.format("/resources/images/bacsi/%s.png", addDoctor.getUsername()));
        if (img != null && !img.isEmpty()) {
            try{
                img.transferTo(new File(targetPath));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //luu y o day la mot giao tac, them tai khoan truoc, sau do them bac si
                String id = UUID.randomUUID().toString();
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setId(id);
                taiKhoan.setUsername(addDoctor.getUsername());
                taiKhoan.setPassword(addDoctor.getPassword());
                taiKhoan.setActive(addDoctor.isActive());
                taiKhoan.setRole(roleService.getById(Role.class, 2));


                BacSi bacSi = new BacSi();
                bacSi.setTaiKhoan(taiKhoan);
                bacSi.setTen(addDoctor.getTen());
                bacSi.setHo(addDoctor.getHo());
                bacSi.setDienThoai(addDoctor.getDienThoai());
                bacSi.setNgaySinh(format.parse(addDoctor.getNgaySinh()));
                bacSi.setGioiTinh(addDoctor.getGioiTinh());
                bacSi.setImage(relativePath);
                bacSi.setQueQuan(addDoctor.getQueQuan());
                bacSi.setEmail(addDoctor.getEmail());

                if(!taiKhoanRepository.themTaiKhoanVaBacSi(taiKhoan, bacSi))
                    throw new Exception("Giao tac them that bat");
            }
            catch (IllegalStateException | IOException | ParseException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    public void suaTaiKhoanVaBacSi(String id, AddDoctor editedDoctor, HttpServletRequest request) throws Exception {
        TaiKhoan taiKhoan = getById(TaiKhoan.class, id);
        BacSi bacSi = taiKhoan.getBacSi();
        MultipartFile img = editedDoctor.getImage();
        String relativePath = "/resources/images/bacsi/" + editedDoctor.getUsername() + ".png";
        String targetPath = request.getSession().getServletContext()
                .getRealPath(String.format("/resources/images/bacsi/%s.png", editedDoctor.getUsername()));
        if (!checkNoChangeUsername(id, editedDoctor)) {
            relativePath = "/resources/images/bacsi/" + editedDoctor.getUsername() + ".png";
            targetPath = request.getSession().getServletContext()
                    .getRealPath(String.format("/resources/images/bacsi/%s.png", editedDoctor.getUsername()));
            String oldPath = request.getSession().getServletContext()
                    .getRealPath(String.format("/resources/images/bacsi/%s.png", taiKhoan.getUsername()));
            File file = new File(oldPath);
            file.renameTo(new File(targetPath));
        }
        if (img != null && !img.isEmpty()) {
            try {
                String oldPath = request.getSession().getServletContext()
                        .getRealPath(String.format("/resources/images/bacsi/%s.png", taiKhoan.getUsername()));
                File file = new File(oldPath);
                file.delete();
                img.transferTo(new File(targetPath));
            }
            catch (IllegalStateException | IOException ex) {
                System.err.println(ex.getMessage());
            }
        }


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        bacSi.setTen(editedDoctor.getTen());
        bacSi.setHo(editedDoctor.getHo());
        bacSi.setEmail(editedDoctor.getEmail());
        bacSi.setGioiTinh(editedDoctor.getGioiTinh());
        bacSi.setNgaySinh(format.parse(editedDoctor.getNgaySinh()));
        bacSi.setQueQuan(editedDoctor.getQueQuan());
        bacSi.setDienThoai(editedDoctor.getDienThoai());
        bacSi.setImage(relativePath);

        taiKhoan.setUsername(editedDoctor.getUsername());
        taiKhoan.setPassword(editedDoctor.getPassword().isEmpty() ? taiKhoan.getPassword() : editedDoctor.getPassword());
        taiKhoan.setActive(editedDoctor.isActive());

        if (!taiKhoanRepository.suaTaiKhoanVaBacSi(taiKhoan, bacSi))
            throw new Exception("Giao tac them that bai");

    }

    @Override
    public boolean checkPassword(AddDoctor addDoctor) {
        return addDoctor.getPassword().equals(addDoctor.getConfirmPassword());
    }

    @Override
    public boolean checkExistedUsername(AddDoctor addDoctor) {
        return getTaiKhoanByUsername(addDoctor.getUsername()) == null;
    }

    @Override
    public boolean checkNoChangeUsername(String id, AddDoctor addDoctor) {
        return getById(TaiKhoan.class, id).getUsername().equals(addDoctor.getUsername());
    }
}
