package com.aaron.ren.job.datasorces.aspect;

import com.aaron.ren.job.datasorces.DataSourceNames;
import com.aaron.ren.job.datasorces.DynamicDataSource;
import com.aaron.ren.job.datasorces.annotation.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("@annotation(com.aaron.ren.job.datasorces.annotation.DataSource)")
	public void dataSourcePointCut() {
		//
	}

	@Around("dataSourcePointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();

		DataSource ds = method.getAnnotation(DataSource.class);
		if(ds == null){
			DynamicDataSource.setDataSource(DataSourceNames.FIRST);
			logger.debug("set datasource is {}", DataSourceNames.FIRST);
		}else {
			String dsName=ds.name();
			DynamicDataSource.setDataSource(dsName);
			logger.debug("set datasource is {}",dsName);
		}

		try {
			return point.proceed();
		} finally {
			DynamicDataSource.clearDataSource();
			logger.debug("clean datasource");
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
