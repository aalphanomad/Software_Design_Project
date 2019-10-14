<?php

class createTest extends PHPUnit_Framework_TestCase
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

    public function testCreate()
    {
        include 'create.php';
        // Test on empty database
        $expected = '{"result":"0"}';
        $this->assertEquals($expected, create("rey","619","COMS3007",NULL,NULL,NULL,NULL,0,$this->myqli_link));
        $this->assertEquals($expected, create("rey","620","COMS3007","COMS2002",NULL,NULL,NULL,0,$this->myqli_link));
        $this->assertEquals($expected, create("rey","621","COMS3007","COMS2002","COMS1015",NULL,NULL,0,$this->myqli_link));
        $this->assertEquals($expected, create("rey","622","COMS3007","COMS2002","COMS1015","COMS2013",NULL,0,$this->myqli_link));
        $this->assertEquals($expected, create("rey","623","COMS3007","COMS2002","COMS1015","COMS2013","COMS2014",0,$this->myqli_link));
        $expected = '{"result":"You didn\'t send the required values"}';
        $this->assertEquals($expected, create("rey","624",NULL,NULL,NULL,NULL,NULL,0,$this->myqli_link));
    }
}
?>