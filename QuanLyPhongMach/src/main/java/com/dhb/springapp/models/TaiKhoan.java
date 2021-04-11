package com.dhb.springapp.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taikhoan")
public class TaiKhoan implements Serializable {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "TaiKhoan")
    private String username;
    @Column(name = "MatKhau")
    private String password;
    @Column(name = "Active")
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "MaRole")
    private Role role;
    @OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BacSi bacSi;
    @OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private NhanVien nhanVien;
    @OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Admin admin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
