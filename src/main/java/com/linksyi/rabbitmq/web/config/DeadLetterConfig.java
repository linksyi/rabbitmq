package com.linksyi.rabbitmq.web.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列配置
 *
 * @author Mr.LinksYi on 2020/9/1
 */
@Configurable
public class DeadLetterConfig {

    public static final String BUSINESS_EXCHANGE = "business.exchange";
    public static final String DEAD_LETTER_EXCHANGE = "dead.letter.exchange";
    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "deadLetter.queuea.routing.key";
    public static final String DEAD_LETTER_QUEUEA = "dead.letter.queuea";
    public static final String BUSINESS_QUEUE = "business.queue";

    /**
     * 声明业务Exchange
     * @return
     */
    @Bean("businessExchange")
    public FanoutExchange businessExchange(){
        return new FanoutExchange(BUSINESS_EXCHANGE);
    }

    /**
     * 声明死信Exchange
     * @return
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明业务队列A
     * @return
     */
    @Bean("businessQueue")
    public Queue businessQueue(){
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE);
//       x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);
        return QueueBuilder.durable(BUSINESS_QUEUE).withArguments(args).build();
    }

    /**
     * 声明死信队列A
     * @return
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        return new Queue(DEAD_LETTER_QUEUEA);
    }

    /**
     * 声明业务队列A绑定关系
     * @param queue 业务队列
     * @param exchange 业务交换机
     * @return
     */
    @Bean
    public Binding businessBinding(@Qualifier("businessQueue") Queue queue,
                                    @Qualifier("businessExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     *  声明死信队列A绑定关系
     * @param queue 死信队列
     * @param exchange 死信交换机
     * @return
     */
    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterQueue") Queue queue,
                                      @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }
}
