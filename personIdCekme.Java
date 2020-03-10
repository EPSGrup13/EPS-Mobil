 public void personIdCekme(){
        String url = "http://sinemakulup.com/aramaYapma.php";

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
                        int person_id = p.getInt("person_id");
                        String firstName = p.getString("firstName");
                        String lastName = p.getString("lastName");
                        int phoneNo = p.getInt("phoneNo");
                        String email = p.getString("email");

                        //LOGDA Göstermek için
                        Log.e("Person_id => ",String.valueOf(person_id));
                        Log.e("***","****");
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
                params.put("userName","eneskamis");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);
    }
