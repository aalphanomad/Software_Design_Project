<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$data=array();
$name=$_REQUEST["name"];
$student_num=$_REQUEST["student_num"];
$count=0;
$result1=mysqli_query($link,"SELECT DATE FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");


if(mysqli_num_rows($result1)>0){
while($row=mysqli_fetch_assoc($result1)){
$count=$count+1;
}
}
print json_encode($count);
?>
