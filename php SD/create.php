<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output=array();

$name=$_REQUEST["name"];
$studentnum=$_REQUEST["studentnum"];
$email=$_REQUEST["email"];
$password=$_REQUEST["password"];
$transcript=$_REQUEST["transcript"];

if (!isset($name, $studentnum, $email, $password, $transcript){
        $output["result"]="You didn't send the required values";
        echo json_encode($output);
        die();
}
    
/* Select queries return a resultset */
$result = mysqli_query($link, "INSERT INTO USER_INFORMATION(NAME, STUDENT_NUMBER, EMAIL_ADDRESS, USER_PASSWORD, TRANSCRIPT) VALUES('$name','$studentnum','$email','$password','$transcript')");
$output["result"]=$result;
mysqli_close($link);
echo json_encode($output);
?>
