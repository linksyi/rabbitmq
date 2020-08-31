package com.linksyi.rabbitmq.web.receiver;


import com.linksyi.rabbitmq.web.config.RabbitConfig;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Mr.zuoyi
 * @description: 广播模型接收端
 * @createTime 2020年08月29日 14:29:00
 */

@Component
public class FanoutReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitConfig.FANOUT_QUEUE_NAME_A),
            exchange = @Exchange(value = RabbitConfig.FANOUT_EXCHANGE_NAME,type = ExchangeTypes.FANOUT)
    ))
    public void receiver0(String str) {
        System.out.println(RabbitConfig.FANOUT_QUEUE_NAME_A+"接收消息:" + str);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitConfig.FANOUT_QUEUE_NAME_B),
            exchange = @Exchange(value = RabbitConfig.FANOUT_EXCHANGE_NAME,type = ExchangeTypes.FANOUT)
    ))
    public void receiver1(String str) {
        System.out.println(RabbitConfig.FANOUT_QUEUE_NAME_B+"接收消息:" + str);
    }
}