package com.example.e_park;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private int person_id;
    private String gelenKadi;

    private PaylasilanTercihYapilandirmasi paylasilanTercihYapilandirmasi;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private Fragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

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

        gelenKadi = getIntent().getExtras().getString("girisKadi");   // Gelen kullanici Adi
        personIdCekme();
        Log.e("Kullanici=>",String.valueOf(gelenKadi));



    }//oncreate bitiyor.



    public void personIdCekme(){
        String url = "http://sinemakulup.com/aramaYapma.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.e("cevap:",response);
                //Log.e JSON türünde veri döndürüyor. Bunu JSON Parse işlemi ile çevirmem gerekiyor.
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray PersonListe = jsonObject.getJSONArray("User");
                    for(int i = 0; i < PersonListe.length(); i++ ) {
                        JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.
                        person_id = p.getInt("person_id");
                        //LOGDA Göstermek için
                        Log.e("Person_id => ",String.valueOf(person_id));
                        //Log.e("***","****");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            //Aranacak Veri işlemlerini yapacağız.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Log.e("Person_id => ",String.valueOf(gelenAd));
                Map<String,String> params = new HashMap<>();
                params.put("userName",gelenKadi);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);

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
                Bundle b2 = new Bundle();
                b2.putInt("deger",person_id);
                fragment.setArguments(b2);

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
        if(person_id == 0)
        {
            paylasilanTercihYapilandirmasi.girisDurumuYaz(false);
            Intent intent = new Intent(getApplicationContext(),GirisSayfasiActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Güvenli oturum sonlandırıldı, Lütfen tekrar Giriş Yapınız",Toast.LENGTH_SHORT).show();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



