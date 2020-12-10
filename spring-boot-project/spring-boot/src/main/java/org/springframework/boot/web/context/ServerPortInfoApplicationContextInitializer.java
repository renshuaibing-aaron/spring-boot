package org.springframework.boot.web.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

/**
 * {@link ApplicationContextInitializer} that sets {@link Environment} properties for the
 * ports that {@link WebServer} servers are actually listening on. The property
 * {@literal "local.server.port"} can be injected directly into tests using
 * {@link Value @Value} or obtained via the {@link Environment}.
 * <p>
 * If the {@link WebServerInitializedEvent} has a
 * {@link WebServerApplicationContext#getServerNamespace() server namespace} , it will be
 * used to construct the property name. For example, the "management" actuator context
 * will have the property name {@literal "local.management.port"}.
 * <p>
 * Properties are automatically propagated up to any parent context.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @since 2.0.0
 */
public class ServerPortInfoApplicationContextInitializer implements
		ApplicationContextInitializer<ConfigurableApplicationContext>, ApplicationListener<WebServerInitializedEvent> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		//它的作用是监听EmbeddedServletContainerInitializedEvent类型的事件。然后将内嵌的Web服务器使用的端口给设置到ApplicationContext中
		applicationContext.addApplicationListener(this);
	}

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		String propertyName = "local." + getName(event.getApplicationContext()) + ".port";
		setPortProperty(event.getApplicationContext(), propertyName, event.getWebServer().getPort());
	}

	private String getName(WebServerApplicationContext context) {
		String name = context.getServerNamespace();
		return StringUtils.hasText(name) ? name : "server";
	}

	private void setPortProperty(ApplicationContext context, String propertyName, int port) {
		if (context instanceof ConfigurableApplicationContext) {
			setPortProperty(((ConfigurableApplicationContext) context).getEnvironment(), propertyName, port);
		}
		if (context.getParent() != null) {
			setPortProperty(context.getParent(), propertyName, port);
		}
	}

	@SuppressWarnings("unchecked")
	private void setPortProperty(ConfigurableEnvironment environment, String propertyName, int port) {
		MutablePropertySources sources = environment.getPropertySources();
		PropertySource<?> source = sources.get("server.ports");
		if (source == null) {
			source = new MapPropertySource("server.ports", new HashMap<>());
			sources.addFirst(source);
		}
		((Map<String, Object>) source.getSource()).put(propertyName, port);
	}

}
