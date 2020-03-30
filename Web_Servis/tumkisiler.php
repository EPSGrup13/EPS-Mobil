<?php
// array for JSON response
$response = array();

//DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
require_once __DIR__ . '/db_config.php';

// Bağlantı oluşturuluyor.
$baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
// Bağlanti kontrolü yapılır.
if (!$baglanti) {
    die("Hatalı bağlantı : " . mysqli_connect_error());
}
    
$sqlsorgu = "SELECT * FROM Person"; // kisiler => Person *
$result = mysqli_query($baglanti, $sqlsorgu);

// result kontrolü yap
if (mysqli_num_rows($result) > 0) {
    
    $response["Person"] = array(); // kisiler => Person *
    
    while ($row = mysqli_fetch_assoc($result)) {
        // temp user array
        $Person = array();  //kisiler => Person *
        
        $Person["person_id"] = $row["person_id"]; // kisiler => Person *
        $Person["firstName"] = $row["firstName"]; //kisiler => Person *
        $Person["lastName"] = $row["lastName"]; //kisiler => Person *
        $Person["phoneNo"] = $row["phoneNo"]; //kisiler => Person *
        $Person["email"] = $row["email"]; //kisiler => Person *

        
        // push single product into final response array
        array_push($response["Person"], $Person); //kisiler => Person *
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
    
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No data found";

    // echo no users JSON
    echo json_encode($response);
}
//bağlantı koparılır.
mysqli_close($baglanti);
?>
