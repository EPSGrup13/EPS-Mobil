package com.example.e_park;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentProfiliGoster extends Fragment {
    private TextView adGoster, soyadGoster, telGoster, mailGoster, bakiyeGoster, sehirGoster, kullaniciAdiGoster;
    private String kullaniciAdi, kID, balance, fname,lname,phone,mail,city_id;
    int kullaniciID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragmentprofilgoster, container, false);

        kullaniciID = this.getArguments().getInt("deger");
        kullaniciAdi = this.getArguments().getString("deger2");
        kID = String.valueOf(kullaniciID);

        kullaniciAdiGoster = viewRoot.findViewById(R.id.kullaniciAdiGosterme);
        adGoster = viewRoot.findViewById(R.id.adGoster);
        soyadGoster = viewRoot.findViewById(R.id.soyadGoster);
        telGoster = viewRoot.findViewById(R.id.telGoster);
        mailGoster = viewRoot.findViewById(R.id.mailGoster);
        sehirGoster = viewRoot.findViewById(R.id.sehiriGoster);
        bakiyeGoster = viewRoot.findViewById(R.id.paraGoster);

        kullaniciAdiGoster.setText(kullaniciAdi);
        balanceCekme();
        bilgiCekme();


        return viewRoot;
    }

    public void bilgiCekme() {

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
                            fname = p.getString("firstName");
                            lname = p.getString("lastName");
                            phone = p.getString("phoneNo");
                            mail = p.getString("email");
                            city_id = p.getString("city_id");

                            adGoster.setText(fname);
                            soyadGoster.setText(lname);
                            telGoster.setText(String.valueOf(phone));
                            mailGoster.setText(mail);

                            if(city_id.equals("82"))
                            {
                                sehirGoster.setText("Belirtilmemiş");
                            }
                            else if(city_id.equals("34"))
                            {
                                sehirGoster.setText("İstanbul");
                            }
                            else if(city_id.equals("6"))
                            {
                                sehirGoster.setText("Ankara");
                            }
                            else if(city_id.equals("35"))
                            {
                                sehirGoster.setText("İzmir");
                            }
                            else if(city_id.equals(""))
                            {
                                sehirGoster.setText("Antalya");
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


    public void balanceCekme() {
        String url = "http://sinemakulup.com/userBalanceCekme.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevap:", response);
                //Log.e JSON türünde veri döndürüyor. Bunu JSON Parse işlemi ile çevirmem gerekiyor.
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray PersonListe = jsonObject.getJSONArray("User");
                    for (int i = 0; i < PersonListe.length(); i++) {
                        JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.
                        balance = p.getString("balance");
                        bakiyeGoster.setText(balance + " Türk Lirası.");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            //Aranacak Veri işlemlerini yapacağız.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("person_id", kID);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }
}
