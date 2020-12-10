package org.springframework.boot.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 *  注解，主要功能自动配置包，它会获取主程序类所在的包路径，并将包路径（包括子包）下的所有组件注册到 Spring IOC 容器中
 * Indicates that the package containing the annotated class should be registered with
 * {@link AutoConfigurationPackages}.
 *
 * @author Phillip Webb
 * @since 1.3.0
 * @see AutoConfigurationPackages
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {

}
