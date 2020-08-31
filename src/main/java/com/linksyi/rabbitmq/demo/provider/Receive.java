package com.linksyi.rabbitmq.demo.provider;

import com.linksyi.rabbitmq.demo.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.zuoyi
 * @description: 点对点接受端
 * @createTime 2020年08月28日 09:39:00
 */

public class Receive {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //绑定队列
        channel.queueDeclare("provider", false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("接收到消息1:" + message);
        };
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("通道关闭" + consumerTag);
        };
        channel.basicConsume("provider", true, deliverCallback, cancelCallback);

    }

}
