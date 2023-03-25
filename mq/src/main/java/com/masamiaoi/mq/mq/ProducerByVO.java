package com.masamiaoi.mq.mq;

import com.masamiaoi.mq.vo.ScProductVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: MASAMIAOI
 * @description: 生产者-对象
 * @date: 2023/3/25 0:01
 * @version: 1.0.0
 */
@Component
public class ProducerByVO {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void produce() throws InterruptedException {
        // 创建一个商品
        ScProductVO product = new ScProductVO();
        product.setName("苹果 14，512G，银灰");
        product.setNumber(8);
        product.setId(2022);
        product.setProductImg("./iphone14.png");
        // 转换发送商品信息
        System.out.println("产品被" + product.getName() + "秒杀，发送通知");
        Thread.sleep(5000);
        rabbitTemplate.convertAndSend("notice_product_queue", product);
    }

}
