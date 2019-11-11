sudo mysql -e "DROP DATABASE test_db;" -uroot ;
sudo mysql -e "create database IF NOT EXISTS test_db;" -uroot;
sudo mysql -u root test_db < ./Tests/d1601745.sql;

./vendor/bin/phpunit --configuration phpunit_mysql.xml --coverage-text