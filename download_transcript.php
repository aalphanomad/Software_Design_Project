<?php

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$student_num = -1;
try {
    $name = $_REQUEST["name"];
    $result = mysqli_query($link, "SELECT STUDENT_NUM FROM USER_INFORMATION WHERE NAME = '$name' ");
    while($row = $result->fetch_array(MYSQL_NUM)) 
    {
        $student_num = $row[0];
    }
} catch (\Throwable $th) {
    $student_num=-1;
}


$result = mysqli_query($link, "SELECT filename FROM USER_TRANSCRIPTS WHERE user_id = '$student_num' ");

if (!$result)
{
  echo("http://lamp.ms.wits.ac.za/~s1601745/pdfs/-1.pdf"); 
}
else
{
    while($row = $result->fetch_array(MYSQL_NUM)) 
    {
        echo ($row[0]."\n");
    }

}

mysqli_close($link);


?>
