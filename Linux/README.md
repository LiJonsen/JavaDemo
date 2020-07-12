#### /目录文件结构说明

* /bin（/usr/bin & /usr/local/bin）：是Binary的缩写，这个目录存放着最经常使用的命令；
* /sbin （/usr/sbin & /usr/local/bin）：s就是super user的意思，这里存放的是系统管理员使用的系统管理程序；
* /home：存放普通用户的主目录，在Linux中每个用户都有一个自己的目录，一般该目录名称是以用户的账号命名的；
* /root：该目录为系统管理员，也称作超级权限者的用户主目录；
* /boot：存放的是启动Linux时使用的一些核心文件，包括一些连接文件以及镜像文件；
* /proc：这个目录是一个虚拟的目录，它是系统内存的映射，访问这个目录来获取系统信息；
* /srv：service缩写，该目录存放一些服务启动之后需要提取的数据；
* /sys：这是Linux2.6内核的一个很大的变化，该目录下安装了2.6内核中新出现的一个文件系统
* /tmp：这个目录是用来存放一些临时文件的；
* /dev：类似于windows的设备管理器，把所有的硬件用文件的形式存储；
* /media：Linux系统会自动识别一些设备，例如U盘、光驱等等...当识别后，Linux会把识别的设备挂载到这个目录下；
* /mnt：系统提供该目录是为了让用户临时挂载别的文件系统的，`我们可以将外部的存储挂载在/mnt上`，然后进入该目录就可以查看里面的内容了；
* /opt：这个给主机额外安装软件所摆放的目录（如：安装Oracle数据库就可以放到该目录下）；
* /lib：系统开机所需要最基本的动态连接共享库（作用类似于windows里的DLL文件）；
* /etc：所有的系统管理所需要的配置文件和子目录；
* /usr：用户的大部分应用程序和文件都放在这个目录下（类似windows下的program files目录）；
  * /usr/local：这是另一个给主机额外安装软件所安装的目录，`一般是通过编译源码方式安装的程序`；
* /var：这个目录中存放着在不断扩充着的东西，习惯将经常被修改的目录放在这个目录下（包括日志文件）；
* /selinux[security-enhanced linux]360：SELinux是一种安全子系统，它能控制程序只能访问特定的文件；
* /lost+found：这个目录一般情况下是空的，当系统非法关机后，这里就存放了一些文件；

> Tip：proc & src & sys这三个目录一般情况不需要改动；

#### vmtools工具

* 可以直接粘贴命令在windows和centos系统之间
* 可以设置windows和centos的共享文件夹





#### 常用指令

##### 关机&重启

> Tip：当我们关机或者重启时，都应该先执行以下 sync 指令，把内存的数据写入磁盘，防止数据丢失。

```
shutdown
	shutdown -h now  // 表示立即关机
	shutdown -h 1 // 表示1分钟后关机
	shutdown -r now // 立即重启
// 效果等价于关机
halt

// 重启系统
reboot 
// 把内存的数据同步到磁盘
sync
```





#### Shell脚本

##### 快速入门

新建一个helloworld.sh脚本文件

helloworld.sh

```shell
#!/bin/bash
echo "Hello Shell...";
```

执行方式一（推荐）

```
# 赋予helloworld.sh脚本的+x执行权限
chmod 744 helloworld.sh
# 执行脚本
# 1. 相对路径
./helloworld.sh
# 2. 绝对路径
~/myShell/helloworld.sh


# 修改权限后，helloworld.sh脚本失去执行权限
chmod 644 helloworld.sh
```



执行方式二（不推荐）

```
# 直接执行
sh helloworld.sh;
```



#### Shell变量

##### 系统变量

> 如：$PATH、$USER、$HOME、$SHELL、$PWD等等...

##### 自定义变量

输出当前日期

```shell
MY_VAR=$(date);
echo "date=$MY_VAR";

# 将命令的返回值赋值给变量
SOME_VAL=`ls -l`;
SOME_VAL2=$(ls -l);
```

注意点：

* 变量名全部大写
* 变量在双引号里通过`$变量名`使用
* `=`号两侧不能有空格
* `unset 变量`可以撤销变量
* `readonly 变量`设置只读

##### 设置环境变量

> 作用：配置全局环境变量后，可以在任意的shell脚本代码中获取到。【`/etc/profile`文件可以配置全局环境变量】



1. 在/etc/profile末尾添加以下代码，并保存

   ```shell
   TOMCAT_HOME=/opt/tomcat
   export TOMCAT_HOME
   ```

2. 控制台输入 `source /etc/profile` 刷新环境变量

3. 在shell脚本中使用

   ```shell
   # 输出：/opt/tomcat
   echo "tomcathome=$TOMCAT_HOME"
   ```

   

#### 函数

##### 系统函数

* basename：

  ```shell
  #basename [string] [suffix]
  # 返回test.txt
  basename /home/root/test.txt
  # 返回test
  basename /home/root/test.txt .txt
  ```

  

* dirname：

  ```shell
  #dirname [string] [suffix]
  # /home/root/
  dirname /home/root/test.txt
  ```

  