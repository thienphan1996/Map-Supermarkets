package com.example.thienphan.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thienphan.model.Item_QuanTriVien;
import com.example.thienphan.quantrivien.ManHinhBanDo;
import com.example.thienphan.quantrivien.ManHinhKhuyenMai;
import com.example.thienphan.quantrivien.ManHinhSanPham;
import com.example.thienphan.quantrivien.ManHinhUser;
import com.example.thienphan.quantrivien.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thien Phan on 28/09/2017.
 */

public class AdapterQuanTriVien extends ArrayAdapter<Item_QuanTriVien> {

    Activity context;
    int resource;
    List<Item_QuanTriVien> objects;

    public AdapterQuanTriVien(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<Item_QuanTriVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        ImageView imgQuanTriVien = row.findViewById(R.id.imgQuanTri);
        TextView txtQuanTri = row.findViewById(R.id.txtTenQuanTri);
        Button btnChiTiet = row.findViewById(R.id.btnChiTiet);
        //Button btnSua = row.findViewById(R.id.btnSua);

        final Item_QuanTriVien item_quanTriVien = this.objects.get(position);

        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyChiTiet(item_quanTriVien);
            }
        });

//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog dialog = new Dialog(context);
//                dialog.setTitle("Tên Item mới");
//                dialog.setCancelable(false);
//                dialog.setContentView(R.layout.danhsachview_themdanhmuc);
//                final EditText edtSoureMoi = dialog.findViewById(R.id.edtThemDanhMuc);
//                edtSoureMoi.setText("R.drawable.");
//                Button btnDongYThayDoiTen = dialog.findViewById(R.id.btnDongYThemDanhMuc);
//                Button btnHuyThayDoiTen = dialog.findViewById(R.id.btnHuyThemDanhMuc);
//
//                btnHuyThayDoiTen.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.cancel();
//                    }
//                });
//                btnDongYThayDoiTen.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        xuLyThayDoiTen(edtSoureMoi,item_quanTriVien);
//                        dialog.cancel();
//                    }
//                });
//                dialog.show();
//            }
//        });

        imgQuanTriVien.setImageResource(item_quanTriVien.getId());
        txtQuanTri.setText(item_quanTriVien.getTenQuanTri());

        return row;
    }

    private void xuLyThayDoiTen(EditText edtSoureMoi, Item_QuanTriVien item_quanTriVien) {
        String str = edtSoureMoi.getText().toString();
        if (str.substring(0,11).equals("R.drawable."))
        {
            int soure = Integer.parseInt(str);
            item_quanTriVien.setId(soure);
            this.notifyDataSetChanged();
            Toast.makeText(this.context,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this.context,"Bạn hãy nhập ID hình ảnh :D",Toast.LENGTH_SHORT);
    }


    private void xuLyChiTiet(Item_QuanTriVien item_quanTriVien) {
        item_quanTriVien.setChiTiet(true);
        if (item_quanTriVien.getTenQuanTri().equals("Sản phẩm"))
        {
            Intent intent = new Intent(this.context, ManHinhSanPham.class);
            context.startActivity(intent);
        }
        else if (item_quanTriVien.getTenQuanTri().equals("Bản đồ"))
        {
            Intent intent = new Intent(this.context, ManHinhBanDo.class);
            context.startActivity(intent);
        }
        else if (item_quanTriVien.getTenQuanTri().equals("Nhân Viên"))
        {
            Intent intent = new Intent(this.context, ManHinhUser.class);
            context.startActivity(intent);
        }
        else if (item_quanTriVien.getTenQuanTri().equals("Khuyến mãi"))
        {
            Intent intent = new Intent(this.context, ManHinhKhuyenMai.class);
            context.startActivity(intent);
        }
    }
}
