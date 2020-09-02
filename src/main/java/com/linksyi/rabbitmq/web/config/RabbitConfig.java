package com.linksyi.rabbitmq.web.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.zuoyi
 * @description: 消息队列配置类
 * @createTime 2020年08月29日 14:00:00
 */

public class RabbitConfig {

    /**
     * 队列名称
     */
    public static final String HELLO_QUEUE_NAME = "hello";

    public static final String WORK_QUEUE_NAME = "work";

    public static final String FANOUT_QUEUE_NAME_A = "fanout_queue_A";
    public static final String FANOUT_QUEUE_NAME_B = "fanout_queue_B";

    public static final String DIRECT_QUEUE_NAME_A = "direct_queue_A";
    public static final String DIRECT_QUEUE_NAME_B = "direct_queue_B";

    public static final String TOPIC_QUEUE_NAME_A = "topic_queue_A";
    public static final String TOPIC_QUEUE_NAME_B = "topic_queue_B";


    /**
     * 交换机名称
     */
    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";

    public static final String DIRECT_EXCHANGE_NAME = "direct_exchange";
    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";


    @Bean
    public RabbitAdmin rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
    }
}
