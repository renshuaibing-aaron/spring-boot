package com.aaron.ren.job.datasorces;

import com.aaron.ren.job.utils.RSAEncrypt;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceConfig.class);

	@Value("${spring.datasource.druid.first.password}")
	private String password;

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.druid.first")
	public DataSource firstDataSource(){
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	public DynamicDataSource dataSource(DataSource firstDataSource) {
		DruidDataSource d=(DruidDataSource) firstDataSource;
		try {
			/*byte[] dd = RSAEncrypt.decrypt(RSAEncrypt.getPublicKey(),Base64.decodeBase64(password));
			String realStr=new String(dd,"utf-8");
			*/
			d.setPassword(password);
		} catch (Exception e) {
			LOGGER.error("解密数据库密码失败",e);
			System.exit(0);
		}
		Map<String, DataSource> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceNames.FIRST, firstDataSource);
		return new DynamicDataSource(firstDataSource, targetDataSources);
	}
}
