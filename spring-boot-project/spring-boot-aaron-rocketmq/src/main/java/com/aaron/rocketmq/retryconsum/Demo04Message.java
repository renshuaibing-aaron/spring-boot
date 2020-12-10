package com.aaron.rocketmq.retryconsum;

import lombok.Data;

@Data
public class Demo04Message {

	public static final String TOPIC = "DEMO_04";

	/**
	 * 编号
	 */
	private Integer id;

	// ... 省略 set/get/toString 方法

}