-- INSERT into EMPLOYEES(id,name)
-- VALUES (1, 'Javid M');

-- hibernate data can be created this way as well.
-- in this case uncomment spring.sql.init.mode=always
INSERT into student(first_name, last_name, email)
VALUES ('Cucu', 'Mammadli', 'cucu@bclc.com');

-- So we eaither create table schema via schema..sql file or via pojos
-- data.sql file is inserting data inside of table