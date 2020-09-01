package com.linksyi.rabbitmq.web.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列配置
 *
 * @author Mr.LinksYi on 2020/9/1
 */
public class DeadLetterConfig {


    /**
     * 创建交换机
     * @return
     */
    @Bean
    public Exchange deadLetterExcahnge() {
        return new DirectExchange("DL_EXCHANGE");
    }

    /**
     * 声明业务Exchange
     * @return
     */
    @Bean("businessExchange")
    public FanoutExchange businessExchange(){
        return new FanoutExchange("businessExchange");
    }

    /**
     * 声明死信Exchange
     * @return
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange("deadLetterExchange");
    }

    /**
     * 声明业务队列A
     * @return
     */
    @Bean("businessQueueA")
    public Queue businessQueueA(){
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", "deadLetterExchange");
//       x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", "deadLetterQueueaRoutingKey");
        return QueueBuilder.durable("businessExchange").withArguments(args).build();
    }

    /**
     * 声明死信队列A
     * @return
     */
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA(){
        return new Queue("deadLetterQueueaName");
    }

    /**
     * 声明业务队列A绑定关系
     * @param queue 业务队列
     * @param exchange 业务交换机
     * @return
     */
    @Bean
    public Binding businessBindingA(@Qualifier("businessQueueA") Queue queue,
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
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue queue,
                                      @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("deadLetterQueueaRoutingKey");
    }
}
