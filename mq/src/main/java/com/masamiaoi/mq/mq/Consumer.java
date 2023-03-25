package com.masamiaoi.mq.mq;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: MASAMIAOI
 * @description: 生产者
 * @date: 2023/3/24 18:47
 * @version: 1.0.0
 */
@Component
public class Consumer {

    /*-----------------------------------------------------queen-----------------------------------------------------*/

    /**
     * 消费者
     * Consumer 消费者通过 @RabbitListener 注解创建侦听器端点，绑定 notice_queue 队列。
     *
     * @RabbitListener 注解提供了@QueueBinding、@Queue、@Exchange 等对象，通过这个组合注解配置交换机、绑定路由并且配置监听功能等。
     * @RabbitHandler 注解为具体接收的方法。
     */
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("notice_queue"))
    public void process(String message) {
        System.out.println("村里猿公子收到通知：" + message);
    }

    /*-----------------------------------------------------queen 多个消费者-----------------------------------------------------*/

    @RabbitListener(queuesToDeclare = @Queue("worksr"))
    public void receivel(String message) {
        System.out.println("C1  :" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("worksr"))
    public void receivel2(String message) {
        System.out.println("C2  :" + message);
    }


    /*-----------------------------------------------------fanout-----------------------------------------------------*/

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//声明临时队列
                    exchange = @Exchange(value = "fanoutsr", type = "fanout")
            )
    })
    public void receivelByFanoutsr1(String message) {
        System.out.println("fanoutsr1  :" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//声明临时队列
                    exchange = @Exchange(value = "fanoutsr", type = "fanout")
            )
    })
    public void receivelByFanoutsr2(String message) {
        System.out.println("fanoutsr2  :" + message);
    }


    /*-----------------------------------------------------routing 动态路由-----------------------------------------------------*/

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//声明临时队列
                    exchange = @Exchange(value = "routing", type = "direct"),
                    key = {"error", "info", "test"}
            )
    })
    public void receivelByRouting1(String message) {
        System.out.println("Routing1  :" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//声明临时队列
                    exchange = @Exchange(value = "routing", type = "direct"),
                    key = {"error", "info"}
            )
    })
    public void receivelByRouting2(String message) {
        System.out.println("Routing2  :" + message);
    }


    /*-----------------------------------------------------topics 动态路由-----------------------------------------------------*/


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//声明临时队列
                    exchange = @Exchange(value = "topicsr", type = "topic"),
                    key = {"order.*"}
            )
    })
    public void receivelByTopics1(String message) {
        System.out.println("topics  :" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//声明临时队列
                    exchange = @Exchange(value = "topicsr", type = "topic"),
                    key = {"order.#"}
            )
    })
    public void receivelByTopics2(String message) {
        System.out.println("topics  :" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//声明临时队列
                    exchange = @Exchange(value = "topicsr", type = "topic"),
                    key = {"viid.#"}
            )
    })
    public void receivelByTopics3(String message) {
        System.out.println("topics - viid  :" + message);
    }
}
