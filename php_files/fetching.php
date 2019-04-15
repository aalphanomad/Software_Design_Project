<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output1=array();
$output2=array();
$output3=array();
$output4=array();
$output5=array();
$output6=array();
$data=array();
$name=$_REQUEST["name"];
$student_num=$_REQUEST["student_num"];
$result1=mysqli_query($link,"SELECT DATE FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");
$result2=mysqli_query($link,"SELECT COURSE FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");
$result3=mysqli_query($link,"SELECT START_TIME FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");
$result4=mysqli_query($link,"SELECT END_TIME FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");
$result5=mysqli_query($link,"SELECT VALID FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");
$result6=mysqli_query($link,"SELECT VENUE FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num'");
if(mysqli_num_rows($result1)>0){
while($row=mysqli_fetch_assoc($result1)){
$output1[]=$row;
}

while($row=mysqli_fetch_assoc($result2)){
$output2[]=$row;
}

while($row=mysqli_fetch_assoc($result3)){
$output3[]=$row;
}

while($row=mysqli_fetch_assoc($result4)){
$output4[]=$row;
}

while($row=mysqli_fetch_assoc($result5)){
$output5[]=$row;
}

while($row=mysqli_fetch_assoc($result6)){
$output6[]=$row;
}
}

$data["dates"]=$output1;
$data["courses"]=$output2;
$data["start_time"]=$output3;
$data["end_time"]=$output4;
$data["valid"]=$output5;
$data["venue"]=$output6;

print json_encode($data);
//$output["result"]=(string)$answer;
//echo json_encode($output);
?>

