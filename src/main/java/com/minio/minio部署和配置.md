# Minio 安装和部署

**what is minio :** 

MinIO 是 Apache License v2 发布的，与 Amazon S3 兼容的云存储服务器。作为对象存储，MinIO 可以存储非结构化数据，例如照片，视频，日志文件，备份和容器映像。一个对象的最大大小为 5TB。

### 下载

官网下载二进制文件

```
服务端 wget https://dl.min.io/server/minio/release/linux-amd64/minio
```

```
客户端 wget https://dl.min.io/client/mc/release/linux-amd64/mc
```

### 配置

在指定目录下新建minio.conf配置文件

```
# 用户名
MINIO_ACCESS_KEY=admin

# 密码
MINIO_SECRET_KEY=*********

# 存放数据的路径
MINIO_VOLUMES="/minio/data"

# 启动端口
MINIO_OPTS="--address :19000"

```

### 设置为服务

vim minio.service 

并将minio.service放到  /etc/systemd/system/

***注意*** ： 遵循unit编写规则，不允许出现注释，并且要赋予 minio相应的执行权限  chmod +x minio 

```
[Unit]
#服务名称
Description=minio
#帮助文档地址
Documentation=https://docs.min.io
Wants=network-online.target
After=network-online.target
# 配置 minio 二进制文件地址
AssertFileIsExecutable=/minio/minio  

[Service]
#指定配置文件
EnvironmentFile=-/minio/minio.conf  
#按照配置文件方式指定运行
ExecStart=/minio/minio server $MINIO_OPTS $MINIO_VOLUMES 
# Let systemd restart this service always
Restart=always
# Specifies the maximum file descriptor number that can be opened by this process
LimitNOFILE=65536
# Disable timeout logic and wait until process is stopped
TimeoutStopSec=infinity

SendSIGKILL=no

[Install]

WantedBy=multi-user.target

```

### 添加服务

```
systemctl daemon-reload   #进程重载
systemctl enable minio.service   #开启服务
systemctl start minio  #开始
systemctl status minio.service  #查看状态
systemctl stop minio  #停止服务
```

