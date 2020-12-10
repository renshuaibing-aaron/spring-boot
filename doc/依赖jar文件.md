1.spring boot 各个maven依赖的作用是什么？

        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.2.5.RELEASE</version>
            <relativePath/> 
        </parent>
这个是spring boot的父依赖 一般 用spring 自动生成的结构就是这样  其实点进去可以看看  就是一个配置信息  一般使用这个 使用这个parent 就不能使用其他的父项目

         <dependencyManagement>
        <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>1.5.8.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            </dependencyManagement>
 这个依赖和上面是一样的  里面配置了springboot的一些常见jar  在实际的引用过程中 可以不用具体的写版本号 
 
 
    <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-web</artifactId>
 		</dependency>
 		
这个就是springboot的web依赖 这里面默认设置了mvc的配置

 如果一个spring boot的web项目需要使用数据库+ mybatis 需要依赖下面三个
         <dependency>
             <groupId>org.mybatis.spring.boot</groupId>
             <artifactId>mybatis-spring-boot-starter</artifactId>
         </dependency> 
           
           mybatis的依赖
         
             <dependency>
                         <groupId>mysql</groupId>
                         <artifactId>mysql-connector-java</artifactId>
                     </dependency>
            数据库连接依赖         
                     
          	   <dependency>
          		   <groupId>org.springframework.boot</groupId>
          		   <artifactId>spring-boot-starter-jdbc</artifactId>
          	   </dependency>
          	   
         这个是数据库连接池依赖，因为mybatis-spring需要用数据源  可以用最简单的spring实现的jdbc  这个就是简单的数据源的
         每次都建立新的连接  
         如果需要数据库连接池 可以使用aibaba的drubid
         
             <dependency>
                         <groupId>com.alibaba</groupId>
                         <artifactId>druid-spring-boot-starter</artifactId>
                         <version>${druid.version}</version>
                     </dependency>          