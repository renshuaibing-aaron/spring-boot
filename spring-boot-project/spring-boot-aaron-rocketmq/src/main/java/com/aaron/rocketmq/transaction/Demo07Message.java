package com.aaron.rocketmq.transaction;

import lombok.Data;

@Data
public class Demo07Message {

	public static final String TOPIC = "DEMO_07";

	/**
	 * 编号
	 */
	private Integer id;


	// ... 省略 set/get/toString 方法

}
