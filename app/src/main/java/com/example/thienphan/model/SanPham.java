package com.example.thienphan.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thien Phan on 29/09/2017.
 */

public class SanPham implements Serializable {
    private int hinhAnhSanPham;
    private String tenSanPham;
    private String tenDanhMuc;
    private String giaSanPham;

    public SanPham() {
    }

    public SanPham(int hinhAnhSanPham, String tenSanPham, String tenDanhMuc, String giaSanPham) {
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.tenSanPham = tenSanPham;
        this.tenDanhMuc = tenDanhMuc;
        this.giaSanPham = giaSanPham;
    }

    public int getHinhAnhSanPham() {
        return hinhAnhSanPham;
    }

    public void setHinhAnhSanPham(int hinhAnhSanPham) {
        this.hinhAnhSanPham = hinhAnhSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public static ArrayList<SanPham> ThongKeDanhMuc(ArrayList<SanPham> arrSanPham,String tenDanhMucThongKe)
    {
        ArrayList<SanPham> arrTemp = new ArrayList<>();
        for (SanPham sanPham: arrSanPham)
        {
            if (sanPham.getTenDanhMuc().equals(tenDanhMucThongKe))
            {
                arrTemp.add(sanPham);
            }
        }
        return arrTemp;
    }


    public static void giaTangDan(ArrayList<SanPham> arrSanPham)
    {
        for (int i = 0; i < (arrSanPham.size());i++)
            for (int j = i + 1; j < arrSanPham.size(); j++)
            {
                SanPham moDelA = arrSanPham.get(i);
                SanPham moDelB = arrSanPham.get(j);
                long a =(long) Long.parseLong(moDelA.getGiaSanPham());
                long b =(long) Long.parseLong(moDelB.getGiaSanPham());
                if ( a > b)
                {
                    SanPham sanPhamTemp = moDelA;
                    arrSanPham.set(i,moDelB);
                    arrSanPham.set(j,sanPhamTemp);
                }
            }
    }

    public static void giaGiamDan(ArrayList<SanPham> arrSanPham)
    {
        for (int i = 0; i < (arrSanPham.size()); i++)
            for (int j = i + 1; j < arrSanPham.size(); j++)
            {
                SanPham moDelA = arrSanPham.get(i);
                SanPham moDelB = arrSanPham.get(j);
                long a = (long) Long.parseLong(moDelA.getGiaSanPham());
                long b = (long) Long.parseLong(moDelB.getGiaSanPham());
                if ( a < b)
                {
                    SanPham sanPhamTemp = moDelA;
                    arrSanPham.set(i,moDelB);
                    arrSanPham.set(j,sanPhamTemp);
                }
            }
    }


    public static void SapXepTheoTen(ArrayList<SanPham> arrSanPham)
    {
        for (int i = 0; i < arrSanPham.size() ; i++)
            for (int j = i + 1; j < arrSanPham.size() ; j++)
            {
                char a = arrSanPham.get(i).getTenSanPham().charAt(0);
                char b = arrSanPham.get(j).getTenSanPham().charAt(0);
                if (a > b)
                {
                    SanPham sP = arrSanPham.get(i);
                    arrSanPham.set(i,arrSanPham.get(j));
                    arrSanPham.set(j,sP);
                }
            }
    }
}
