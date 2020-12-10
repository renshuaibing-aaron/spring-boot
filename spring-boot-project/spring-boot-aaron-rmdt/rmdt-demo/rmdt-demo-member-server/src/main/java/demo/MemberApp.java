package demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.rmdt.demo.mapper")
@ComponentScan(basePackages = {"org.rmdt.*"})
public class MemberApp {

    public static void main(String[] args) {
        SpringApplication.run(MemberApp.class,args);
    }
}
