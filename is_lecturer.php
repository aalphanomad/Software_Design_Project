<?php
function is_lecturer($student_num, $password, $role, $link)
{
    /* Select queries return a resultset */
    $result1=mysqli_query($link,"SELECT ROLE FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num' AND USER_PASSWORD='$password' AND ROLE='$role'");
    if(mysqli_num_rows($result1)>0){
    $output["result"]='0';
    }
    else{
    $output["result"]='-1';
    }
    return json_encode($output);
}

$username = "s1601745";
$password = "s1601745";	
$database = "d1601745";
// @codeCoverageIgnoreStart

try {
    $link = mysqli_connect("127.0.0.1", $username, $password, $database);

    $student_num=$_REQUEST["student_num"];
    $password=$_REQUEST["password"];
    $role=$_REQUEST["role"];
    
    print is_lecturer($student_num, $password, $role, $link);
    mysqli_close($link);
} catch (Exception $th) {
    //throw $th;
}
// @codeCoverageIgnoreEnd


?>

