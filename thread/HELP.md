# thread

> 线程相关的实现方式

## 1 继承 thread

## 2 实现 Runnable

## 3 实现 callable， 利用 future 来获取线程结果

## 4 使用线程池

> 线程池好处：

* 1.提高响应速度（减少了创建新线程的时间）
* 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
* 3.便于线程管理

> 核心参数：

* corePoolSize：核心池的大小
* maximumPoolSize：最大线程数
* keepAliveTime：线程没有任务时最多保持多长时间后会终止

> 步骤：
> 1.以方式二或方式三创建好实现了Runnable接口的类或实现Callable的实现类
> 2.实现run或call方法
> 3.创建线程池
> 4.调用线程池的execute方法执行某个线程，参数是之前实现Runnable或Callable接口的对象

## 5 使用匿名类


