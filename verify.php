<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);
$output=array();
$name=trim($_REQUEST["name"]);
$student_num=trim($_REQUEST["student_num"]);
$course=trim($_REQUEST["course"]);
$date=trim($_REQUEST["date"]);
$venue=trim($_REQUEST["venue"]);
error_log("In verify");
$test=array();
error_log("*$name,*$student_num,*$course,*$date,*$venue");
$result1=mysqli_query($link,"SELECT VALID FROM  BOOKINGS WHERE NAME='$name' and STUDENT_NUM='$student_num' and COURSE='$course' and DATE='$date' and VENUE='$venue'");
//print "SELECT VALID FROM  BOOKINGS WHERE NAME='$name' and STUDENT_NO='$studentnum' and COURSE='$course' and DATE='$date' and VENUE='$venue'";
//print json_encode(mysqli_fetch_assoc($result1));
//var_dump(mysqli_fetch_assoc($result1));
//die();
if(mysqli_fetch_assoc($result1)["VALID"]==0){
	$result2=mysqli_query($link,"UPDATE BOOKINGS SET VALID=1  WHERE NAME='$name' and STUDENT_NUM='$student_num' and COURSE='$course' and DATE='$date' and VENUE='$venue'");
error_log("UPDATE BOOKINGS SET VALID=1  WHERE NAME='$name' and STUDENT_NUM='$student_num' and COURSE='$course' and DATE='$date' and VENUE='$venue'");
$test["result"]="0";
print json_encode($test);
}

else{
	$test["result"]="-1";
	print  json_encode($test);
}

mysqli_close($link);
?>

