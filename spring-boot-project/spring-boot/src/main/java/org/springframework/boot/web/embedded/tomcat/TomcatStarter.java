package org.springframework.boot.web.embedded.tomcat;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 *
 * 这里需要注意的是 虽然TomcatStarter 实现了ServletContainerInitializer 符合Servlet3.0 规范的，但实际上并没有被 SPI 加载
 *
 * {@link ServletContainerInitializer} used to trigger {@link ServletContextInitializer
 * ServletContextInitializers} and track startup errors.
 *
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
class TomcatStarter implements ServletContainerInitializer {

	private static final Log logger = LogFactory.getLog(TomcatStarter.class);

	/**
	 * 这个是在springboot里面引入springmvc 的关键 这个是注入dispatchservlet类关键
	 * todo 注意分析这个类
	 */
	private final ServletContextInitializer[] initializers;

	private volatile Exception startUpException;

	TomcatStarter(ServletContextInitializer[] initializers) {
		this.initializers = initializers;
	}

	/**
	 *此处需要注意两点
	 * 1.这个方法是怎么触发的
	 * 2.这个方法的执行有哪些作用？
	 *    明显可以看出这里会执行initializers的onStartup 方法
	 * @param classes
	 * @param servletContext
	 * @throws ServletException
	 */
	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {

		System.out.println("============TomcatStarter#onStartup【加载Dispatchservlet？】====================");
		try {
			for (ServletContextInitializer initializer : this.initializers) {
				initializer.onStartup(servletContext);
			}
		}
		catch (Exception ex) {
			this.startUpException = ex;
			// Prevent Tomcat from logging and re-throwing when we know we can
			// deal with it in the main thread, but log for information here.
			if (logger.isErrorEnabled()) {
				logger.error("Error starting Tomcat context. Exception: " + ex.getClass().getName() + ". Message: "
						+ ex.getMessage());
			}
		}
	}

	public Exception getStartUpException() {
		return this.startUpException;
	}

}
