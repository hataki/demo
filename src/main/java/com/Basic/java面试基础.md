# java基础面试

## equals与==区别

==是运算符，用于基本数据比较的是值，用于引用对象比较的是内存地址，

equals不重写，底层就是==实现，没区别

（String是重写了equals的）

## 为什么要重写hashcode

hash保证了一个对象只对应一个hash值，Object中定义的hashcode方法生成的hash与对象属性无关，自定义hash可以与属性有关！

HashMap中，接住equals和hashcode来完成数据存储的，根据对象的内容查询转换为根据索引查询

## hashmap在1.8中的优化

**数据结构**

1.7 数组+单向链表，链表插入使用**头插法**

1.8 数组+单向链表+红黑树，链表插入使用**尾插法**

**hash函数**

1.8的hash()中，将hash值的高位前16位参与到取模运算中，是的计算结果的不稳定性增强，降低发生哈希碰撞的概率

**扩容**

1.7 全部元素都会换位

1.8 部分

## hashmap线程安全的方式

synchronize

currenthashmap cas 非公平锁，分片竞争，