1.注意springboot的自动配置和sping ioc的自动装配的区别
自动配置：是springboot提供的 实现通过jar包依赖 能够自动配置应用程序 。
比如：我们引入spring-boot-starter-web之后 就自动引入了 spring mvc的jar包 然后 springboot 会进行自动配置spring mvc
减少之前在使用spring mvc的时候 一大堆xml文件或者 注解 是不是很牛逼
自动装配：是spring提供的IOC的注入方式 

2.自动配置是怎么实现的？
主要是通过@SpringBootApplication 这个复杂的注解实现的

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {}
```

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration{}
```

主要是这个类 AutoConfigurationImportSelector

由spring ioc的知识 我们知道 一个类要想被容器托管 有很多的方式(xml,注解)  还有有个import方式
其中import方式共有两种类型
implements ImportBeanDefinitionRegistrar  通过 public void registerBeanDefinitions 方法在容器里面注入bean
 implements DeferredImportSelector    通过public String[] selectImports  方法在容器里面注入bean
 
 注入的时机是context.refresh()的时候 (具体时机 ？？)
 
 所以springboot 自动配置的大概原理就是 
 springboot在启动的过程中中 在执行context.refresh() 方法的时候 会进行通过资源加载器 查找配置文件  然后根据配置条件(配置文件的是否)
 将配置类 加载进入容器里面 完成springboot的自动配置





















