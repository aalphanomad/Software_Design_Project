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

$year = date("Y");
if (isset($_REQUEST['month'])) {
	$month = $_REQUEST['month'];
	$result = mysqli_query($link, "SELECT $target from $table where STUDENT_NUM = '$student_num' AND DATE LIKE '%$month%'");
}
else
{
	$result = mysqli_query($link, "SELECT $target from $table where STUDENT_NUM = '$student_num'");
}
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

$the_date = date("h_i_d_m");
shell_exec("./csv_files/ssh.py");
$command = "mv csv_files/timesheet.pdf csv_files/$student_num" . "_timesheet_$the_date.pdf";
shell_exec($command);

echo "http://lamp.ms.wits.ac.za/~s1601745/csv_files/$student_num"."_timesheet_$the_date.pdf";
//echo "http://lamp.ms.wits.ac.za/~s1601745/csv_files/$student_num"."_timesheet_.pdf";
mysqli_close($link);
?>




