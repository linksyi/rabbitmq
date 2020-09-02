package com.linksyi.rabbitmq.web.controller;

import com.linksyi.rabbitmq.web.config.DeadLetterConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rabbitmq高级模式
 *
 * @author Mr.LinksYi on 2020/9/1
 */

@RequestMapping
@RestController
public class SeniorController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("delayed")
    public String delayedSend(){
        rabbitTemplate.convertSendAndReceive(DeadLetterConfig.BUSINESS_EXCHANGE, "", "发送一条死信消息deadletter");
        rabbitTemplate.convertSendAndReceive(DeadLetterConfig.BUSINESS_EXCHANGE, "", "发送一条业务消息business");
        return "死信队列发送成功";
    }
}
