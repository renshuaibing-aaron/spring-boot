package com.aaron.rocketmq.cluster;

// Demo01Message.java

import lombok.Data;

@Data
public class Demo01Message {

	public static final String TOPIC = "DEMO_01";

	/**
	 * 编号
	 */
	private Integer id;

	// ... 省略 set/get/toString 方法

}