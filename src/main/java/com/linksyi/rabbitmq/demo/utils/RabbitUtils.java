package com.linksyi.rabbitmq.demo.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.zuoyi
 * @description:
 * @createTime 2020年08月28日 11:03:00
 */

public class RabbitUtils {

    private static Connection connection;

    static {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("fawkes.ml");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("1qaz@WSX");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("/demo");

        //获取连接
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
