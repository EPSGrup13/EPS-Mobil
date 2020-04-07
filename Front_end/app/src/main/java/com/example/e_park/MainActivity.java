package com.example.e_park;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.String;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Button buttoncisikYap;
    private PaylasilanTercihYapilandirmasi paylasilanTercihYapilandirmasi;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigationview);
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);


        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            Intent intent= new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.nav_item_profilinf){
            Toast.makeText(getApplicationContext(),"Profil Bilgileri",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.nav_item_carinf){
            Toast.makeText(getApplicationContext(),"Araç Bilgileri",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.nav_item_rezv){
            Toast.makeText(getApplicationContext(),"Rezervasyon",Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



