<?php

class updateCoursesTest extends PHPUnit_Framework_TestCase
{
    /**
     * @var PDO
     */
    private $pdo;
    private $myqli_link;

    public function setUp()
    {
        //this stuff creates the table since PDO works nicely I've left it here
        $this->pdo = new PDO($GLOBALS['db_dsn'], $GLOBALS['db_username'], $GLOBALS['db_password']);
        $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        //Create the USER_INFORMATION table
        /*
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
        */
        // for our code we need a mysqli lonk and not a PDO object
        $this->myqli_link = mysqli_connect("127.0.0.1", $GLOBALS['db_username'], $GLOBALS['db_password'],"test_db");
        
    }

    public function tearDown()
    {
        //$this->pdo->query("DROP TABLE IF EXISTS USER_INFORMATION");
    }

    public function testUpdateCourses()
    {
        include 'update_courses.php';
        $this->assertEquals('true', update_courses("1","COMS3003",true,true,$this->myqli_link)); //weird
        $this->assertEquals('true', update_courses("1","COMS3005",true,true,$this->myqli_link));
        $this->assertEquals('true', update_courses("1","COMS3005",true,false,$this->myqli_link));
        $this->assertEquals('false', update_courses("1","COMS3003",true,false,$this->myqli_link));
        $this->assertEquals('false', update_courses("009","COMS1018",true,true,$this->myqli_link));
        $this->assertEquals('false', update_courses("009","COMS1018",false,true,$this->myqli_link));
        $this->assertEquals('false', update_courses("009","COMS1018",true,false,$this->myqli_link));
        $this->assertEquals('false', update_courses("009","COMS1018",false,false,$this->myqli_link));
    }
}
?>