<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output1=array();
$data=array();
$name=$_REQUEST["name"];
$student_num=$_REQUEST["student_num"];
$result1=mysqli_query($link,"SELECT COURSE_1,COURSE_2,COURSE_3,COURSE_4,COURSE_5 FROM USER_INFORMATION WHERE NAME= '$name' AND STUDENT_NUM='$student_num'");

if(mysqli_num_rows($result1)>0){
while($row=mysqli_fetch_assoc($result1)){
$output1[]=$row;
}
}

$data["courses"]=$output1;
print json_encode($data);
//$output["result"]=(string)$answer;
//echo json_encode($output);
?>
