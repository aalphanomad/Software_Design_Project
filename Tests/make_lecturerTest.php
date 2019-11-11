<?php

class makeLecturerTest extends PHPUnit_Framework_TestCase
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
        // for our code we need a mysqli lonk and not a PDO object
        $this->myqli_link = mysqli_connect("127.0.0.1", $GLOBALS['db_username'], $GLOBALS['db_password'],"test_db");
        
    }

    public function tearDown()
    {
        //$this->pdo->query("DROP TABLE IF EXISTS USER_INFORMATION");
    }

    public function testMakeLecturer()
    {
        include 'make_lecturer.php';
        $this->assertEquals('true', make_lecturer("1",$this->myqli_link));
        //$this->assertEquals('false', make_lecturer("-996666666666",$this->myqli_link));
        $this->pdo->query("UPDATE USER_INFORMATION SET  ROLE='0' WHERE STUDENT_NUM='1'");
    }
}
?>