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

1.Loading 
	1.双亲委派，主要是为了类加载的安全机制
	2.LazyLoading五种情况
	  - -new getstatic putstatic invokestatic 指令 ，访问final变量除外
	  - -java.lang.reflect 对类的反射调用
	  - -初始化子类，父类首先初始化
	  - -虚拟机启动时，被执行的主类必须初始化
	  - -动态语言支持java.lang.invoke.MethodHandle解析的结果为REF_getstatic REF_putstatic REF_invokestatic的方法句柄时，该类必须初始化	
	3. ClassLoader的源码
   		1. findInCache -> parent.loadClass -> findClass()
        2. 自定义类加载器
           1. extends ClassLoader
           2. overwrite findClass() -> defineClass(byte[] -> Class clazz)
           3. 加密
           4. <font color=red>第一节课遗留问题：parent是如何指定的，打破双亲委派，学生问题桌面图片</font>
              1. 用super(parent)指定
              2. 双亲委派的打破
                 1. 如何打破：重写loadClass（）
                 2. 何时打破过？
                    1. JDK1.2之前，自定义ClassLoader都必须重写loadClass()
                    2. ThreadContextClassLoader可以实现基础类调用实现类代码，通过thread.setContextClassLoader指定
                    3. 热启动，热部署
                       1. osgi tomcat 都有自己的模块指定classloader（可以加载同一类库的不同版本）

        3. 混合执行 编译执行 解释执行

           1. 检测热点代码：-XX:CompileThreshold = 10000

4. Linking 
   1. Verification
      1. 验证文件是否符合JVM规定
   2. Preparation
      1. 静态成员变量赋默认值
   3. Resolution
      1. 将类、方法、属性等符号引用解析为直接引用
         常量池中的各种符号引用解析为指针、偏移量等内存地址的直接引用

5. Initializing

   1. 调用类初始化代码 <clinit>，给静态成员变量赋初始值









classLoader  把二进制字节码放入内存当中，同时生成了一个class类的对象，该对象指向了上述内存地址（在堆heap中，不是栈stack！！，class在metesapce当中），通过class对象，来访问二进制code

class采用双亲委派机制按需要动态加载类（由左到右，在由右到左）

CustomClassLoader  ---  App --- Extension --- Bootstrap

why这么做？  主要是为了安全

for example: 自定义一个Java.lang.String 自定义类，直接覆盖掉sun核心String包，这样就可以在自定义String中做一些非法事情等等、、 