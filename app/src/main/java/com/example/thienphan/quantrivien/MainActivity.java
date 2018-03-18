package com.example.thienphan.quantrivien;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.thienphan.adapter.AdapterQuanTriVien;
import com.example.thienphan.model.Item_QuanTriVien;
import com.example.thienphan.model.SanPham;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnDangXuat,btnXinChaoTaiKhoan;
    String tenTaiKhoan,matKhauMoi;
    GridView gvQuanTriVien;
    ArrayList<Item_QuanTriVien> arrItemQuanTriVien;
    AdapterQuanTriVien adapterQuanTriVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {

        btnXinChaoTaiKhoan = (Button) findViewById(R.id.btnXinChaoTaiKhoan);
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);

        //Tên hiển thị Button tài khoản
        Intent intent = getIntent();
        tenTaiKhoan = intent.getStringExtra("TENTAIKHOAN");
        btnXinChaoTaiKhoan.setText(" Xin chào,"+tenTaiKhoan+" ");

        arrItemQuanTriVien = new ArrayList<>();
        arrItemQuanTriVien.add(new Item_QuanTriVien(R.drawable.product3,false,"Sản phẩm"));
        arrItemQuanTriVien.add(new Item_QuanTriVien(R.drawable.map,false,"Bản đồ"));
        if (tenTaiKhoan.equals("admin"))
        {
            arrItemQuanTriVien.add(new Item_QuanTriVien(R.drawable.user,false,"Nhân Viên"));
        }
        arrItemQuanTriVien.add(new Item_QuanTriVien(R.drawable.sale,false,"Khuyến mãi"));


        gvQuanTriVien = (GridView) findViewById(R.id.gvQuanTriVien);
        adapterQuanTriVien = new AdapterQuanTriVien(
                MainActivity.this,
                R.layout.quantrivien_item,
                arrItemQuanTriVien);
        gvQuanTriVien.setAdapter(adapterQuanTriVien);

    }

    private void addEvents() {
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Ban có chắc muốn đăng xuất không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


//        btnXinChaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog dialog = new Dialog(MainActivity.this);
//                dialog.setTitle("Đổi mật khẩu");
//                dialog.setCancelable(false);
//                dialog.setContentView(R.layout.danhsachview_themdanhmuc);
//
//                final EditText edtMatKhauMoi = dialog.findViewById(R.id.edtThemDanhMuc);
//                Button btnDongYDoiMatKhau = dialog.findViewById(R.id.btnDongYThemDanhMuc);
//                Button btnHuyDoiMatKhau = dialog.findViewById(R.id.btnHuyThemDanhMuc);
//
//                btnHuyDoiMatKhau.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.cancel();
//                    }
//                });
//                btnDongYDoiMatKhau.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.cancel();
//                        Toast.makeText(MainActivity.this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                dialog.show();
//            }
//        });



    }



}
