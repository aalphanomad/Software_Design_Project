<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output=array();
$student_num=$_REQUEST["student_num"];
$course=$_REQUEST["course"];

// first we check if this allocation exists
$result1 = mysqli_query($link, "SELECT * FROM USER_COURSE_ALLOC WHERE STUDENT_NUM='$student_num' AND COURSE ='$course'  ");
if($result1->num_rows === 0)
{
    // if it does not exist then we make a new one
    $result=mysqli_query($link,"INSERT INTO USER_COURSE_ALLOC (STUDENT_NUM, COURSE, CONFIRMED) VALUES ('$student_num','$course','0')");   
}
else
{
    // This is already selected dont change anything
    
    //TODO: remove a course alloc
    //      see remove course.php
}

echo json_encode($result);
mysqli_close($link);
?>



