package com.yhr.transactionalOutboxDemo.repository;

import com.yhr.transactionalOutboxDemo.persistent.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}

