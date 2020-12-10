package annotation;


import common.enums.MessageEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luohaipeng
 * 在需要监听消息队列的方法上贴上该注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Listener {

    /**
     * 监听消息队列的地点
     * @return
     */
    String destination() default "";

    /**
     * 消息传递域的类型
     * @return
     */
    MessageEnum messageDomain() default MessageEnum.P2P;
}
