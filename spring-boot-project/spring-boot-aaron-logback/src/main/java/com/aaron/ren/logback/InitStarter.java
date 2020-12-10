package com.aaron.ren.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class InitStarter implements CommandLineRunner{



	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner example start");
		log.info("========23432=============");
	}
}
