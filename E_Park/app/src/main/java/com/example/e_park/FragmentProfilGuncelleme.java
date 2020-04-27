package com.example.e_park;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentProfilGuncelleme  extends Fragment {
    private TextView kullaniciAdiGosterme;
    private EditText isim, soyisim, tel, mail;
    private Button degisiklikKaydet;
    private Spinner spinner;
    private int kullaniciID,secilmisSehirId;
    private String kID, kullaniciAdi,seciliSehir;
    private ArrayList<String> sehirler = new ArrayList<>();
    private ArrayAdapter<String> veriAdaptoru;
    AwesomeValidation awesomeValidation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragmentprofilguncelleme,container,false);

        kullaniciID = this.getArguments().getInt("deger");
        kullaniciAdi = this.getArguments().getString("deger2");
        kID = String.valueOf(kullaniciID);
        //kullaniciAdiGosterme.setText(kullaniciAdi);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(getActivity(),R.id.edit_kisim, "[a-zA-Z][a-zA-ZİıŞşĞğÜüÇçÖö ]{2,25}$",R.string.invalid_isim);
        awesomeValidation.addValidation(getActivity(),R.id.edit_kisim, "[a-zA-Z][a-zA-ZİıŞşĞğÜüÇçÖö ]{2,25}$",R.string.invalid_soyisim);
        awesomeValidation.addValidation(getActivity(), R.id.edit_kTel, "0[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}", R.string.invalid_phone);
        awesomeValidation.addValidation(getActivity(),R.id.edit_kMail, Patterns.EMAIL_ADDRESS,R.string.invalid_mail);

        isim = viewRoot.findViewById(R.id.editTextAd);
        soyisim = viewRoot.findViewById(R.id.editTextSoyad);
        tel = viewRoot.findViewById(R.id.editTextTel);
        mail = viewRoot.findViewById(R.id.editTextEmail);
        degisiklikKaydet = viewRoot.findViewById(R.id.buttonDegisiklikKaydet);
        spinner = viewRoot.findViewById(R.id.spinnerSehir);
        sehirler.add("Şehir Seçin");
        sehirler.add("İstanbul");
        sehirler.add("Ankara");
        sehirler.add("İzmir");
        veriAdaptoru = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,sehirler);
        spinner.setAdapter(veriAdaptoru);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    //Şehir Seçiniz Kısmında bir şey göstermesin. Default olsun.
                }
                else
                {
                    //Toast.makeText(getActivity(),"Sehir:"+sehirler.get(position),Toast.LENGTH_SHORT).show();
                    seciliSehir = sehirler.get(position);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        degisiklikKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad = isim.getText().toString();
                String soyad = soyisim.getText().toString();
                String telefon = tel.getText().toString();
                String mailadresi = mail.getText().toString();

                if(TextUtils.isEmpty(ad) && TextUtils.isEmpty(soyad) && TextUtils.isEmpty(ad) && TextUtils.isEmpty(telefon) && TextUtils.isEmpty(mailadresi))
                {
                    Toast.makeText(getActivity(),"Tüm alanlar doldurulmalıdır!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(ad) || TextUtils.isEmpty(soyad) || TextUtils.isEmpty(ad) || TextUtils.isEmpty(telefon) || TextUtils.isEmpty(mailadresi))
                {
                    Toast.makeText(getActivity(),"Boş alan bırakılamaz!",Toast.LENGTH_SHORT).show();
                }
                else if(awesomeValidation.validate() == false)
                {
                    Toast.makeText(getActivity(),"Kurallara göre bilgileri doldurunuz!",Toast.LENGTH_SHORT).show();
                }
                else if(awesomeValidation.validate() == true)
                {
                    switch (seciliSehir){
                        case "İstanbul":
                            secilmisSehirId = 34;
                            break;
                        case "Ankara":
                            secilmisSehirId = 06;
                            break;
                        case "İzmir":
                            secilmisSehirId = 35;
                            break;
                    }
                    sehirGuncelle();
                    kisiKaydet();
                }
            }
        });

        return viewRoot;
    }


    public void sehirGuncelle() {
        //gidecek url belirliyorum. webservis insert kısmı burada
        String url = "http://sinemakulup.com/sehirekle.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        //Toast.makeText(getActivity(), "Şehir Güncellenmiştir.", Toast.LENGTH_SHORT).show();
                    }
                    if (success.equals("0")) {
                        //Toast.makeText(getActivity(), "Bilgi Güncelleme Başarısız.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            //göndereceğim veriyi yazıyorum.
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("person_id",kID);
                params.put("city_id", String.valueOf(secilmisSehirId));
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }



    public void kisiKaydet() {
        //gidecek url belirliyorum. webservis insert kısmı burada
        String url = "http://sinemakulup.com/profilguncelleme.php ";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1"))
                    {
                        Toast.makeText(getActivity(),"Bilgileriniz Başarılı bir şekilde Kayıt Edildi.",Toast.LENGTH_SHORT).show();
                    }
                    if(success.equals("0"))
                    {
                        Toast.makeText(getActivity(),"Kayıt işlemi BAŞARISIZ!",Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Kayıt Başarısız"+e.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("person_id",kID);
                params.put("firstName",isim.getText().toString());
                params.put("lastName",soyisim.getText().toString());
                params.put("phoneNo",tel.getText().toString());
                params.put("email",mail.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }


}
