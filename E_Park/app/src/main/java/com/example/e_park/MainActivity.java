package com.example.e_park;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttoncisikYap;
    private PaylasilanTercihYapilandirmasi paylasilanTercihYapilandirmasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttoncisikYap = findViewById(R.id.button_cikisYap);
        paylasilanTercihYapilandirmasi = new PaylasilanTercihYapilandirmasi(getApplicationContext());

        buttoncisikYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paylasilanTercihYapilandirmasi.girisDurumuYaz(false);
                Intent cikis = new Intent(getApplicationContext(), GirisSayfasiActivity.class);
                startActivity(cikis);
                finish();
            }
        });

    }
}
