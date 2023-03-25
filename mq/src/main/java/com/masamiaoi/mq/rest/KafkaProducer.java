package com.masamiaoi.mq.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: MASAMIAOI
 * @description: kafka 生产者
 * @date: 2023/3/25 14:11
 * @version: 1.0.0
 */
//@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @GetMapping("/kafka/normal/{message}")
    public String sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic-test-llc", normalMessage);
        return "ok";
    }


}
