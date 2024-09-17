package com.yhr.transactionalOutboxDemo.service;

import com.yhr.transactionalOutboxDemo.persistent.Outbox;
import com.yhr.transactionalOutboxDemo.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxProcessor {

    private final OutboxRepository outboxRepository;

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedDelay = 5000)
    public void processOutbox() {
        List<Outbox> unprocessedEvents = outboxRepository.findByProcessedFalse();
        unprocessedEvents.forEach(event -> {
            try {
                jmsTemplate.convertAndSend("order.queue", event.getPayload());
                event.setProcessed(true);
                outboxRepository.save(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
