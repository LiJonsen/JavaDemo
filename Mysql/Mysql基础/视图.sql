/*
视图
*/

USE myemployees;

# 使用视图获取last_name以K开头，并且在相同部门的员工信息

CREATE VIEW v1 AS
SELECT e.last_name,e.department_id FROM employees e
INNER JOIN departments d
ON e.`department_id`=d.`department_id`;

# 使用v1虚拟表查询
SELECT * FROM v1 WHERE last_name LIKE 'K%';



# 案例1：创建视图emp_v1,要求查询电话号码以'011'开头的员工姓名、工资、邮箱
DROP VIEW IF EXISTS emp_v1;
CREATE VIEW emp_v1 AS
SELECT last_name 姓名,salary 工资,email 邮箱,phone_number
FROM employees WHERE phone_number LIKE '011%';

SELECT * FROM emp_v1;

# 案例2：创建视图emp_v2,要求查询部门的最高工资高于12000的部门信息
DROP VIEW IF EXISTS emp_v2;
CREATE VIEW emp_v2 AS
SELECT d.*,e.salary FROM departments d
INNER JOIN employees e
ON e.`department_id`=d.`department_id`
WHERE e.salary>12000;

SELECT * FROM emp_v2;



/*
视图的更新
*/





