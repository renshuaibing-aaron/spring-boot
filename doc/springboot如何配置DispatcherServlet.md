1.其实这个题目起的有点小了 ,应该是问在springboot里面是怎么去掉web.xml的？
我们知道在最早的版本里面 是有web.xml的 tomcat会根据里面的配置来加载servlet filter listener
但是springboot 乃至spring 里面 web.xml 早就删除了 需要理解spring和spring boot里面删除web.xml
后是怎么配置这些tomcat 包含的servlet的？
http://svip.iocoder.cn/RocketMQ/Interview/
https://www.cnkirito.moe/servlet-explore/


1.问题springboot 如何配置dispatchServlet 或者是springboot  如何配置mvc 

自己的理解 
从spring 到spring mvc 到springboot 在spring mvc里面关键是dispatchservlet的配置，在之前的模式里面
可以知道 需要配置xml相关的东西
在boot里面是这样配置的
首先根据自动配置原理(pom 文件引入的jar文件) 然后利用DispatcherServletAutoConfiguration配置相应的Bean
即是dispatchservlet 
然后创建web server(内嵌的servlet容器) 在start过程中会执行上下文里ServletContextInitializer类型的Bean
完成初始化和配置



2.