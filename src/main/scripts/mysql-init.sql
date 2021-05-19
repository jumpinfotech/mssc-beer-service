-- script>set up MySQL database schema with a user account + permissions that will be used by Spring Boot

-- IntelliJ has 2 warnings
-- 1. SQL dialect is not configured. MySQL matches best>add in an SQL dialect by clicking Use MySQL link
-- 2. No data sources are configured to run this SQL and provide advanced code assistance> I'm not going to configure a data source> intelliJ can be set up to watch a database + it does a nice job. 

-- He copies the script and pastes it into his SQL client>he is connected to a natively installed MySQL v8.0.16 using localhost.
-- Run>schema is added>verify the schema privileges>user beer_service only has permissions to interact with beerservice schema.

-- added comments to parts of script that are unclear:-
DROP DATABASE IF EXISTS beerservice;
-- MySQL uses username@ and then you can specify a specific IP address or a % for a wildcard. 
DROP USER IF EXISTS `beer_service`@`%`;
-- we set the character set to utf-8
CREATE DATABASE IF NOT EXISTS beerservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- when you create a new user account it doesn't have any permissions, mysql_native_password is important for MySQL 8, it's not available on MySQL 5
CREATE USER IF NOT EXISTS `beer_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
--  SQL permissions granted to user beer_service, giving it pretty much full control over the beerservice schema.
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `beerservice`.* TO `beer_service`@`%`;
-- deprecated in MySQL v8, needed if using v5 
FLUSH PRIVILEGES;
