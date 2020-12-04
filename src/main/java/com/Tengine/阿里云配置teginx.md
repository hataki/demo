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

解决：编译的时候使用了最新版本的lua-nginx-module模块，使用了最新版就会触发该问题，官方还未修复，使用低于lua-nginx-module-0.10.14方可解决，本次后改为使用的lua-nginx-module-0.10.11版本