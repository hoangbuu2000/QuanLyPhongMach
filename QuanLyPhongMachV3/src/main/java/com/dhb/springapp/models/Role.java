package com.dhb.springapp.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Ten")
    private String ten;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<TaiKhoan> dsTaiKhoan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<TaiKhoan> getDsTaiKhoan() {
        return dsTaiKhoan;
    }

    public void setDsTaiKhoan(Set<TaiKhoan> dsTaiKhoan) {
        this.dsTaiKhoan = dsTaiKhoan;
    }
}
