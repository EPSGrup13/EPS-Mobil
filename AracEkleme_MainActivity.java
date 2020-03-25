
public class MainActivity extends AppCompatActivity {
    private Spinner spinner,spinner2,spinner3;
    private ArrayAdapter<String>spinnerAdapter;
    String aracTip, aracMarka,aracModel,ilPlaka,row_plate,full_plate;
    TextView textView,textView2,textView3,textView4,textView5,textView7;
    EditText girilenPlaka;
    Button buton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);

        girilenPlaka = findViewById(R.id.plakaGir);

        buton = findViewById(R.id.button);
        
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String veri = girilenPlaka.getText().toString();
                ilPlaka = veri.substring(0,2);  // il kısmı olan 34 gibi alanı alıyoruz. 34ABC123
                row_plate = veri.substring(2);  // geri kalan kısım ABC123 gibi alan
                full_plate = veri;
                 textView.setText(aracTip);  //otomobil
                 textView2.setText(aracMarka);  //marka
                 textView3.setText(aracModel);  //model
                 textView4.setText(ilPlaka);   //34
                 textView5.setText(row_plate);  //ABC123
                 textView7.setText(full_plate); //34ABC123
                aracKaydet();
            }
        });
        final String[] aracTipi = {"Araç Tipinizi Seçiniz","Otomobil","Motorsiklet","Ticari Araç"};

        final String[] otomobilMarkaları = {"Araç Markanızı Seçiniz","Alfa Romeo","Aston Martin","Audi","Bentley","Bmw","Bugatti","Cadillac","Chery",
        "Chevrolet","Citroen","Dacia","Dodge","Ferrari","Fiat","Ford","Geely","Honda","Hyundai","Infiniti","Isuzu","Jaguar","Kia","Lada","Lamborghini",
        "Lexus","Maserati","Mazda","Mercedes","Mini","Mitsubishi","Nissan","Opel","Peugeot","Porsche","Renault","Rover","Seat","Skoda","Smart",
        "Subaru","Suzuki","Tesla","Tofaş","Toyota","Volkswagen","Volvo","Diğer"};

        final String[] motorsikletMarkalari = {"Aeon","Altai","Asya","Bajaj","Benelli","Bianchi","Bisan","Bmw","CF Moto","Daelim","Ducati",
        "Falcon","Gilera","Harley-Davidson","Honda","Hyundai","Jawa","Kanuni","Kawasaki","Kral Motor","Mondial","RKS","Salcano","Suzuki","Yuki","Diğer"};

        //Otomobil Modelleri
        final String[] oto_AlfaRomeo_model ={"Giulia","Giulietta","MiTo","Diger"}; //1
        final String[] oto_AstonMartin_model ={"Cygnet","DB7","DB9","DB11","Rapide","Vantage","Diger"}; //2
        final String[] oto_Audi_model ={"A1","A3","A4","A5","A6","A7","A8","R8","RS","S Serisi","TT","Diger"}; //3
        final String[] oto_Bentley_model ={"Brooklands","Continental","Flying Spur","Mulsanne","Diger"}; //4
        final String[] oto_Bmw_model ={"1 Serisi","2 Serisi","3 Serisi","4 Serisi","5 Serisi","6 Serisi","7 Serisi","8 Serisi","I Serisi","M Serisi","Z Serisi","Diger"}; //5
        final String[] oto_Bugatti_model ={"Chiron","Diger"}; //6
        final String[] oto_Cadillac_model ={"BTS","Brougham","CT6","CTS","Diger"}; //7
        final String[] oto_Cherry_model ={"Alia","Chance","Kimo","Diger"}; //8
        final String[] oto_Chevrolet_model ={"Beretta","Aveo","Camaro","Cruze","Impala","Diger"}; //9
        final String[] oto_Citroen_model ={"C1","C2","C3","C4","C5","C6","C7","C8","C-Elysee","Saxo","Evasion","Diger"}; //10
        final String[] oto_Dacia_model ={"Lodgy ","Logan","Sandero","Solenza","Diger"}; //11
        final String[] oto_Dodge_model ={"Avenger ","Challenger ","Charger","Neon","Diger"}; //12
        final String[] oto_Ferrari_model ={"360 ","430","488","550","812 Superfast","F12","FF","Diger"}; //13
        final String[] oto_Fiat_model ={"Albea ","Bravo","Egea","Linea","Palio","Panda","Punto","Copue","Marea","Tempra","Tipo","Diger"}; //14
        final String[] oto_Ford_model ={"B-Max ","C-Max","Escort","Festiva","Fiesta","Focus","Fusion","Mondeo","Sierra","S-Max","Taunus","Diger"}; //15
        final String[] oto_Geely_model ={"Echo ","Emgrand","Familia","FC","Diger"}; //16
        final String[] oto_Honda_model ={"Accord ","City","Civic","CR-Z","Jazz","S200","Legend","Stream","Diger"}; //17
        final String[] oto_Hyundai_model ={"Accent","Accent Blue","Elantra","Getz","Ioniq","i-10","i-20","i-30","i-40","Sonata","Coupe","Diger"}; //18
        final String[] oto_Infiniti_model ={"G","i-30","M","Q30","Q50","Q60","Diger"}; //19
        final String[] oto_Isuzu_model ={"Gemini","Diger"}; //20
        final String[] oto_Jaguar_model ={"F-Type","S-Type","XE","XJ","X-Type","Diger"}; //21
        final String[] oto_Kia_model ={"Capital","Carnival","Ceed","Cerato","Optima","Pride","Rio","Stinger","Venga","Diger"}; //22
        final String[] oto_Lada_model ={"Kalina","Nova","Priora","Samara","VAZ","Vega","Diger"}; //23
        final String[] oto_Lamborghini_model ={"Aventador","Gallardo","Huracan","Diger"}; //24
        final String[] oto_Lexus_model ={"CT","ES","GS","SC","Diger"}; //25
        final String[] oto_Maserati_model ={"4 Serisi","Cambiocorsa","Ghibli","GranSport","GT","Diger"}; //26
        final String[] oto_Mazda_model ={"2","3","4","5","6","121","323","Lantis,","Diger"}; //27
        final String[] oto_Mercedes_model ={"A Serisi","AMG","B Serisi","CLA","CLS","C Serisi","E Serisi","SL","SLC","260","420","Diger"}; //28
        final String[] oto_Mini_model ={"Cooper","Cooper S","John Cooper","One","Diger"}; //29
        final String[] oto_Mitsubishi_model ={"3000GT","Attrage","Carisma","Eclipse","Lancer","Space","Diger"}; //30
        final String[] oto_Nissan_model ={"Almera","GT-R","Micra","Note","200 SX","350 Z","Laurel Altima","Maxima","Sunny","Diger"}; //31
        final String[] oto_Opel_model ={"Adam","Agila","Astra","Corsa","GT","Insignia","Manta","Meriva","Tigra","Vectra","Zafira","Diger"}; //32
        final String[] oto_Peugeot_model ={"106","107","205","206","207","208","301","306","307","308","RCZ","Diger"}; //33
        final String[] oto_Porsche_model ={"718","911","924","928","Cayman","Taycan"}; //34
        final String[] oto_Renault_model ={"Clio","Espace","Fluence","Laguna","Latitude","Megane","Safrane","Symbol","Scenic","Twingo","Talisman","R9","R11","Diger"}; //35
        final String[] oto_Rover_model ={"25","45","400","216","420","620","MGF","Streetwise","Diger"}; //36
        final String[] oto_Seat_model ={"Alhambra","Altea","Arosa","Exeo","Leon","Marbella","Diger"}; //37
        final String[] oto_Skoda_model ={"Citigo","Fabia","Felicia","Forman","Octavia","Rapid","SuperB","Diger"}; //38
        final String[] oto_Smart_model ={"Forfour","Fortwo","Roadster","Diger"}; //39
        final String[] oto_Subaru_model ={"BRZ","Impreza","Legacy","Vivio","Diger"}; //40
        final String[] oto_Suzuki_model ={"Alto","Liana","S-Cross","Swift","SX4","Diger"}; //41
        final String[] oto_Tesla_model ={"Model 3","Model S","Model X","Diger"}; //42
        final String[] oto_Tofaş_model ={"Doğan","Kartal","Murat","Serçe","Şahin","Diger"};//43
        final String[] oto_Toyota_model ={"Auris","Avensis","Corolla","GT86","Yaris","Carina","Celica","Corona","Supra","Urban","Diger"}; //44
        final String[] oto_Volkswagen_model ={"Areton","Beetle","EOS","Golf","Jetta","Passat","Polo","Santana","Scirocco","VW CC","Bora","Diger"}; //45
        final String[] oto_Volvo_model ={"C30","C70","S40","S60","S70","S80","S90","V40","V50","V60","V90","740","Diger"}; //46
        final String[] oto_Diger_model ={"Diger"}; //47

        //Motorsiklet Modelleri
        final String[] motor_Aeon ={"Aero 50","Elite 250","Pulsar 150","Diger"}; //1
        final String[] motor_Altai ={"Misk 50","Uma 50"}; //2
        final String[] motor_Asya ={"Armada 250","AS 100-7","AS 100-8","AS 150-12","Efsane Eco 100"," Polo 50","RX 250 Cross","Diger"}; //3
        final String[] motor_Bajaj ={"Avenger Street","Boxer","Discover","Dominar","Diger"}; //4
        final String[] motor_Benelli ={"125-S","502-C","Leoncino","TNT 250","Diger"}; //5
        final String[] motor_Bianchi ={"Happy 50","Hunter 125","Diger"}; //6
        final String[] motor_Bisan ={"Atlas 125","Ceylan","Shalter","Star","Teos 125","Diger"}; //7
        final String[] motor_Bmw ={"C-600 Sport","C-650 GT","F 650","F 800 GT","G 310","Diger"}; //8
        final String[] motor_CFMoto ={"150NK","250NK","400NK","650 MT","Diger"}; //9
        final String[] motor_Daelim ={"Beagle","Dart 125","S2 250","S3","Diger"}; //10
        final String[] motor_Ducati ={"749","848 Evo","Diavel","Panigale","Scrambler","Diger"}; //11
        final String[] motor_Falcon ={"Attack 50","Breeze","Crazy 125","Custom 150","Diger"}; //12
        final String[] motor_Gilera ={"CX 125", "GP 800", "Nexus","Runner","Diger"}; //13
        final String[] motor_HarleyDavidson ={"Cvo","Dyna","FLHX","Freewheeler","Road King","Screamin","Softail","Touring Road","Diger"}; //14
        final String[] motor_Honda ={"Activa 100","Activa 110","Beat","C 90","CB 125","CG 125","CRF","NC 750 X","Diger"}; //15
        final String[] motor_Hyundai ={"Cityzen 125","Diger"}; //16
        final String[] motor_Jawa ={"250 Ceylan","250 Classic","250 Laser","Diger"}; //17
        final String[] motor_Kanuni ={"BD100","Bobcat","Breton S","Caracal","Fox","Explorer", "Phantom","Diger"}; //18
        final String[] motor_Kawasaki ={"EL 250","EN 500","ER-6 F","GTR 1400","GPZ 900 R", "J 300","KMX 125","Ninja H2","Diger"}; //19
        final String[] motor_Kral_Motor ={"KR-13","KR-100-2","KR-100-A","KR-44 Pion","Diger"}; //20
        final String[] motor_Mondial ={"500 BeeStreet","50 HC","50 TT","100 KM","Diger"}; //21
        final String[] motor_Salcano ={"Altay 100","Astro 100","Hunter","Nova","Diger"}; //22
        final String[] motor_Suzuki ={"Address 110","BKing","DR350","GSX","Diger"}; //23
        final String[] motor_Yuki ={"Afşin 250","Drag 200","Forza 170","FX1", "YK 100","Diger"}; //24
        final String[] motor_Diger ={"Diger"}; //25

        // Araç tipini seçtiriyoruz.
        spinnerAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,aracTipi);
        spinner.setAdapter(spinnerAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Veri Kontrol Edelim.
                aracTip = aracTipi[position];


                //Toast.makeText(getApplicationContext(),"Araç Tip:"+aracTip,Toast.LENGTH_SHORT).show();

                if(position == 1) // Otomobil markaları listelenecek
                {
                    ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,otomobilMarkaları);
                    spinner2.setAdapter(modelAdapter);


                    //Otomobil Modelleri Listelenecek..
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            aracMarka = otomobilMarkaları[position];


                            //Seçilen Markanın Modelleri Gelecek
                            if(position == 1)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_AlfaRomeo_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_AlfaRomeo_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 2)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_AstonMartin_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_AstonMartin_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 3)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Audi_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Audi_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 4)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Bentley_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Bentley_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 5)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Bmw_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Bmw_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 6)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Bugatti_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Bugatti_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 7)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Cadillac_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Cadillac_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 8)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Cherry_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Cherry_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 9)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Chevrolet_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Chevrolet_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 10)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Citroen_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Citroen_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 11)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Dacia_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Dacia_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 12)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Dodge_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Dodge_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 13)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Ferrari_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Ferrari_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 14)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Fiat_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Fiat_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 15)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Ford_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Ford_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 16)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Geely_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Geely_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 17)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Honda_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Honda_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 18)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Hyundai_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Hyundai_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 19)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Infiniti_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Infiniti_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 20)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Isuzu_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Isuzu_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 21)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Jaguar_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Jaguar_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 22)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Kia_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Kia_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 23)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Lada_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Lada_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 24)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Lamborghini_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Lamborghini_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 25)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Lexus_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Lexus_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 26)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Maserati_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Maserati_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 27)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Mazda_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Mazda_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 28)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Mercedes_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Mercedes_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 29)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Mini_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Mini_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 30)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Mitsubishi_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Mitsubishi_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 31)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Nissan_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Nissan_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 32)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Opel_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Opel_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 33)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Peugeot_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Peugeot_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 34)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Porsche_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Porsche_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 35)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Renault_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Renault_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 36)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Rover_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Rover_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 37)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Seat_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Seat_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 38)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Skoda_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Skoda_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 39)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Smart_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Smart_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 40)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Subaru_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Subaru_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 41)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Suzuki_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Suzuki_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 42)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Tesla_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Tesla_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 43)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Tofaş_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Tofaş_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 44)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Toyota_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Toyota_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 45)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Volkswagen_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Volkswagen_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 46)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Volvo_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Volvo_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 47)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,oto_Diger_model);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = oto_Diger_model[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if(position == 2) //motorsiklet markaları listelenecek.
                {
                    ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motorsikletMarkalari);
                    spinner2.setAdapter(modelAdapter);

                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            aracMarka = motorsikletMarkalari[position];


                            //Seçilen Markanın Modelleri Gelecek
                            if(position == 1)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Aeon);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Aeon[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 2)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Altai);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Altai[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 3)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Asya);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Asya[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 4)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Bajaj);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Bajaj[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 5)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Benelli);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Benelli[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 6)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Bianchi);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Bianchi[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 7)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Bisan);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Bisan[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 8)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Bmw);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Bmw[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 9)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_CFMoto);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_CFMoto[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 10)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Daelim);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Daelim[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 11)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Ducati);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Ducati[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 12)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Falcon);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Falcon[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 13)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Gilera);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Gilera[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 14)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_HarleyDavidson);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_HarleyDavidson[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 15)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Honda);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Honda[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 16)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Hyundai);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Hyundai[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 17)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Jawa);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Jawa[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 18)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Kanuni);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Kanuni[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }  else if(position == 19)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Kawasaki);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Kawasaki[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 20)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Kral_Motor);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Kral_Motor[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 21)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Mondial);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Mondial[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 22)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Salcano);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Salcano[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 23)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Suzuki);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Suzuki[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 24)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Yuki);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Yuki[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            else if(position == 25)
                            {
                                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motor_Diger);
                                spinner3.setAdapter(modelAdapter);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        aracModel = motor_Diger[position];
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void aracKaydet() {
        //gidecek url belirliyorum. webservis insert kısmı burada
        String url = "http://sinemakulup.com/mobilAracEkle.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1"))
                    {
                        Toast.makeText(getApplicationContext(),"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Kayıt Başarısız"+e.toString(),Toast.LENGTH_SHORT).show();
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
                params.put("full_plate",full_plate);
                params.put("raw_plate",row_plate);
                params.put("brand", aracMarka);
                params.put("model", aracModel);
                params.put("is_main", "1");
                params.put("city_id", ilPlaka);
                params.put("person_id", "15"); // daha sonra güncellenecek
                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);
    }
}
