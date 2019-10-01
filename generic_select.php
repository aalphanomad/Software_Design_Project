<?php
$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output=array();
$table=$_REQUEST["table"];
$target=$_REQUEST["target"];
$filter=$_REQUEST["filter"];
$value = $_REQUEST["value"];
/* Select queries return a resultset */
$result = mysqli_query($link, "SELECT $target from $table where $filter = '$value'");

$arrayName = [];
while($row = $result->fetch_array(MYSQL_NUM)) 
{
	$myArray[] = $row;
}

$encoded = json_encode($myArray);

echo $encoded;

mysqli_close($link);
?>




