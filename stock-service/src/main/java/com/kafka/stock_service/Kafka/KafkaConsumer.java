package com.kafka.stock_service.Kafka;

import com.kafka.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(
            topics = "orders_topics",
            groupId = "stock")
    private void consume(OrderEvent orderEvent){
//        logger.info(String.format("Order event retrieved in stock service !! %s",orderEvent.toString()));

        System.out.println("saving in DB values -> "+ orderEvent);

    }
}
