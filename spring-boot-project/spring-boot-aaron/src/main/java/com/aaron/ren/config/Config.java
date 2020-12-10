package com.aaron.ren.config;

import com.aaron.ren.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	public Person createPerson(){
		return new Person();
	}

}
