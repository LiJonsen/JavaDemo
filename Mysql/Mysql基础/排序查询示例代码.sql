# 排序查询

# 1. 查询部门编号>=90的员工信息，并且按照入职日期进行降序排序
SELECT * FROM employees WHERE department_id>=90 ORDER BY hiredate DESC;

# 2.1 按照年薪的高低，限时员工的信息和年薪（按表达式降序排序）
SELECT *,salary*12*(1+IFNULL(commission_pct,0)) AS 年薪 FROM employees ORDER BY salary * 12*(1+IFNULL(commission_pct,0)) DESC;
# 2.2 按照年薪的高低，限时员工的信息和年薪（按别名升序排序）
SELECT *,salary*12*(1+IFNULL(commission_pct,0)) AS 年薪 FROM employees ORDER BY 年薪;

# 3. 按姓名的长度排序显示员工的姓名和工资【按函数排序】
SELECT last_name,salary,LENGTH(last_name) AS 名字长度 FROM employees ORDER BY LENGTH(last_name) DESC;  

# 4. 查询员工信息，要求先按工资升序，工资一样再按员工编号降序【多个字段排序】
SELECT * FROM employees ORDER BY salary,employee_id DESC;