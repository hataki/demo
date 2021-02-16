# Redis RDB和AOF实操

ps -fe |grep redis 

确保没有redis运行

修改配置 vim ../redis.conf 

- deamonize no  #默认是yes，守护进程（后台）,需要改为no，改成前台

- 把日志文件注释到，这样会把日志打印到屏幕上

- appendonly yes #默认为no，修改为yes

- aof-use-rdb-preamble no #关闭新版本的混合特性

- 启动redis-server ../redis.conf  # 此时，会在配置的aof路径下生成appendonly.aof文件

- 在redis-cli执行操作： set k1 hello ， 打开aof文件如下

  ```
  *2 #代表后边有几个元素组成
  $6 #描述有几个字符组成 
  SELECT #命令
  $1 
  0 #选择0号库
  *3
  $3
  set
  $2
  k1
  $5
  hello
  ```

rdb文件开头会有REDIS ，但是老版本aof开头没有

使用 redis-check-rdb dump.rdb 来查看rdb文件中记载的内容

bgrewriteaof 命令，重写aof，去重操作指令（old version ）

---

aof-use-rdb-preamble yes #开启新版本的混合特性

再次执行 bgrewriteaof 命令 （bgsave 出发 rdb ）

查看 appendonly.aof文件，开头会有rdb文件的表示

---

执行flashall 之后，appendonly.aof文件中会记录 flashall操作，但是之前的操作日志还存在

此时如果出发  bgrewriteaof 命令，那么appendonly.aof文件就会被清空，不管是不是融合了rdb；

当你发现错误时，如果触发了 bgrewriteaof，那么很难在回复了

 