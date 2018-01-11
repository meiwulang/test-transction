# test-transction
测试spring事务传播    

测试条件：    
@Transactional修饰在service层类上或者其方法上    
测试结论： 
>
>1、被@Transactional修饰的方法调用其他@Transactional修饰的方法 只会创建一个Transaction并绑定到一个sqlsession上
>
>2、control一个方法层调用service层单个方法（例如test1）     
>    i   若service层方法之间不存在调用关系，会创建多个事务 事务之间不存在关联 ，事务之间的异常回滚互补影响   
>    ii  若service层方法之间存在调用关系（例如 service1中test1.调用了service1的test2 （同一对象的方法））,    
>    会创建一个事务，（绑定一个sqlsession），故多个方法的回滚/提交会统一操作   
>
>3、control层一个方法调用service层多个方法（例如test1、test3、test4,）   
>     i   若service这几个方法之间不存在调用关系，则test1、test3、test4各自的方法回滚/提交分开处理，互不影响   
>     ii  若service这几个方法之间存在调用关系，则存在调用关系的方法只开启一个事务，回滚/提交由调用者的事务决定，   
>     其他方法各自的事务方法回滚/提交分开处理，互不影响   
>4、当 方法内部调用其他对象的含有事务的方法 满足spring事务设计 （例如 control.test 调用service1.test1 service1.test1内部调用了 service2.test2）
>5、事务的实现方式是aop环切，如果想解决结论2的问题 ，可以通过修改
*开启支持@AspectJ风格的切面声明 添加注解 @EnableAspectJAutoProxy(exposeProxy=true)*
*代码内部调用改为aop调用  参考方法com.test.jdbc.service.TestTransctional.test1()*
*并引入以来around文件所在的jar*
>        &lt;dependency>  
>            &lt;groupId>org.springframework</groupId>  
>            &lt;artifactId>spring-aspects</artifactId>  
>        &lt;/dependency> 

