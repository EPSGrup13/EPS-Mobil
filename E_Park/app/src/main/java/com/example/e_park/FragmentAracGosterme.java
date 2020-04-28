package com.example.e_park;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class FragmentAracGosterme extends Fragment {
    private int kullaniciID;
    private String kID, gelenPlaka, gelenMarka,gelenModel, gelenWehicle_id;
    private TextView textViewMarka, textViewModel;
    ArrayList<String> Plaka = new ArrayList<>();
    ArrayList<String> Marka = new ArrayList<>();
    ArrayList<String> Model = new ArrayList<>();
    ListView listView;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_aracgosterme,container,false);

        kullaniciID = this.getArguments().getInt("deger");
        kID = String.valueOf(kullaniciID);
        personIdCekme();
        listView = viewRoot.findViewById(R.id.listView);


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
                        final JSONObject p = PersonListe.getJSONObject(i); //her bir degeri p nesnesine alıyorum.
                        gelenPlaka = p.getString("full_plate");
                        gelenMarka = p.getString("brand");
                        gelenModel = p.getString("model");
                        //gelenWehicle_id = p.getString("wehicle_id");
                        Plaka.add(gelenPlaka);
                        Marka.add(gelenMarka);
                        Model.add(gelenModel);
                       // WehicleID.add(gelenWehicle_id);

                        counter++;
                       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                textViewMarka.setText(Marka.get(position));
                                textViewModel.setText(Model.get(position));
                            }
                        });*/

                    }
                    Log.e("ssssss",String.valueOf(counter));

                    if(counter == 1)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 2)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 3)

                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 4)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 5)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 6)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 7)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 8)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 9)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else if(counter == 10)
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Bu Aracı Silmek İstediğinize Emin Misiniz?");
                                builder.setMessage("Seçili Plaka: "+Plaka.get(position)+"\nAraç Markanız: "+Marka.get(position)+"\nAraç Modeliniz: "+Model.get(position));
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        aracSil();
                                    }
                                });
                                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(),"Silme işleminden vazgeçildi.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Exception hata=> ", e.getMessage());
                    if(Plaka.isEmpty())
                    {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Plaka);
                        Plaka.add("Henüz Kayıtlı Aracınız Bulunmamaktadır.");
                        listView.setAdapter(arrayAdapter);
                    }

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

    public void aracSil()
    {
        String url = "http://sinemakulup.com/wehicleDelete.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"Araç Başarılı Bir Şekilde Silindi.",Toast.LENGTH_SHORT).show();

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
                params.put("full_plate",gelenPlaka);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }

}
