# 1.基础查询

USE myemployees;

SELECT last_name,salary FROM employees;

# 别名

SELECT last_name 姓,first_name 名 FROM employees;

# 去重

SELECT DISTINCT department_id FROM employees;

# 多个字段连接成一个字段
SELECT CONCAT(last_name,first_name) 姓名 FROM employees;


# 判断=null，并设置默认值
SELECT IFNULL(commission_pct,0) AS 奖金率,commission_pct FROM employees;