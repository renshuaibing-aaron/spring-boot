package com.aaron.redission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testStringSetKey() throws InterruptedException {
		stringRedisTemplate.opsForValue().set("yunai", "shuai");

		String str = stringRedisTemplate.opsForValue().get("yunai");
		logger.info(str);
		new CountDownLatch(1).await();
	}


	@Test
	public void testStringSetKey2() {
		//stringRedisTemplate.op
	}
}
