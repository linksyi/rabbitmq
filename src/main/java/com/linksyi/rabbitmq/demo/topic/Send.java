package com.linksyi.rabbitmq.demo.topic;

import com.linksyi.rabbitmq.demo.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.zuoyi
 * @description: 主题模式发送端
 * @createTime 2020年08月28日 17:21:00
 */

public class Send {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();

        Channel channel = connection.createChannel();
        //申明交换机
        channel.exchangeDeclare("topics", "topic");
        //将消息发送到交换机中
        channel.basicPublish("topics", "log.error.test", false, null, "hello Topics1".getBytes());
        channel.basicPublish("topics", "log.error", false, null, "hello Topics1".getBytes());

        channel.close();
        connection.close();
    }
}
