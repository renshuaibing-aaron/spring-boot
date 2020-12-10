package com.aaron.ren.consumer;

import com.aaron.ren.service.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Reference
	private HelloService helloService;

	@RequestMapping(value = "/hello")
	public String hello() {
		String hello = helloService.sayHello("world");
		System.out.println(helloService.sayHello("BJQ"));
		return hello;
	}

}