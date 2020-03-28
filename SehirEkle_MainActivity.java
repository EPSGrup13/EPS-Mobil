import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button butonKaydet;

    private ArrayList<String> sehirler = new ArrayList<>();

    private ArrayAdapter<String> veriAdaptoru;

    private String seciliSehir ="";
    private int secilmisSehirId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        butonKaydet = findViewById(R.id.button);

        sehirler.add("Şehir Seçin");
        sehirler.add("İstanbul");
        sehirler.add("Ankara");
        sehirler.add("İzmir");
        sehirler.add("Eskişehir");
        sehirler.add("Trabzon");
        sehirler.add("Bursa");
        sehirler.add("Sivas");

        veriAdaptoru = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,sehirler);
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
                   // Toast.makeText(getApplicationContext(),"Sehir:"+sehirler.get(position),Toast.LENGTH_SHORT).show();
                    seciliSehir = sehirler.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        butonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (seciliSehir){
                    case "İstanbul":
                        secilmisSehirId = 34;
                        break;
                    case "Ankara":
                        secilmisSehirId = 6;
                        break;
                    case "İzmir":
                        secilmisSehirId = 35;
                        break;
                    case "Eskişehir":
                        secilmisSehirId = 26;
                        break;
                    case "Trabzon":
                        secilmisSehirId = 61;
                        break;
                    case "Bursa":
                        secilmisSehirId = 16;
                        break;
                    case "Sivas":
                        secilmisSehirId = 58;
                        break;
                }
                kisiGuncelle();
            }

        });

    }

    public void kisiGuncelle() {
        Toast.makeText(getApplicationContext(),"sehir:"+secilmisSehirId,Toast.LENGTH_SHORT).show();
        //gidecek url belirliyorum. webservis insert kısmı burada
        String url = "http://sinemakulup.com/sehirekle.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                params.put("person_id","67");
                params.put("city_id", String.valueOf(secilmisSehirId));
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(istek);
    }
}
