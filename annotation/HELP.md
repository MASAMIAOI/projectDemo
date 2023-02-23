# 注解测试工程demo 相关 内容

## @Inherited 注解使用

> 标明被标明的注解在使用中是否可以被继承

## ElementType 参数解释

- @Target(ElementType.TYPE)   //接口、类、枚举

- @Target(ElementType.FIELD) //字段、枚举的常量

- @Target(ElementType.METHOD) //方法

- @Target(ElementType.PARAMETER) //方法参数

- @Target(ElementType.CONSTRUCTOR)  //构造函数

- @Target(ElementType.LOCAL_VARIABLE)//局部变量

- @Target(ElementType.ANNOTATION_TYPE)//注解

- @Target(ElementType.PACKAGE) ///包

## Field常用方法

- getType()： 获取属性声明时类型对象（返回class对象）

- getGenericType() ： 返回属性声的Type类型

- getName() ： 获取属性声明时名字

- getAnnotations() ： 获得这个属性上所有的注释

- getModifiers() ： 获取属性的修饰

- isEnumConstant() ： 判断这个属性是否是枚举类

- isSynthetic() ： 判断这个属性是否是 复合类

- get(Object obj) ： 取得obj对象这个Field上的值

- set(Object obj, Object value) ： 向obj对象的这个Field设置新值value

## AOP + 集合 注解

> 进行切面拦截某些内容。例如 上传文件大小校验 ， 限流校验 ，入参校验

| class           | Description        |
|-----------------|--------------------|
| AnnotationAop   | 入参校验切面             |
| FileUploadAop   | 文件校验切面             |
| LimitAop        | 限流切面               |

## RateLimiter 限流方法

- acquire() 获取一个令牌, 改方法会阻塞直到获取到这一个令牌, 返回值为获取到这个令牌花费的时间

- acquire(int permits) 获取指定数量的令牌, 该方法也会阻塞, 返回值为获取到这 N 个令牌花费的时间

- tryAcquire() 判断时候能获取到令牌, 如果不能获取立即返回 false

- tryAcquire(int permits) 获取指定数量的令牌, 如果不能获取立即返回 false

- tryAcquire(long timeout, TimeUnit unit) 判断能否在指定时间内获取到令牌, 如果不能获取立即返回 false

- tryAcquire(int permits, long timeout, TimeUnit unit) 同上

## 限流相关概念

**PV**
> page view 页面总访问量，每刷新一次记录一次。

**uv**
> unique view 客户端主机访问，指一天内相同IP的访问记为1次。

**QPS**
> query per second,即每秒访问量。qps很大程度上代表了系统的繁忙度，没次请求可能存在多次的磁盘io，网络请求
> 多个cpu时间片，一旦qps超过了预先设置的阀值，可以考量扩容增加服务器，避免访问量过大导致的宕机。

**RT**
> response time,每次请求的响应时间,直接决定用户体验性。

### 限流常见算法

- 时间窗口算法
- 漏桶算法
- 令牌算法

### RateLimiter 限流方法说明

#### SmoothBursty

~~~
平稳限流
~~~

#### smoothWarmingUp

~~~
预热限流
~~~

### nginx 配置文件限流方式

~~~
nginx http配置：
    #请求数量控制，每秒20个
    limit_req_zone $binary_remote_addr zone=one:10m rate=20r/s;
    #并发限制30个
    limit_conn_zone $binary_remote_addr zone=addr:10m;
 
    server块配置
    limit_req zone=one burst=5;
    limit_conn addr 30;
~~~




