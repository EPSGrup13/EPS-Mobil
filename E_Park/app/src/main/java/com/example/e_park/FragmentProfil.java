package com.example.e_park;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentProfil extends Fragment {
    TextView textViewAdSoyad,textViewbakiye;
    EditText editTextMail, editTextTel;
    Button butonDuzenle, butonKaydet;
    int kullaniciID;
    String kID, balance, firstName, lastName, tel, city_id,email, adSoyad, guncelMail, guncelTel;
    int phonee;
    String maill;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_profil,container,false);


        kullaniciID = this.getArguments().getInt("deger");
        balance = this.getArguments().getString("balanceDeger");
        kID = String.valueOf(kullaniciID);
        //Log.e("Frag",kID);

        firstName = this.getArguments().getString("firstNameDeger");
        lastName = this.getArguments().getString("lastNameDeger");
        //tel = this.getArguments().getString("telefon");
        //city_id = this.getArguments().getString("city_id");
        //email = this.getArguments().getString("email");

        adSoyad = (firstName + " " + lastName);

        textViewAdSoyad = viewRoot.findViewById(R.id.textViewadSoyad);
        editTextMail = viewRoot.findViewById(R.id.editTextEmail);
        editTextTel = viewRoot.findViewById(R.id.editTextTel);
        textViewbakiye = viewRoot.findViewById(R.id.textViewBakiye);
        butonDuzenle = viewRoot.findViewById(R.id.buttonDuzenle);
        butonKaydet = viewRoot.findViewById(R.id.buttonKaydet);
        personIdCekme();
        //Açıldığında bunlar görünecek.
        textViewAdSoyad.setText(String.valueOf(adSoyad));
        //editTextMail.setText(email);
        //editTextTel.setText(tel);
        textViewbakiye.setText(balance);


        butonDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDuzenlemeAc(editTextMail);
                editTextDuzenlemeAc(editTextTel);
                butonKaydet.setVisibility(View.VISIBLE);
            }
        });
        butonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDuzenlemeKapat(editTextMail);
                editTextDuzenlemeKapat(editTextTel);
                guncelMail = editTextMail.getText().toString();
                guncelTel = editTextTel.getText().toString();

                if(TextUtils.isEmpty(guncelMail) && TextUtils.isEmpty(guncelTel))
                {
                    Toast.makeText(getActivity(),"Tüm alanlar doldurulmalıdır!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(guncelMail) || TextUtils.isEmpty(guncelTel))
                {
                    Toast.makeText(getActivity(),"Boş alan bırakılamaz!",Toast.LENGTH_SHORT).show();
                }
                else {
                    kisiGuncelle();
                    personIdCekme();
                    butonKaydet.setVisibility(View.INVISIBLE);
                }

            }
        });
        return viewRoot;
    }






    public void personIdCekme() {

        String url = "http://sinemakulup.com/aramaYapma3.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevapbilgi:", response);
                //Log.e JSON türünde veri döndürüyor. Bunu JSON Parse işlemi ile çevirmem gerekiyor.
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray PersonListe = jsonObject.getJSONArray("Person");
                    for (int i = 0; i < PersonListe.length(); i++) {
                        JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.

                        phonee = p.getInt("phoneNo");
                        maill = p.getString("email");
                        editTextMail.setText(maill);
                        editTextTel.setText(String.valueOf(phonee));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Exception hata=> ", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Hata", error.toString());
            }
        }) {
            //Aranacak Veri işlemlerini yapacağız.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Log.e("Person_id => ",String.valueOf(gelenAd));
                Map<String, String> params = new HashMap<>();
                params.put("person_id", kID);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }


    public void kisiGuncelle() {
        //gidecek url belirliyorum. webservis insert kısmı burada
        String url = "http://sinemakulup.com/mobilProfilUpdate.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1"))
                    {
                        Toast.makeText(getActivity(),"Bilgileriniz Güncellenmiştir.",Toast.LENGTH_SHORT).show();
                    }
                    if(success.equals("0"))
                    {
                        Toast.makeText(getActivity(),"Bilgi Güncelleme Başarısız.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            //göndereceğim veriyi yazıyorum.
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("person_id",kID);
                params.put("phoneNo", guncelTel);
                params.put("email", guncelMail);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }

    public void editTextDuzenlemeAc(EditText editTextname)
    {
        //Hepsini tek tek yapmak yerine fonksiyon ile tek satırda yapmak için yazdım.
        editTextname.setClickable(true);
        editTextname.setCursorVisible(true);
        editTextname.setFocusable(true);
        editTextname.setFocusableInTouchMode(true);
    }
    public void editTextDuzenlemeKapat(EditText editTextname)
    {
        //Hepsini tek tek yapmak yerine fonksiyon ile tek satırda yapmak için yazdım.
        editTextname.setClickable(false);
        editTextname.setCursorVisible(false);
        editTextname.setFocusable(false);
        editTextname.setFocusableInTouchMode(false);
    }








}
