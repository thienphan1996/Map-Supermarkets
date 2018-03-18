package com.example.thienphan.quantrivien;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thienphan.adapter.AdapterKhuyenMai;
import com.example.thienphan.model.KhuyenMai;

import java.util.ArrayList;

public class ManHinhKhuyenMai extends AppCompatActivity {

    Button btnTroVeKhuyenMai,btnThemKhuyenMai;

    ArrayList<KhuyenMai> arrKhuyenMai;
    AdapterKhuyenMai adapterKhuyenMai;
    ListView lvKhuyenMai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_khuyen_mai);


        addControls();
        addEvents();
    }

    private void addEvents() {
        lvKhuyenMai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                final Dialog dialog = new Dialog(ManHinhKhuyenMai.this);
                dialog.setTitle("Cập nhật khuyến mãi");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_khuyenmai);

                final TextView edtCapNhatThongTinKm = dialog.findViewById(R.id.edtCapNhatThongTinKM);
                Button btnCapNhatKhuyenMai = dialog.findViewById(R.id.btnCapNhatKhuyenMai);
                Button btnXoaKhuyenMai = dialog.findViewById(R.id.btnXoaKhuyenMai);
                Button btnHuyCapNhatKhuyenMai = dialog.findViewById(R.id.btnHuyCapNhatKhuyenMai);
                edtCapNhatThongTinKm.setText(arrKhuyenMai.get(position).getThongTinKhuyenMai());

                btnHuyCapNhatKhuyenMai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                btnXoaKhuyenMai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arrKhuyenMai.remove(position);
                        adapterKhuyenMai.notifyDataSetChanged();
                        dialog.cancel();
                        Toast.makeText(ManHinhKhuyenMai.this,"Đã xóa khuyến mãi",Toast.LENGTH_SHORT).show();
                    }
                });
                btnCapNhatKhuyenMai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str = edtCapNhatThongTinKm.getText().toString();
                        arrKhuyenMai.get(position).setThongTinKhuyenMai(str);
                        adapterKhuyenMai.notifyDataSetChanged();
                        dialog.cancel();
                        Toast.makeText(ManHinhKhuyenMai.this,"Cập nhật khuyến mãi thành công!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        btnTroVeKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnThemKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ManHinhKhuyenMai.this);
                dialog.setTitle("Thêm khuyến mãi");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.danhsachview_themdanhmuc);
                final EditText edtTenKhuyenMai = dialog.findViewById(R.id.edtThemDanhMuc);
                Button btnDongy = dialog.findViewById(R.id.btnDongYThemDanhMuc);
                Button btnHuy = dialog.findViewById(R.id.btnHuyThemDanhMuc);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                btnDongy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        KhuyenMai khuyenMai = new KhuyenMai(R.drawable.km_sale50,edtTenKhuyenMai.getText().toString());
                        arrKhuyenMai.add(0,khuyenMai);
                        adapterKhuyenMai.notifyDataSetChanged();
                        dialog.cancel();
                        Toast.makeText(ManHinhKhuyenMai.this,"Đã thêm khuyến mãi!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }

    private void addControls() {
        btnTroVeKhuyenMai = (Button) findViewById(R.id.btnTroVeKhuyenMai);
        btnThemKhuyenMai = (Button) findViewById(R.id.btnThemKhuyenMai);
        arrKhuyenMai = new ArrayList<>();
        arrKhuyenMai.add(new KhuyenMai(R.drawable.km_bigsale,"Giảm giá lớn mùa trung thu,chỉ duy nhất 3 ngày"));
        arrKhuyenMai.add(new KhuyenMai(R.drawable.km_blackfriday,"Ngày hội khuyến mãi Black Friday giảm giá tất cả mặt hàng"));
        arrKhuyenMai.add(new KhuyenMai(R.drawable.km_freeship,"Chương trình ưu đãi miễn phí cước vận chyển cho tất cả mặt hàng trên 2kg"));
        arrKhuyenMai.add(new KhuyenMai(R.drawable.km_hpmomenday,"Mừng ngày quốc tế phụ nữ giảm giá 15% cho khách hàng nữ"));
        arrKhuyenMai.add(new KhuyenMai(R.drawable.km_sale50,"Giảm giá 50% một số mặt hàng điện tử và gia dụng chỉ 3 ngày"));
        lvKhuyenMai = (ListView) findViewById(R.id.lvKhuyenMai);
        adapterKhuyenMai = new AdapterKhuyenMai(this,R.layout.khuyenmai_item,arrKhuyenMai);
        lvKhuyenMai.setAdapter(adapterKhuyenMai);
    }
}
