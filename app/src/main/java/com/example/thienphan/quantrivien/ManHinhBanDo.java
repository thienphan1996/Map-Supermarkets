package com.example.thienphan.quantrivien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManHinhBanDo extends AppCompatActivity {

    Button btnTroVeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_ban_do);

        btnTroVeMap = (Button) findViewById(R.id.btnTroVeMap);

        btnTroVeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
