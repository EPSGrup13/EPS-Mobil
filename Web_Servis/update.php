<?php

$response = array();

    if (isset($_POST['person_id']) && isset($_POST['firstName']) && isset($_POST['lastName']) && isset($_POST['phoneNo'])&& isset($_POST['email'])) {
        $person_id = $_POST['person_id'];
        $firstName = $_POST['firstName'];
        $lastName = $_POST['lastName'];
        $phoneNo = $_POST['phoneNo'];
        $email = $_POST['email'];


        //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
        require_once __DIR__ . '/db_config.php';
        
        // Bağlantı oluşturuluyor.
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        
        // Bağlanti kontrolü yapılır.
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "UPDATE Person SET Person.firstName = '$firstName',Person.lastName = '$lastName', Person.phoneNo = '$phoneNo', Person.email = '$email' WHERE Person.person_id = $person_id  ";
        
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
