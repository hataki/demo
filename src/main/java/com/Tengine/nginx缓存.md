## Nginx缓存

### Nginx全局内存缓存

### http_proxy_cache 本地磁盘缓存





tmpfs

系统默认开启，大小约为物理内存的50%

tmpfs没有占用实际的物理内存，只有在写入数据的时候，才会占用实际内存！



查看命令 df -hT

面对高并发要查看 ： socket和线程数