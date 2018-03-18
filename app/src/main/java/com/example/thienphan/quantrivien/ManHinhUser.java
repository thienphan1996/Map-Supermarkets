package com.example.thienphan.quantrivien;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thienphan.model.TaiKhoan;

import java.util.ArrayList;

public class ManHinhUser extends AppCompatActivity {

    Button btnTroVeUser,btnThayDoiMaDK;

    ListView lvDanhSachNhanVien;
    ArrayList<String> arrTaiKhoan;
    ArrayAdapter<String> adapterDanhSachUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_user);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTroVeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnThayDoiMaDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ManHinhUser.this);
                dialog.setTitle("Đổi mã đăng ký");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.danhsachview_themdanhmuc);
                EditText edtMaDangKyMoi = dialog.findViewById(R.id.edtThemDanhMuc);
                Button btnHuyThayDoi = dialog.findViewById(R.id.btnHuyThemDanhMuc);
                Button btnDongYThayDoi = dialog.findViewById(R.id.btnDongYThemDanhMuc);

                btnHuyThayDoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                btnDongYThayDoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ManHinhUser.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        lvDanhSachNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                final AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhUser.this);
                builder.setTitle("Xóa tài khoản");
                builder.setMessage("Bạn có muốn xóa tài khoản "+arrTaiKhoan.get(i)+" không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tenTaiKhoan = arrTaiKhoan.get(position);
                        arrTaiKhoan.remove(position);
                        adapterDanhSachUser.notifyDataSetChanged();
                        dialogInterface.cancel();
                        Toast.makeText(ManHinhUser.this,"Đã xóa nhân viên "+tenTaiKhoan,Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void addControls() {
        btnTroVeUser = (Button) findViewById(R.id.btnTroVeUser);
        btnThayDoiMaDK = (Button) findViewById(R.id.btnThayDoiMaDK);
        lvDanhSachNhanVien = (ListView) findViewById(R.id.lvDanhSachNhanVien);
        arrTaiKhoan = new ArrayList<>();

        arrTaiKhoan.add("admin");
        arrTaiKhoan.add("quanly");
        arrTaiKhoan.add("phoquanly");
        arrTaiKhoan.add("thuky");
        arrTaiKhoan.add("daihoccantho");
        arrTaiKhoan.add("hethongthongtin");
        arrTaiKhoan.add("phanhuuthien");
        arrTaiKhoan.add("duongquangthien");
        arrTaiKhoan.add("truongthientai");

        adapterDanhSachUser = new ArrayAdapter<String>(ManHinhUser.this,android.R.layout.simple_list_item_checked,arrTaiKhoan);
        lvDanhSachNhanVien.setAdapter(adapterDanhSachUser);
    }
}
