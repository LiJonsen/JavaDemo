/*
1. 创建数据库
IN NOT EXISTS - 判断是否不存在
IN EXISTS - 判断是否存在
*/

CREATE DATABASE IF NOT EXISTS books;


# 2. 库的修改(不推荐)
RENAME DATABASE books TO new_books;

# 更改库的字符集
ALTER DATABASE books CHARACTER SET gbk;

# 3. 库的删除
DROP DATABASE IF EXISTS books;
