<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output=array();
$name=$_REQUEST["name"];
$student_num=$_REQUEST["student_num"];
$course=$_REQUEST["course"];
$date=$_REQUEST["date"];
$venue=$_REQUEST["venue"];
$chkStartTime=$_REQUEST["chkStartTime"];
$chkEndTime=$_REQUEST["chkEndTime"];
$valid=$_REQUEST["valid"];
$result = mysqli_query($link, "INSERT INTO BOOKINGS(NAME,STUDENT_NUM,COURSE, DATE, VENUE,START_TIME,END_TIME,VALID) VALUES('$name','$student_num','$course','$date','$venue','$chkStartTime','$chkEndTime','$valid')");



/* Select queries return a resultset */
$output["result"]=$result;
mysqli_close($link);
echo json_encode($output);
?>

