### Prototype 原型模式/克隆模式

~~~
接口Cloneable里面的clone方法，是protect native的
protected native Object clone() throws CloneNotSupportedException;
亦即，protected说明只有子类才可以调用，子类继承了这个接口之后，是不用实现clone()方法，
同时native说明是本地方法区方法，java没有实现而是由C++实现，java只是调用；
---- 要想调用的话，必须要重写为public方法 
~~~
---
Object里面的clone()方法怎么实现?
---

Prototype 模式适用于 使用大量相同对象，属性多，且属性指定很麻烦的时候；

* 区分 深克隆/浅克隆 *
