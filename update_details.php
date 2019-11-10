<?php
function update_details($student_num,$course1,$course2,$course3,$course4,$course5,$link)
{
    $output=array();
    if(isset($course1)){
    $result1=mysqli_query($link,"UPDATE USER_INFORMATION SET COURSE_1='$course1',COURSE_2=null,COURSE_3=null,COURSE_4=null,COURSE_5=null WHERE STUDENT_NUM='$student_num'");
    $output["result"]=0;
    }

    if(isset($course2)){
    $result1=mysqli_query($link,"UPDATE USER_INFORMATION SET COURSE_1='$course1',COURSE_2='$course2',COURSE_3=null,COURSE_4=null,COURSE_5=null WHERE STUDENT_NUM='$student_num'");
    $output["result"]=0;
    }

    if(isset($course3)){
    $result1=mysqli_query($link,"UPDATE USER_INFORMATION SET COURSE_1='$course1',COURSE_2='$course2',COURSE_3='$course3',COURSE_4=null,COURSE_5=null WHERE STUDENT_NUM='$student_num'");
    $output["result"]=0;

    }

    if(isset($course4)){
    $result1=mysqli_query($link,"UPDATE USER_INFORMATION SET COURSE_1='$course1',COURSE_2='$course2',COURSE_3='$course3',COURSE_4='$course4',COURSE_5=null WHERE STUDENT_NUM='$student_num'");
    $output["result"]=0;
    }

    if(isset($course5)){
    $result1=mysqli_query($link,"UPDATE USER_INFORMATION SET COURSE_1='$course1',COURSE_2='$course2',COURSE_3='$course3',COURSE_4='$course4',COURSE_5='$course5' WHERE STUDENT_NUM='$student_num'");
    $output["result"]=0;
    }

    else{
    $output["result"]=0; 
    }

    return json_encode($output["result"]);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
// @codeCoverageIgnoreStart
try {
    $link = mysqli_connect("127.0.0.1", $username, $password,$database);
    $student_num=$_REQUEST["student_num"];
    if(isset($_REQUEST["course1"])){
        $course1=$_REQUEST["course1"];
    }

    if(isset($_REQUEST["course2"])){
        $course2=$_REQUEST["course2"];
    }

    if(isset($_REQUEST["course3"])){
        $course3=$_REQUEST["course3"];
    }

    if(isset($_REQUEST["course4"])){
        $course4=$_REQUEST["course4"];
    }

    if(isset($_REQUEST["course5"])){
        $course5=$_REQUEST["course5"];
    }

    update_details($student_num,$course1,$course2,$course3,$course4,$course5,$link);
    mysqli_close($link);
} catch (Exception $th) {
    //throw $th;
}
// @codeCoverageIgnoreEnd
?>



