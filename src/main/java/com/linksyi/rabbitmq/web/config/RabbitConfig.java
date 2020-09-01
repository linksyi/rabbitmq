package com.linksyi.rabbitmq.web.config;


import org.springframework.amqp.core.*;
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

@Configurable
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
}
