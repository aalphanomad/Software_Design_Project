<?php


function get_students($student_num, $link)
{
    $output0 = array();
    $output1 = array();
    $output2 = array();
    $output3 = array();
    $output4 = array();
    $output5 = array();
    $output6 = array();
    $outputA1 = array();
    $outputA2 = array();
    $outputA3 = array();
    $outputA4 = array();
    $outputA5 = array();
    $outputA6 = array();

    $data = array();



    $result1 = mysqli_query($link, "SELECT COURSE_1,COURSE_2,COURSE_3,COURSE_4,COURSE_5 FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num' AND ROLE=1");
    $test1 = mysqli_fetch_assoc($result1)["COURSE_1"];
    mysqli_data_seek($result1, 0);
    $test2 = mysqli_fetch_assoc($result1)["COURSE_2"];
    mysqli_data_seek($result1, 0);
    $test3 = mysqli_fetch_assoc($result1)["COURSE_3"];
    mysqli_data_seek($result1, 0);
    $test4 = mysqli_fetch_assoc($result1)["COURSE_4"];
    mysqli_data_seek($result1, 0);
    $test5 = mysqli_fetch_assoc($result1)["COURSE_5"];
    mysqli_data_seek($result1, 0);

    array_push($output0, $test1, $test2, $test3, $test4, $test5);
    if ($test1 != null) {
        $resultA = mysqli_query($link, "SELECT NAME,STUDENT_NUM FROM USER_INFORMATION WHERE ROLE=0 AND COURSE_1='$test1' OR COURSE_2='$test1' OR COURSE_3='$test1' OR COURSE_4='$test1' OR COURSE_5='$test1'");
        while ($row = mysqli_fetch_assoc($resultA)) {
            $output1[] = $row["NAME"];
            $outputA1[] = $row["STUDENT_NUM"];
        }
    }


    if ($test2 != null) {
        $resultB = mysqli_query($link, "SELECT NAME,STUDENT_NUM FROM USER_INFORMATION WHERE ROLE=0 AND COURSE_1='$test2' OR COURSE_2='$test2' OR COURSE_3='$test3' OR COURSE_4='$test3' OR COURSE_5='$test3'");
        while ($row = mysqli_fetch_assoc($resultB)) {
            $output2[] = $row["NAME"];
            $outputA2[] = $row["STUDENT_NUM"];
        }
    }

    if ($test3 != null) {
        $resultC = mysqli_query($link, "SELECT NAME,STUDENT_NUM FROM USER_INFORMATION WHERE ROLE=0 AND COURSE_1='$test3' OR COURSE_2='$test3' OR COURSE_3='$test3' OR COURSE_4='$test3' OR COURSE_4='$test3'");
        while ($row = mysqli_fetch_assoc($resultC)) {
            $output3[] = $row["NAME"];
            $outputA3[] = $row["STUDENT_NUM"];
        }
    }

    if ($test4 != null) {
        $resultD = mysqli_query($link, "SELECT NAME,STUDENT_NUM FROM USER_INFORMATION WHERE ROLE=0 AND COURSE_1='$test4' OR COURSE_2='$test4' OR COURSE_3='$test4' OR COURSE_4='$test5' OR COURSE_5='$test5'");
        while ($row = mysqli_fetch_assoc($resultD)) {
            $output4[] = $row["NAME"];
            $outputA4[] = $row["STUDENT_NUM"];
        }
    }

    if ($test5 != null) {
        $resultE = mysqli_query($link, "SELECT NAME,STUDENT_NUM FROM USER_INFORMATION WHERE ROLE=0 AND COURSE_1='$test5' OR COURSE_2='$test5' OR COURSE_3='$test5' OR COURSE_4='$test5' OR COURSE_1='$test5'");
        while ($row = mysqli_fetch_assoc($resultE)) {
            $output5[] = $row["NAME"];
            $outputA5[] = $row["STUDENT_NUM"];
        }
    }
    $data["Courses"] = $output0;
    $data["Course1"] = $output1;
    $data["Course2"] = $output2;
    $data["Course3"] = $output3;
    $data["Course4"] = $output4;
    $data["Course5"] = $output5;
    $data["stu_Course1"] = $outputA1;
    $data["stu_Course2"] = $outputA2;
    $data["stu_Course3"] = $outputA3;
    $data["stu_Course4"] = $outputA4;
    $data["stu_Course5"] = $outputA5;


    return json_encode($data);
}

// @codeCoverageIgnoreStart
try
{
    $username = "s1601745";
    $password = "s1601745";
    $database = "d1601745";
    $link = mysqli_connect("127.0.0.1", $username, $password, $database);
    
    $student_num = $_REQUEST["student_num"];
    
    print get_students($student_num, $link);
}
catch(Exception $e)
{
    echo 0;
}
// @codeCoverageIgnoreEnd
?>