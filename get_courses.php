<?php
function get_courses($name, $student_num, $link)
{
    $output1=array();
    $result1=mysqli_query($link,"SELECT COURSE_1,COURSE_2,COURSE_3,COURSE_4,COURSE_5 FROM USER_INFORMATION  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");


    while($row=mysqli_fetch_assoc($result1)){
    $output1[]=$row;
    }
    
    $data["result"]=$output1;
    
    
    return json_encode($data);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);
$name=$_REQUEST["name"];
$student_num=$_REQUEST["student_num"];

print get_courses($name, $student_num, $link);
?>

