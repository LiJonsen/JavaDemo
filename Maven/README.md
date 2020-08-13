> Maven官网下载地址： https://maven.apache.org/download.cgi 

#### 一、快速开始

##### 1.1  修改conf/setting.xml配置

更改Maven本地仓库存放目录路径

```xml
<localRepository>D:\ApacheSpace\maven-repository</localRepository>
```

添加淘宝镜像源：

```xml
<mirror>
    <id>aliyunmaven</id>
    <mirrorOf>central</mirrorOf>
    <name>central</name> 
    <url>
    	https://maven.aliyun.com/repository/central
    </url>
</mirror>
```



##### 1.2 在Idea中创建Maven项目

* 将本地的maven目录配置到idea中：
  * 打开File/Settings 菜单，搜索Maven；
  * 配置`Maven home directory(maven 环境变量本地路径)` 和 `User settings file(本地settings.xml文件夹路径)` 以及 `Local repository(本地maven仓库文件夹路径)`；

* 新建maven项目：
  * New Project创建Maven项目，选择`maven-archetype-*`创建项目；
  * 创建`maven-archetype-quickstart`项目，快速配置项目名称；

##### 1.3 报错处理

###### plugins目录报错`Cannot resolve plugin ...`：

* 配置淘宝镜像源（参考上面1.1）；
* 需要到本地maven仓库目录下，找到apache中的plugins目录将其删除，然后重新在idea中下载，等待下载完成后报错自动消失；



######  构建警告UTF-8编码问题

```
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
```

解决：在pom.xml中添加配置

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

