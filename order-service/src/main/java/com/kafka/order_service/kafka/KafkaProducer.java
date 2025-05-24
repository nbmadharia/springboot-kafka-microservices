package com.kafka.order_service.kafka;

import com.kafka.base_domains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;



import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

//    private static final Logger logger = LogManager.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private NewTopic topic;

    public KafkaProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendMessage(OrderEvent orderEvent){
//        logger.info("Order Event => %s", orderEvent.toString());
        System.out.println("producer send message inside");
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
