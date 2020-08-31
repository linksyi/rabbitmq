package com.linksyi.rabbitmq.demo.work;

import com.linksyi.rabbitmq.demo.utils.RabbitUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * @author Mr.zuoyi
 * @description: 工作队列接收者
 * @createTime 2020年08月28日 11:11:00
 */

public class Receive1 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //channel.queueDeclare("work-queues", false, false, false, null);
        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("Received2:" + new String(message.getBody()));
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };
        CancelCallback cancelCallback = (String consumerTag) -> {
        };
        channel.basicConsume("work-queues", false, deliverCallback, cancelCallback);
    }
}
