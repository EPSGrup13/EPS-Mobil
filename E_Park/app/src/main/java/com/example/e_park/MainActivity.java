package com.example.e_park;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

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
    private PaylasilanTercihYapilandirmasi paylasilanTercihYapilandirmasi;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Fragment fragment;
    

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

        paylasilanTercihYapilandirmasi = new PaylasilanTercihYapilandirmasi(getApplicationContext());

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
        if (item.getItemId()==R.id.cikisYap)
        {
            paylasilanTercihYapilandirmasi.girisDurumuYaz(false);
            Intent cikis = new Intent(getApplicationContext(), GirisSayfasiActivity.class);
            startActivity(cikis);
            finish();
        }
        else if (item.getItemId() == R.id.nav_item_profilinf){
            Toast.makeText(getApplicationContext(),"Profil Bilgileri",Toast.LENGTH_SHORT).show();
            fragment = new FragmentProfil();
        }
        else if (item.getItemId() == R.id.nav_item_carinf){
            Toast.makeText(getApplicationContext(),"Araç Bilgileri",Toast.LENGTH_SHORT).show();
            fragment = new FragmentArac();
        }
        else if (item.getItemId() == R.id.nav_item_rezv){
            Toast.makeText(getApplicationContext(),"Güncel Rezervasyon",Toast.LENGTH_SHORT).show();
            fragment = new FragmentGuncelRezerve();
        }
        else if (item.getItemId() == R.id.nav_item_oldrezv)
        {
            Toast.makeText(getApplicationContext(),"Geçmiş Rezervasyon",Toast.LENGTH_SHORT).show();
            fragment = new FragmentGecmisRezerve();
        }
        else if (item.getItemId() == R.id.nav_item_findpark)
        {
            Toast.makeText(getApplicationContext(),"Otopark Bul",Toast.LENGTH_SHORT).show();
            fragment = new FragmentOtoparkBul();
        }
        else if(item.getItemId() == R.id.nav_item_pay)
        {
            Toast.makeText(getApplicationContext(),"Ödeme Yöntemleri",Toast.LENGTH_SHORT).show();
            fragment = new FragmentBakiye();
        }
        else if(item.getItemId() == R.id.nav_item_sss)
        {
            Toast.makeText(getApplicationContext(),"Sıkça Sorulan Sorular",Toast.LENGTH_SHORT).show();
            fragment = new FragmentSikcaSorulanSorular();
        }
        else if(item.getItemId() == R.id.nav_item_iletisim)
        {
            Toast.makeText(getApplicationContext(),"İletişim Bilgileri",Toast.LENGTH_SHORT).show();
            fragment =new FragmentIletisim();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



