package com.example.thienphan.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thienphan.model.SanPham;
import com.example.thienphan.quantrivien.R;

import java.util.List;

/**
 * Created by Thien Phan on 29/09/2017.
 */

public class AdapterSanPham extends ArrayAdapter<SanPham> {

    Activity context;
    int resource;
    List<SanPham> objects;

    public AdapterSanPham(@NonNull Activity context, @LayoutRes int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);

        ImageView imgSanPham = item.findViewById(R.id.imgSanPham);
        TextView txtTenSanPham = item.findViewById(R.id.txtTenSanPham);
        TextView txtTenDanhMuc = item.findViewById(R.id.txtTenDanhMuc);
        TextView txtGia = item.findViewById(R.id.txtGia);

        SanPham sanPham = this.objects.get(position);

        imgSanPham.setImageResource(sanPham.getHinhAnhSanPham());
        txtTenSanPham.setText(sanPham.getTenSanPham());
        txtTenDanhMuc.setText(sanPham.getTenDanhMuc());
        String giaSanPham = sanPham.getGiaSanPham();
        String setGiaHangTram,setGiaHangNghin,giaThucTe;
        if (giaSanPham.length() >=7 )
        {
            setGiaHangTram = giaSanPham.substring((giaSanPham.length()-3),(giaSanPham.length()));
            setGiaHangNghin = giaSanPham.substring(giaSanPham.length()-6,giaSanPham.length()-3);
            giaThucTe = giaSanPham.substring(0,(giaSanPham.length()-6))+","+setGiaHangNghin+","+setGiaHangTram+"đ";
            txtGia.setText(giaThucTe);
        }
        else if(giaSanPham.length() >=4)
        {
            setGiaHangTram = giaSanPham.substring((giaSanPham.length()-3),(giaSanPham.length()));
            giaThucTe = giaSanPham.substring(0,(giaSanPham.length()-3))+","+setGiaHangTram+"đ";
            txtGia.setText(giaThucTe);
        }
        else
        {
            txtGia.setText(sanPham.getGiaSanPham()+"đ");
        }

        return item;
    }
}
