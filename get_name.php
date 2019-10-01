<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output=array();
$student_num=$_REQUEST["student_num"];

/* Select queries return a resultset */
$result1=mysqli_query($link,"SELECT NAME FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");
$output["result"]=$result1;
echo mysqli_fetch_assoc($output["result"])["NAME"];
mysqli_close($link);

?>

