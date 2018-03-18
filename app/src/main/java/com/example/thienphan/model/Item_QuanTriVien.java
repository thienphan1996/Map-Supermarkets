package com.example.thienphan.model;

/**
 * Created by Thien Phan on 28/09/2017.
 */

public class Item_QuanTriVien {
    private int id;
    private boolean chiTiet;
    private String tenQuanTri;

    public Item_QuanTriVien() {
    }

    public Item_QuanTriVien(int id, boolean chiTiet, String tenQuanTri) {
        this.id = id;
        this.chiTiet = chiTiet;
        this.tenQuanTri = tenQuanTri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(boolean chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getTenQuanTri() {
        return tenQuanTri;
    }

    public void setTenQuanTri(String tenQuanTri) {
        this.tenQuanTri = tenQuanTri;
    }
}
