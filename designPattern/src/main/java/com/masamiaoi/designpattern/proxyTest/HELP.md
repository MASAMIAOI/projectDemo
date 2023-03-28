## 静态代理 和 动态代理

> Proxy类下边的方法newProxyInstance * public static Object newProxyInstance(ClassLoader loader,
> 参数1:实现的接口的类加载器 * Class<?>[] interfaces,
> 参数2:基于接口的字节码文件对象数组 * InvocationHandler h)
> 参数3:是接口InvocationHandler :代理的处理程序 * throws IllegalArgumentException *
> 参数3是一个接口:自定一个类实现这个接口 * 重写这个接口中的invoke方法 * Object invoke(Object proxy,Method method,Object[] args)throws Throwable

* 静态代理

> 静态代理相当于是多写了一个代理类，在调用的时候调用的是代理类，在代理类中的处理还是原生的处理逻辑，不过在前后添加上需要添加的代码。
> 缺点：需要为每一个被代理的对象都创建一个代理类。

**特点**

~~~
    代理角色和真实角色都需要实现同一个接口,
    真实角色专注于自己的事情,
    代理角色目的就是帮助真实角色完成一件事情
    多线程的实现方式2:实现一个接口Runnable 使用的就是”静态代理”的思想
~~~

* 动态代理

> Java标准库提供了动态代理功能，允许在运行期动态创建一个接口的实例； 动态代理是通过 Proxy 创建代理对象，然后将接口方法“代理”给 InvocationHandler 完成的。

### staticTest

> 静态代理相关示例

### dynamicTest

> 动态代理相关示例