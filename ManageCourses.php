<?php
function manage_courses($adminUsername,$adminPassword,$course_name,$course_code,$event,$link)
{
    $output = array();
    $data = array();
    $ans = -1;
    $output["user"] = -1;
    $output["course"] = 0;
    $output["delete"] = -1;
    $result1 = mysqli_query($link, "SELECT USER_PASSWORD FROM USER_INFORMATION WHERE STUDENT_NUM='$adminUsername' and ROLE='2'");
    if (mysqli_num_rows($result1) > 0 and mysqli_fetch_assoc($result1)["USER_PASSWORD"] == $adminPassword) {
        $output["user"] = 0;
        $ans = 0;
    }
    if ($ans == 0) {
    
        if ($event == 1) {
            $result1 = mysqli_query($link, "SELECT * FROM COURSES  WHERE COURSE_NAME='$course_name' or  COURSE_CODE='$course_code'");
            if (mysqli_num_rows($result1) > 0) {
                $output["course"] = -1;
                $ans = -1;
            } else {
                $result1 = mysqli_query($link, "INSERT INTO COURSES(COURSE_CODE,COURSE_NAME) VALUES('$course_code','$course_name')");
            }
        } elseif ($event == 2) {
            $result1 = mysqli_query($link, "DELETE FROM COURSES WHERE COURSE_CODE='$course_code'");
            $output["delete"] = 0;
        }
    }
    return json_encode($output);
}


$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
// @codeCoverageIgnoreStart
try {
    $link = mysqli_connect("127.0.0.1", $username, $password, $database);

    $adminUsername = $_REQUEST["adminUsername"];
    $adminPassword = $_REQUEST["adminPassword"];
    $course_name = $_REQUEST["course_name"];
    $course_code = $_REQUEST["course_code"];
    $event = $_REQUEST["event"];

    echo manage_courses($adminUsername, $adminPassword, $course_name, $course_code, $event,$link);
} catch (Exception $th) {
    //throw $th;
}
// @codeCoverageIgnoreEnd
?>