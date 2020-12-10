package com.aaron.rocketmq.sequentialmessag;

import lombok.Data;

@Data
public class Demo06Message {

	public static final String TOPIC = "DEMO_06";

	/**
	 * 编号
	 */
	private Integer id;

	// ... 省略 set/get/toString 方法

}
