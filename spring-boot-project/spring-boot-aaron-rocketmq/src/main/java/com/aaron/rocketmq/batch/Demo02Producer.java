package com.aaron.rocketmq.batch;

// Demo02Producer.java

import com.aaron.rocketmq.transaction.Demo07Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Demo02Producer {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	public SendResult sendBatch(Collection<Integer> ids) {
		// <X> 创建多条 Demo02Message 消息
		List<Message> messages = new ArrayList<>(ids.size());
		for (Integer id : ids) {
			// 创建 Demo02Message 消息
			Demo02Message demo02Message = new Demo02Message();
			demo02Message.setId(id);
			// 构建 Spring Messaging 定义的 Message 消息

			Message message = MessageBuilder.withPayload(demo02Message).build();
			messages.add(message);
		}
		// 同步批量发送消息
		return rocketMQTemplate.syncSend(Demo02Message.TOPIC, messages, 30 * 1000L);
	}

}