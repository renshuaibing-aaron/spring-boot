1.

@EnableAutoConfiguration的理念和做事方式其实一脉相承，简单概括一下就是，借助@Import的支持，收集和注册特定场景相关的bean定义。
@EnableAutoConfiguration自动配置的魔法骑士就变成了：从classpath中搜寻所有的META-INF/spring.factories配置文件，
并将其中org.springframework.boot.autoconfigure.EnableutoConfiguration对应的配置项通过反射（Java Refletion）
实例化为对应的标注了@Configuration的JavaConfig形式的IoC容器配置类，然后汇总为一个并加载到IoC容器


2.深度探究SpringApplication的执行流程
  我的理解 是根据环境里面的jar文件 读取里面的配置文件 然后进行自动化配置 
  这其中进行一些操作  
  
  https://mp.weixin.qq.com/s?__biz=MzU2Njg3OTU1Mg==&mid=2247485267&idx=1&sn=6d22840e2af18a3f5e16b2c6513aa1c5&chksm=fca4f37ccbd37a6afbf65dfb336beffda06125ac8fcdf5c2e275fdfe4ba7f61160c3db6e49a6&scene=27#wechat_redirect
  
  
  https://blog.csdn.net/zlc3323/article/details/100137222
  
  https://www.jianshu.com/p/63ad69c480fe
  
  
2.
初始化SpringApplication ：包括环境变量 资源 构造器 监听器
开始启动：启动监听(listeners)  加载配置(enviroment) 创建上下文(applicationContext)
自动化配置
Tomcat初始化
Tomcat接收请求
Spring MVC初始化 (特别要注意这里,这里跟sring mvc 很不一样,这里先先接受请求后 才初始化mvc)



























