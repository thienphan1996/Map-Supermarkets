package com.example.thienphan.quantrivien;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thienphan.adapter.AdapterSanPham;
import com.example.thienphan.model.SanPham;

import java.util.ArrayList;



public class ManHinhSanPham extends AppCompatActivity {
    TextView txtDanhMuc,txtSanPham;
    Button btnBack,btnThemDanhMuc,btnXoaTatCa;
    Button btnThemSanPham;
    ArrayList<String> arrDanhMuc,arrSapXep;
    Spinner spDanhMucSanPham,spSapXep;
    ArrayAdapter<String> adapterDanhMuc,adapterSapXep;

    ArrayList<SanPham> arrSanPhamThongKe;

    int postion = -1;
    int positionGridViewLongClick = -1;
    int postitonGridViewOnClick = -1;

    private ArrayList<SanPham> arrSanPham;

    GridView gvSanPham;
    AdapterSanPham adapterSanPham;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_san_pham);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnBack = (Button) findViewById(R.id.btnBack);
        btnThemDanhMuc = (Button) findViewById(R.id.btnThemDanhMuc);
        btnThemSanPham = (Button) findViewById(R.id.btnThemSanPham);
        btnXoaTatCa = (Button) findViewById(R.id.btnXoaTatCa);


        //Thêm dữ liệu cho list danh mục
        arrDanhMuc = new ArrayList<>();
        arrDanhMuc.add("Tất cả sản phẩm");
        arrDanhMuc.add("Gia dụng");
        arrDanhMuc.add("Tiêu dùng");
        arrDanhMuc.add("Điện tử");
        arrDanhMuc.add("Thực phẩm");
        arrDanhMuc.add("Thời trang");
        arrDanhMuc.add("Trang sức");
        //Spinner danh mục sản phẩm
        spDanhMucSanPham = (Spinner) findViewById(R.id.spDanhMucSanPham);
        adapterDanhMuc = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                arrDanhMuc
        );
        adapterDanhMuc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDanhMucSanPham.setAdapter(adapterDanhMuc);

        //Spinner sắp xếp
        spSapXep = (Spinner) findViewById(R.id.spSapXep);
        arrSapXep = new ArrayList<>();
        arrSapXep.add("Sắp xếp");
        arrSapXep.add("Tên sản phẩm");
        arrSapXep.add("Giá tăng dần");
        arrSapXep.add("Giá giảm dần");

        adapterSapXep = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrSapXep);
        adapterSapXep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSapXep.setAdapter(adapterSapXep);

        //Thêm dữ liệu cho grid sản phẩm
        arrSanPham = new ArrayList<>();
        arrSanPham.add(new SanPham(R.drawable.dt_tivi,"Tivi Sony","Điện tử","10000000"));
        arrSanPham.add(new SanPham(R.drawable.dt_imac,"iMac 2015","Điện tử","30000000"));
        arrSanPham.add(new SanPham(R.drawable.dt_iphonex,"Iphone X 32GB","Điện tử","29990000"));
        arrSanPham.add(new SanPham(R.drawable.dt_dellxps,"LapTop Dell XPS","Điện tử","34990000"));
        arrSanPham.add(new SanPham(R.drawable.dt_tainghe,"Tai nghe Sony","Điện tử","500000"));
        arrSanPham.add(new SanPham(R.drawable.dt_galaxys8,"Galaxy S8","Điện tử","19000000"));
        arrSanPham.add(new SanPham(R.drawable.dt_mibran2,"Đồng hồ MiBrand2","Điện tử","390000"));
        arrSanPham.add(new SanPham(R.drawable.dt_sony_xzpremium,"Điện thoại Sony XZ","Điện tử","15990000"));
        arrSanPham.add(new SanPham(R.drawable.tt_balolaptop,"Balo Laptop","Thời trang","500000"));
        arrSanPham.add(new SanPham(R.drawable.tt_aothunnam,"Áo thun nam","Thời trang","350000"));
        arrSanPham.add(new SanPham(R.drawable.tt_sominam,"Áo sơ mi nam","Thời trang","200000"));
        arrSanPham.add(new SanPham(R.drawable.tt_cavat,"Cà vạt nam","Thời trang","250000"));
        arrSanPham.add(new SanPham(R.drawable.tt_nontreem,"Nón trẻ em","Thời trang","39000"));
        arrSanPham.add(new SanPham(R.drawable.tt_quanjeannam,"Quần jean nam","Thời trang","400000"));
        arrSanPham.add(new SanPham(R.drawable.tt_somitreem,"Áo sơ mi trẻ em","Thời trang","120000"));
        arrSanPham.add(new SanPham(R.drawable.gd_banhocsinh,"Bàn học sinh","Gia dụng","150000"));
        arrSanPham.add(new SanPham(R.drawable.gd_chaochongdinh,"Chảo chống dính","Gia dụng","90000"));
        arrSanPham.add(new SanPham(R.drawable.gd_xoong,"Xoong nồi","Gia dụng","70000"));
        arrSanPham.add(new SanPham(R.drawable.tp_banhngot,"Bánh ngọt","Thực phẩm","15000"));
        arrSanPham.add(new SanPham(R.drawable.tp_banhquy,"Bánh Quy","Thực phẩm","30000"));
        arrSanPham.add(new SanPham(R.drawable.tp_banhtrungthu,"Bánh Trung Thu","Thực phẩm","70000"));
        arrSanPham.add(new SanPham(R.drawable.tp_keodautay,"Kẹo dâu tây","Thực phẩm","10000"));
        arrSanPham.add(new SanPham(R.drawable.tp_snackbapngot,"Snack bắp ngọt","Thực phẩm","7000"));
        arrSanPham.add(new SanPham(R.drawable.tp_snackkhoaitay,"Snack Khoai tây","Thực phẩm","12000"));
        arrSanPham.add(new SanPham(R.drawable.ts_bongtainu,"Bông tay nữ","Trang sức","990000"));
        arrSanPham.add(new SanPham(R.drawable.ts_daychuyendoi,"Dây chuyền đôi","Trang sức","1200000"));
        arrSanPham.add(new SanPham(R.drawable.ts_daychuyennu,"Dây chuyền nữ","Trang sức","2200000"));
        arrSanPham.add(new SanPham(R.drawable.ts_donghonam,"Đồng hồ nam","Trang sức","1500000"));
        arrSanPham.add(new SanPham(R.drawable.ts_nhandeotay,"Nhẫn đeo tay","Trang sức","1200000"));
        arrSanPham.add(new SanPham(R.drawable.td_botgiacomo,"Bột giặc omo","Tiêu dùng","65000"));
        arrSanPham.add(new SanPham(R.drawable.td_nuocngot7up,"Nước ngọt 7up","Tiêu dùng","8000"));
        arrSanPham.add(new SanPham(R.drawable.td_nuocngotcoca,"CocaCola","Tiêu dùng","10000"));
        arrSanPham.add(new SanPham(R.drawable.td_nuoctuongmaggi,"Nước tương maggi","Tiêu dùng","18000"));


        gvSanPham = (GridView) findViewById(R.id.gvSanPham);
        adapterSanPham = new AdapterSanPham(
                ManHinhSanPham.this,
                R.layout.sanpham_item,
                arrSanPham);
        gvSanPham.setAdapter(adapterSanPham);
    }

    private void addEvents() {
        btnThemDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ManHinhSanPham.this);
                dialog.setTitle("Nhập tên danh mục");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.danhsachview_themdanhmuc);
                final EditText edtThemDanhMuc = dialog.findViewById(R.id.edtThemDanhMuc);
                Button btnDongYThemDanhMuc = dialog.findViewById(R.id.btnDongYThemDanhMuc);
                Button btnHuyThemDanhMuc = dialog.findViewById(R.id.btnHuyThemDanhMuc);

                btnDongYThemDanhMuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str = edtThemDanhMuc.getText().toString();
                        arrDanhMuc.add(str);
                        adapterDanhMuc.notifyDataSetChanged();
                        dialog.cancel();
                        Toast.makeText(ManHinhSanPham.this,"Đã thêm danh mục " +str.toString(),Toast.LENGTH_SHORT).show();
                    }
                });

                btnHuyThemDanhMuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ManHinhSanPham.this,ManHinhThemSanPham.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ARRDANHMUC",arrDanhMuc);
                intent.putExtra("BUNDLE_DANHMUC",bundle);
                startActivityForResult(intent,96);
            }
        });

        btnXoaTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhSanPham.this);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Ban có chắc muốn xóa tất cả không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SanPham sanPham = new SanPham();
                        while (!arrSanPhamThongKe.isEmpty())
                        {
                            sanPham = arrSanPhamThongKe.get(0);
                            if(arrSanPham.contains(sanPham)==true)
                            {
                                arrSanPham.remove(arrSanPham.indexOf(sanPham));
                                arrSanPhamThongKe.remove(arrSanPhamThongKe.indexOf(sanPham));
                            }
                        }
                        adapterSanPham.notifyDataSetChanged();
                        dialogInterface.cancel();
                        Toast.makeText(ManHinhSanPham.this,"Đã xóa, còn lại " +arrSanPham.size()+" sản phẩm",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        spDanhMucSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                postion = i;
                xuLySelectedSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                postitonGridViewOnClick = i;
                xuLyOnItemClickGridView();
            }
        });

        spSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1)
                {
                    SanPham.SapXepTheoTen(arrSanPhamThongKe);
                    SanPham.SapXepTheoTen(arrSanPham);
                    adapterSanPham.notifyDataSetChanged();
                }
                if (i == 2)
                {
                    SanPham.giaTangDan(arrSanPhamThongKe);
                    SanPham.giaTangDan(arrSanPham);
                    adapterSanPham.notifyDataSetChanged();
                }
                else if (i == 3)
                {
                    SanPham.giaGiamDan(arrSanPhamThongKe);
                    SanPham.giaGiamDan(arrSanPham);
                    adapterSanPham.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void xuLyOnItemClickGridView() {
        final Dialog dialog = new Dialog(ManHinhSanPham.this);
        dialog.setTitle("Cập nhật sản phẩm");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_thongtinsanpham);
        final EditText edtThayDoiTen = dialog.findViewById(R.id.edtThayDoiTen);
        final EditText edtThayDoiGia = dialog.findViewById(R.id.edtThayDoiGia);
        Button btnCapNhat = dialog.findViewById(R.id.btnCapNhat);
        Button btnHuyCapNhat = dialog.findViewById(R.id.btnHuyCapNhat);
        Button btnXoaSanPham = dialog.findViewById(R.id.btnXoaSanPham);
        ImageView imgViTri = dialog.findViewById(R.id.imgViTri);
        TextView txtViTri = dialog.findViewById(R.id.txtViTri);
        if (arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc().equals("Điện tử"))
        {
            txtViTri.setText("Vị trí của "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc());
            imgViTri.setImageResource(R.drawable.dt_map);
        }
        else if (arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc().equals("Tiêu dùng"))
        {
            txtViTri.setText("Vị trí của "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc());
            imgViTri.setImageResource(R.drawable.td_map);
        }
        else if (arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc().equals("Gia dụng"))
        {
            txtViTri.setText("Vị trí của "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc());
            imgViTri.setImageResource(R.drawable.gd_map);
        }
        else if (arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc().equals("Thực phẩm"))
        {
            txtViTri.setText("Vị trí của "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc());
            imgViTri.setImageResource(R.drawable.tp_map);
        }
        else if (arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc().equals("Thời trang"))
        {
            txtViTri.setText("Vị trí của "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc());
            imgViTri.setImageResource(R.drawable.tt_map);
        }
        else if(arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc().equals("Trang sức"))
        {
            txtViTri.setText("Vị trí của "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc());
            imgViTri.setImageResource(R.drawable.ts_map);
        }
        else
        {
            txtViTri.setText("Vị trí của "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenDanhMuc());
            imgViTri.setImageResource(R.drawable.map_custom_dialog);
        }
        edtThayDoiTen.setText(arrSanPhamThongKe.get(postitonGridViewOnClick).getTenSanPham());
        edtThayDoiGia.setText(arrSanPhamThongKe.get(postitonGridViewOnClick).getGiaSanPham());

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrSanPhamThongKe.get(postitonGridViewOnClick).setTenSanPham(edtThayDoiTen.getText().toString());
                arrSanPhamThongKe.get(postitonGridViewOnClick).setGiaSanPham(edtThayDoiGia.getText().toString());
                for (int i = 0; i < arrSanPham.size() ; i++)
                {
                    if (arrSanPham.get(i).getHinhAnhSanPham() == arrSanPhamThongKe.get(postitonGridViewOnClick).getHinhAnhSanPham())
                    {
                        arrSanPham.get(i).setTenSanPham(arrSanPhamThongKe.get(postitonGridViewOnClick).getTenSanPham());
                        arrSanPham.get(i).setGiaSanPham(arrSanPhamThongKe.get(postitonGridViewOnClick).getGiaSanPham());
                    }
                }
                adapterSanPham.notifyDataSetChanged();
                dialog.cancel();
                Toast.makeText(ManHinhSanPham.this,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
            }
        });
        btnHuyCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnXoaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManHinhSanPham.this,"Đã xóa sản phẩm "+arrSanPhamThongKe.get(postitonGridViewOnClick).getTenSanPham(),Toast.LENGTH_SHORT).show();
                for (int i = 0; i < arrSanPham.size() ; i++)
                {
                    if (arrSanPham.get(i).getHinhAnhSanPham() == arrSanPhamThongKe.get(postitonGridViewOnClick).getHinhAnhSanPham())
                    {
                        arrSanPham.remove(i);
                        continue;
                    }
                }
                arrSanPhamThongKe.remove(postitonGridViewOnClick);
                adapterSanPham.notifyDataSetChanged();
                dialog.cancel();
            }
        });

        dialog.show();
    }


    private void xuLySelectedSpinner() {
        if (postion == 0)
        {
            adapterSanPham.notifyDataSetChanged();
            arrSanPhamThongKe = (ArrayList<SanPham>) arrSanPham.clone();
            adapterSanPham = new AdapterSanPham(
                    ManHinhSanPham.this,
                    R.layout.sanpham_item,
                    arrSanPhamThongKe);
            gvSanPham.setAdapter(adapterSanPham);
        }
        else
        {
            String danhMuc = arrDanhMuc.get(postion);
            arrSanPhamThongKe = SanPham.ThongKeDanhMuc(arrSanPham,danhMuc);
            if (arrSanPhamThongKe.size()==0 || arrSanPham.size()==0)
            {
                Toast.makeText(this,"Danh sách trống!",Toast.LENGTH_SHORT).show();
            }
            adapterSanPham.notifyDataSetChanged();
            adapterSanPham = new AdapterSanPham(
                    this,
                    R.layout.sanpham_item,
                    arrSanPhamThongKe);
            gvSanPham.setAdapter(adapterSanPham);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 96 && resultCode == 69)
        {
            SanPham sanPhamMoi = (SanPham) data.getSerializableExtra("SANPHAMMOI");
            arrSanPhamThongKe.add(sanPhamMoi);
            arrSanPham.add(sanPhamMoi);
            adapterSanPham.notifyDataSetChanged();
            Toast.makeText(this,"Đã thêm sản phẩm "+sanPhamMoi.getTenSanPham()+"!",Toast.LENGTH_SHORT).show();
        }
    }
}
