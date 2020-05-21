/*
表的管理练习
*/
CREATE DATABASE IF NOT EXISTS books;

# 1. 创建表dept1
USE books;
CREATE TABLE IF NOT EXISTS dept1(
	id INT(7),
	NAME VARCHAR(25)
);

# 2. 将表departments中的数据插入到新表dept2中
CREATE TABLE dept2 SELECT * FROM myemployees.`departments`;

# 3. 创建表emp5
CREATE TABLE IF NOT EXISTS emp5(
	id INT(7),
	first_name VARCHAR(25),
	last_name VARCHAR(25),
	dept_id INT(7)
);

# 4. 将Last_name的长度增加到50
ALTER TABLE emp5 MODIFY COLUMN last_name VARCHAR(50);

# 5. 根据表employees创建employees2
CREATE TABLE employees2 LIKE myemployees.`employees`;

# 6. 删除表emp5
DROP TABLE emp5;

# 7. 将表employees2重命名为emp5
ALTER TABLE employees2 RENAME TO emp5;

# 8. 在表emp5中添加新列test_column
ALTER TABLE emp5 ADD COLUMN test_column VARCHAR(10);

# 9. 直接删除表emp5中的test_column列
ALTER TABLE emp5 DROP COLUMN test_column;

DESC emp5;
