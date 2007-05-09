REVOKE ALL PRIVILEGES ON progress.* FROM 'buildapp'@'localhost', 'buildadmin'@'localhost';
DELETE FROM mysql.user WHERE user='buildapp' OR user='buildadmin';
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS progress;
 
