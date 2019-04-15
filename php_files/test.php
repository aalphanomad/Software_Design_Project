<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output=array();
$name=$_REQUEST["name"];
$student_num=$_REQUEST["student_num"];
$course=$_REQUEST["course"];
$date=$_REQUEST["date"];
$venue=$_REQUEST["venue"];
$chkStartTime=$_REQUEST["chkStartTime"];
$chkEndTime=$_REQUEST["chkEndTime"];
$valid=$_REQUEST["valid"];

$result1=mysqli_query($link,"SELECT START_TIME,END_TIME WHERE NAME='$name' and COURSE='$course' and DATE='$date' and VENUE='$venue'");
 

//$result2 = mysqli_query($link, "INSERT INTO BOOKINGS(NAME,STUDENT_NO,COURSE, DATE, VENUE,START_TIME,END_TIME,VALID) VALUES('$name','$student_num','$course','$date','$venue','$chkStartTime','$chkEndTime','$valid')");
/*
if($result1->num_rows> 0 ){
while($row = $result1->fetch_assoc()){
$chkStartTime=strtotime('$chkStartTime');
$chkEndTime=strtotime('$chkEndTime');

$startTime=strtotime('$row["START_TIME"]');
$endTime=strtotime('$row["END_TIME"]');

if($chkStartTime > $startTime && $chkEndTime < $endTime)
{	#-> Check time is in between start and end time
	echo "1 Time is in between start and end time";
}elseif(($chkStartTime > $startTime && $chkStartTime < $endTime) || ($chkEndTime > $startTime && $chkEndTime < $endTime))
{	#-> Check start or end time is in between start and end time
	echo "2 ChK start or end Time is in between start and end time";
}elseif($chkStartTime==$startTime || $chkEndTime==$endTime)
{	#-> Check start or end time is at the border of start and end time
	echo "3 ChK start or end Time is at the border of start and end time";
}elseif($startTime > $chkStartTime && $endTime < $chkEndTime)
{	#-> start and end time is in between  the check start and end time.
	echo "4 start and end Time is overlapping  chk start and end time";
}
}
}
/* Select queries return a resultset
*/
$output["result1"]=mysqli_fetch_assoc($result1)["START_TIME"];
mysqli_close($link);
echo json_encode($output);
?>

