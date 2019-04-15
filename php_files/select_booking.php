<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output=array();
$table=$_REQUEST["table"];
$target=$_REQUEST["target"];
$student_num = $_REQUEST["student_num"];
/* Select queries return a resultset */
$result = mysqli_query($link, "SELECT $target from $table where STUDENT_NUM = '$student_num'");
$curr_dir = getcwd();
$fp = fopen($curr_dir.'/csv_files/file.csv', 'w');

$arrayName = [];
while($row = $result->fetch_array(MYSQL_NUM)) 
{
	$myArray[] = $row;
}

$encoded = json_encode($myArray);
$decoded = json_decode($encoded);
foreach($decoded as $row)
{
	fputcsv($fp,$row);
}


shell_exec("./csv_files/ssh.py");

echo "http://lamp.ms.wits.ac.za/~s1601745/csv_files/report.pdf";
mysqli_close($link);
?>




