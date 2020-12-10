package com.aaron.rocketmq.guanbo;

import lombok.Data;

@Data
public class Demo05Message {

	public static final String TOPIC = "DEMO_05";

	/**
	 * 编号
	 */
	private Integer id;

	// ... 省略 set/get/toString 方法

}