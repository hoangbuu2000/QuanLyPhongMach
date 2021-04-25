package com.dhb.springapp.utils;

import com.dhb.springapp.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private final static SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost/java");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "123456789");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

        conf.setProperties(settings);
        conf.addAnnotatedClass(BacSi.class);
        conf.addAnnotatedClass(TaiKhoan.class);
        conf.addAnnotatedClass(ToaThuoc.class);
        conf.addAnnotatedClass(ChiTietCaKhamBenh.class);
        conf.addAnnotatedClass(Role.class);
        conf.addAnnotatedClass(Admin.class);
        conf.addAnnotatedClass(CaKhamBenh.class);
        conf.addAnnotatedClass(BenhNhan.class);
        conf.addAnnotatedClass(ChiTietToaThuoc.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(LoaiBenh.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(PhieuKhamBenh.class);
        conf.addAnnotatedClass(Thuoc.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();

        FACTORY = conf.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
