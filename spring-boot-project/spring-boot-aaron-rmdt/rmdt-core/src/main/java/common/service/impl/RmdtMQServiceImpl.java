package core.service.impl;

import core.bootstrap.ApplicationContextHolder;
import core.message.RmdtTransactionMessage;
import core.service.RmdtMQService;
import org.rmdt.annotation.Listener;
import org.rmdt.common.config.RmdtConfig;
import org.rmdt.common.domain.Participant;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author luohaipeng
 */
@Service
public class RmdtMQServiceImpl implements RmdtMQService {

    private RmdtTransactionMessage rmdtTransactionMessage;

    @Override
    public void start(RmdtConfig rmdtConfig) {
        rmdtTransactionMessage = ApplicationContextHolder.getBean(RmdtTransactionMessage.class.getName());
        rmdtTransactionMessage.init(rmdtConfig);
        setListenerMessage();
    }


    @Override
    public void sendMessage(Participant participant) {
        rmdtTransactionMessage.send(participant);
    }

    /**
     * 初始化消息队列监听器
     */
    private void setListenerMessage() {
        ArrayList<Listener> listeners = new ArrayList<>();
        Map<String, Object> beans = ApplicationContextHolder.getApplicationContext().getBeansWithAnnotation(Component.class);
        beans.forEach((k,v) -> {
            Class clazz = v.getClass();
            Method[] methods = clazz.getMethods();
            listeners.addAll(Stream.of(methods).map(this::getAnnotation).filter(a -> Objects.nonNull(a)).collect(Collectors.toList()));
        });
        rmdtTransactionMessage.listen(listeners);
    }

    private Listener getAnnotation(Method method) {
        return method.getAnnotation(Listener.class);
    }

}
