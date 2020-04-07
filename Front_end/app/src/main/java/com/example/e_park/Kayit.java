package com.example.e_park;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kayit extends AppCompatActivity {
    EditText kadi,ksifre,kisim,ksoyisim,kmail,ktel;
    Button kayitol,giriseDon;
    Spinner spinner;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        kadi = findViewById(R.id.edit_kAdi);
        ksifre = findViewById(R.id.edit_kSifre);
        kisim = findViewById(R.id.edit_kisim);
        ksoyisim = findViewById(R.id.edit_ksoyad);
        kmail = findViewById(R.id.edit_kMail);
        ktel = findViewById(R.id.edit_kTel);
        spinner = findViewById(R.id.spinner);


        //Girilen bilgilerin tiplerinin kontrolünü sağlıyoruz..
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.edit_kAdi, "^[A-ZA-z]\\w{4,20}$",R.string.invalid_kadi);
        awesomeValidation.addValidation(this,R.id.edit_kisim, "[a-zA-Z][a-zA-ZİıŞşĞğÜüÇçÖö ]{2,25}$",R.string.invalid_isim);
        awesomeValidation.addValidation(this,R.id.edit_kisim, "[a-zA-Z][a-zA-ZİıŞşĞğÜüÇçÖö ]{2,25}$",R.string.invalid_soyisim);
        awesomeValidation.addValidation(this, R.id.edit_kTel, "0[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}", R.string.invalid_phone);
        awesomeValidation.addValidation(this,R.id.edit_kMail, Patterns.EMAIL_ADDRESS,R.string.invalid_mail);
        awesomeValidation.addValidation(this,R.id.edit_kSifre,"^[#\\w@_-]{8,20}$",R.string.invalid_sifre);

        kayitol = findViewById(R.id.btn_kayit);
        giriseDon = findViewById(R.id.btn_giriseDon);

        giriseDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //şimdilik kayıt devam düzeltilecek.
                Intent giriseDon = new Intent(getApplicationContext(),GirisSayfasiActivity.class);
                startActivity(giriseDon);
            }
        });
        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kAdi = kadi.getText().toString();
                String sifre = ksifre.getText().toString();
                String ad = kisim.getText().toString();
                String soyad = ksoyisim.getText().toString();
                String mail = kmail.getText().toString();
                String tel = ktel.getText().toString();

                if(TextUtils.isEmpty(kAdi) && TextUtils.isEmpty(sifre) && TextUtils.isEmpty(ad) && TextUtils.isEmpty(soyad) && TextUtils.isEmpty(mail) && TextUtils.isEmpty(tel))
                {
                    Toast.makeText(getApplicationContext(),"Tüm alanlar doldurulmalıdır!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(kAdi) || TextUtils.isEmpty(sifre) || TextUtils.isEmpty(ad) || TextUtils.isEmpty(soyad) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(tel))
                {
                    Toast.makeText(getApplicationContext(),"Boş alan bırakılamaz!",Toast.LENGTH_SHORT).show();
                }
                else if(awesomeValidation.validate() == false)
                {
                    Toast.makeText(getApplicationContext(),"Kurallara göre bilgileri doldurunuz!",Toast.LENGTH_SHORT).show();
                }
                else if(awesomeValidation.validate() == true)
                {
                    kisiKaydet();
                }
            }
        });
    }
    public void kisiKaydet() {
        //gidecek url belirliyorum. webservis insert kısmı burada
        String url = "http://sinemakulup.com/mobile-codes/regist.php ";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1"))
                    {
                        Toast.makeText(getApplicationContext(),"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                        Intent aracEkleyeGonder = new Intent(getApplicationContext(),AracEklemeActivity.class);
                        aracEkleyeGonder.putExtra("kullaniciAdi", kadi.getText().toString());
                        startActivity(aracEkleyeGonder);
                        finish();
                    }
                    if(success.equals("0"))
                    {
                        Toast.makeText(getApplicationContext(),"Kayıt işlemi BAŞARISIZ!",Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Kayıt Başarısız"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            //göndereceğim veriyi yazıyorum.
            //default veri gönderip test ediyorum.
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userName",kadi.getText().toString());
                params.put("password",ksifre.getText().toString());
                params.put("firstName",kisim.getText().toString());
                params.put("lastName",ksoyisim.getText().toString());
                params.put("phoneNo",ktel.getText().toString());
                params.put("email",kmail.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);
    }
}
