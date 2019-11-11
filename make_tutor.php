<?php


function make_tutor($student_num,$link)
{
    // first we check if this user exists
    $result1 = mysqli_query($link, "SELECT * FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num' AND ROLE=1");
    if($result1->num_rows === 0)
    {
        // can't make a user admin if they dont exist
        $result=false;
    }
    else
    {
    
        $result=mysqli_query($link,"UPDATE USER_INFORMATION SET  ROLE='0' WHERE STUDENT_NUM='$student_num'");
        $result=true;
    }
    
    return json_encode($result);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
// @codeCoverageIgnoreStart
try {
    $link = mysqli_connect("127.0.0.1", $username, $password,$database);
    $student_num=$_REQUEST["student_num"];
    echo make_tutor($student_num, $link);
    mysqli_close($link);
} catch (Exception $th) {
    //throw $th;
}
// @codeCoverageIgnoreEnd


?>
