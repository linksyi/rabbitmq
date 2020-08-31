package com.linksyi.rabbitmq.demo.fanout;

import com.linksyi.rabbitmq.demo.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author Mr.zuoyi
 * @description: 订阅模式发送端
 * @createTime 2020年08月28日 15:46:00
 */

public class Send {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();

        Channel channel = connection.createChannel();
        //申明交换机
        channel.exchangeDeclare("publish.subscribe", "fanout");
        //将消息发送到交换机中
        channel.basicPublish("publish.subscribe", "", false, null, "hello publish-subscribe1".getBytes());
    }
}
