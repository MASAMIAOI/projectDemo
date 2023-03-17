# 邮件发送

使用 thymeleaf + mail 发送邮件
使用 freemarker + mail 发送邮件

# thymeleaf 使用说明

注意：

* 使用@Controller注解而不是@RestController，因此@RestController会将结果解析后直接展示字符串内容，而不能根据字符串获取对应的模板名称
* 服务接口需要加入Model对象作为参数，并使用Model对象来绑定需要传递的数据，以便在thymeleaf中使用

~~~
<html xmlns:th="http://www.thymeleaf.org">:这句声明使用thymeleaf标签
<p th:text="${hello}"></p>:这句使用 th:text="${变量名}" 表示 使用thymeleaf获取文本数据，类似于EL表达式。
~~~