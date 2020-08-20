
#### DesignPatterns 设计模式

-- 封装变化 --

* SingleTon 单例模式
* Strategy 策略模式
* AbstractFactory 抽象工厂模式
* Facada 门面模式 （对外）
* Mediate 调停者模式 （对内）
* _典型应用 -- 中间件_  
* ChainOfResponsibility 责任链模式
* Observer 观察者模式 ***essential*** (Observer/Listenre/Hook/Callback都是观察者模式)
* Composite 组合模式 --树状结构专用模式
* Flyweight 享元模式 --共享元数据(比如String)
* Proxy 代理模式 --静态/动态代理 (Spring AOP) !!! 被代理对象必须提供接口，否则无法动态代理 !!!
* Visitor 访问者模式 -- 一种将数据操作和数据结构分离的设计模式--> 各角色分离，提高扩展性；违反迪米特原则，修改成本过大
* Builder 构造器模式
* Bridge 桥接模式
* Command 命令行模式(Action动作/Transaction事务模式)
* Prototype 原型模式（又叫做克隆模式）
* Memento 回忆录模式
* TemplateMethod 模板方法模式（又叫 钩子模式）

--- 
### 指导思想
* 可维护性Maintainability
  * 修改功能，需要改动的地方越少，可维护性就越好
* 可复用性Reusability
  * 代码可以被重复利用
  * 写出自己总结的类库
* 可扩展性Extensibility/Scalability
  * 添加功能无需修改原来的代码
* 灵活性flexibility/mobility/adaptability
  * 代码接口可以灵活调用
---
---
### 单一职责原则
- Single Responsibility Principle 
- 一个类别太大，只负责单一的职责
- 高内聚，低耦合
---
### 开闭原则
- Open-Closed Principle
- 对扩展开放，对修改关闭
  - 尽量不要修改原来代码的情况下进行扩展
- 抽象化，多态是开闭原则的关键
---
### 里氏替换原则
* Liscov Substitution Principle
* 所有使用父类的地方，必须能够透明的使用子类对象
---
### 依赖倒置原则
* Dependency Inversion Principle
* 依赖倒置原则
  * 依赖抽象，而不是依赖具体
  * 面向抽象编程
---
### 接口隔离原则
* Interface Segregation Principle
* 每一个接口都应该承担独立的抉择，不干不该自己干的事情
  * Flyable Runnable 不该合二为一
  * 避免子类实现不需要实现的方法
  * 需要对客户提供接口的时候，只需要暴露最小的接口
---
### 迪米特法则
- Law of Demeter
- 尽量不要和陌生人说话
- 在迪米特法则中，对于一个对象，非陌生人包括一下几类：
  - 当前对象本身（this）
  - 一参数形式传入到当前对象方法中的对象
  - 当前对象的成员对象
  - 如果当前对象的成员对象是一个集合，那么集合中的元素也都是friends
  - 当前对象创建的对象
- 和其它类的耦合度变低
  