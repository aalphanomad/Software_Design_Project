<?php
function get_all_courses($link)
{
    $output=array();
    $output1=array();
    $output2=array();
    /* Select queries return a resultset */
    $result1=mysqli_query($link,"SELECT COURSE_CODE,COURSE_NAME FROM COURSES");
    while($row=mysqli_fetch_assoc($result1)){
    $output1[]=$row["COURSE_NAME"];
    $output2[]=$row["COURSE_CODE"];
        }
    $output["course_name"]=$output1;
    $output["course_code"]=$output2;
    return json_encode($output);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);

print get_all_courses($link);
mysqli_close($link);

?>




