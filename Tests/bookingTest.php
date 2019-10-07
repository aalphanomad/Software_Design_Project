<?php

class bookingTest extends PHPUnit_Framework_TestCase
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
        $this->pdo->query(" CREATE TABLE IF NOT EXISTS `BOOKINGS` (
            `ID` int(11) NOT NULL AUTO_INCREMENT,
            `NAME` varchar(20) NOT NULL,
            `STUDENT_NUM` varchar(10) NOT NULL,
            `COURSE` varchar(10) DEFAULT NULL,
            `DATE` varchar(20) NOT NULL,
            `VENUE` varchar(50) DEFAULT NULL,
            `START_TIME` time DEFAULT NULL,
            `END_TIME` time NOT NULL,
            `ACTIVITY` varchar(20) NOT NULL,
            `VALID` tinyint(1) NOT NULL,
            PRIMARY KEY (`ID`),
            KEY `ID` (`ID`)
          ) ENGINE=InnoDB DEFAULT CHARSET=latin1");

        // for our code we need a mysqli lonk and not a PDO object
        $this->myqli_link = mysqli_connect("127.0.0.1", $GLOBALS['db_username'], $GLOBALS['db_password'],"test_db");
        
    }

    public function tearDown()
    {
        $this->pdo->query("DROP TABLE BOOKINGS");
    }

    public function testBooking()
    {
        include 'booking.php';
        // Test on empty database
        $expected = '{"result":"0"}';
        $this->assertEquals($expected, booking("Tutor","1","25 Mar 2019","COMS2002","fjff","0","09:15:00","12:15:00","Tutoring",$this->myqli_link));

        //TODO import actual database for more robust testing
    }
}
?>