package com.aaron.ren.job.config;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJobConfig {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected String name;

	protected String cron;

	protected int shardingCount;

	protected String shardingItemParameters;

	protected String description;

	protected String jobParameter;

	public String getCron() {
		return cron;
	}

	public int getShardingCount() {
		return shardingCount;
	}

	public String getShardingItemParameters() {
		return shardingItemParameters;
	}

	public String getDescription() {
		return description;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public void setShardingCount(int shardingCount) {
		this.shardingCount = shardingCount;
	}

	public void setShardingItemParameters(String shardingItemParameters) {
		this.shardingItemParameters = shardingItemParameters;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobParameter() {
		return jobParameter;
	}

	public void setJobParameter(String jobParameter) {
		this.jobParameter = jobParameter;
	}

	/**
	 * 配置测试定时任务
	 *
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected LiteJobConfiguration getJobConfiguration(){
		logger.info("配置定时任务:{}({})\r\ncron:{}\r\nshardingCount:{}\r\nshardingItemParameters:{}",name,description,cron,shardingCount,shardingItemParameters);
		return LiteJobConfiguration.newBuilder(
				new SimpleJobConfiguration(
						JobCoreConfiguration.newBuilder(getClass().getCanonicalName(), cron, shardingCount)
								.shardingItemParameters(shardingItemParameters).description
								(description).jobParameter(jobParameter).build(),getClass().getCanonicalName())
		).overwrite(true).build();
	}


}
