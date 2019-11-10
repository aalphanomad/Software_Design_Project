<?php




function create($student_num, $name, $email, $password, $course1, $course2, $course3, $course4, $course5, $role, $link)
{
        $output = array();
        $test = mysqli_query($link, "SELECT * FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");
        if (sizeof(mysqli_fetch_assoc($test)) != 0) {
                $output["result"] = "1";
        } else {
        
                if (!isset($name, $student_num, $email, $password, $course1)) {
                        $output["result"] = "You didn't send the required values";
                        //return json_encode($output);
                        //die();
                } else if (isset($course1) and !isset($course2) and !isset($course3) and !isset($course4) and !isset($course5)) {
                        $result = mysqli_query($link, "INSERT INTO USER_INFORMATION(NAME, STUDENT_NUM, EMAIL_ADDRESS, USER_PASSWORD,ROLE,COURSE_1) VALUES('$name','$student_num','$email','$password','$role','$course1')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course1')");
                } else if (isset($course1) and isset($course2) and !isset($course3) and !isset($course4) and !isset($course5)) {
                        $result = mysqli_query($link, "INSERT INTO USER_INFORMATION(NAME, STUDENT_NUM, EMAIL_ADDRESS, USER_PASSWORD,ROLE,COURSE_1,COURSE_2) VALUES('$name','$student_num','$email','$password','$role','$course1','$course2')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course1')");
                        $result3 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course2')");
                } else if (isset($course1) and isset($course2) and isset($course3) and !isset($course4)  and !isset($course5)) {
                        $result = mysqli_query($link, "INSERT INTO USER_INFORMATION(NAME, STUDENT_NUM, EMAIL_ADDRESS, USER_PASSWORD,ROLE,COURSE_1,COURSE_2,COURSE_3) VALUES('$name','$student_num','$email','$password','$role','$course1','$course2','$course3')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course1')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course2')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course3')");
                } else if (isset($course1) and isset($course2) and isset($course3) and isset($course4) and !isset($course5)) {
                        $result = mysqli_query($link, "INSERT INTO USER_INFORMATION(NAME, STUDENT_NUM, EMAIL_ADDRESS, USER_PASSWORD,ROLE,COURSE_1,COURSE_2,COURSE_3,COURSE_4) VALUES('$name','$student_num','$email','$password','$role','$course1','$course2','$course3','$course4')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course1')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course2')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course3')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course4')");
                } else {
                        $result = mysqli_query($link, "INSERT INTO USER_INFORMATION(NAME, STUDENT_NUM, EMAIL_ADDRESS, USER_PASSWORD,ROLE,COURSE_1,COURSE_2,COURSE_3,COURSE_4,COURSE_5) VALUES('$name','$student_num','$email','$password','$role','$course1','$course2','$course3','$course4','$course5')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course1')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course2')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course3')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course4')");
                        $result2 = mysqli_query($link, "INSERT INTO USER_COURSE_ALLOC(STUDENT_NUM,COURSE) VALUES('$student_num','$course5')");
                }
        
                $output["result"] = "0";
        }

        return json_encode($output);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
// @codeCoverageIgnoreStart
try {
        $link = mysqli_connect("127.0.0.1", $username, $password, $database);


        $name = $_REQUEST["name"];
        $student_num = $_REQUEST["student_num"];
        $email = $_REQUEST["email"];
        $password = $_REQUEST["password"];
        if (isset($_REQUEST["course1"])) {
                $course1 = $_REQUEST["course1"];
        }
        if (isset($_REQUEST["course2"])) {
                $course2 = $_REQUEST["course2"];
        }
        if (isset($_REQUEST["course3"])) {
                $course3 = $_REQUEST["course3"];
        }
        if (isset($_REQUEST["course4"])) {
                $course4 = $_REQUEST["course4"];
        }
        if (isset($_REQUEST["course5"])) {
                $course5 = $_REQUEST["course5"];
        }
        $role = $_REQUEST["role"];
        echo create($student_num,$name,$email,$password,$course1, $course2, $course3, $course4, $course5, $role, $link);
        mysqli_close($link);
} catch (Exception $e) {
        //echo "failed to make link in create.php \n";
}

// @codeCoverageIgnoreEnd
?>