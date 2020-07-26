#### Nginx快速开始

> * Nginx：高性能的Http和反向代理的服务器，常用于高并发处理；
> * 官网下载地址： https://nginx.org/en/download.html ；
> * Centos8安装：`yum install -y nginx；`

常用命令

```nginx
# 查看Nginx安装路径
nginx -t

#启动
nginx

# 停止
nginx -s stop

# 重新加载
nginx -s reload

# centos查看nginx进程
# -e：显示所有进程		-f：全格式
ps -ef | grep nginx
```



#### 正向代理

> 特点： 需要在客户端配置代理服务器进行指定网站访问 ；

![reverseProxy](D:\Josen\JavaDemo\Nginx\images\proxy.png)

#### 反向代理

> 特点：暴露的是代理服务器的地址，隐藏了真实的服务器地址；

![reverseProxy](D:\Josen\JavaDemo\Nginx\images\reverseProxy.png)

nginx.conf配置

```nginx
http{
    server{
        // 客户端实际访问的地址
        listen	80;
        server_name	127.0.0.1;
        location /{
            # 配置代理服务器地址
            proxy_pass 127.0.0.1:8080;
            root html;
            index index.html index.htm;
        }
    }
}
```



#### 负载均衡

> 特点： 增加服务器的数量，然后将请求分发到各个服务器上，将原先请求集中到单个服务器上的 情况改为将请求分发到多个服务器上，将负载分发到不同的服务器；

![reverseProxy](D:\Josen\JavaDemo\Nginx\images\fzjh.png)

nginx.conf配置

```nginx
http{
    # 配置多个服务器地址
    upstream myserver{
        server 127.0.0.1:9090;
        server 127.0.0.1:9091;
    }
    server{
        listen	80;
        server_name	127.0.0.1;
        location /{
            # 配置均衡负载
            proxy_pass http://myserver;
            root html;
            index index.html index.htm;
        }
    }
}
```

nginx 分配服务器策略

* 轮询（默认）
  * 每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除；
* weight：
  * 代表权重，权重越高被分配的客户端越多（默认为1）；
* ip_hash：
  * 每个请求按访问IP的hash结果分配，这样每个访客固定访问一个后端服务器；
* fair（第三方）
  * 按后端服务器的响应时间来分配请求，响应时间短的优先分配；



#### 动静分离

> 特点：将静态资源（html、css、images）和动态资源（Java、Nodejs、Jsp）划分存储，通过nginx实现不同的url访问不同的服务器资源；

![reverseProxy](D:\Josen\JavaDemo\Nginx\images\djfl.png)

#### 高可用集群

> 特点：开启两个服务器（Master&Backup），两个服务器中开启并配置好`nginx & keepalived & tomcat`环境，当Master服务器的Nginx宕机或关闭时，则会自动开启Backup服务器的Nginx，避免客户端无法访问。

![gky](D:\Josen\JavaDemo\Nginx\images\gky.png)

#### 配置SSL证书（https）

> * 证书下载：SSL证书管理控制台（阿里云）
>   * <img src="D:\Josen\JavaDemo\Nginx\images\ssl_download.png" alt="ssl_download" style="zoom:80%;" />
> * 在Nginx服务器配置SSL证书： https://help.aliyun.com/document_detail/98728.html?spm=5176.2020520163.0.0.b68656a7whHJKZ 



查看是否已安装http_ssl_module模块

```
nginx -V
```

![nginx_config](D:\Josen\JavaDemo\Nginx\images\nginx_config.png)

修改防火墙配置

```shell
# 开启443端口
firewall-cmd --add-port=443/tcp --permanent

# 添加https服务
firewall-cmd --add-service=https –permanent

# 重启防火墙
firewall-cmd --reload
```





#### 内容补充

#####  防火墙

>  问题：在终端开启服务器守护进程后，无法正常访问



```shell
# 查看防火墙开放的端口号
firewall-cmd --list-all
# 添加http服务
firewall-cmd --add-service=http –permanent
# 设置开放的端口号
firewall-cmd --add-port=80/tcp --permanent
# 重启防火墙
firewall-cmd –reload
```



![reverseProxy](D:\Josen\JavaDemo\Nginx\images\firewallList.png)

> Tip：如果防火墙已经开放指定的端口号还是不行，登录到云服务器后台，`配置安全组后即可`；