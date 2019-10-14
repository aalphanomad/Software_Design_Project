<?php

function change_role($student_num, $role, $link)
{
    $curr_role = mysqli_query($link, "SELECT * FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'")->fetch_object()->ROLE;
    // first we check if this user exists
    $result1 = mysqli_query($link, "SELECT * FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");
    if($result1->num_rows === 0 || $curr_role === 4)
    {
        // can't change a users role if they dont exist or if they are super admin
        $result=false;
    }
    else
    {
        $result=mysqli_query($link,"UPDATE USER_INFORMATION SET  ROLE='$role' WHERE STUDENT_NUM='$student_num'");
        $result=true;
    } 
    
    return json_encode($result);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
// @codeCoverageIgnoreStart
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output=array();
$student_num=$_REQUEST["student_num"];
$role=$_REQUEST["role"];
// @codeCoverageIgnoreStart

echo change_role($student_num, $role, $link);
mysqli_close($link);
?>