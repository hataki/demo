# KeepAlive 

学习KeepAlive之前，需要明确的点：

1. lvs（linux virtual server）会挂，业务下线，单点故障
2. rs（real server）会挂，一部分用户请求异常，lvs还存在rs的负载记录

解决

单点故障的解决方式 -- 单个出问题，那么就更改成群集/多个的方式

多点（主备，主主）；

主备 ： rs挂怎么确定？ 访问底层，验证应用层http协议 ---》 返回 200 ok 

linux内核中有 lvs模块 ： ipvs  < -- 增加源码代码 / 第三方实现 （人为因素--不稳定受情绪控制，追求自动化程序  ---->   keepalive 出现了）

KeepAlive 代替人工实现自动运维，解决单点故障，实现HA（high available）

1. 监控自己服务
2. Master通告自己还活着。Backup监听Master状态，当Master挂掉，剩下的backup选举出新的Master
3. 配置vip，添加ipvs
4. 对后端server做健康检查

*. KeepAlive是一个通用工具，主要作为HA实现，nginx可以作为公的的负载均衡来实现，但是nginx就成了单点故障，此时可以使用KeepAlive解决；

## KeepAlive实验

keepalived实验：
主机： node01~node04

node01:
	ipvsadm -C
	ifconfig eth0:8 down

----------------------------
node01,node04:
	yum install keepalived ipvsadm -y
	配置：
		cd  /etc/keepalived/
		cp keepalived.conf keepalived.conf.bak
		vi keepalived.conf
			node01:
			vrrp：虚拟路由冗余协议（virtual route redundancy protocol）！
				vrrp_instance VI_1 {
					state MASTER         //  node04  BACKUP
					interface eth0
					virtual_router_id 51
					priority 100		 //	 node04	 50
					advert_int 1
					authentication {
						auth_type PASS
						auth_pass 1111
					}
					virtual_ipaddress {
						192.168.150.100/24 dev eth0 label  eth0:3
					}
				}
			virtual_server 192.168.150.100 80 {
				delay_loop 6
				lb_algo rr
				lb_kind DR
				nat_mask 255.255.255.0
				persistence_timeout 0
				protocol TCP

				real_server 192.168.150.12 80 {
					weight 1
					HTTP_GET {
						url {
						  path /
						  status_code 200
						}
						connect_timeout 3
						nb_get_retry 3
						delay_before_retry 3
					}   
				}       
				real_server 192.168.150.13 80 {
					weight 1
					HTTP_GET {
						url {
						  path /
						  status_code 200
						}
						connect_timeout 3
						nb_get_retry 3
						delay_before_retry 3
					}
				}
			scp  ./keepalived.conf  root@node04:`pwd`
***安装帮助文档***  yum install man 

补充： 

在java中 是线程

在linux的c环境 是进程 

http 是fork出一个线程 ； 

nginx 采用异步的方式

***当KeepAlive的进程被意外终止时候（比如kill -9 结束），会导致这个出现不可预知的错误（存在多个vip，lvs失效等等），这个时候就需要*** 

***引入 zookeeper 来解决***

