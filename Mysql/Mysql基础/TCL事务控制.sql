/*
创建事务

隐式事务：

显式事务：
*/

# 显示自动提交功能状态
SHOW VARIABLES LIKE 'autocommit';
# 显示引擎
SHOW ENGINES;


/*
savepoint-设置保存点
必须搭配rollback回滚使用
*/ 

SET autocommit=0;
START TRANSACTION;
DELETE FROM dept2 WHERE	department_id=10;
SAVEPOINT s1;
DELETE FROM dept2 WHERE department_id=20;
# 回滚到s1处
ROLLBACK TO s1; 

