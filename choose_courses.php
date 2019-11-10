<?php
function choose_courses($student_num, $course, $link)
{
    // first we check if this allocation exists
    $result1 = mysqli_query($link, "SELECT * FROM USER_COURSE_ALLOC WHERE STUDENT_NUM='$student_num' AND COURSE ='$course'  ");
    if ($result1->num_rows === 0) {
        // if it does not exist then we make a new one
        $result = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC (STUDENT_NUM, COURSE, CONFIRMED) VALUES ('$student_num','$course','0')");
    } else {
        // This is already selected dont change anything
        $result = -1;
        //TODO: remove a course alloc
        //      see remove course.php
    }

    return json_encode($result);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
try {
    $link = mysqli_connect("127.0.0.1", $username, $password, $database);

    $student_num = $_REQUEST["student_num"];
    $course = $_REQUEST["course"];

    echo choose_courses($student_num, $course, $link);
    mysqli_close($link);
} catch (Exception $th) {
    //throw $th;
}


?>