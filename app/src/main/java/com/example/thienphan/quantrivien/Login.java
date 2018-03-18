package com.example.thienphan.quantrivien;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.example.thienphan.model.TaiKhoan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class Login extends AppCompatActivity implements Serializable{

    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnDangKy;
    CheckBox chkNhoMatKhau;
    ArrayList<TaiKhoan> arrTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addcontrols();
        addEvents();
    }

    private void addEvents() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDangNhap();
            }
        });

        chkNhoMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    edtTaiKhoan.setText("admin");
                    edtMatKhau.setText("admin");
                }
                else
                {
                    edtTaiKhoan.setText(null);
                    edtMatKhau.setText(null);
                }

            }
        });



        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Login.this);
                dialog.setTitle("Đăng ký tài khoản");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_dangky);

                final EditText edtDangKyTaiKhoan = dialog.findViewById(R.id.edtDangKyTaiKhoan);
                final EditText edtDangKyMatKhau = dialog.findViewById(R.id.edtDangKyMatKhau);
                final EditText edtDangKyNhapLaiMatKhau = dialog.findViewById(R.id.edtDangKyNhapLaiMatKhau);
                final EditText edtMaDangKy = dialog.findViewById(R.id.edtMaDangKy);

                Button btnDongYDangKy = dialog.findViewById(R.id.btnDongYDangKy);
                Button btnHuyDangKy = dialog.findViewById(R.id.btnHuyDangKy);

                btnDongYDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tenTaiKhoan = edtDangKyTaiKhoan.getText().toString();
                        String tenMatKhau = edtDangKyMatKhau.getText().toString();
                        String nhapLaiMatKhau = edtDangKyNhapLaiMatKhau.getText().toString();
                        String maDangKy = edtMaDangKy.getText().toString();
                        boolean ktra = true;

                        if (tenMatKhau.isEmpty() == true || tenMatKhau.isEmpty() == true)
                        {
                            ktra=false;
                            Toast.makeText(Login.this,"Chưa nhập tài khoản,mật khẩu",Toast.LENGTH_SHORT).show();
                        }
                        else if (tenMatKhau.equals(nhapLaiMatKhau) == false)
                        {
                            Toast.makeText(Login.this,"Nhập lại mật khẩu không khớp!",Toast.LENGTH_SHORT).show();
                            ktra=false;
                        }
                        else if (maDangKy.isEmpty())
                        {
                            Toast.makeText(Login.this,"Mã đăng ký không được để trống",Toast.LENGTH_SHORT).show();
                            ktra = false;
                        }
                        for (int i = 0; i < arrTaiKhoan.size(); i++)
                        {
                             if (arrTaiKhoan.get(i).getTenTaiKhoan().equals(tenTaiKhoan))
                            {
                                Toast.makeText(Login.this,"Tài khoản đã đã tôn tại",Toast.LENGTH_SHORT).show();
                                ktra = false;
                                break;
                            }
                            else if (arrTaiKhoan.get(i).getMaDangKy().equals(maDangKy))
                            {
                                Toast.makeText(Login.this,"Mã đăng ký đã tôn tại, gợi ý: NV"+(arrTaiKhoan.size()+1),Toast.LENGTH_SHORT).show();
                                ktra = false;
                                break;
                            }

                            else if (maDangKy.isEmpty() == false)
                            {
                                char kytu0 = maDangKy.charAt(0);
                                char kytu1 = maDangKy.charAt(1);
                                if (kytu0 != 'N' || kytu1 != 'V')
                                {
                                    Toast.makeText(Login.this,"Mã đăng ký không hợp lệ, gợi ý: NV"+(arrTaiKhoan.size()+1),Toast.LENGTH_SHORT).show();
                                    ktra = false;
                                    break;
                                }
                            }

                        }
                        if (ktra)
                        {
                            TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan,tenMatKhau,maDangKy);
                            arrTaiKhoan.add(taiKhoan);
//                        try {
//                            FileOutputStream fos = openFileOutput("dstaikhoan.txt",0);
//                            OutputStreamWriter osw = new OutputStreamWriter(fos);
//                            BufferedWriter bw = new BufferedWriter(osw);
//                            bw.write(tenTaiKhoan+";"+tenMatKhau+";"+maDangKy);
//                            bw.newLine();
//                            bw.close();
//                            osw.close();
//                            Toast.makeText(Login.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                            Toast.makeText(Login.this,"Không tìm thấy file",Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            Toast.makeText(Login.this,"Đăng ký thất bại",Toast.LENGTH_LONG).show();
//                        }
                            edtTaiKhoan.setText(tenTaiKhoan);
                            edtMatKhau.setText(tenMatKhau);
                            dialog.cancel();
                            Toast.makeText(Login.this,"Đăng ký tài khoản thành công",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnHuyDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }

    private void xuLyDangNhap() {
        String taiKhoan = (String) edtTaiKhoan.getText().toString();
        String matKhau =(String) edtMatKhau.getText().toString();
        boolean ktra = false;
        for (int i = 0; i < arrTaiKhoan.size() ; i++)
        {
            if(arrTaiKhoan.get(i).getTenTaiKhoan().equals(taiKhoan)==true && arrTaiKhoan.get(i).getMatKhau().equals(matKhau)==true)
            {
                ktra = true;
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("TENTAIKHOAN",taiKhoan);
                startActivity(intent);
            }
        }
        if (ktra == false)
        {
            Toast.makeText(this,"Tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_SHORT).show();
        }
    }

//    private void xuLyDoiMatKhau() {
//        if (getIntent()!=null)
//        {
//            Intent intent = getIntent();
//            String matKhauMoi = intent.getStringExtra("MATKHAUMOI");
//            String tenTaiKhoan = intent.getStringExtra("TAIKHOAN");
//            for (int i = 0 ; i < arrTaiKhoan.size(); i++)
//            {
//                if (arrTaiKhoan.get(i).getTenTaiKhoan().equals(tenTaiKhoan))
//                {
//                    arrTaiKhoan.remove(i);
//                    break;
//                }
//            }
//            arrTaiKhoan.add(new TaiKhoan(tenTaiKhoan,matKhauMoi,"NV"));
//            arrTaiKhoan.get(arrTaiKhoan.size()-1).setMatKhau(matKhauMoi);
//            edtTaiKhoan.setText(tenTaiKhoan);
//            edtMatKhau.setText(matKhauMoi);
//        }
//    }


    private void addcontrols() {

        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        chkNhoMatKhau = (CheckBox) findViewById(R.id.chkNhoMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);

        arrTaiKhoan = new ArrayList<>();
        arrTaiKhoan.add(new TaiKhoan("admin","admin","NV1"));
        arrTaiKhoan.add(new TaiKhoan("quanly","quanly","NV2"));
        arrTaiKhoan.add(new TaiKhoan("phoquanly","phoquanly","NV3"));
        arrTaiKhoan.add(new TaiKhoan("thuky","thuky","NV4"));
        arrTaiKhoan.add(new TaiKhoan("daihoccantho","daihoccantho","NV5"));
        arrTaiKhoan.add(new TaiKhoan("hethongthongtin","hethongthongtin","NV6"));
        arrTaiKhoan.add(new TaiKhoan("phanhuuthien","phanhuuthien","NV7"));
        arrTaiKhoan.add(new TaiKhoan("duongquangthien","duongquangthien","NV8"));
        arrTaiKhoan.add(new TaiKhoan("truongthientai","truongthientai","NV9"));
    }
}
