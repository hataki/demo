# CentoOS7防火墙

uname -a 查看当前linux版本

CentOS7 的防火墙配置跟以前版本有很大区别，CentOS7这个版本的防火墙默认使用的是firewall，与之前的版本Centos 6.x使用iptables不一样

## firewall防火墙 centos7 

1、查看firewall服务状态

systemctl status firewalld

出现Active: active (running)切高亮显示则表示是启动状态。

出现 Active: inactive (dead)灰色表示停止，看单词也行。
2、查看firewall的状态

firewall-cmd --state
3、开启、重启、关闭、firewalld.service服务

\# 开启
service firewalld start
\# 重启
service firewalld restart
\# 关闭
service firewalld stop
4、查看防火墙规则

firewall-cmd --list-all 
5、查询、开放、关闭端口

\# 查询端口是否开放
firewall-cmd --query-port=8080/tcp
\# 开放80端口
firewall-cmd --permanent --add-port=80/tcp
\# 移除端口
firewall-cmd --permanent --remove-port=8080/tcp
\#重启防火墙(修改配置后要重启防火墙)
firewall-cmd --reload

\# 参数解释
1、firwall-cmd：是Linux提供的操作firewall的一个工具；
2、--permanent：表示设置为持久；
3、--add-port：标识添加的端口；

##  iptables防火墙 centos6 

1、基本操作

\# 查看防火墙状态

service iptables status 

\# 停止防火墙

service iptables stop 

\# 启动防火墙

service iptables start 

\# 重启防火墙

service iptables restart 

\# 永久关闭防火墙

chkconfig iptables off 

\# 永久关闭后重启

chkconfig iptables on　　

2、开启80端口

vim /etc/sysconfig/iptables
\# 加入如下代码
-A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT
保存退出后重启防火墙

service iptables restart

**CentOS7 默认使用firewalld防火墙，如果想换回iptables防火墙，可关闭firewalld并安装iptables。**

1、关闭firewall：

- 停止firewall
  `systemctl stop firewalld.service`
- 禁止firewall开机启动
  `systemctl disable firewalld.service`
- 查看默认防火墙状态（关闭后显示notrunning，开启后显示running）
  `firewall-cmd --state`

2.安装iptables-services

```
yum install iptables-services
```

3.修改防火墙配置文件

```
vi /etc/sysconfig/iptables
```