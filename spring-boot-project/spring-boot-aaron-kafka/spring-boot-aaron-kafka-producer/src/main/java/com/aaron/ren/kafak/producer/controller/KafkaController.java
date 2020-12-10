package com.aaron.ren.kafak.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;

	@GetMapping("/message/send")
	public boolean send(@RequestParam String message) throws InterruptedException {

		for(int i=1;i<=500;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("发送数据"+i);
			kafkaTemplate.send("testMuliTopic1",message);
		}

		return true;
	}

	/**
	 * 指定在1分区发送内容
	 */
	@GetMapping("/message/send2")
	public String show() {
		kafkaTemplate.send("testTopic","1", "你好");
		return "发送成功";
	}

}