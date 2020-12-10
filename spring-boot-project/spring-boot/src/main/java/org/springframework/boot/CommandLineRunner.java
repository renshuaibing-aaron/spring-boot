package org.springframework.boot;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Interface used to indicate that a cluster should <em>run</em> when it is contained within
 * a {@link SpringApplication}. Multiple {@link CommandLineRunner} beans can be defined
 * within the same application context and can be ordered using the {@link Ordered}
 * interface or {@link Order @Order} annotation.
 * <p>
 * If you need access to {@link ApplicationArguments} instead of the raw String array
 * consider using {@link ApplicationRunner}.
 *
 * @author Dave Syer
 * @since 1.0.0
 * @see ApplicationRunner
 */
@FunctionalInterface
public interface CommandLineRunner {

	/**
	 * Callback used to run the cluster.
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	void run(String... args) throws Exception;

}
