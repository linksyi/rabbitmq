package com.linksyi.rabbitmq.web.receiver;


import com.linksyi.rabbitmq.web.config.DeadLetterConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Mr.zuoyi
 * @description: 死信队列接收端
 * @createTime 2020年08月29日 14:29:00
 */

@Component
public class SeniorReceiver {

    @RabbitListener(queues = DeadLetterConfig.BUSINESS_QUEUE)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());

        boolean ack = true;
        try {
            if (msg.contains("deadletter")) {
                throw new RuntimeException("dead letter exception");
            }
        } catch (Exception e) {
            ack = false;
        }
        if (!ack) {
            System.out.println("死信消息转发：" + msg);
            //拒绝消息，消息会被自动转发到死信交换机上
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            System.out.println("收到业务消息：" + msg);
            //确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = DeadLetterConfig.DEAD_LETTER_QUEUEA)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}