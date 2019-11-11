<?php

class getPeopleTest extends PHPUnit_Framework_TestCase
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

    public function testGetPeople()
    {
        include 'get_people.php';
        $this->assertEquals('{"tutors":["Zack Hemsey","Juno Reactor"],"lecturers":["Ryan Wood","Mayur","Admin"]}', get_people("COMS3003",$this->myqli_link));
    }
}
?>