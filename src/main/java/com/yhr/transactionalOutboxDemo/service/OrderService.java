package com.yhr.transactionalOutboxDemo.service;

import com.yhr.transactionalOutboxDemo.persistent.Order;
import com.yhr.transactionalOutboxDemo.persistent.Outbox;
import com.yhr.transactionalOutboxDemo.repository.OrderRepository;
import com.yhr.transactionalOutboxDemo.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OutboxRepository outboxRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder() {
        Order order = new Order();
        order.setDescription("Some order");
        orderRepository.save(order);

        Outbox outboxEvent = new Outbox();
        outboxEvent.setEventType("order.created");
        outboxEvent.setPayload(order.toString());
        outboxEvent.setProcessed(false);

        outboxRepository.save(outboxEvent);
    }
}
