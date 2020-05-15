/*
连接查询
含义：又称为多表查询，当查询的字段来自于多个表，就会用到连接查询
*/

USE myemployees;

/*
 为表起别名
 作用： ① 提高代码简介度  ② 区分多个重名的字段
 注意点：如果为表起了别名，查询的字段就不能使用原来的表名进行限定；
*/

SELECT e.last_name,e.job_id,j.job_title 
FROM employees AS e,jobs AS j
WHERE e.`job_id`=j.`job_id`;

/*
sql 1999

语法：
	select 查询语句 
	from 表1 别名  
	【连接类型(inner/left outer等)】 join 表2 别名 
	on 连接条件 
	【where 筛选条件】
	...
*/

/* 
1. 等值连接
*/
# 案例1： 查询员工名、部门名

SELECT last_name 员工名,department_name 
FROM employees e
INNER JOIN departments d
ON e.department_id=d.`department_id`;



# 案例2：查询员工名、部门名、工种名，并按部门名排序（三表查询）
SELECT e.last_name 员工名, d.department_name 部门名,j.job_title 工种名
FROM employees e
INNER JOIN departments d
ON e.`department_id`=d.`department_id`
INNER JOIN jobs j
ON j.`job_id` = e.`job_id`
ORDER BY d.department_name;

/* 
2. 非等值连接
案例：查询员工的工资级别(按级别升序)
*/
SELECT last_name 名字,salary 工资,grade_level 工资级别 
FROM employees e
INNER JOIN job_grades g
ON e.`salary` BETWEEN g.`lowest_sal` AND g.`highest_sal`
ORDER BY grade_level;


/* 
3. 自连接
# 案例：查询员工名字、该员工上级名字
*/
SELECT e.last_name 员工名字,m.last_name 上级名字
FROM employees e
JOIN employees m
ON e.`manager_id`=m.`employee_id`;

/* 
4. 左外连接 & 右外连接
*/

# 练习1：查询哪个城市(主)没有部门(从)
SELECT d.department_name 部门名称,l.*
FROM locations l
LEFT OUTER JOIN departments d
ON d.`location_id` = l.`location_id`
WHERE d.`department_id` IS NULL;

# 练习2：查询部门名为SAL或IT的员工信息
SELECT d.department_name 部门名称,e.*
FROM employees e
RIGHT OUTER JOIN departments d
ON e.`department_id`=d.`department_id`
WHERE d.`department_name` IN('SAL','IT');



