package com.masamiaoi.mq.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author: MASAMIAOI
 * @description: kafka 消费者
 * @date: 2023/3/25 14:13
 * @version: 1.0.0
 */
//@Component
public class KafkaConsumer {
    // 消费监听
    @KafkaListener(topics = {"topic-test-llc"})
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费Topic：" + record.topic() + "**分区" + record.partition() + "**值内容" + record.value());
    }

}
