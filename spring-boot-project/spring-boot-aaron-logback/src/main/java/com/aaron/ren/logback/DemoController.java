package com.aaron.ren.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class DemoController {

	public static ScheduledExecutorService tableMetaExcutor = new ScheduledThreadPoolExecutor(1	);



	/*RestController
	 * 系统自动直接将实体序列化为JSON返回。
	 * */
	@RequestMapping("/test")
	public void constant() {
		System.out.println("CommandLineRunner example start");
		log.info("========23432=============");
	}



}
