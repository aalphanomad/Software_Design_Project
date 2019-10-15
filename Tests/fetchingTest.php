<?php

class fetchingTest extends PHPUnit_Framework_TestCase
{
    /**
     * @var PDO
     */
    private $pdo;
    private $myqli_link;

    public function setUp()
    {
        echo "setting up fetchingTest";
        //this stuff creates the table since PDO works nicely I've left it here
        $this->pdo = new PDO($GLOBALS['db_dsn'], $GLOBALS['db_username'], $GLOBALS['db_password']);
        $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $this->pdo->query(" CREATE TABLE IF NOT EXISTS`USER_INFORMATION` (
            `NAME` varchar(50) DEFAULT NULL,
            `STUDENT_NUM` varchar(10) NOT NULL,
            `EMAIL_ADDRESS` varchar(50) DEFAULT NULL,
            `USER_PASSWORD` varchar(50) NOT NULL,
            `ROLE` tinyint(1) DEFAULT NULL,
            `COURSE_1` varchar(20) DEFAULT NULL,
            `COURSE_2` varchar(20) DEFAULT NULL,
            `COURSE_3` varchar(20) DEFAULT NULL,
            `COURSE_4` varchar(20) DEFAULT NULL,
            `COURSE_5` varchar(20) DEFAULT NULL,
            `TRANSCRIPT` varchar(100) DEFAULT NULL,
            UNIQUE KEY `STUDENT_NUMBER` (`STUDENT_NUM`)
           ) ENGINE=InnoDB DEFAULT CHARSET=latin1");

        // for our code we need a mysqli lonk and not a PDO object
        $this->myqli_link = mysqli_connect("127.0.0.1", $GLOBALS['db_username'], $GLOBALS['db_password'],"test_db");
        
    }

    public function tearDown()
    {
        //$this->pdo->query("DROP TABLE BOOKINGS");
    }

    public function testFetching()
    {
        include 'fetching.php';
        // Test on empty database
        $expected = '{"dates":[{"DATE":"01 Jun 2019"},{"DATE":"01 Jun 2019"},{"DATE":"30 May 2019"},{"DATE":"25 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"},{"DATE":"27 Sep 2019"}],"courses":[{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS3007"},{"COURSE":"COMS2015"},{"COURSE":"COMS1017"},{"COURSE":"COMS3007"},{"COURSE":"COMS1017"},{"COURSE":"COMS1017"},{"COURSE":"COMS1017"}],"start_time":[{"START_TIME":"10:59:00"},{"START_TIME":"20:21:00"},{"START_TIME":"20:21:00"},{"START_TIME":"07:00:00"},{"START_TIME":"16:10:00"},{"START_TIME":"17:00:00"},{"START_TIME":"08:45:00"},{"START_TIME":"17:20:00"},{"START_TIME":"17:30:00"},{"START_TIME":"08:55:00"},{"START_TIME":"09:15:00"},{"START_TIME":"20:10:00"},{"START_TIME":"09:25:00"},{"START_TIME":"09:35:00"},{"START_TIME":"09:50:00"},{"START_TIME":"19:40:00"},{"START_TIME":"10:05:00"},{"START_TIME":"21:20:00"},{"START_TIME":"22:40:00"},{"START_TIME":"11:30:00"},{"START_TIME":"23:00:00"},{"START_TIME":"12:10:00"},{"START_TIME":"03:30:00"},{"START_TIME":"12:35:00"},{"START_TIME":"04:30:00"},{"START_TIME":"13:00:00"}],"end_time":[{"END_TIME":"12:59:00"},{"END_TIME":"20:25:00"},{"END_TIME":"20:25:00"},{"END_TIME":"07:05:00"},{"END_TIME":"16:15:00"},{"END_TIME":"17:05:00"},{"END_TIME":"08:50:00"},{"END_TIME":"17:25:00"},{"END_TIME":"17:35:00"},{"END_TIME":"09:00:00"},{"END_TIME":"09:20:00"},{"END_TIME":"20:15:00"},{"END_TIME":"09:30:00"},{"END_TIME":"09:40:00"},{"END_TIME":"09:55:00"},{"END_TIME":"19:45:00"},{"END_TIME":"10:10:00"},{"END_TIME":"21:25:00"},{"END_TIME":"22:45:00"},{"END_TIME":"11:55:00"},{"END_TIME":"23:05:00"},{"END_TIME":"12:20:00"},{"END_TIME":"03:45:00"},{"END_TIME":"12:45:00"},{"END_TIME":"04:35:00"},{"END_TIME":"13:05:00"}],"valid":[{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"1"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"},{"VALID":"0"}],"venue":[{"VENUE":"hvdd"},{"VENUE":"nbg"},{"VENUE":"Pandors house"},{"VENUE":"gjherghjre"},{"VENUE":"hjvhsgsh"},{"VENUE":"gjhghj"},{"VENUE":"sig"},{"VENUE":"iere"},{"VENUE":"yruieyr"},{"VENUE":"sig"},{"VENUE":"sig"},{"VENUE":"ghghj"},{"VENUE":"sig"},{"VENUE":"sig"},{"VENUE":"sig"},{"VENUE":"yuiy"},{"VENUE":"sig"},{"VENUE":"uiyiuyi`"},{"VENUE":"uyiuyui"},{"VENUE":"sig"},{"VENUE":"hhgjhghj"},{"VENUE":"sig"},{"VENUE":"ytytuy"},{"VENUE":"sig"},{"VENUE":"rwew"},{"VENUE":"sig"}],"activity":[{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Marking"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Tutoring"},{"ACTIVITY":"Other"},{"ACTIVITY":"Marking"}]} ';
        $this->assertEquals($expected, fetching("tuor","1",$this->myqli_link));
        $expected = '{"result":"You didn\'t send the required values"}';
        $this->assertEquals($expected, create("rey","624",NULL,NULL,NULL,NULL,NULL,0,$this->myqli_link));
    }
}
?>