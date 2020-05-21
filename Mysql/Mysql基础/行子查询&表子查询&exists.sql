/*
一、子查询-行子查询（一行多列或多行多列）
*/
USE myemployees;
# 案例：查询员工编号最小，并且工资最高的员工信息

SELECT * FROM employees 
WHERE (employee_id,salary) = (
	SELECT MIN(employee_id), MAX(salary) FROM employees
);


/*
二、子查询-表子查询（多行多列）
在from后面使用子查询（支持表子查询）
特点：将子查询结果充当一张表，要求必须起别名
*/

# 案例：查询每个部门的平均工资以及该工资的等级；

# ①-获取每个部门的平均工资

SELECT AVG(salary),department_id 
FROM employees GROUP BY department_id; 

# ②-根据平均工资，给出对应工资等级(使用连接查询)
SELECT ag_dep.*,j.`grade_level` 工资等级
FROM (
	SELECT AVG(salary) avg_salary,department_id 
	FROM employees GROUP BY department_id
) ag_dep
INNER JOIN job_grades j
ON ag_dep.avg_salary BETWEEN j.`lowest_sal` AND j.`highest_sal`;


/*
三、exists后面（相关子查询）：表子查询
exists：存在
语法：exists(查询语句);
返回结果：1或0
*/

# 案例：查询所有员工的部门名
SELECT department_name FROM departments d
WHERE EXISTS(
	SELECT * FROM employees e WHERE d.`department_id`=e.`department_id`
);
