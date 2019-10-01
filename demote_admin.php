<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output=array();
$student_num=$_REQUEST["student_num"];

// first we check if this user exists
$result1 = mysqli_query($link, "SELECT * FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num' AND ROLE=2 OR ROLE=3");
if($result1->num_rows === 0)
{
    // can't make a user admin if they dont exist
    $result=false;
}
else
{

    $result=mysqli_query($link,"UPDATE USER_INFORMATION SET  ROLE='1' WHERE STUDENT_NUM='$student_num'");
    $result=true;
}

echo json_encode($result);
mysqli_close($link);
?>
