USE myemployees;

# 1. 查询和Zlotkey相同部门的员工姓名和工资；(标量子查询)
# 1.1 获取last_name=Zlotkey的部门id
SELECT department_id FROM employees WHERE last_name='Zlotkey';
# 1.2 根据1.1子查询返回的deparment_id，返回与该id相同的员工姓名、工资
SELECT last_name 姓名,salary 工资,department_id 部门ID FROM employees 
WHERE department_id = (
	SELECT department_id FROM employees WHERE last_name='Zlotkey'
);


# 2. 查询工资比公司平均工资高的员工号、姓名和工资；
# 2.1 获取公司平均工资
SELECT AVG(salary) FROM employees;
# 2.2 获取比2.1子查询平均工资高的员工号、姓名、工资
SELECT department_id 工号,last_name 姓名,salary 工资 FROM employees
WHERE salary > (
	SELECT AVG(salary) FROM employees
);

# 3. 查询各部门中工资比本部门平均工资高的员工。【显示员工号、姓名和工资】
# 3.1 获取每个部门的平均工资
SELECT AVG(salary) 
FROM employees GROUP BY department_id 
;
# 3.2 根据3.1子查询，获取每个部门中比本部门平均工资高的员工信息
SELECT e.*,avg_dep.avg_salary 平均工资 FROM (
	SELECT AVG(salary) avg_salary,department_id avg_dep_id
	FROM employees GROUP BY department_id 
) avg_dep 
INNER JOIN employees e
ON e.`department_id` = avg_dep.avg_dep_id
WHERE e.`salary`>avg_dep.avg_salary;

# 4. 查询姓名中包含字母u，并且在相同部门的员工。【显示员工号、姓名】
# 4.1 查询姓名包含字母u的员工
SELECT department_id,last_name FROM employees WHERE last_name LIKE '%u%';


# 5. 查询在部门表中location_id=1700的部门工作的员工信息。【显示员工号、姓名】
# 5.1 根据location_id获取deparment_id
SELECT department_id FROM departments WHERE location_id=1700;
# 5.2 根据deparment_id获取员工信息
SELECT employee_id 员工号,last_name 姓名 FROM employees
WHERE department_id IN(
	SELECT department_id FROM departments WHERE location_id=1700
);
	
# 6. 查询管理者是K_ing的员工姓名和工资
# 6.1 查询King的employee_id
SELECT employee_id FROM employees WHERE last_name='K_ing';
# 6.2 查询manage_id=6.1子查询的员工信息
SELECT * FROM employees WHERE manager_id IN (
	SELECT employee_id FROM employees WHERE last_name='K_ing'
);

# 7. 查询工资最高的员工姓名，要求first_name和last_name显示为一列。【列名为：姓.名】
SELECT CONCAT(first_name,last_name) '姓.名' FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);