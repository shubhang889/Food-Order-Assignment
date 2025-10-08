package com.example.demo.controller;

import com.example.demo.model.dto.AssignOrderRequest;
import com.example.demo.model.dao.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/assign")
    public ResponseEntity<Order> assignOrder(@RequestBody AssignOrderRequest request) {
        Order newOrder = new Order(request.getOrderId(), request.getPickupLocation());
        Order assignedOrder = orderService.assignOrder(newOrder);
        if (assignedOrder != null) {
            return ResponseEntity.ok(assignedOrder);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{orderId}/complete")
    public ResponseEntity<Order> completeOrder(@PathVariable String orderId) {
        Order completedOrder = orderService.completeOrder(orderId);
        if (completedOrder != null) {
            return ResponseEntity.ok(completedOrder);
        }
        return ResponseEntity.notFound().build();
    }
}
