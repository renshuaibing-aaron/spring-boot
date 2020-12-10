package org.springframework.boot.web.servlet;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * A {@link ServletContextInitializer} to register {@link DelegatingFilterProxy}s in a
 * Servlet 3.0+ container. Similar to the {@link ServletContext#addFilter(String, Filter)
 * registration} features provided by {@link ServletContext} but with a Spring Bean
 * friendly design.
 * <p>
 * The cluster name of the actual delegate {@link Filter} should be specified using the
 * {@code targetBeanName} constructor argument. Unlike the {@link FilterRegistrationBean},
 * referenced filters are not instantiated early. In fact, if the delegate filter cluster is
 * marked {@code @Lazy} it won't be instantiated at all until the filter is called.
 * <p>
 * Registrations can be associated with {@link #setUrlPatterns URL patterns} and/or
 * servlets (either by {@link #setServletNames name} or via a
 * {@link #setServletRegistrationBeans ServletRegistrationBean}s. When no URL pattern or
 * servlets are specified the filter will be associated to '/*'. The targetBeanName will
 * be used as the filter name if not otherwise specified.
 *
 * @author Phillip Webb
 * @since 1.4.0
 * @see ServletContextInitializer
 * @see ServletContext#addFilter(String, Filter)
 * @see FilterRegistrationBean
 * @see DelegatingFilterProxy
 */
public class DelegatingFilterProxyRegistrationBean extends AbstractFilterRegistrationBean<DelegatingFilterProxy>
		implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private final String targetBeanName;

	/**
	 * Create a new {@link DelegatingFilterProxyRegistrationBean} instance to be
	 * registered with the specified {@link ServletRegistrationBean}s.
	 * @param targetBeanName name of the target filter cluster to look up in the Spring
	 * application context (must not be {@code null}).
	 * @param servletRegistrationBeans associate {@link ServletRegistrationBean}s
	 */
	public DelegatingFilterProxyRegistrationBean(String targetBeanName,
			ServletRegistrationBean<?>... servletRegistrationBeans) {
		super(servletRegistrationBeans);
		Assert.hasLength(targetBeanName, "TargetBeanName must not be null or empty");
		this.targetBeanName = targetBeanName;
		setName(targetBeanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	protected String getTargetBeanName() {
		return this.targetBeanName;
	}

	@Override
	public DelegatingFilterProxy getFilter() {
		return new DelegatingFilterProxy(this.targetBeanName, getWebApplicationContext()) {

			@Override
			protected void initFilterBean() throws ServletException {
				// Don't initialize filter cluster on init()
			}

		};
	}

	private WebApplicationContext getWebApplicationContext() {
		Assert.notNull(this.applicationContext, "ApplicationContext be injected");
		Assert.isInstanceOf(WebApplicationContext.class, this.applicationContext);
		return (WebApplicationContext) this.applicationContext;
	}

}
