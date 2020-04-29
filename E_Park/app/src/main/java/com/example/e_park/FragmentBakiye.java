package com.example.e_park;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class FragmentBakiye extends Fragment {
    private TextView textViewGuncelBakiye;
    private EditText editTextBakiyeYukle, kartAdi, kartNo, kartCvc;
    private Button buton10, buton25, buton50, buton100, butonBakiyeYukle;
    int kullaniciID;
    String kID,balance, kAd, kNo, kCvc;
    Double balanceTut, balance2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_bakiye,container,false);

        kullaniciID = this.getArguments().getInt("deger");
        kID = String.valueOf(kullaniciID);
        balanceCekme();

        textViewGuncelBakiye = viewRoot.findViewById(R.id.textViewGuncelBakiye);
        editTextBakiyeYukle = viewRoot.findViewById(R.id.editTextBakiye);
        buton10 = viewRoot.findViewById(R.id.button10);
        buton25 = viewRoot.findViewById(R.id.button25);
        buton50 = viewRoot.findViewById(R.id.button50);
        buton100 = viewRoot.findViewById(R.id.button100);
        kartAdi = viewRoot.findViewById(R.id.editTextKartadi);
        kartNo = viewRoot.findViewById(R.id.editTextKartNo);
        kartCvc = viewRoot.findViewById(R.id.editTextKartcvc);


        butonBakiyeYukle = viewRoot.findViewById(R.id.buttonbakiyeYukle);

        buton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextBakiyeYukle.setText("10");
            }
        });
        buton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextBakiyeYukle.setText("25");
            }
        });
        buton50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextBakiyeYukle.setText("50");
            }
        });
        buton100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextBakiyeYukle.setText("100");
            }
        });


        kAd = kartAdi.getText().toString();
        kNo = kartNo.getText().toString();
        kCvc = kartCvc.getText().toString();



            butonBakiyeYukle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Bakiye Yükleme Uyarısı!");
                        builder.setMessage("Ücret Kartınızdan Tahsil Edilecek ve Bakiyeniz Yüklenecek. Onaylıyor musunuz?");
                        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                balance2 = Double.valueOf(editTextBakiyeYukle.getText().toString());
                                balanceGuncelleme();
                                balanceCekme();
                                Toast.makeText(getActivity(),"Bakiye Yükleme İşleminiz Tamamlanmıştır.",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(),"Bakiye Yükleme İşleminden Vazgeçildi.",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                }
            });

        return viewRoot;
    }

    public void balanceCekme(){
        String url = "http://sinemakulup.com/userBalanceCekme.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevap:",response);
                //Log.e JSON türünde veri döndürüyor. Bunu JSON Parse işlemi ile çevirmem gerekiyor.
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray PersonListe = jsonObject.getJSONArray("User");
                    for(int i = 0; i < PersonListe.length(); i++ ) {
                        JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.
                        balance = p.getString("balance");
                        textViewGuncelBakiye.setText(balance+" TL");
                        //LOGDA Göstermek için
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
                params.put("person_id",kID);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    } //elleme

    public void balanceGuncelleme(){
        String url = "http://sinemakulup.com/userBalanceCekme.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevap cekmetutma:",response);
                //Log.e JSON türünde veri döndürüyor. Bunu JSON Parse işlemi ile çevirmem gerekiyor.
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray PersonListe = jsonObject.getJSONArray("User");
                    for(int i = 0; i < PersonListe.length(); i++ ) {
                        JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.
                        balanceTut = p.getDouble("balance");
                         final Double total = balance2 + balanceTut;
                         Log.e("totla",String.valueOf(total));

                        String url = "http://sinemakulup.com/bakiyeGuncelle.php";
                        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("cevap cekmetutma2:",response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            //göndereceğim veriyi yazıyorum.
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("person_id",kID);
                                params.put("balance", String.valueOf(total));

                                return params;
                            }
                        };
                        Volley.newRequestQueue(getActivity()).add(istek);

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
                params.put("person_id",kID);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }


}
