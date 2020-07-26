# MySQL进阶

```
DDL
	操作数据库
		create database 
		alter database .. character set
		drop database
		show databases
	操作表
		create table
		drop table
		show tables
		desc 表名;
		
		alter table 
			add: 添加字段
			modify: 修改字段类型
			change: 修改字段名(也可以修改类型)
			drop: 删除字段
			
			
DML
	插入数据: 
		insert into 表名 (字段1, 字段2 ... ) values (值1, 值2 ...)
		insert into 表名 values (所有字段值);
	修改数据:
		update 表名 set 字段名 = 字段值 where 条件;
	删除数据:
		delete from 表名 where 条件;

DQL
	select
		*, 指定字段, ifnull(字段, 值), [as], distinct, +,-,*,/
		
		>, <, >=, <=, =, <>
		between 小值 and 大值
		and, or, not()
		in()
		is null, is not null
		like: _: 单个任意字符 %: 任意字符的任意个
		
		
		排序:  order by asc|desc
		聚合:  count(*), sum(字段), max(字段), min(字段), avg(字段)
		分组:  group by 分组字段
				分组会和聚合函数一起使用, 以什么分组就将这个字段查询出来
			   分组后的过滤: having 过滤条件
		分页: limit 索引 长度
		
		
		
		
		主键约束 primary key auto_increment
		非空约束 not null
		唯一约束 unique
		默认值约束 default 默认值
```



## 一. 外键约束【理解】

### 1. 单表的弊端&为什么要拆表

- 有如下商品表, 观察操作的时候是否会有问题

| 商品编号pid | 商品名称pname | 商品价格price | 商品库存pcount | 商品种类category |
| ----------- | ------------- | ------------- | -------------- | ---------------- |
| 1           | 华为P40       | 5688          | 500            | 手机数码         |
| 2           | 小米12        | 3688          | 800            | 手机数码         |
| 3           | 格力空调      | 2399          | 300            | 大型家电         |
| 4           | 创维电视      | 2099          | 200            | 大型家电         |

- 单表的问题
  - 只想增加一个商品种类, 而不像增加商品
  - 想修改商品种类, 要修改所有该类别的商品
  - 想删除商品种类, 要删除所有该类别的商品

**拆表**

| 商品编号pid | 商品名称pname | 商品价格price | 商品库存pcount | 商品种类cid(外键) |
| ----------- | ------------- | ------------- | -------------- | ----------------- |
| 1           | 华为P40       | 5688          | 500            | 1                 |
| 2           | 小米12        | 3688          | 800            | 1                 |
| 3           | 格力空调      | 2399          | 300            | 2                 |
| 4           | 创维电视      | 2099          | 200            | 2                 |

- 分类表(category)

| cid(主键) | cname    |
| --------- | -------- |
| 1         | 手机数码 |
| 2         | 大型家电 |
| 3         | 鞋靴箱包 |

### 2. 拆表之后的问题

- 解决了一个问题: 添加问题
  - 如果只想添加分类, 不想添加商品

**仍然存在的问题**

- 修改商品表中的分类信息, 但是分类表中没有对应的信息, 那么这条数据就变成脏数据
- 修改分类表中的id, 商品表中之前对应的数据, 找不到对应分类, 也变成了脏数据

<font color = 'red'>**解决方案**</font>

- 问题的原因在于: 两张表之间是有联系的, 但是我们没有定义这种联系
- 解决方案:
  - 修改商品表中的分类信息, 修改到的值是分类表中不存在的, 如果不存在就不允许修改
  - 修改分类表中的id, 如果商品表中有对应id的信息, 就不允许修改

### 3. 外键约束

#### 3.1主表(parent)和从表(child)

- 当表①中的数据是来源于另一张表②, 那我们就可以将表①称之为从表, 表②称之为主表
  - 能够单独存在的表就是主表
  - 不能单独存在(有一些数据不是到是什么)就是从表

#### 3.2 外键和外键约束

- 外键: 从表中的某个字段是来源于主表的, 那么这个字段就称之为外键

- 外键约束: 设置成外键的字段, 一定是来源于主表的主键, 让这两个字段产生关联

- 添加外键约束的格式:

  ```mysql
  constraint 外键约束名 foreign key(外键字段) reference 主表名(主键字段)
  ```


- 删除外键

  ```mysq
  alter table ... drop foreign key 外键约束名;
  ```

- 添加外键

  ```mysql
  alter table ... add constraint 外键约束名 foreign key(外键字段) reference 主表名(主键字段);
  ```

  

**数据准备**

```mysql
-- 创建两张表(一定先创建主表, 然后创建从表)
-- 创建分类表
create table category (
    cid int primary key auto_increment,
    cname varchar(20)
);

-- 创建商品表
create table product (
	pid int primary key auto_increment,
    pname varchar(20),
    price double,
    pcount int,
    cid int,
    -- 外键约束
    constraint fk_pro_cid_cat_cid foreign key(cid) references category(cid)
);



insert into catagory (cname) values ("手机数码"), ("大型家电");

insert into product (pname, price, pcount, cid) values ('华为P40', 5688, 800, 1);
insert into product (pname, price, pcount, cid) values ('小米12', 3688, 500, 1);
insert into product (pname, price, pcount, cid) values ('格力空调', 2399, 200, 2);
insert into product (pname, price, pcount, cid) values ('创维电视', 2099, 300, 2);
```



> 外键的级联操作
>
> ​	修改主表中的外键字段, 从表跟着改变
>
> - 直接将这两个代码, 添加到外键约束的后面
>
> `on update cascade`
>
> `on delete cascade`
>
> `-- 外键约束, 添加级联操作
>     constraint fk_pro_cid_cat_cid foreign key(cid) references category(cid) on delete cascade on update cascade`



## 二. 表关系【理解】

### 1. 一对一

- 不常用, 因为一对一的两张表可以合并成一张表.
- 以下情况可以拆分成2张表
  - 用户信息和账户信息

![image-20200720123227810](img/image-20200720123227810.png)

### 2. 一对多

- 建表原则: 在多的一方创建外键, 指向一的一方的主键(从表创建外键指向主表的主键)
- 例如: 分类和商品, 用户和订单

![image-20200720123254990](img/image-20200720123254990.png)

### 3. 多对多

- 建表原则: 创建一张第三方表, 这张表至少有两个字段, 分别作为外键, 指向另外两张表的主键
- 例如: 老师和学生, 学生和课程

![image-20200720123311861](img/image-20200720123311861.png)

> 键笔记中的表格《多表间的建表原则》

## 三. 多表查询【重点】

**数据准备**

```mysql
# 创建部门表
CREATE TABLE dept (
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(20)
);
INSERT INTO dept (NAME) VALUES ('开发部'),('市场部'),('财务部');
# 创建员工表
CREATE TABLE emp (
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(10),
gender CHAR(1), # 性别
salary DOUBLE, # 工资
join_date DATE, # 入职日期
dept_id INT
);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('孙悟空','男',7200,'2013-02-24',1);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('猪八戒','男',3600,'2010-12-02',2);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('唐僧','男',9000,'2008-08-08',2);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('白骨精','女',5000,'2015-10-07',3);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('蜘蛛精','女',4500,'2011-03-14',1);
```

> **多表查询的特点**
>
> 1. 在建表的时候, 会有拆表情况的存在, 但是多张表中的数据是有关系的, 所以查询数据的时候需要从这多张表中查询出有关联的数据
> 2. 多表查询的关键在于: 把多表关联起来, 关联的时候一定要有条件

### 1. 笛卡尔积

- 查询每个部门有哪些人

  - 由于部门和员工信息存在两张表中, 所以需要从两张表中查询

  ```mysql
  select * from emp, dept;
  ```

  - 清除笛卡尔积(添加连接条件)

  ```mysql
  select * from emp, dept where emp.dept_id = dept.id;
  ```

### 2. 内连接

- 隐式内连接

  **格式**

  ```mysql
  select * from 表1, 表2 where 条件
  ```

- 显式内连接

  **格式**

  ```mysql
  select * from 表1 [inner] join 表2 on 连接条件 where 其他过滤条件
  ```

- 练习

```mysql
-- 查询工资大于5000的员工姓名和所属部门
-- 1. 确定要查询哪些表
select * from emp, dept;

-- 2. 确定连接条件
-- 隐式内连接
select * from emp e, dept d where e.dept_id = d.id;
-- 显式内连接
select * from emp e inner join dept d on e.dept_id = d.id;

-- 3. 确定查询字段和过滤条件
-- 隐式内连接
select 
	e.`NAME`, d.`NAME` 
from 
	emp e, dept d 
where 
	e.dept_id = d.id 
	and e.salary > 5000;
	
-- 显式内连接
select e.`NAME`, d.`NAME` from emp e inner join dept d on e.dept_id = d.id where e.salary > 5000;

```

```mysql
-- 查询唐僧的信息，显示员工id，姓名，性别，工资和所在的部门名称
-- 1. 确定表
-- 2. 连接条件
-- 3. 查询字段和过滤条件
-- 隐式内连接
select e.id, e.name, e.gender, e.salary, d.name from emp e, dept d where e.dept_id = d.id and e.name = '唐僧';
-- 显式内连接
select e.id, e.name, e.gender, e.salary, d.name from emp e inner join dept d on e.dept_id = d.id where e.name =  '唐僧';
```



> 隐式内链接和显式内连接查询的结果是一样, 只是格式不同

### 3. 外连接

- **查询效果**: 查询一张表的全部数据, 以及另一张表的关联数据

#### 3.1 左外连接

- 查询左表的所有数据, 以及右表相关联的数据

- **格式: ** 

  ```mysql
  select ... from 左表 left [outer] join 右表 on 关联条件 where 其他过滤条件
  ```

> 谁写在左边(前面)就是左表, 谁写在右边(后面)就是右表

**数据准备**

```mysql
-- 在部门表中添加一个销售部
INSERT INTO dept (NAME) VALUES ('销售部');
```

```mysql
-- 左外连接
-- select * from 左表 left outer join 右表 on 连接条件
select * from dept d left outer join emp e on e.dept_id = d.id;
```



#### 3.2 右外连接

- 查询右表的所有数据, 以及左表相关联的数据

- **格式**

  ```mysql
  select ... from 左表 right [outer] join 右表 on 关联条件 where 其他过滤条件
  ```

**数据准备**

```mysql
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('沙僧','男',6666,'2013-02-24',NULL);
```

```mysql
-- 右外连接
-- select * from 左表 right outer join 右表 on 连接条件
select * from emp e right outer join  dept d on e.dept_id = d.id;
```



> 左外连接和右外连接查询出的结果是一样的(改变表的顺序), 所以只需要掌握一种即可

### 4. 子查询

- 子查询(双层嵌套查询), 没有固定的语法, 只是一种查询技巧
- 特点
  1. 子查询必须放在()中
  2. 子查询的结果有3种情况
     - 子查询的结果是一个值
     - 子查询的结果是单列多行
     - 子查询的结果是多列多行(表)
  3. 子查询的结果是单列, 在where后面作为条件(一个值: >, <, =;   单列多行: in())
  4. 子查询的结果是多列, 在from后面作为表

#### 4.1 子查询的结果是一个值

- 查询工资最高的员工是谁

```mysql
-- 1. 查询出最高工资是多少(子查询)
select max(salary) from emp; -- 把它当成一个值- 最高工资(9000)
-- 2. 查询是最高工资的员工
select * from emp where salary = (select max(salary) from emp);
```

- 查询工资小于平均工资的员工有哪些

```mysql
-- 1. 查询出平均工资
select avg(salary) from emp;
-- 2. 查询出小于平均工资的员工
select * from emp where salary < (select avg(salary) from emp);
```



#### 4.2 子查询的结果是单列多行

- 查询工资大于5000的员工, 来自哪些部门

```mysql
-- 查询工资大于5000的员工, 来自哪些部门
-- 1. 查询工资大于5000的员工所在的部门id
select dept_id from emp where salary > 5000;
-- 2. 在部门表中, 查询出对应id的部门名称
select name from dept where id in(select dept_id from emp where salary > 5000);
```



- 查询开发部和财务部所有员工的信息

```mysql
-- 1. 查询开发部和财务部对应的部门id
select id from dept where name = '开发部' or name = '财务部'; -- (1, 3)
-- 2. 在员工表中, 查询出部门id是子查询结果的
select * from emp where dept_id in(select id from dept where name = '开发部' or name = '财务部');
```



#### 4.3 子查询的结果是多行多列

- 查询出2011年以后入职的员工信息, 包括部门名称

```mysql
-- 1. 查询出2011年以后入职的员工信息
select * from emp where join_date >= '2011-1-1'
-- 2. 虚拟表和部门表进行连接查询, 查询出部门名称
-- 沙僧的部门id是null, 内连接查询不到这条数据
select e.id, e.name, e.salary, d.name, e.join_date from dept d , (select * from emp where join_date >= '2011-1-1') e where e.dept_id = d.id;

-- 使用外连接查询出沙僧
select e.id, e.name, e.salary, d.name, e.join_date from dept d right join (select * from emp where join_date >= '2011-1-1') e on e.dept_id = d.id;
```



### 5. 多表查询练习

**准备数据**

```mysql
# 部门表
CREATE TABLE dept (
id INT PRIMARY KEY PRIMARY KEY, # 部门id
dname VARCHAR(50), # 部门名称
loc VARCHAR(50) # 部门位置
);
# 添加4个部门
INSERT INTO dept(id,dname,loc) VALUES
(10,'教研部','北京'),
(20,'学工部','上海'),
(30,'销售部','广州'),
(40,'财务部','深圳');

# 职务表，职务名称，职务描述
CREATE TABLE job (
id INT PRIMARY KEY,
jname VARCHAR(20),
description VARCHAR(50)
);
# 添加4个职务
INSERT INTO job (id, jname, description) VALUES
(1, '董事长', '管理整个公司，接单'),
(2, '经理', '管理部门员工'),
(3, '销售员', '向客人推销产品'),
(4, '文员', '使用办公软件');
# 员工表
CREATE TABLE emp (
id INT PRIMARY KEY, # 员工id
ename VARCHAR(50), # 员工姓名
job_id INT, # 职务id
mgr INT , # 上级领导
joindate DATE, # 入职日期
salary DECIMAL(7,2), # 工资
bonus DECIMAL(7,2), # 奖金
dept_id INT, # 所在部门编号
CONSTRAINT emp_jobid_ref_job_id_fk FOREIGN KEY (job_id) REFERENCES job (id),
CONSTRAINT emp_deptid_ref_dept_id_fk FOREIGN KEY (dept_id) REFERENCES dept (id)
);
# 添加员工
INSERT INTO emp(id,ename,job_id,mgr,joindate,salary,bonus,dept_id) VALUES
(1001,'孙悟空',4,1004,'2000‐12‐17','8000.00',NULL,20),
(1002,'卢俊义',3,1006,'2001‐02‐20','16000.00','3000.00',30),
(1003,'林冲',3,1006,'2001‐02‐22','12500.00','5000.00',30),
(1004,'唐僧',2,1009,'2001‐04‐02','29750.00',NULL,20),
(1005,'李逵',4,1006,'2001‐09‐28','12500.00','14000.00',30),
(1006,'宋江',2,1009,'2001‐05‐01','28500.00',NULL,30),
(1007,'刘备',2,1009,'2001‐09‐01','24500.00',NULL,10),
(1008,'猪八戒',4,1004,'2007‐04‐19','30000.00',NULL,20),
(1009,'罗贯中',1,NULL,'2001‐11‐17','50000.00',NULL,10),
(1010,'吴用',3,1006,'2001‐09‐08','15000.00','0.00',30),
(1011,'沙僧',4,1004,'2007‐05‐23','11000.00',NULL,20),
(1012,'李逵',4,1006,'2001‐12‐03','9500.00',NULL,30),
(1013,'小白龙',4,1004,'2001‐12‐03','30000.00',NULL,20),
(1014,'关羽',4,1007,'2002‐01‐23','13000.00',NULL,10);
# 工资等级表
CREATE TABLE salarygrade (
grade INT PRIMARY KEY,
losalary INT,
hisalary INT
);
# 添加5个工资等级
INSERT INTO salarygrade(grade,losalary,hisalary) VALUES
(1,7000,12000),
(2,12010,14000),
(3,14010,20000),
(4,20010,30000),
(5,30010,99990);
```

> 如果有n张表要查询, 需要(n - 1)个连接条件

- 查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述

```mysql

```

- 查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述，部门名称，部门位置

```mysql

```
- 查询所有员工信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级

```mysql

```
- 查询经理的信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级

```mysql

```
- 查询出部门编号、部门名称、部门位置、部门人数

```mysql

```



## 四. 事务【理解】

### 1. 事务的简介

- 什么是事务: 事务是数据库里面的一个概念, 表示一个事务的多个组成单元, 要么全部成功, 要么全部失败
- 事务的作用: 保证事务里的多个操作, 要么全部成功, 要么全部失败
- 事务的经典使用场景: 银行转账

### 2. MySQL中的事务管理

**准备数据**

```mysql
CREATE TABLE account (
	aid INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20),
	money DOUBLE
);
INSERT INTO account (aid,name,money) VALUES (NULL, 'tom', 10000);
INSERT INTO account (aid,name,money) VALUES (NULL, 'jerry', 10000);
```

#### (1) 手动事务管理

- 语法

  ```mysql
  -- 开启事务
  start transaction
  
  -- 结束
  -- 提交事务
  commit
  -- 回滚事务(回滚到事务开启前的状态)
rollback
  ```
  
  

#### (2) 自动事务管理

- 语法

  ```mysql
  -- 查看自动事务管理的状态
  select @@autocommit;
  -- 开启事务
  set autocommit = 0;
  -- 提交事务
  commit
  -- 回滚事务
  rollback
  ```

  

#### (3) 回滚点

- 语法

  ```mysql
  -- 设置回滚点
  savepoint 回滚点名;
  -- 回滚到回滚点
  rollback to 回滚点名
  ```

  > 注意: rollback to  不会结束事务

#### (4) 事务的原理

![image-20200720145556186](img/image-20200720145556186.png)

### 3. 事务的特性和并发问题

#### (1) 事务的四大特性ACID【重点】

| 简写 | 全称                | 特点                                              |
| ---- | ------------------- | ------------------------------------------------- |
| A    | Atomicity(原子性)   | 指事务是不可分割的,要么全部成功, 要么全部失败.    |
| C    | Consistency(一致性) | 指事务提交前后的状态和数据是一致的.               |
| I    | Isolation(隔离性)   | 指多事务并发时, 理论上是互相独立, 互不干扰的.     |
| D    | Druability(持久性)  | 指事务一旦提交, 数据就持久化保存到磁盘中不会丢失. |

#### (2) 事务并发的问题

- 什么是事务并发问题: 事务有隔离性, 所以理论上多个事务并发时, 是完全独立, 互不干扰的. 但是实际情况中, 做不到完全隔离, 所以事务之间会出现干扰的情况, 这些情况, 就是事务并发的问题.

- 有哪些并发问题: 有3种事务并发的问题, 按照严重的级别从高到低分别是:

  | 问题       | 描述                                                         |
  | ---------- | ------------------------------------------------------------ |
  | 脏读       | 一个事务中读取到另一个事务未提交的数据.                      |
  | 不可重复读 | 一个事务中多次读取的数据不一致, 原因是受其他事务已提交update的干扰 |
  | 幻读       | 一个事务中多次读取的数据不一致, 原因是受其他事务已提交insert/delete的干扰 |

- 解决并发问题: 我们可以通过设置事务之间的隔离级别来解决事务并发中出现的问题

#### (3) 事务隔离级别

| 隔离级别                         | 脏读 | 不可重复读 | 幻读 |
| -------------------------------- | ---- | ---------- | ---- |
| `read uncommitted(RU)`           | 有   | 有         | 有   |
| `read committed(RC)`(Oracle默认) | 无   | 有         | 有   |
| `repeatable read(RR)`(MySQL默认) | 无   | 无         | 有   |
| `serializable(SR)`               | 无   | 无         | 无   |

- 查询和设置事务隔离级别的语法
  - 查看隔离级别: `select @@tx_isolation`
  - 设置隔离级别: `set session transaction isolation level 隔离级别`

### 4. 演示不同隔离级别出现的问题

- 演示`read uncommitted`：存在脏读

  | 步骤  | 客户端1（效果演示的事务）                              | 客户端2（干扰事务）       |
  | ----- | ------------------------------------------------------ | ------------------------- |
  | 第1步 | 设置隔离级别为：read uncommitted<br />开启事务         | 开启事务                  |
  | 第2步 | 先查询account的数据：看tom的余额                       |                           |
  | 第3步 |                                                        | 修改tom的余额，但是不提交 |
  | 第4步 | 再查询account的数据：看tom的余额<br />金额变了，有脏读 |                           |
  | 最终  | 关闭事务                                               | 关闭事务                  |

- 演示`read committed`：没有脏读，有不可重复读

  | 步骤  | 客户端1（演示效果）                           | 客户端2（干扰事务）       |
  | ----- | --------------------------------------------- | ------------------------- |
  | 第1步 | 设置隔离级别：read committed<br />开启事务    | 开启事务                  |
  | 第2步 | 先查询tom的余额                               |                           |
  | 第3步 |                                               | 修改tom的余额，但是不提交 |
  | 第4步 | 再查询tom的余额：数据不变，没有脏读问题       |                           |
  | 第5步 |                                               | 提交事务                  |
  | 第6步 | 再查询tom的余额：数据变了，存在不可重复读问题 |                           |
  | 最终  | 关闭事务                                      |                           |

- 演示`repeatable read`：没有脏读、没有不可重复读，有幻读

  | 步骤  | 客户端1（演示效果）                          | 客户端2（干扰事务）       |
  | ----- | -------------------------------------------- | ------------------------- |
  | 第1步 | 设置隔离级别：repeatable read <br />开启事务 | 开启事务                  |
  | 第2步 | 先查询tom的余额                              |                           |
  | 第3步 |                                              | 修改tom的余额，但是不提交 |
  | 第4步 | 再查询tom的余额：数据不变，没有脏读问题      |                           |
  | 第5步 |                                              | 提交事务                  |
  | 第6步 | 再查询tom的余额：数据不变，没有不可重复读    |                           |
  | 最终  | 关闭事务                                     |                           |

- 演示`serializable`：串行化，没有任何并发问题

  | 步骤  | 客户端1（演示效果）                      | 客户端2（干扰事务）          |
  | ----- | ---------------------------------------- | ---------------------------- |
  | 第1步 | 设置隔离级别：serializable<br />开启事务 | 开启事务                     |
  | 第2步 | 查询数据                                 |                              |
  | 第3步 |                                          | 插入一条记录：应该是等待状态 |
  | 第4步 | 结束事务                                 | 立即自动执行                 |

## 五. 数据库范式【了解】

**什么是数据库范式**

- NF , Normal Form, 设计一个科学的, 合理的数据库所需要遵守的规范, 叫做数据库范式

**有哪些数据库范式**

- 有六大范式
  - 1NF：第一范式，设计数据库要满足的最基础的要求
  - 2NF：第二范式，在1NF基础上，满足更多要求
  - 3NF：第三范式，在2NF基础上，满足更多要求
  - BCNF：巴斯-科德范式，在3NF基础上，满足更多要求
  - 4NF：第四范式，在BCNF基础上，满足更多要求
  - 5NF：第五范式，在4NF基础上，满足更多要求。完美范式
- 注意:
  - 在实际数据库的设计中, 只需要满足3NF即可

**常见的数据库范式**

- 1NF: 要求每一列都是不可分割的	
  
  - 不符合1NF
  
  ![image-20200719212221319](img/image-20200719212221319.png)
  
  - 符合1NF
  
  ![image-20200719212546978](img/image-20200719212546978.png)

- 2NF要求所有字段必须全部依赖于主键字段
  
- 不符合2NF
  
  ![image-20200719212546978](img/image-20200719212546978.png)
  
- 符合2NF
  
  ![image-20200719220159475](img/image-20200719220159475.png)
  
- 3NF : 消除依赖传递

  - 不符合3NF

  ![image-20200719220159475](img/image-20200719220159475.png)

  - 符合3NF

  ![image-20200720154857074](img/image-20200720154857074.png)

