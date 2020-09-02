package com.linksyi.rabbitmq.web.receiver;


import com.linksyi.rabbitmq.web.config.RabbitConfig;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Mr.zuoyi
 * @description: 广播模型接收端
 * @createTime 2020年08月29日 14:29:00
 */

@Component
public class DirectReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitConfig.DIRECT_QUEUE_NAME_A),
            exchange = @Exchange(value = RabbitConfig.DIRECT_EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            key = "A"
    ))
    public void receiver0(String str) {
        System.out.println(RabbitConfig.DIRECT_QUEUE_NAME_A+"接收消息:" + str);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitConfig.DIRECT_QUEUE_NAME_B),
            exchange = @Exchange(value = RabbitConfig.DIRECT_EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            key = "B"
    ))
    public void receiver1(String str) {
        System.out.println(RabbitConfig.DIRECT_QUEUE_NAME_B+"接收消息:" + str);
    }
}