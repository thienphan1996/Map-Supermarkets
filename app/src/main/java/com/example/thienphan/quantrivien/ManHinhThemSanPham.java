package com.example.thienphan.quantrivien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thienphan.model.SanPham;

import java.util.ArrayList;

public class ManHinhThemSanPham extends AppCompatActivity {
    EditText edtThemTenSanPham,edtThemGiaSanPham,edtThemHinhAnhSanPham;
    Button btnDongYThemSanPham,btnHuyThemSanPham;
    Spinner spinnerThemDanhMuc;
    ArrayList<String> arrSanPhamThem;
    ArrayAdapter<String> adapterThemSanPham;
    Intent intent;
    int position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_them_san_pham);

        addControls();
        addEvents();

    }

    private void addControls() {
        edtThemGiaSanPham = (EditText) findViewById(R.id.edtThemGiaSanPham);
        edtThemTenSanPham = (EditText) findViewById(R.id.edtThemTenSanPham);
        btnDongYThemSanPham = (Button) findViewById(R.id.btnDongYThemSanPham);
        btnHuyThemSanPham = (Button) findViewById(R.id.btnHuyThemSanPham);

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE_DANHMUC");
        arrSanPhamThem = (ArrayList<String>) bundle.getSerializable("ARRDANHMUC");

        spinnerThemDanhMuc = (Spinner) findViewById(R.id.spThemDanhMuc);
        adapterThemSanPham = new ArrayAdapter<String>(
                ManHinhThemSanPham.this,
                android.R.layout.simple_spinner_item,
                arrSanPhamThem
        );
        adapterThemSanPham.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThemDanhMuc.setAdapter(adapterThemSanPham);
    }

    private void addEvents() {
        btnDongYThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDongYThemSanPham(arrSanPhamThem);
            }
        });
        btnHuyThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinnerThemDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void xuLyDongYThemSanPham(ArrayList<String> arrSanPhamThem) {
        String tenSPMoi =(String) edtThemTenSanPham.getText().toString();
        String giaSPMoi =(String) edtThemGiaSanPham.getText().toString();
        if (tenSPMoi.isEmpty()==false && giaSPMoi.isEmpty()==false && (position >= 1))
        {
            String tenDanhMuc = arrSanPhamThem.get(position);
            int imgLink;
            if (tenDanhMuc.equals("Điện tử"))
            {
                imgLink =R.drawable.dt_dienthoai_laptop;
            }
            else if (tenDanhMuc.equals("Gia dụng"))
            {
                imgLink =R.drawable.gd_xoongnoi;
            }
            else if (tenDanhMuc.equals("Tiêu dùng"))
            {
                imgLink =R.drawable.td_hangtieudung;
            }
            else if (tenDanhMuc.equals("Mỹ phẩm"))
            {
                imgLink =R.drawable.mp_mypham;
            }
            else if (tenDanhMuc.equals("Thực phẩm"))
            {
                imgLink =R.drawable.tp_thucpham;
            }
            else if (tenDanhMuc.equals("Quà tặng,đồ chơi"))
            {
                imgLink =R.drawable.qt_quatang;
            }
            else imgLink = R.drawable.st_sieuthi;

            SanPham sanPhamMoi = new SanPham(imgLink,tenSPMoi,tenDanhMuc,giaSPMoi);
            intent.putExtra("SANPHAMMOI",sanPhamMoi);
            setResult(69,intent);
            finish();
        }
        else if (tenSPMoi.isEmpty()==true || giaSPMoi.isEmpty()==true)
        {
            Toast.makeText(this,"Tên sản phẩm,giá tiền không được trống!",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this,"Danh mục không được chọn tất cả!",Toast.LENGTH_SHORT).show();
    }
}
