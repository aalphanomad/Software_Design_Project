<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output=array();

$studentnum=$_REQUEST["studentnum"];
$password=$_REQUEST["password"];

/* Select queries return a resultset */
if ($result = mysqli_query($link, "SELECT * from USER_INFORMATION where STUDENT_NUMBER='$studentnum'")) {
while ($row=$result->fetch_assoc()){
$output[]=$row;
}
}
mysqli_close($link);
echo json_encode($output);
?>
