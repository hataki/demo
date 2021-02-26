### php7安装

#### 1.获取安装包，本次使用版本为7.2.27

wget https://www.php.net/distributions/php-7.2.27.tar.gz

tar -xzvf php-7.2.27.tar.gz

#### 2.yum安装搜需要的依赖

yum install gcc-c++  zlib zlib-devel pcre pcre-devel  libjpeg libjpeg-devel libpng libpng-devel freetype freetype-devel libxml2 libxml2-devel glibc glibc-devel glib2 glib2-devel bzip2 bzip2-devel ncurses ncurses-devel curl curl-devel e2fsprogs e2fsprogs-devel krb5 krb5-devel openssl openssl-devel openldap openldap-devel nss_ldap openldap-clients openldap-servers



#### 3.添加配置、检查配置环境、编译安装

##### ！！配置参数不能过长换行！！

```
./configure --prefix=/softw/php7 --with-config-file-path=/softw/php7 --enable-mbstring --with-openssl --enable-ftp --with-gd --with-jpeg-dir=/usr --with-png-dir=/usr --with-mysql=/softw/mysql/mysql8 --with-mysqli=/softw/mysql/mysql8/bin/mysql_config --enable-mysqlnd --with-pdo-mysql=mysqlnd --with-pear --enable-sockets --with-freetype-dir=/usr --with-zlib --with-libxml-dir=/usr --with-xmlrpc --enable-zip --enable-fpm --enable-xml --enable-sockets --with-gd --with-zlib --with-iconv --enable-zip --with-freetype-dir=/usr/lib/ --enable-soap --enable-pcntl --enable-cli --with-curl

```

```
--下面的执行成功
./configure --prefix=/softw/php7 --with-config-file-path=/softw/php7 --enable-mbstring --with-openssl --enable-ftp --with-gd --with-jpeg-dir=/usr --with-png-dir=/usr --with-mysql=mysqlnd --with-mysqli=mysqlnd --with-pdo-mysql=mysqlnd --with-pear --enable-sockets --with-freetype-dir=/usr --with-zlib --with-libxml-dir=/usr --with-xmlrpc --enable-zip --enable-fpm --enable-xml --enable-sockets --with-gd --with-zlib --with-iconv --enable-zip --with-freetype-dir=/usr/lib/ --enable-soap --enable-pcntl --enable-cli --with-curl
```

```
./configure --prefix=/usr/local/php7.2 --with-curl --with-freetype-dir --with-gd
 --with-gettext --with-iconv-dir --with-kerberos --with-libdir=lib64 --with-libxml-dir
 --with-mysqli --with-openssl --with-pcre-regex --with-pdo-mysql --with-pdo-sqlite
 --with-pear --with-png-dir --with-xmlrpc --with-xsl --with-zlib --enable-fpm
 --enable-bcmath --enable-libxml --enable-inline-optimization --enable-mbregex
 --enable-mbstring --enable-opcache --enable-pcntl --enable-shmop --enable-soap
 --enable-sockets --enable-sysvsem --enable-xml --enable-zip
```



各个参数解释：

```
./configure --prefix=/softw/php7 \
--with-config-file-path=/softw/php7 \
--enable-mbstring \
--with-openssl \
--enable-ftp \
--with-gd \
--with-jpeg-dir=/usr \
--with-png-dir=/usr \
--with-mysql=/softw/mysql/mysql8 \
--with-mysqli=/softw/mysql/mysql8/bin/mysql_config \
--with-pdo-mysql=mysqlnd \
--with-pear \
--enable-sockets \
--with-freetype-dir=/usr \
--with-zlib \
--with-libxml-dir=/usr \
--with-xmlrpc \
--enable-zip \ 
--enable-fpm \
--enable-xml \
--enable-sockets \
--with-gd \
--with-zlib \
--with-iconv \
--enable-zip \
--with-freetype-dir=/usr/lib/ \
--enable-soap \
--enable-pcntl \
--enable-cli \
--with-curl 
```

make && make install 

安装过程会比较漫长。。。。。。。

my_global.h: No such file or directory

```
yum install mysql-devel
```



#### 复制配置文件到相应目录

```
cp /softw/php7/php-7.2.27/php.ini-development /softw/php7/lib/php.ini

cp /softw/php7/etc/php-fpm.conf.default /softw/php7/etc/php-fpm.conf

cp /softw/php7/sapi/fpm/php-fpm /softw/php7/bin

cp /softw/php7/etc/php-fpm.d/www.conf.defaulte /softw/php7/etc/php-fpm.d/www.conf 
```

需要着重提醒的是，如果文件不存在，则阻止 Nginx 将请求发送到后端的 PHP-FPM 模块， 以避免遭受恶意脚本注入的攻击。

将 php.ini 文件中的配置项 [cgi.fix_pathinfo](http://hacksteven.com/?p=107#ini.cgi.fix-pathinfo) 设置为 0 。

添加环境变量

```
vim /etc/profile

```

创建一个用户用于php-fpm的运行

useradd www-php

修改 /softw/php7/etc/php-fpm.d/www.conf 

user = www-php

group = www-php



在nginx 添加相应的配置即可！

启动php-fpm  默认端口为9000

在nginx的html目录下新建 showInfo.php

```
<?php
	echo phpinfo();
?>
```







4.

#### **Tips**

php7.2版本之后不再支持mysql