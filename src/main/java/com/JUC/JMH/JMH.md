# JMH教程

### 一、注解讲解（常用）

@Benchmark

​	需要测试的方法

@Warmup( iterations =1 , time = 3 )

​	预热（多少次；多少时间）：当一段代码执行次数非常多的时候，jvm会对其进行优化，转换为jit（just in time，本地代码exe，不再是class文件）来提高效率

@Fork(5)

​	使用多少个线程去执行需要测试的程序

@BenchmarkMode(Mode.Throughput)

​	测试模式，吞吐量模式（执行一次要多少秒...每秒执行多少次）

@Measurement( iterations = 1 , time = 3)

​	整体需要测试（迭代）多少次，一般iteration设置比较大