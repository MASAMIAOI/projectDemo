package com.masamiaoi.mq.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: MASAMIAOI
 * @description: 生产者
 * @date: 2023/3/24 18:45
 * @version: 1.0.0
 */
@Component
public class Producer {

    /**
     * rabbit 模板
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产者
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     */
    public void produce() throws InterruptedException {
        String message = "疫情期间注意防护";
        System.out.println("乡长说：" + message);
        Thread.sleep(5000);
        rabbitTemplate.convertAndSend("notice_queue", message);
    }

    /**
     * 生产者 - 批量
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     */
    public void produceList() throws InterruptedException {
        String message = "疫情期间注意防护";
        System.out.println("乡长说：" + message);
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("worksr", message);
        }
    }


    /**
     * 生产者 - fanout
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     */
    public void produceByFanout() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("fanoutsr", "", "hello spring boot rabbitmq" + i);
        }
    }


    /**
     * 生产者 - Routing
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     * 指定routingkey，如果消费者拥有和生产者相同的routingkey就可以接受到生产者发送的信息
     */
    public void produceByRouting() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("routing", "info", "routing: " + i);
        }
    }

    /**
     * 生产者 - Routing
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     * 指定routingkey，如果消费者拥有和生产者相同的routingkey就可以接受到生产者发送的信息
     */
    public void produceByRoutingTest() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("routing", "test", "routingTest: " + i);
        }
    }

    /**
     * 生产者 - topics
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     * 指定routingkey，如果消费者拥有和生产者相同的routingkey就可以接受到生产者发送的信息
     */
    public void produceByTopicsToSave() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 1; i++) {
            rabbitTemplate.convertAndSend("topicsr", "order.save.oid", "save: " + i);
        }
    }

    /**
     * 生产者 - topics
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     * 指定routingkey，如果消费者拥有和生产者相同的routingkey就可以接受到生产者发送的信息
     */
    public void produceByTopicsToViid() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 1; i++) {
            rabbitTemplate.convertAndSend("topicsr", "order.viid.oid", "viid: " + i);
        }
    }

    /**
     * 生产者 - topics
     * 生产者需要用到 RabbitTemplate 类来生产消息并进行消息发送，代码如下。
     * 指定routingkey，如果消费者拥有和生产者相同的routingkey就可以接受到生产者发送的信息
     */
    public void produceByTopicsToViidKey() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 1; i++) {
            rabbitTemplate.convertAndSend("topicsr", "viid.viid.oid", "viid - viied: " + i);
        }
    }


}
