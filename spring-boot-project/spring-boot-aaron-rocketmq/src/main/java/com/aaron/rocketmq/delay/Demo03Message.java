package com.aaron.rocketmq.delay;

import lombok.Data;

@Data
public class Demo03Message {

	public static final String TOPIC = "DEMO_03";

	/**
	 * 编号
	 */
	private Integer id;

	// ... 省略 set/get/toString 方法

}
