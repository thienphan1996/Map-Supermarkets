package com.example.thienphan.model;

import java.io.Serializable;

/**
 * Created by Thien Phan on 02/10/2017.
 */

public class TaiKhoan implements Serializable {
    private String tenTaiKhoan;
    private String matKhau;
    private String maDangKy;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, String maDangKy) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.maDangKy = maDangKy;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaDangKy() {
        return maDangKy;
    }

    public void setMaDangKy(String maDangKy) {
        this.maDangKy = maDangKy;
    }
}
