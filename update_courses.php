<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output=array();
$student_num=$_REQUEST["student_num"];
$course=$_REQUEST["course"];
$change_confirmed = true;
if (isset($_REQUEST["confirmed"]))
{
    $confirmed=$_REQUEST["confirmed"];
} 
else
{
    $change_confirmed = false;
}

// first we check if this allocation exists
$result1 = mysqli_query($link, "SELECT * FROM USER_COURSE_ALLOC WHERE STUDENT_NUM='$student_num' AND COURSE ='$course'  ");
if($result1->num_rows === 0)
{
    // if it does not exist then we make a new one
    
    // if we are receiving confirmation then we set it to whatever the user has specified
    // otherwise we set it to the default of 0
    if ($change_confirmed) {
        $result=mysqli_query($link,"INSERT INTO USER_COURSE_ALLOC (STUDENT_NUM, COURSE, CONFIRMED) VALUES ('$student_num','$course','$confirmed')");
    
    } else {
        $result=mysqli_query($link,"INSERT INTO USER_COURSE_ALLOC (STUDENT_NUM, COURSE, CONFIRMED) VALUES ('$student_num','$course','0')");   
    }
    
}
else
{
    // otherwise we simply update the existing one
    // we don't want to reset whether the course is confirmed or not
    if ($change_confirmed) 
    {
        $result=mysqli_query($link,"UPDATE USER_COURSE_ALLOC SET  CONFIRMED='$confirmed' WHERE STUDENT_NUM='$student_num' AND COURSE='$course'");
    }
    else
    {
        // no change occurred but we'd still like to say that the query was correct and successful
        $result=true;
    }
}

echo json_encode($result);
mysqli_close($link);
?>



