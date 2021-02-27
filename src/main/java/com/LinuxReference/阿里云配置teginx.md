## 前期准备

下载tar包
#########
提前准备好需要安装的模块  

##### openssl / luaJit etc..

## Luajit 安装

#### lua下载地址： http://luajit.org/

LuaJIT-2.0.1.tar.gz 下载在/softw/tengine/ 目录下。

#### 编译安装命令：

cd /softw/tengine/LuaJIT-2.0.5

make  

make install

或者 make && make install 

安装路径参数 PREFIX=/softw/tengine/LuaJIT-2.0.5

安装到默认路径，提示如下

==== Successfully installed LuaJIT 2.0.5 to /usr/local ====

#### 配置lua的环境变量

export LUAJIT_LIB=/usr/local/lib

export LUAJIT_INC=/usr/local/include/luajit-2.0

source /etc/profile

建立软连接

ln -s /usr/local/lib/libluajit-5.1.so.2 /lib64/liblua-5.1.so2

（不然nginx/tengine启动会报错！！！）

## 下载ngx_devel_kit（NDK）模块 

直接下载即可

 wget https://github.com/simpl/ngx_devel_kit/archive/v0.3.1.tar.gz

tar -xzvf v0.3.1.tar.gz

## 下载最新的lua-nginx-module 模块

直接下载即可

 ~~wget https://github.com/openresty/lua-nginx-module/archive/v0.10.17.tar.gz~~ 高版本不支持nginx！

wget https://github.com/openresty/lua-nginx-module/archive/v0.10.11.tar.gz

tar -xzvf v0.10.11.tar.gz

## Sticky模块

直接下载即可

wget https://bitbucket.org/nginx-goodies/nginx-sticky-module-ng/get/master.tar.gz

**tar -zxf master.tar.gz**

nginx-goodies-nginx-sticky-module-ng-c78b7dd79d0d.tar.gz(这个包是tengine sticky的扩展包) 

mv  nginx-goodies-nginx-sticky-module-ng-c78b7dd79d0d/ nginx-sticky

## Tengine安装

配置模块（lua，https，负载均衡等）

./configure --prefix=/softw/tengine \
--with-pcre \
--with-http_sub_module \
--with-http_stub_status_module \
--with-http_ssl_module \
--with-http_flv_module  \
--with-http_realip_module \
--with-http_gunzip_module \
--with-http_gzip_static_module \
--add-module=/softw/tengine/module/ngx_devel_kit-0.3.1 \
--add-module=/softw/tengine/module/lua-nginx-module-0.10.11 \
--add-module=/softw/tengine/module/nginx-sticky

```
说明：
#指定运行权限的用户   --user=nginx
#指定运行的权限用户组   --group=nginx
#指定安装路径   --prefix=/apps/nginx
#支持nginx状态查询 --with-http_stub_status_module
#开启ssl支持   --with-http_ssl_module
#开启GZIP功能   --with-http_gzip_static_module
#开启concat功能  --with-http_concat_module=shared
nginx rewrite依赖于PCRE库，所以需要在linux系统中编译安装PCRE库
--with-pcre=/usr/local/pcre-8.37 \
 --with-pcre-jit \
--with-zlib=/usr/local/zlib-1.2.11 \
--with-http_spdy_module \
```

编译安装

make

make install 

查看安装好的配置

sbin/nginx -t

sbin/nginx -V

启动脚本--不可用

```
#!/bin/sh
#
# nginx - this script starts and stops the nginx daemon
#
# chkconfig:   - 85 15 
# description:  Nginx is an HTTP(S) server, HTTP(S) reverse \
#               proxy and IMAP/POP3 proxy server
# processname: nginx
# config:      /etc/nginx/nginx.conf
# config:      /etc/sysconfig/nginx
# pidfile:     /var/run/nginx.pid
 
# Source function library.
. /etc/rc.d/init.d/functions
 
# Source networking configuration.
. /etc/sysconfig/network
 
# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0
 
nginx="/softw/tengine/sbin/nginx"
prog=$(basename $nginx)
 
NGINX_CONF_FILE="/softw/tengine/sbin/conf/nginx.conf"
 
[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx
 
lockfile=/var/lock/subsys/nginx
 
make_dirs() {
   # make required directories
   user=`nginx -V 2>&1 | grep "configure arguments:" | sed 's/[^*]*--user=\([^ ]*\).*/\1/g' -`
   options=`$nginx -V 2>&1 | grep 'configure arguments:'`
   for opt in $options; do
       if [ `echo $opt | grep '.*-temp-path'` ]; then
           value=`echo $opt | cut -d "=" -f 2`
           if [ ! -d "$value" ]; then
               # echo "creating" $value
               mkdir -p $value && chown -R $user $value
           fi
       fi
   done
}
 
start() {
    [ -x $nginx ] || exit 5
    [ -f $NGINX_CONF_FILE ] || exit 6
    make_dirs
    echo -n $"Starting $prog: "
    daemon $nginx -c $NGINX_CONF_FILE
    retval=$?
    echo
    [ $retval -eq 0 ] && touch $lockfile
    return $retval
}
 
stop() {
    echo -n $"Stopping $prog: "
    killproc $prog -QUIT
    retval=$?
    echo
    [ $retval -eq 0 ] && rm -f $lockfile
    return $retval
}
 
restart() {
    configtest || return $?
    stop
    sleep 1
    start
}
 
reload() {
    configtest || return $?
    echo -n $"Reloading $prog: "
    killproc $nginx -HUP
    RETVAL=$?
    echo
}
 
force_reload() {
    restart
}
 
configtest() {
  $nginx -t -c $NGINX_CONF_FILE
}
 
rh_status() {
    status $prog
}
 
rh_status_q() {
    rh_status >/dev/null 2>&1
}
 
case "$1" in
    start)
        rh_status_q && exit 0
        $1
        ;;
    stop)
        rh_status_q || exit 0
        $1
        ;;
    restart|configtest)
        $1
        ;;
    reload)
        rh_status_q || exit 7
        $1
        ;;
    force-reload)
        force_reload
        ;;
    status)
        rh_status
        ;;
    condrestart|try-restart)
        rh_status_q || exit 0
            ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart|condrestart|try-restart|reload|force-reload|configtest}"
        exit 2
esac
```

添加脚本到  /etc/init.d/nginx 

赋予权限 chmod 777 nginx

执行 : service nginx status 

### 如果这时候报错：Unit nginx.service could not be found.

则执行如下语句，添加服务到sys

```kotlin
chkconfig --add nginx
```

## 添加php支持

在 nginx.conf 配置文件中，添加

```
 location ~ \.php$ {
            root           html;
            fastcgi_pass   127.0.0.1:9000;
            fastcgi_index  index.php;
            fastcgi_param  SCRIPT_FILENAME $document_root$fastcgi_script_name;
            fastcgi_param  SCRIPT_NAME  $fastcgi_script_name;
            include        fastcgi_params;
        }
```



## Nginx配置权限设置

模块ngx_http_auth_basic_module 允许使用“HTTP基本认证”协议验证用户名和密码来限制对资源的访问。

使用htpasswd -c -d /www/tools/Auth/auth_file [username]

输入密码：

确认输入的密码：

在 nginx.conf 的location中添加如下配置：

```
location ~ (.*)\.avi$ {
              auth_basic  "closed site";
              auth_basic_user_file /softw/tengine/mypwd/auth_file;
        }
```

 



## Tips:在不添加服务的情况下，快捷启动nginx

1. 添加nginx的环境变量
2. 在usr/bin下面配置nginx的软连接（当前用户可用）

## 遇到的问题

### 1../nginx: error while loading shared libraries: libluajit-5.1.so.2: cannot open shared object file:

nginx: error while loading shared libraries: libluajit-5.1.so.2: cannot open shared object file: No such file or directory

出现这个错误，是变量没有写进去，所以就添加下变量：

cat /etc/ld.so.conf
include ld.so.conf.d/*.conf
echo “/usr/local/lib” >> /etc/ld.so.conf
ldconfig

### 2.安装nginx+lua时提示：./configure: error: ngx_http_lua_module requires the Lua library.

！！！ 首先确认环境变量配置的luajit路径是否正确！！！

yum install lua-devel

### 3.启动nginx/tengine，提示 failed to load the 'resty.core' module

报错详情：

```
nginx: [alert] detected a LuaJIT version which is not OpenResty's; many optimizations will be disabled and performance will be compromised (see https://github.com/openresty/luajit2 for OpenResty's LuaJIT or, even better, consider using the OpenResty releases from https://openresty.org/en/download.html)
nginx: [alert] failed to load the 'resty.core' module (https://github.com/openresty/lua-resty-core); ensure you are using an OpenResty release from https://openresty.org/en/download.html (reason: module 'resty.core' not found:
    ``no field package.preload['resty.core']
    ``no file './resty/core.lua'
    ``no file '/usr/local/LuaJIT/share/luajit-2.0.5/resty/core.lua'
    ``no file '/usr/local/share/lua/5.1/resty/core.lua'
    ``no file '/usr/local/share/lua/5.1/resty/core/init.lua'
    ``no file '/usr/local/LuaJIT/share/lua/5.1/resty/core.lua'
    ``no file '/usr/local/LuaJIT/share/lua/5.1/resty/core/init.lua'
    ``no file './resty/core.so'
    ``no file '/usr/local/lib/lua/5.1/resty/core.so'
    ``no file '/usr/local/LuaJIT/lib/lua/5.1/resty/core.so'
    ``no file '/usr/local/lib/lua/5.1/loadall.so'
    ``no file './resty.so'
    ``no file '/usr/local/lib/lua/5.1/resty.so'
    ``no file '/usr/local/LuaJIT/lib/lua/5.1/resty.so'
    ``no file '/usr/local/lib/lua/5.1/loadall.so') in /usr/local/nginx/conf/nginx.conf:210
```

解决：编译的时候使用了最新版本的lua-nginx-module模块，使用了最新版就会触发该问题，官方还未修复，使用低于**lua-nginx-module-0.10.14**方可解决，本次后改为使用的lua-nginx-module-0.10.11版本

### 4.解决 413 Request Entity Too Large（请求实体太大）

请求的body的大小，在Content-Length后显示，Nginx默认的request body为1M，小于我们上传的大小

***解决方案***
找到自己主机的nginx.conf配置文件，打开
在http{}中加入 client_max_body_size 10m;
然后重启nginx
/etc/init.d/nginx restart