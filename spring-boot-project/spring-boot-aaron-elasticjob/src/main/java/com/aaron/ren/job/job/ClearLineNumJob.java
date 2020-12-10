package com.aaron.ren.job.job;

import com.aaron.ren.job.config.SimpleJobConfig;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix="jobs.testjob")
public class ClearLineNumJob extends SimpleJobConfig implements SimpleJob {

	@Autowired
	private ZookeeperRegistryCenter regCenter;

	@PostConstruct
	public void init(){
		new SpringJobScheduler(this, regCenter, getJobConfiguration()).init();
	}


	@Override
	public void execute(ShardingContext context) {
		long start=System.currentTimeMillis();
		System.out.println("=========测试定时任务=========="+System.currentTimeMillis()/1000);
		long end=System.currentTimeMillis();
	}

}


