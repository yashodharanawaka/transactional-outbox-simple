package com.yhr.transactionalOutboxDemo.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @JmsListener(destination = "order.queue")
    public void receiveOrderMessage(String message) {
        System.out.println("Received Order Message: " + message);
    }
}
