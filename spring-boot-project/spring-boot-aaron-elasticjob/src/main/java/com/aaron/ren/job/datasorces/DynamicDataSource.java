package com.aaron.ren.job.datasorces;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	public DynamicDataSource(javax.sql.DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(new HashMap<Object,Object>(targetDataSources));
		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getDataSource();
	}

	public static void setDataSource(String dataSource) {
		CONTEXT_HOLDER.set(dataSource);
	}

	public static String getDataSource() {
		return CONTEXT_HOLDER.get();
	}

	public static void clearDataSource() {
		CONTEXT_HOLDER.remove();
	}

}
