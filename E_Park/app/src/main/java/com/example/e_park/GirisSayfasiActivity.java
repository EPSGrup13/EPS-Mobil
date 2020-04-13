package com.example.e_park;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GirisSayfasiActivity extends AppCompatActivity {
    private EditText kullaniciAdi, kullaniciSifre;
    private Button buton_girisYap, buton_kayitOl;
    private PaylasilanTercihYapilandirmasi paylasilanTercihYapilandirmasi;
    int person_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_sayfasi);

        kullaniciAdi = findViewById(R.id.editText_kullaniciAdi);
        kullaniciSifre = findViewById(R.id.editText_kullaniciSifre);
        buton_girisYap = findViewById(R.id.button_giris);
        buton_kayitOl = findViewById(R.id.button_kayit);
        paylasilanTercihYapilandirmasi = new PaylasilanTercihYapilandirmasi(getApplicationContext());
        if(paylasilanTercihYapilandirmasi.girisDurumuOku())
        {
            Intent basarili = new Intent(getApplicationContext(),MainActivity.class);
            basarili.putExtra("girisKadi",String.valueOf(kullaniciAdi));
            startActivity(basarili);
            finish();
        }
        //GirişYap  butonuna tıklandığında olacak işlemler..
        buton_girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kAdi = kullaniciAdi.getText().toString();
                String sifre = kullaniciSifre.getText().toString();
                //Kullanıcıadı ve şifre alanlarının boş dolu olup olmadığı kontrol ediliyor.
                if(TextUtils.isEmpty(kAdi) && TextUtils.isEmpty(sifre) )
                {
                    Toast.makeText(getApplicationContext(),"Kullanıcı adı ve Şifre boş bırakılamaz.",Toast.LENGTH_SHORT).show();
                }
                else if(kAdi.isEmpty() && !sifre.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Kullanıcı adı boş bırakılamaz.",Toast.LENGTH_SHORT).show();
                }
                else if(!kAdi.isEmpty() && sifre.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Şifre boş bırakılamaz.",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Eğer alanlar dolu ise Login metodu tetikleniyor.
                    Login();
                }
            }
        });
        //Kayıt ol butonuna tıklandığında Kayıt sayfasına gönderiyoruz..
        buton_kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kayitOlaGit = new Intent(getApplicationContext(),Kayit.class);
                startActivity(kayitOlaGit);
            }
        });
    }
    public void Login() {
        //gidecek url belirliyorum. webservis insert kısmı burada
        String url = "http://sinemakulup.com/mobile-codes/login.php ";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1"))
                    {
                        String kulAdi = kullaniciAdi.getText().toString();
                        String kulSifre = kullaniciSifre.getText().toString();
                        if(kulAdi.equals(kulAdi) && kulSifre.equals(kulSifre))
                        {

                            personIdCekme(String.valueOf(kullaniciAdi));
                            Intent basarili = new Intent(getApplicationContext(),MainActivity.class);
                            //basarili.putExtra("girisKadi",kulAdi);
                            startActivity(basarili);
                            paylasilanTercihYapilandirmasi.girisDurumuYaz(true);
                            finish();
                        }
                        Toast.makeText(getApplicationContext(),"Giriş Başarılı..",Toast.LENGTH_SHORT).show();
                        //Eğer giriş başarılı ise Ana sayfaya yönlendiriyoruz.
                        personIdCekme(kulAdi);
                        Intent AnaSayfayaGit = new Intent(getApplicationContext(),MainActivity.class);
                        AnaSayfayaGit.putExtra("girisKadi",kulAdi);
                        startActivity(AnaSayfayaGit);
                    }
                    else if(success.equals("0")){
                        Toast.makeText(getApplicationContext(),"Giris Başarısız..",Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Giriş Başarısız.."+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            //göndereceğim veriyi yazıyorum.
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mailField",kullaniciAdi.getText().toString());
                params.put("passField", kullaniciSifre.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);
    }
    public void personIdCekme(final String userName){

        String url = "http://sinemakulup.com/aramaYapma.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
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
                Map<String,String> params = new HashMap<>();
                params.put("userName",userName);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);
    }
}
