package com.example.thienphan.model;

import java.io.Serializable;

/**
 * Created by Thien Phan on 02/10/2017.
 */

public class KhuyenMai implements Serializable {
    private int imgKhuyenMai;
    private String thongTinKhuyenMai;

    public KhuyenMai() {
    }

    public KhuyenMai(int imgKhuyenMai, String thongTinKhuyenMai) {
        this.imgKhuyenMai = imgKhuyenMai;
        this.thongTinKhuyenMai = thongTinKhuyenMai;
    }

    public int getImgKhuyenMai() {
        return imgKhuyenMai;
    }

    public void setImgKhuyenMai(int imgKhuyenMai) {
        this.imgKhuyenMai = imgKhuyenMai;
    }

    public String getThongTinKhuyenMai() {
        return thongTinKhuyenMai;
    }

    public void setThongTinKhuyenMai(String thongTinKhuyenMai) {
        this.thongTinKhuyenMai = thongTinKhuyenMai;
    }
}
