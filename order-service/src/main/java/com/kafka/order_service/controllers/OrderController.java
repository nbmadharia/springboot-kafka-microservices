package com.kafka.order_service.controllers;

import com.kafka.base_domains.dto.Order;
import com.kafka.base_domains.dto.OrderEvent;
import com.kafka.order_service.kafka.KafkaProducer;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

//    private static final Logger logger = LogManager.getLogger(OrderController.class);


    private static KafkaProducer kafkaProducer;

    public OrderController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){


        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();

        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(order);

        kafkaProducer.sendMessage(orderEvent);

//        logger.info(" order controller placed!! %s", orderEvent.toString() );

        return "Order places successfully";

    }
}
