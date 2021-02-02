## Nginx缓存

### Nginx全局内存缓存

### http_proxy_cache 本地磁盘缓存





tmpfs

系统默认开启，大小约为物理内存的50%

tmpfs没有占用实际的物理内存，只有在写入数据的时候，才会占用实际内存！



查看命令 df -hT

面对高并发要查看 ： socket和线程数





### URL一致性哈希负载均衡

#### nginx url_hash 

有针对性的对url进行一致性hash 定向负载到后端Nginx 

提高Nginx缓存系统命中率

Nginx第三方模块，在转发请求时如果后端服务器宕机，会导致503错误



#### lua-resty-http

### GitHub主页

https://github.com/ledgetech/lua-resty-http

### 安装组件

wget https://raw.githubusercontent.com/pintsized/lua-resty-http/master/lib/resty/http_headers.lua  

wget https://raw.githubusercontent.com/pintsized/lua-resty-http/master/lib/resty/http.lua 





### 其他缓存

#### 客户端

##### 浏览器缓存

##### ETag**:

http1.1支持

在HTTP协议中If-Modified-Since和If-None-Match分别对应Last-Modified和ETag

Entity Tag 的缩写，中文译过来就是实体标签的意思.

HTTP中并没有指定如何生成ETag，哈希是比较理想的选择。

在计算Etag的时候，会产生CPU的耗费，所以也可以用时间戳，但这样直接使用Last-Modified即可。

ETag 用来校验用户请求的资源是否有变化，作用和lastmodified很像，区别是lastmodified精确到秒，ETag可以用hash算法来生成更精确的比对内容。

当用户首次请求资源的时候返回给用户数据和200状态码并生成ETag，再次请求的时候服务器比对ETag，没有发生变化的话返回304



#### 浏览器缓存原则

- **首页**可以看做是框架 应该禁用缓存，以保证加载的资源都是最新的

- 还有一些场景下我们希望禁用浏览器缓存。比如轮训api上报数据数据

- 浏览器缓存很难彻底禁用，大家的做法是加版本号，随机数等方法。

- 只缓存200响应头的数据，像3XX这类跳转的页面不需要缓存。
- 对于js，css这类可以缓存很久的数据，可以通过加版本号的方式更新内容
- 不需要强一致性的数据，可以缓存几秒
- 异步加载的接口数据，可以使用ETag来校验。
- 在服务器添加Server头，有利于排查错误



#### 应用缓存

分为手机APP和Client以及是否遵循http协议

在没有联网的状态下可以展示数据

流量消耗过多

- 漂亮的加载过程
- 提前下发  避免秒杀时同时下发数据造成流量短时间暴增
- 兜底数据 在服务器崩溃和网络不可用的时候展示
- 临时缓存  退出即清理
- 固定缓存  展示框架这种，可能很长时间不会更新，可用随客户端下发
- 父子连接 页面跳转时有一部分内容不需要重新加载，可用从父菜单带过来
- 预加载     某些逻辑可用判定用户接下来的操作，那么可用异步加载那些资源
- 异步加载 先展示框架，然后异步加载内容，避免主线程阻塞