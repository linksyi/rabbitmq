package com.linksyi.rabbitmq.web.receiver;


import com.linksyi.rabbitmq.web.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Mr.zuoyi
 * @description: 工作模型接收端
 * @createTime 2020年08月29日 14:29:00
 */

@Component
public class WorkReceiver {

    @RabbitListener(queuesToDeclare = @Queue(RabbitConfig.WORK_QUEUE_NAME))
    public void receiver0(String str) {
        System.out.println(RabbitConfig.WORK_QUEUE_NAME+"0接收消息:" + str);
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitConfig.WORK_QUEUE_NAME))
    public void receiver1(String str) {
        System.out.println(RabbitConfig.WORK_QUEUE_NAME+"1接收消息:" + str);
    }
}
