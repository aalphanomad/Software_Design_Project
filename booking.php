
<?php


function booking($name,$student_num, $date,$course, $venue, $valid, $chkStartTime, $chkEndTime, $activity, $link)
{
    $output = array();

    $errors = 0;

    $result1 = mysqli_query($link, "SELECT *  FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NUM='$student_num' and DATE='$date' ");
    //$result1=mysqli_query($link,"SELECT * FROM BOOKINGS WHERE NAME='$name'");

    //$result2 = mysqli_query($link, "INSERT INTO BOOKINGS(NAME,STUDENT_NO,COURSE, DATE, VENUE,START_TIME,END_TIME,VALID) VALUES('$name','$student_num','$course','$date','$


    if (mysqli_num_rows($result1) > 0) {
        while ($row = mysqli_fetch_assoc($result1)) {

            $startTime = $row["START_TIME"];
            $endTime = $row["END_TIME"];



            if ($chkStartTime > $startTime && $chkEndTime < $endTime) {       #-> Check time is in between start and end time
                //echo json_encode("1 Time is in between start and end time");
                $errors = $errors + 1;
            } elseif (($chkStartTime > $startTime && $chkStartTime < $endTime) || ($chkEndTime > $startTime && $chkEndTime < $endTime)) {       #-> Check start or end time is in between start and end time
                //echo json_encode("2 ChK start or end Time is in between start and end time");
                $errors = $errors + 1;
            } elseif ($chkStartTime == $startTime || $chkEndTime == $endTime) {       #-> Check start or end time is at the border of start and end time
                //echo json_encode("3 ChK start or end Time is at the border of start and end time");
                $errors = $errors + 1;
            } elseif ($startTime > $chkStartTime && $endTime < $chkEndTime) {       #-> start and end time is in between  the check start and end time.
                //echo json_encode("4 start and end Time is overlapping  chk start and end time");
                $errors = $errors + 1;
            }
        }
    }
    if ($errors == 0) {
        $result = mysqli_query($link, "INSERT INTO BOOKINGS(NAME,STUDENT_NUM,COURSE, DATE, VENUE,START_TIME,END_TIME,ACTIVITY,VALID) VALUES('$name','$student_num','$course','$date','$venue','$chkStartTime','$chkEndTime','$activity','$valid')");
        $answer = 0;
    } else {
        $answer = -1;
    }
    //echo json_encode($result);
    //echo json_encode($errors);
    $output["result"] = (string) $answer;
    return json_encode($output);
}

try
{
    $username = "s1601745";
    $password = "s1601745";
    $database = "d1601745";
    $link = mysqli_connect("127.0.0.1", $username, $password, $database);
    
    $name = $_REQUEST["name"];
    $student_num = $_REQUEST["student_num"];
    $date = $_REQUEST["date"];
    $course = $_REQUEST["course"];
    $venue = $_REQUEST["venue"];
    $valid = $_REQUEST["valid"];
    $chkStartTime = $_REQUEST["chkStartTime"];
    $chkEndTime = $_REQUEST["chkEndTime"];
    $activity = $_REQUEST["activity"];
    
    print booking($name,$student_num, $date,$course, $venue, $valid, $chkStartTime, $chkEndTime, $activity, $link);
}
catch(Exception $e)
{
    echo 0;
}

?>

