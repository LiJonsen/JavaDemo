# 分组查询

# 1. 查询每个工种的最高工资

SELECT MAX(salary) 工种最高工资,job_id FROM employees GROUP BY job_id ORDER BY 工种最高工资 DESC;

# 2. 查询每个位置上的部门个数
SELECT COUNT(*) 个数,location_id FROM departments GROUP BY location_id;


/*
添加筛选条件
案例1：查询邮箱中包含a字符(where like)的每个部门（group by）的平均工资(select avg)

案例2：查询有奖金(where)的每个领导(group)手下员工的最高工资(select max)
*/

# 案例1
SELECT AVG(salary) 部门平均工资,department_id FROM employees WHERE email LIKE '%a%' GROUP BY department_id;

# 案例2
SELECT MAX(salary) 最高工资,manager_id FROM employees WHERE commission_pct IS NOT NULL GROUP BY manager_id;


/*
添加分组后的筛选
案例1：查询哪个部门员工数量>2

案例2：查询每个工种有奖金的员工，显示最高工资>12000的工种编号(job_id)和最高工资

案例3：查询领导编号>102的每个领导手下的最低工资>5000的领导编号是哪个？
*/

# 案例1SQL
SELECT COUNT(*) 员工数量,department_id FROM employees GROUP BY department_id HAVING COUNT(*)>2;

# 案例2SQL
SELECT MAX(salary) 最高工资,job_id 工种编号 FROM employees WHERE commission_pct IS NOT NULL GROUP BY job_id HAVING MAX(salary)>12000;

# 案例3SQL
SELECT MIN(salary),manager_id FROM employees WHERE manager_id>102 GROUP BY manager_id HAVING MIN(salary)>5000;


/*
按表达式或函数分组
案例： 按员工姓名的长度分组，查询每一组员工的个数，筛选员工个数>5的有哪些？

*/

SELECT COUNT(*) 员工个数,LENGTH(last_name) 名字长度 FROM employees GROUP BY 名字长度 HAVING 员工个数>5;

/*
按多个字段排序
案例：查询每个部门每个工种的平均工资
*/

SELECT AVG(salary) 平均工资,department_id 部门ID, job_id 工种ID FROM employees GROUP BY department_id,job_id;


/*
添加排序
案例：查询每个部门每个工种的平均工资，并且按平均工资的高低排序
*/
SELECT AVG(salary) 平均工资,department_id 部门ID, job_id 工种ID FROM employees GROUP BY department_id,job_id ORDER BY AVG(salary) DESC;


/*
练习题
*/

# 1. 查询各job_id的员工工资的最大值、最小值、平均值、总和，并按job_id升序；
SELECT MAX(salary) 最大值,MIN(salary) 最小值,AVG(salary) 平均值,SUM(salary) 总和,job_id FROM employees GROUP BY job_id;

# 2. 查询员工最高工资和最低工资的差距；
SELECT MAX(salary) - MIN(salary) 差距 FROM employees;

# 3. 查询各个管理者手下员工的最低工资，其中最低工资不能低于6000，没有管理者的员工不计算在内；
SELECT MIN(salary) 最低工资, manager_id FROM employees WHERE manager_id IS NOT NULL GROUP BY manager_id HAVING MIN(salary)>=6000;

# 4. 查询所有部门的编号，员工数量和工资平均值，并按平均工资降序；
SELECT COUNT(*) 员工数量,AVG(salary) 工资平均值,department_id FROM employees GROUP BY department_id ORDER BY AVG(salary) DESC;

# 5. 选择具有各个job_id的员工人数；
SELECT COUNT(*) 人数,job_id FROM employees GROUP BY job_id;

