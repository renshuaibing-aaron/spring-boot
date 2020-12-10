package com.aaron.ren.kafak.consumer.listener;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.*;

@Component
public class ConsumerListener {

	private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
	private ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingDeque<Runnable>(1024),
			threadFactory,
			new ThreadPoolExecutor.AbortPolicy()
	);

	@KafkaListener(topics = "testMuliTopic1")
	public void onMessage(String message) throws InterruptedException {

		pool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("接收到数据" + message + System.currentTimeMillis());
				//模拟一秒处理一条数据
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	//配置topic和分区,可以配置多个
	//topic为队列名称
	//partitions表示值的的分区，这里指定了0和2分区
	//partitionOffsets表示详细的指定分区，partition表示那个分区，initialOffset表示Offset的初始位置
	@KafkaListener(topicPartitions =
			{ @TopicPartition(topic = "testTopic",
					partitions = { "0", "2" },
					partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "4"))
			}
	)public void consumer(ConsumerRecord consumerRecord){
		Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
		if(kafkaMassage.isPresent()){
			Object o = kafkaMassage.get();
			System.out.println("接收到的消息是："+o);
		}

	}

}