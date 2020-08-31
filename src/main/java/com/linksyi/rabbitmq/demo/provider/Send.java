package com.linksyi.rabbitmq.demo.provider;

import com.linksyi.rabbitmq.demo.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.zuoyi
 * @description: 点对点发送端
 * @createTime 2020年08月28日 09:39:00
 */

public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //绑定队列
        channel.queueDeclare("provider", false, false, false, null);

        channel.basicPublish("", "provider", null, "hello provider".getBytes());

        channel.close();
        connection.close();
    }
}
