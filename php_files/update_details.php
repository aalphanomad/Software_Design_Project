<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);
$output=array();
$student_num=$_REQUEST["student_num"];
$course1=$_REQUEST["course1"];
$course2=$_REQUEST["course2"];
$course3=$_REQUEST["course3"];
$course4=$_REQUEST["course4"];
$course5=$_REQUEST["course5"];




$result1=mysqli_query($link,"UPDATE USER_INFORMATION SET COURSE_1='$course1',COURSE_2='$course2',COURSE_3='$course3',COURSE_4='$course4',COURSE_5='$course5' WHERE STUDENT_NUM='$student_num'");
echo json_encode($result1);
mysqli_close($link);
?>

