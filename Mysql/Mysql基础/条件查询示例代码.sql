# 条件查询

# 1. 查询员工工资>12000的员工信息
SELECT * FROM employees WHERE salary>12000;

# 2. 查询部门编号不等于100号的员工名和部门编号
# 方式一：
SELECT last_name,department_id FROM employees WHERE department_id!=100;
# 方式二：
SELECT last_name,department_id FROM employees WHERE department_id<>100;


# 3. 查询工资在10000-15000之间的员工名、工资、奖金
SELECT last_name,salary,commission_pct FROM employees WHERE salary>=10000&&salary<=15000;

# 4. 查询部门编号在80-90之间，或者工资大于15000的员工信息
SELECT * FROM employees WHERE (department_id>=80 && department_id<=90) || salary > 15000;

# 5. 查询last_name字符里包含a的员工信息
# %：表示通配符
SELECT * FROM employees WHERE last_name LIKE '%a%';

# 6.1 查询last_name字符里第三个字母为s的员工信息
SELECT * FROM employees WHERE last_name LIKE '__s%';

# 6.2 escape关键字：指定下一个字符为转义字符 （类似java中的\）
SELECT * FROM employees WHERE last_name LIKE '_$_%' ESCAPE '$';

# 7. 查询员工编号100-120之间的员工信息（使用between and）
SELECT * FROM employees WHERE employee_id BETWEEN 100 AND 120;

# 8.1 查询没有奖金的员工名、工资、奖金率
SELECT last_name,salary,commission_pct FROM employees WHERE commission_pct IS NULL;
# 8.2 查询有奖金的员工名、工资、奖金率
SELECT last_name,salary,commission_pct FROM employees WHERE commission_pct IS NOT NULL;

# 9. <=> 安全等于（可用于判断null）
SELECT * FROM employees WHERE salary <=> 12000;

SELECT * FROM employees;


SELECT * FROM employees WHERE last_name LIKE "%%" AND commission_pct LIKE "%%";

