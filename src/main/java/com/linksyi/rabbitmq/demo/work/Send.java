package com.linksyi.rabbitmq.demo.work;

import com.linksyi.rabbitmq.demo.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.zuoyi
 * @description: 工作队列发送者
 * @createTime 2020年08月28日 11:10:00
 */

public class Send {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        for (int i = 0; i < 10; i++) {
        channel.queueDeclare("work-queues", false, false, false, null);
        channel.basicPublish("", "work-queues", null, ("hello work-queues" + i).getBytes());
        }

        channel.close();
        connection.close();
    }
}
