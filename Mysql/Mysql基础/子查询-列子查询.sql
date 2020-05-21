/*
子查询-列子查询(多行子查询)
搭配多行操作符使用：in any/some all
特点：多行一列
*/

USE myemployees;

# 案例1：返回location_id时1400或1700的部门中的所有员工姓名；

# ①-获取location_id=1400、1700的部门ID
SELECT DISTINCT department_id FROM departments
WHERE location_id IN(1400,1700);

# ②-根据条件①子查询，返回员工姓名
SELECT last_name FROM employees
WHERE department_id IN(
	SELECT DISTINCT department_id FROM departments
	WHERE location_id IN(1400,1700)
);

/*案例2：
获取其他工种中job_id='IT_PROG'的所有工资，
返回比该工种任意一个工资低的员工姓名、工号、job_id和salary
*/

# ①-获取job_id='IT_PROG'的salary
SELECT DISTINCT job_id,salary FROM employees
WHERE job_id='IT_PROG';

# ②-返回比条件①工资任意一个低的员工姓名、job_id 工号和salary
# 方式一：
SELECT last_name 姓名,job_id 工号,salary 工资 FROM employees
WHERE salary < ANY(
	SELECT DISTINCT salary FROM employees
	WHERE job_id='IT_PROG'
);
# 方式二：
SELECT last_name 姓名,job_id 工号,salary 工资 FROM employees
WHERE salary < (
	SELECT DISTINCT MAX(salary) FROM employees
	WHERE job_id='IT_PROG'
);

/*
案例3：
获取其他工种中job_id='IT_PROG'的所有工资，
返回比该工种<=最低工资的员工姓名、工号、job_id和salary
*/
# 方式一：
SELECT last_name 姓名,job_id 工号,salary 工资 FROM employees
WHERE salary < ALL(
	SELECT DISTINCT salary FROM employees
	WHERE job_id='IT_PROG'
);
# 方式二：
SELECT last_name 姓名,job_id 工号,salary 工资 FROM employees
WHERE salary < (
	SELECT DISTINCT MIN(salary) FROM employees
	WHERE job_id='IT_PROG'
);