<?php

$response = array();

    if (isset($_POST['person_id']) && isset($_POST['city_id'])) {
        $person_id = $_POST['person_id'];
		$city_id = $_POST['city_id'];
     


        //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
        require_once __DIR__ . '/db_config.php';
        
        // Bağlantı oluşturuluyor.
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        
        // Bağlanti kontrolü yapılır.
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "UPDATE Person SET Person.city_id = '$city_id' WHERE Person.person_id = $person_id";
        
    if (mysqli_query($baglanti, $sqlsorgu)) {

        $response["success"] = 1;
        $response["message"] = "successfully ";

        echo json_encode($response);
    } else {

        $response["success"] = 0;
        $response["message"] = "No product found";

        echo json_encode($response);
    }
        
        //bağlantı koparılır.
        mysqli_close($baglanti);
        
} else {

    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    echo json_encode($response);
}
    
?>
