# jvm调优

## jvm基础

jvm组成

classLoader（+ java类库），字节码解释器，jit库即时编译器，执行引擎 ---> 硬件

jvm是一个跨语言平台，目前实现用的orecle的hotspot，也有很多收费的商业版jvm

jdk > jre >jvm  

jvm用来执行class文件，只跟class文件有关系，其他语言还有scala，kotlin等（class按照一定的格式，由二进制字节码组成，是一种符合jvm的规范）

### class文件详解

开头固定 是一个magic value ： cafe babe  

0000 0034  最低jvm version 和最高 jvm version

constant pool count 常量池数量

constant pool 数量是上面count-1





**java内8中原子性操作**

lock

read

load

use

assign

store

write

unlock

volatile保证可见性

但是，long和double加了volatile可以保证其原子性

## 类加载器

**Class Loading Linking Initializing  类在内存中的加载的初始化**

### class cycle



classLoader  把二进制字节码放入内存当中，同时生成了一个class类的对象，该对象指向了上述内存地址（在堆heap中，不是栈stack！！，class在metesapce当中），通过class对象，来访问二进制code

class采用双亲委派机制按需要动态加载类（由左到右，在由右到左）

CustomClassLoader  ---  App --- Extension --- Bootstrap

why这么做？  主要是为了安全

for example: 自定义一个Java.lang.String 自定义类，直接覆盖掉sun核心String包，这样就可以在自定义String中做一些非法事情等等、、 