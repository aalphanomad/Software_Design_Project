<?php


try {
    $student_num=$_REQUEST["student_num"];
} catch (\Throwable $th) {
    $student_num=-1;
}

// code from:  https://stackoverflow.com/questions/33981316/upload-pdf-file-to-server-and-get-its-url

$file_name = "$student_num".".pdf"; //name of your file
$server_path = "pdfs/"; //server path to folder
$web_path = "http://lamp.ms.wits.ac.za/~s1601745/$server_path"; //web path to folder

$file = $server_path.$file_name;
file_put_contents($file,"");

$fp = fopen("php://input", 'r');
while ($buffer =  fread($fp, 8192)) {
    file_put_contents($file,$buffer,FILE_APPEND | LOCK_EX);
}



$full_path = $web_path.$file_name;
echo "INSERT INTO USER_TRANSCRIPTS(user_id, filename) VALUES('$student_num','$full_path') \n\n\n\n";

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$result = mysqli_query($link, "INSERT INTO USER_TRANSCRIPTS(user_id, filename) VALUES('$student_num','$full_path')");

if (!$result)
{
  echo("Error description: " . mysqli_error($link));
}

mysqli_close($link);


?>
