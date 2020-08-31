package com.linksyi.rabbitmq.web.receiver;


import com.linksyi.rabbitmq.web.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Mr.zuoyi
 * @description: 点对点接收端
 * @createTime 2020年08月29日 14:29:00
 */

@Component
public class HelloReceiver {

    @RabbitListener(queuesToDeclare = @Queue(RabbitConfig.HELLO_QUEUE_NAME))
    public void receiver0(String str) {
        System.out.println(RabbitConfig.HELLO_QUEUE_NAME+"接收消息:" + str);
    }
}
