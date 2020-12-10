package com.aaron.dataredis;

import com.aaron.dataredis.bean.UserCacheObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {


	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void testStringSetKey() {
		stringRedisTemplate.opsForValue().set("yunai", "shuai");
	}


	@Test
	public void testStringSetKeyUserCache() {
		UserCacheObject object = new UserCacheObject();
		object.setId(1);
		object.setName("芋道源码");
		object.setGender(1);

		String key = String.format("user:%d", object.getId());
		redisTemplate.opsForValue().set(key, object);
	}

	@Test
	public void testStringGetKeyUserCache() {
		String key = String.format("user:%d", 1);
		UserCacheObject value = (UserCacheObject) redisTemplate.opsForValue().get(key);
		System.out.println(value.getId());

	}


}
