<?php
function signin($student_num, $password, $link)
{
	$output = array();
	/* Select queries return a resultset */
	$result1 = mysqli_query($link, "SELECT USER_PASSWORD from USER_INFORMATION where STUDENT_NUM='$student_num'");
	$result2 = mysqli_query($link, "SELECT ROLE FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");
	$result3 = mysqli_query($link, "SELECT NAME FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");

	$output["result"] = $result1;
	//while($row=mysqli_fetch_assoc($result))
	//	$test[]=$row;
	//print(mysqli_fetch_assoc($result)["USER_PASSWORD"]);
	//print json_encode($password);
	//print json_encode($studentnum);
	$test = array();

	if ($password == mysqli_fetch_assoc($result1)["USER_PASSWORD"]) {
		$test["result"] = 1;
		$test["name"] = mysqli_fetch_assoc($result3)["NAME"];
		$test["student_num"] = $student_num;
		$test["role"] = mysqli_fetch_assoc($result2)["ROLE"];
		return json_encode($test);

		//HOW DO I SEND THIS?
	} else {
		$test["result"] = 0;
		return json_encode($test);
	}
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
try
{
	$link = mysqli_connect("127.0.0.1", $username, $password, $database);

	$student_num = $_REQUEST["student_num"];
	$password = $_REQUEST["password"];

	echo signin($student_num, $password, $link);
	mysqli_close($link);
}
catch (Exception $e)
{
	echo 0;
}

?>
