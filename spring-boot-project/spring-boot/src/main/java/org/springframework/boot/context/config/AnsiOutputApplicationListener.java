package org.springframework.boot.context.config;

import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiOutput.Enabled;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * An {@link ApplicationListener} that configures {@link AnsiOutput} depending on the
 * value of the property {@code spring.output.ansi.enabled}. See {@link Enabled} for valid
 * values.
 *
 * @author Raphael von der Grün
 * @author Madhura Bhave
 * @since 1.2.0
 */
public class AnsiOutputApplicationListener
		implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

	//监听的是 ApplicationEnvironmentPreparedEvent 事件
	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		ConfigurableEnvironment environment = event.getEnvironment();

		//根据环境变量 spring.output.ansi.enabled 的值，设置 AnsiOutput.enabled 属性
		Binder.get(environment).bind("spring.output.ansi.enabled", AnsiOutput.Enabled.class)
				.ifBound(AnsiOutput::setEnabled);

		//根据环境变量 "spring.output.ansi.console-available 的值，设置 AnsiOutput.consoleAvailable 属性
		AnsiOutput.setConsoleAvailable(environment.getProperty("spring.output.ansi.console-available", Boolean.class));
	}

	@Override
	public int getOrder() {
		// Apply after ConfigFileApplicationListener has called EnvironmentPostProcessors
		return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
	}

}
