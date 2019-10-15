<?php
function errors($name, $student_num, $date, $chkStartTime, $chkEndTime, $link)
{
    // @codeCoverageIgnoreStart
    $errors = 0;
    //$result1=mysqli_query($link,"SELECT *  FROM BOOKINGS  WHERE NAME='$name' and  STUDENT_NO='$student_no' and DATE='$date' and START_TIME='$chkStartTime' and END_TIME='$
    $result1 = mysqli_query($link, "SELECT * FROM BOOKINGS WHERE NAME='$name' and STUDENT_NUM='$student_num' and DATE='$date'");

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
        return json_encode("hello");
    }

    return json_encode($errors);
}

$username = "s1601745";
$password = "s1601745";
$database = "d1601745";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$name = $_REQUEST["name"];
$student_num = $_REQUEST["student_num"];
$date = $_REQUEST["date"];
$chkStartTime = $_REQUEST["chkStartTime"];
$chkEndTime = $_REQUEST["chkEndTime"];
echo errors($name, $student_num, $date, $chkStartTime, $chkEndTime, $link);
// @codeCoverageIgnoreEnd
?>

