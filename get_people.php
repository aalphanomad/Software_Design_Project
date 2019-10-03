<?php
function get_people($course, $link)
{
    $output1=array();
    $output2=array();
    $output3=array();
    $output4=array();
    $output5=array();
    $output6=array();
    $data=array();


    //TUTORS
    $result1=mysqli_query($link,"SELECT NAME FROM USER_INFORMATION WHERE ROLE=0 AND (COURSE_1='$course' OR COURSE_2='$course' OR COURSE_3='$course' OR COURSE_4='$course' OR COURSE_5='$course')");
    while($row=mysqli_fetch_assoc($result1)["NAME"]){
    $output1[]=$row;
    }


    //LECTURERS
    $result2=mysqli_query($link,"SELECT NAME FROM USER_INFORMATION WHERE ROLE=1 AND (COURSE_1='$course' OR COURSE_2='$course' OR COURSE_3='$course' OR COURSE_4='$course' OR COURSE_5='$course')");
    while($row=mysqli_fetch_assoc($result2)["NAME"]){
    $output2[]=$row;
    }

    $data["tutors"]=$output1;
    $data["lecturers"]=$output2;

    return json_encode($data);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);
$course=$_REQUEST["course"];
echo get_people($course, $link);
?>

