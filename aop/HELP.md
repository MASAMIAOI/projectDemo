## aop 相关概念

(1) Aspect（切面）： Aspect 声明类似于 Java 中的类声明， 切面是通知和切点的结合；

(2) Joint point（连接点）：表示在程序中明确定义的点，典型的包括方法调用，对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它 joint point；

(3)Pointcut（切点）：表示一组 joint point，这些 joint point 或是通过逻辑关系组合起来，或是通过通配、正则表达式等方式集中起来，它定义了相应的 Advice 将要发生的地方；

(4) Advice（通知）：Advice描述了切面何时以及如何执行增强处理 ，通过 before、after 和 around 来区别是在每个 joint point 之前、之后还是代替执行的代码，他定义了在 Pointcut 里面定义的程序点具体要做的操作；

相关注解：
@Aspect： 定义切面类，把当前类标识为一个切面供容器读取；

@Pointcut： 切点
Pointcut是植入Advice的触发条件；Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码；

@Around：环绕增强；

@AfterReturning：后置增强，方法正常退出时执行；

@Before：前置增强，方法之前执行；

@AfterThrowing：抛出异常执行；

@After: final增强，不管是抛出异常或者正常退出都会执行；

