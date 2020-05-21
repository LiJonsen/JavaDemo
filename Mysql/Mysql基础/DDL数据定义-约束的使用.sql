USE books;
DROP TABLE IF EXISTS stuinfo;
CREATE TABLE stuinfo(
	id INT PRIMARY KEY, # 主键
	stuName VARCHAR(20) NOT NULL, # 非空
#	gender char(1) check(gender='男' or gender='女'), # 检查约束【Mysql中无效】
	age INT DEFAULT 18, # 默认
	seat INT UNIQUE, # 唯一，类似主键，但可以为空
	majorid INT,
	class_id INT,
	# 添加表级外键约束
	CONSTRAINT fk_stuinfo_major FOREIGN KEY(majorId) REFERENCES major(id)  
);
DROP TABLE IF EXISTS major;
CREATE TABLE major(
	id INT PRIMARY KEY,
	NAME VARCHAR(20) NOT NULL
);




# 修改表时添加约束

# 班级信息

CREATE TABLE classInfo(
	id INT PRIMARY KEY  , # 班级id
	NAME VARCHAR(20) NOT NULL, # 班级名称
	class_type ENUM('A','B','C') NOT NULL DEFAULT 'c' # 班级类型
);


ALTER TABLE classInfo MODIFY NAME VARCHAR(25) DEFAULT 'Mary';

ALTER TABLE classInfo ADD UNIQUE(seat);

# 添加外键
ALTER TABLE stuinfo ADD 
CONSTRAINT fk_stuinfo_classInfo 
FOREIGN KEY(class_id) 
REFERENCES classInfo(id);

# 修改表时添加约束：

# 1. 删除默认约束
ALTER TABLE stuinfo MODIFY COLUMN age INT;

# 2. 删除唯一
ALTER TABLE stuinfo DROP INDEX seat;

# 3. 删除外键
ALTER TABLE stuinfo DROP FOREIGN KEY fk_stuinfo_classInfo;




SHOW INDEX FROM stuinfo;






