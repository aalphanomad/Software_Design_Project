<?php
$username = "s1601745";
$password = "s1601745";	
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output=array();

$student_num=$_REQUEST["student_num"];
/* Select queries return a resultset */
$result1=mysqli_query($link,"SELECT NAME,ROLE FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");
/*
if(mysqli_num_rows($result1)>0){
    $output["name"]=mysqli_fetch_assoc($result1)["NAME"];
    $output["role"]=mysqli_fetch_assoc($result1)["ROLE"];
}
*/

while($row=mysqli_fetch_assoc($result1)){
    print json_encode($row);
    }
mysqli_close($link);

?>

