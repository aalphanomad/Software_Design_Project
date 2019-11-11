<?php
function get_name($student_num, $link)
{
    $output=array();
    /* Select queries return a resultset */
    $result1=mysqli_query($link,"SELECT NAME FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");
    $output["result"]=$result1;
    return mysqli_fetch_assoc($output["result"])["NAME"];
}


$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
// @codeCoverageIgnoreStart
try {
    $link = mysqli_connect("127.0.0.1", $username, $password, $database);
    $student_num=$_REQUEST["student_num"];

    echo get_name($student_num, $link);
    mysqli_close($link);

} catch (Exception $e) {
    echo 0;
}
// @codeCoverageIgnoreEnd
?>

