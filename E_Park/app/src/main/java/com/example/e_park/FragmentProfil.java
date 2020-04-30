package com.example.e_park;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentProfil extends Fragment {
    TextView textViewAdSoyad,textViewbakiye, textViewSehir;
    EditText editTextMail, editTextTel;
    Button butonDuzenle, butonKaydet;
    int kullaniciID,secilmisSehirId;
    String kID, balance, firstName, lastName, tel, city_id,email, adSoyad, guncelMail, guncelTel,maill,phonee,seciliSehir;
    private Spinner spinner;

    private ArrayList<String> sehirler = new ArrayList<>();
    private ArrayAdapter<String> veriAdaptoru;

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

        adSoyad = (firstName + " " + lastName);

        textViewAdSoyad = viewRoot.findViewById(R.id.textViewadSoyad);
        editTextMail = viewRoot.findViewById(R.id.editTextEmail);
        editTextTel = viewRoot.findViewById(R.id.editTextTel);
        textViewbakiye = viewRoot.findViewById(R.id.textViewBakiye);
        textViewSehir = viewRoot.findViewById(R.id.textViewSehir);
        butonDuzenle = viewRoot.findViewById(R.id.buttonDuzenle);
        butonKaydet = viewRoot.findViewById(R.id.buttonKaydet);
        spinner = viewRoot.findViewById(R.id.spinner4);
        sehirler.add("Şehir Seçin");
        sehirler.add("İstanbul");
        sehirler.add("Ankara");
        sehirler.add("İzmir");
        veriAdaptoru = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,sehirler);
        spinner.setAdapter(veriAdaptoru);

        personIdCekme();


        //Açıldığında bunlar görünecek.
        textViewAdSoyad.setText(String.valueOf(adSoyad));
        //editTextMail.setText(email);
        //editTextTel.setText(tel);
        textViewbakiye.setText(balance);
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

        butonDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDuzenlemeAc(editTextMail);
                editTextDuzenlemeAc(editTextTel);
                butonKaydet.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                textViewSehir.setVisibility(View.INVISIBLE);
            }
        });

        butonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDuzenlemeKapat(editTextMail);
                editTextDuzenlemeKapat(editTextTel);
                spinner.setVisibility(View.INVISIBLE);
                textViewSehir.setVisibility(View.VISIBLE);

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
                    Log.e("Sehir",seciliSehir);
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
                    personIdCekme();
                    kisiGuncelle();
                    sehirGuncelle();
                    butonKaydet.setVisibility(View.INVISIBLE);
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
                    String success = jsonObject.getString("success");
                    for (int i = 0; i < PersonListe.length(); i++) {
                        JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.
                        if (success.equals("1")) {
                            phonee = p.getString("phoneNo");
                            maill = p.getString("email");
                            city_id = p.getString("city_id");
                            editTextMail.setText(maill);
                            editTextTel.setText(String.valueOf(phonee));
                            if(city_id.equals("82"))
                            {
                                textViewSehir.setText("Belirtilmemiş");
                            }
                            else if(city_id.equals("34"))
                            {
                                textViewSehir.setText("İstanbul");
                            }
                            else if(city_id.equals("6"))
                            {
                                textViewSehir.setText("Ankara");
                            }
                            else if(city_id.equals("35"))
                            {
                                textViewSehir.setText("İzmir");
                            }
                        }
                        if (success.equals("0")) {
                            Toast.makeText(getActivity(), "Bir Hata meydana geldi.", Toast.LENGTH_SHORT).show();
                        }

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
