/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.cli.compiler.autoconfigure;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import org.springframework.boot.cli.compiler.AstUtils;
import org.springframework.boot.cli.compiler.CompilerAutoConfiguration;
import org.springframework.boot.cli.compiler.DependencyCustomizer;

/**
 * {@link CompilerAutoConfiguration} for Spring Batch.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @since 1.0.0
 */
public class SpringBatchCompilerAutoConfiguration extends CompilerAutoConfiguration {

	@Override
	public boolean matches(ClassNode classNode) {
		return AstUtils.hasAtLeastOneAnnotation(classNode, "EnableBatchProcessing");
	}

	@Override
	public void applyDependencies(DependencyCustomizer dependencies) {
		dependencies.ifAnyMissingClasses("org.springframework.batch.com.shirc.redis.delay.queue.core.Job").add("spring-boot-starter-batch");
		dependencies.ifAnyMissingClasses("org.springframework.jdbc.com.shirc.redis.delay.queue.core.JdbcTemplate").add("spring-jdbc");
	}

	@Override
	public void applyImports(ImportCustomizer imports) {
		imports.addImports("org.springframework.batch.repeat.RepeatStatus",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.scope.context.ChunkContext",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.step.tasklet.Tasklet",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.configuration.annotation.StepScope",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.configuration.annotation.JobBuilderFactory",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.configuration.annotation.StepBuilderFactory",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.configuration.annotation.EnableBatchProcessing",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.Step", "org.springframework.batch.com.shirc.redis.delay.queue.core.StepExecution",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.StepContribution", "org.springframework.batch.com.shirc.redis.delay.queue.core.Job",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.JobExecution", "org.springframework.batch.com.shirc.redis.delay.queue.core.JobParameter",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.JobParameters", "org.springframework.batch.com.shirc.redis.delay.queue.core.launch.JobLauncher",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.converter.JobParametersConverter",
				"org.springframework.batch.com.shirc.redis.delay.queue.core.converter.DefaultJobParametersConverter");
	}

}
