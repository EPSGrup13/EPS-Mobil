package com.example.e_park;

import android.os.Bundle;
import android.text.Layout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentArac extends Fragment {
    TextView textViewAracYok, textViewMarka, textViewModel, textViewPlaka, textViewMarka2, textViewModel2, textViewPlaka2;
    int kullaniciID;
    String kID, gelenMarka, gelenModel, gelenPlaka;
    ArrayList<String> aracBilgisi = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_arac,container,false);
        textViewAracYok = viewRoot.findViewById(R.id.textViewAracYok);
        textViewMarka = viewRoot.findViewById(R.id.textViewMarka);
        textViewModel = viewRoot.findViewById(R.id.textViewModel);
        textViewPlaka = viewRoot.findViewById(R.id.textViewPlaka);
        textViewMarka2 = viewRoot.findViewById(R.id.textViewMarka2);
        textViewModel2 = viewRoot.findViewById(R.id.textViewModel2);
        textViewPlaka2 = viewRoot.findViewById(R.id.textViewPlaka2);

        kullaniciID = this.getArguments().getInt("deger");
        kID = String.valueOf(kullaniciID);

        personIdCekme();
        textViewAracYok.setText("Kayıtlı Araç Bulunamadı");
        textViewMarka.setVisibility(View.INVISIBLE);
        textViewModel.setVisibility(View.INVISIBLE);
        textViewPlaka.setVisibility(View.INVISIBLE);
        textViewMarka2.setVisibility(View.INVISIBLE);
        textViewModel2.setVisibility(View.INVISIBLE);
        textViewPlaka2.setVisibility(View.INVISIBLE);
        return viewRoot;
    }

    public void personIdCekme() {

        String url = "http://sinemakulup.com/aracGoster.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevapbilgi:", response);
                //Log.e JSON türünde veri döndürüyor. Bunu JSON Parse işlemi ile çevirmem gerekiyor.
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray PersonListe = jsonObject.getJSONArray("Wehicle");
                    String success = jsonObject.getString("success");
                    int counter = 0;
                    for (int i = 0; i < PersonListe.length(); i++) {
                        JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.
                        gelenPlaka = p.getString("full_plate");
                        gelenMarka = p.getString("brand");
                        gelenModel = p.getString("model");
                        aracBilgisi.add(gelenPlaka);
                        aracBilgisi.add(gelenMarka);
                        aracBilgisi.add(gelenModel);
                        counter++;

                    }
                    Log.e("ssssss",String.valueOf(counter));

                    if(counter == 1)
                    {
                        textViewMarka.setText(aracBilgisi.get(0));
                        textViewModel.setText(aracBilgisi.get(1));
                        textViewPlaka.setText(aracBilgisi.get(2));
                        textViewAracYok.setVisibility(View.INVISIBLE);
                        textViewMarka.setVisibility(View.VISIBLE);
                        textViewModel.setVisibility(View.VISIBLE);
                        textViewPlaka.setVisibility(View.VISIBLE);

                    }
                    else if(counter == 2)
                    {
                        textViewPlaka.setText(aracBilgisi.get(0));
                        textViewModel.setText(aracBilgisi.get(1));
                        textViewMarka.setText(aracBilgisi.get(2));
                        textViewPlaka2.setText(aracBilgisi.get(3));
                        textViewModel2.setText(aracBilgisi.get(4));
                        textViewMarka2.setText(aracBilgisi.get(5));
                        textViewMarka.setVisibility(View.VISIBLE);
                        textViewModel.setVisibility(View.VISIBLE);
                        textViewPlaka.setVisibility(View.VISIBLE);
                        textViewAracYok.setVisibility(View.INVISIBLE);
                        textViewMarka2.setVisibility(View.VISIBLE);
                        textViewModel2.setVisibility(View.VISIBLE);
                        textViewPlaka2.setVisibility(View.VISIBLE);
                    }
                    else if(counter == 3)
                    {

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

}
