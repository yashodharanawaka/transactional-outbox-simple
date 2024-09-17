package com.yhr.transactionalOutboxDemo.controller;

import com.yhr.transactionalOutboxDemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity createOrder() {
        orderService.createOrder();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
