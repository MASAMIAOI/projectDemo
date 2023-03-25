package com.masamiaoi.mq.mq;

import com.alibaba.fastjson.JSON;
import com.masamiaoi.mq.vo.ScProductVO;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: MASAMIAOI
 * @description: vo 消费
 * @date: 2023/3/25 0:04
 * @version: 1.0.0
 */

@Component
public class ConsumerByVO {

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("notice_product_queue"))
    public void process(ScProductVO product) {
        System.out.println("收到秒杀产品信息为：" + JSON.toJSONString(product));
    }

}
