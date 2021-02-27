### 阿里云服务器手动分配SWAP

#### virtual memory exhausted: Cannot allocate memory

问题原因：由于物理内存本身很小，且阿里云服务器并没有分配swap空间，当物理内存不够用时，

​              物理内存中暂时不用的内容没地方转存。

解决方法：手动分配一个swap空间

​             dd if=/dev/zero of=/swap bs=1024 count=1M     #创建一个大小为1G的文件/swap
​             mkswap /swap                                                 #将/swap作为swap空间
​             swapon /swap                                                  #enable /swap file  for paging and swapping

修改建议权限 0644 -> 0600 

chmod 0600 /swap 

​             echo "/swap swap swap sw 0 0" >> /etc/fstab    #Enable swap on boot, 开机后自动生效

```
[root@iZ2zej2liju58hin5ltuflZ ~]# dd if=/dev/zero of=/swap bs=1024 count=1M
记录了1048576+0 的读入
记录了1048576+0 的写出
1073741824字节(1.1 GB)已复制，7.81136 秒，137 MB/秒
[root@iZ2zej2liju58hin5ltuflZ ~]# mkswap /swap
正在设置交换空间版本 1，大小 = 1048572 KiB
无标签，UUID=8f2768b4-4188-4b12-bcc2-a6c87bb404e6
[root@iZ2zej2liju58hin5ltuflZ ~]# swapon /swap
swapon: /swap：不安全的权限 0644，建议使用 0600。
[root@iZ2zej2liju58hin5ltuflZ ~]# chmod 0600 /swap
[root@iZ2zej2liju58hin5ltuflZ ~]# echo "/swap swap swap sw 0 0" >> /etc/fstab


```

