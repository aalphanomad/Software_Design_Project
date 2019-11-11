<?php

class generic_selectTest extends PHPUnit_Framework_TestCase
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
        $this->pdo->query(" 	CREATE TABLE IF NOT EXISTS `USER_INFORMATION` (
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

        /*$this->pdo->query("INSERT INTO `USER_INFORMATION` (
            `NAME` ,
            `STUDENT_NUM` ,
            `EMAIL_ADDRESS` ,
            `USER_PASSWORD` ,
            `ROLE` ,
            `COURSE_1` ,
            `COURSE_2` ,
            `COURSE_3` ,
            `COURSE_4` ,
            `COURSE_5` ,
            `TRANSCRIPT`
            )
            VALUES (
            'Tutor', '1', '1@students.wits.ac.za', 'test', '0', 'COMS3007', 'COMS2015', 'COMS1017', NULL , NULL , NULL
            );
            ");*/

        // for our code we need a mysqli lonk and not a PDO object
        $this->myqli_link = mysqli_connect("127.0.0.1", $GLOBALS['db_username'], $GLOBALS['db_password'],"test_db");
        
    }

    public function tearDown()
    {
        //$this->pdo->query("DROP TABLE USER_INFORMATION");
    }

    public function testGeneric_select()
    {
        include 'generic_select.php';
        $this->assertEquals('[["Tutor","1","1@students.wits.ac.za","test","0","COMS3007","COMS2015","COMS1017",null,null,null]]', generic_select("USER_INFORMATION","*","STUDENT_NUM","1",$this->myqli_link));
    }
}
?>