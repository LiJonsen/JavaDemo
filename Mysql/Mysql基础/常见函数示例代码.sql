# 函数

SELECT TRIM("  a b c ");

SELECT LPAD("Josne",10,"*");

SELECT REPLACE("jojosen","jo","po");

# 查询有奖金的员工姓名、奖金率、入职日期
SELECT last_name,commission_pct,DATE_FORMAT(hiredate,"%Y年%m月%d日") AS 入职日期 FROM employees WHERE commission_pct IS NOT NULL;



# 流程控制函数

/* 
case案例：查询员工的工资 
部门编号=30,显示工资为1.1倍，
部门编号=40,显示工资为1.5倍，
部门编号=50,显示工资为2倍，
其他工资不变
*/

SELECT 
salary 原始工资,
last_name 名字,
CASE department_id
WHEN 30 THEN salary * 1.1
WHEN 40 THEN salary * 1.5
WHEN 50 THEN salary * 2
ELSE salary
END AS 新工资
 FROM employees;
 
 
 
 
 SELECT last_name FROM employees ORDER BY last_name;
 
 
 SELECT SUM(DISTINCT salary) 去重总薪资, SUM(salary) 正常总薪资 FROM employees;

# 获取有奖金率的员工个数
SELECT COUNT(commission_pct) 有奖金率员工总数 FROM employees;

# 获取employees数据总数
SELECT COUNT(*) FROM employees;

SELECT COUNT(1) FROM employees;



/*
1. 查询公司员工工资的最大值、最小值、平均值、总和
2. 查询员工表中的最大入职时间和最小入职时间的相差天数
3. 查询部门编号为90的员工个数
*/

SELECT MAX(salary) 最大值,MIN(salary) 最小值,AVG(salary) 平均值,SUM(salary) 总和 FROM employees;

SELECT 
MAX(hiredate) 最新一批入职时间,
MIN(hiredate) 第一批入职时间,
DATEDIFF(MAX(hiredate),MIN(hiredate)) 相差天数
FROM employees;


SELECT COUNT(department_id=100) FROM employees;


SELECT COUNT(*) "部门编号=90总数" FROM employees WHERE department_id=90;



