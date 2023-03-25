package com.masamiaoi.mq;

import com.masamiaoi.mq.mq.Producer;
import com.masamiaoi.mq.mq.ProducerByVO;
import com.masamiaoi.mq.mq.RabbitSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MqApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    Producer producer;


    @Autowired
    ProducerByVO producerByVO;

    @Test
    public void amqpTest() throws InterruptedException {
        // 生产者发送消息
        producer.produce();
    }

    @Test
    public void amqpTestList() throws InterruptedException {
        // 生产者发送消息
        producer.produceList();
    }

    @Test
    public void produceByFanout() throws InterruptedException {
        // 生产者发送消息
        producer.produceByFanout();
    }

    @Test
    public void produceByRouting() throws InterruptedException {
        // 生产者发送消息
        producer.produceByRouting();
        producer.produceByRoutingTest();
    }

    @Test
    public void produceBytTopics() throws InterruptedException {
        // 生产者发送消息
        producer.produceByTopicsToSave();
//        producer.produceByTopicsToViid();
        producer.produceByTopicsToViidKey();
    }


    @Test
    public void amqpTestOne() throws InterruptedException {
        // 生产者发送消息
        producerByVO.produce();
    }


    @Autowired
    private RabbitSender rabbitSender;


    @Test
    public void testSender1() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put("number", "12345");
        properties.put("send_time", LocalDateTime.now());
        rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);
    }

}
