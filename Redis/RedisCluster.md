####  Redis集群Cluster模式

>  概述： Redis Cluster是一个高性能高可用的分布式系统。由多个Redis实例组成的整体，数据按照Slot存储分布在多个Redis实例上，通过Gossip协议来进行节点之间通信 ；
>

Redis Cluster的特性：

* 写安全、可用性、性能 ；

* 两种重定向：MOVED和ASK

* 容错机制：PFAIL和FAIL两种状态

* Key分配模型：使用CRC16算法，如果需要分配到相同的slot，可以使用tag 

限制：

1. key批量操作支持有限。只支持具有相同slot值的key执行批量操作。

2. 事务操作支持有限。只支持同一个节点上的多个key的事务操作。

3. key是数据分区的最小粒度，因为不能讲一个大的键值对象，如hash，list等映射到不同的节点上。

4. 不支持多数据库，单机下的Redis可以支持16个数据库，但集群之只能使用一个数据库空间，即db 0。

5. 复制结构只支持一层，从节点只能复制主节点，不支持嵌套树状复制结构









##### 报错

<font color="red">redis.clients.jedis.exceptions.JedisMovedDataException: MOVED 6918 127.0.0.1:6380</font>





```
./redis-cli -a root --cluster add-node 42.194.197.225:6382 106.53.88.252:6379 --cluster-slave --cluster-master-id 79d086d5bdd0c81936aaf3730eb61571f75c8024
```

