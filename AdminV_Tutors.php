
<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);
$output=array();
$name=array();
$student_num=array();

$course=$_REQUEST["course"];
$result1=mysqli_query($link,"SELECT NAME,STUDENT_NUM FROM USER_INFORMATION WHERE (ROLE='0'  AND COURSE_1='$course' OR COURSE_2='$course' OR COURSE_3='$course' OR COURSE_4='$course' OR COURSE_5='$course')");
while($row=mysqli_fetch_assoc($result1)){
$name[]=$row["NAME"];
$student_num[]=$row["STUDENT_NUM"];
}
$output["name"]=$name;
$output["student_num"]=$student_num;
print json_encode($output);

?>


