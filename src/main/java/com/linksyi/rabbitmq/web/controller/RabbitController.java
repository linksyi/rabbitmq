package com.linksyi.rabbitmq.web.controller;

import com.linksyi.rabbitmq.web.config.RabbitConfig;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.zuoyi
 * @description: 发送端
 * @createTime 2020年08月29日 14:28:00
 */

@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * hello模型
     */
    @RequestMapping("hello")
    public String helloSend(){
        rabbitTemplate.convertAndSend("", RabbitConfig.HELLO_QUEUE_NAME, "hello message");
        return "点对点模式发送成功";
    }

    /**
     * work模型
     */
    @RequestMapping("work")
    public String workSend(){
        rabbitTemplate.convertAndSend("", RabbitConfig.WORK_QUEUE_NAME, "work message");
        return "工作模型模式发送成功";
    }

    /**
     * 广播模型
     */
    @RequestMapping("fanout")
    public String fanoutSend(){
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE_NAME, "", "fanout message");
        return "广播模型模式发送成功";
    }

    /**
     * 路由模型
     */
    @RequestMapping("direct")
    public String directSend(){
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE_NAME, "A", "direct message");
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE_NAME, "B", "direct message");
        return "路由模型模式发送成功";
    }

    /**
     * 动态路由模型
     */
    @RequestMapping("topic")
    public String topicSend(){
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, "topic.A", "topic message");
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, "topic.B.B", "topic message");
        return "动态路由路由模型模式发送成功";
    }
}
