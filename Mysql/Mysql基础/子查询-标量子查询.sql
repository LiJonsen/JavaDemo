/*
1. 标量子查询
特点：标量子查询（单行子查询）：结果集只有一行一列
*/

# 案例1：查询员工工资>员工id=110的所有员工信息；
SELECT * FROM employees 
WHERE salary > (SELECT salary FROM employees WHERE employee_id=110);

# 案例2：返回job_id与141号员工相同，salary比143号员工多的员工姓名、job_id和工资；
# ① 获取141号员工job_id
SELECT job_id FROM employees WHERE employee_id=141;
# ② 获取143号salary
SELECT salary FROM employees WHERE employee_id=143;
# ③ 获取满足条件1+2的员工姓名、job_id和工资
SELECT last_name,job_id,salary FROM employees 
WHERE job_id = (
  SELECT job_id FROM employees WHERE employee_id=141
) AND salary > (
  SELECT salary FROM employees WHERE employee_id=143
);

# 案例3：返回工资最低的员工last_name,salary,job_id；

SELECT last_name,salary,job_id FROM employees WHERE salary=(
  SELECT MIN(salary) FROM employees
);


# 案例4：查询最低工资大于50号部门最低工资的部门id和其最低工资；

# 4.1 查询所有部门最低工资
SELECT MIN(salary),department_id FROM employees
GROUP BY department_id; 

# 4.2 查询50号部门最低工资
SELECT MIN(salary) FROM employees WHERE department_id = 50;

# 4.3 显示所有【部门最低工资>50号部门最低工资】 的部门id和最低工资

SELECT MIN(salary),department_id FROM employees
GROUP BY department_id HAVING MIN(salary) > (
	SELECT MIN(salary) FROM employees WHERE department_id = 50
); 


/*
二、放在select后面的子查询（仅支持标量子查询）
案例：查询每个部门的员工个数，并显示部门信息
*/

SELECT d.*,
(SELECT COUNT(*) FROM employees e 
WHERE e.department_id = d.`department_id`) 员工个数 
FROM departments d;


