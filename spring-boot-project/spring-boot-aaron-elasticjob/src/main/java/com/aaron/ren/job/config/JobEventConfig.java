package com.aaron.ren.job.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 定时器时事件数据源配置
 *
 */
@Configuration
public class JobEventConfig {

	@Bean
	public JobEventConfiguration jobEventConfiguration(DataSource dataSource) {
		return new JobEventRdbConfiguration(dataSource);
	}
}
