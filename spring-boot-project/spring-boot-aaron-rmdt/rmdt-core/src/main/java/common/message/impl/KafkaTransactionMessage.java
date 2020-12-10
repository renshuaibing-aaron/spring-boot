package core.message.impl;

import core.message.RmdtTransactionMessage;
import core.serialize.ObjectSerializer;
import core.service.RmdtInvocationService;
import org.rmdt.annotation.Listener;
import org.rmdt.common.config.RmdtConfig;
import org.rmdt.common.domain.Participant;

import java.util.List;

/**
 * @author luohaipeng
 * 由Kafka提供消息队列服务支持
 */
public class KafkaTransactionMessage implements RmdtTransactionMessage {
    @Override
    public void init(RmdtConfig rmdtConfig) {

    }

    @Override
    public void setObjectSerializer(ObjectSerializer objectSerializer) {

    }

    @Override
    public void setInvocationService(RmdtInvocationService rmdtInvocationService) {

    }

    @Override
    public void send(Participant participant) {

    }

    @Override
    public String getMessageQueueName() {
        return "kafka";
    }

    @Override
    public void listen(List<Listener> listeners) {

    }
}
