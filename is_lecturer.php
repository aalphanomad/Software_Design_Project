<?php
$username = "s1601745";
$password = "s1601745";	
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output=array();

$student_num=$_REQUEST["student_num"];
$password=$_REQUEST["password"];
$role=$_REQUEST["role"];
/* Select queries return a resultset */
$result1=mysqli_query($link,"SELECT ROLE FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num' AND USER_PASSWORD='$password' AND ROLE='$role'");
if(mysqli_num_rows($result1)>0){
$output["result"]='0';
}
else{
$output["result"]='-1';
}
print json_encode($output);
mysqli_close($link);

?>

