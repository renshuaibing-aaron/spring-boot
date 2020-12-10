package com.aaron.rocketmq.batch;

// Demo02Message.java

import lombok.Data;

@Data
public class Demo02Message {

	public static final String TOPIC = "DEMO_02";

	/**
	 * 编号
	 */
	private Integer id;

	// ... 省略 set/get/toString 方法

}