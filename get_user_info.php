<?php
function get_user_info($student_num, $link)
{
    $output=array();
    /* Select queries return a resultset */
    $result1=mysqli_query($link,"SELECT NAME,ROLE FROM USER_INFORMATION WHERE STUDENT_NUM='$student_num'");
    /*
    if(mysqli_num_rows($result1)>0){
        $output["name"]=mysqli_fetch_assoc($result1)["NAME"];
        $output["role"]=mysqli_fetch_assoc($result1)["ROLE"];
    }
    */
    while($row=mysqli_fetch_assoc($result1)){
        return json_encode($row);
        }
    return $result1;
}


try {
    $username = "s1601745";
    $password = "s1601745";	
    $database = "d1601745";
    $link = mysqli_connect("127.0.0.1", $username, $password, $database);
    $student_num=$_REQUEST["student_num"];

    
    print get_user_info($student_num, $link);

    mysqli_close($link);
} catch (Exception $e) {
    echo 0;
}


?>

