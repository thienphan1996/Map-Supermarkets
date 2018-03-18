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

import com.example.thienphan.model.KhuyenMai;
import com.example.thienphan.quantrivien.R;

import java.util.List;

/**
 * Created by Thien Phan on 02/10/2017.
 */

public class AdapterKhuyenMai extends ArrayAdapter<KhuyenMai> {
    Activity context;
    int resource;
    List<KhuyenMai> objects;
    public AdapterKhuyenMai(@NonNull Activity context, @LayoutRes int resource, @NonNull List<KhuyenMai> objects) {
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

        ImageView imgKhuyenMai = item.findViewById(R.id.imgThongTinKhuyenMai);
        TextView txtThongTinKhuyenMai = item.findViewById(R.id.txtThongTinKhuyenMai);

        imgKhuyenMai.setImageResource(this.objects.get(position).getImgKhuyenMai());
        txtThongTinKhuyenMai.setText(this.objects.get(position).getThongTinKhuyenMai());

        return item;
    }
}
