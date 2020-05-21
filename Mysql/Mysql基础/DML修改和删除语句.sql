/*
修改语句

*/

USE girls;
/* 
修改多表的记录案例：

语法：
update 表1 别名
inner join 表2 别名
on 连接条件
set 列=新值,列=新值
where 查询条件
*/

# 案例：修改张无忌的女朋友手机号=114
UPDATE boys bo
INNER JOIN beauty bea
ON bea.`boyfriend_id`=bo.`id`
SET bea.phone='199'
WHERE bo.`boyName`='张无忌';


/*
删除语句
*/
# 1. 删除单表的记录
SELECT * FROM beauty;
# 案例：删除beauty表中phone以9结尾的记录
DELETE FROM beauty WHERE phone LIKE '%9';

# 2. 多表的删除

# 案例：删除所有张无忌的女朋友信息
DELETE bea FROM boys bo
INNER JOIN beauty bea
ON bo.id = bea.boyfriend_id
WHERE bo.boyName='张无忌';

# 案例：删除黄晓明以及所有女朋友信息
DELETE bo,bea
FROM beauty bea
INNER JOIN boys bo
ON bea.boyfriend_id=bo.id
WHERE bo.boyName='黄晓明';

# 3. truncate删除方式

TRUNCATE TABLE admin;



