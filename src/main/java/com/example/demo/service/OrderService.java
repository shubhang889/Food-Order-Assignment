package com.example.demo.service;

import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.PartnerNotFoundException;
import com.example.demo.model.dao.Order;
import com.example.demo.model.dao.Partner;
import com.example.demo.model.enums.PartnerStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();
    private final PartnerService partnerService;

    public OrderService(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    public Order assignOrder(Order order) {
        Partner nearestPartner = partnerService.findNearestAvailablePartner(order.getPickupLocation());
        if (nearestPartner != null) {
            order.setPartnerId(nearestPartner.getId());
            partnerService.updatePartnerStatus(nearestPartner.getId(), PartnerStatus.BUSY);
            orders.put(order.getOrderId(), order);
            return order;
        }
        throw new PartnerNotFoundException("Delivery Partner not found!");
    }

    public Order completeOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null && order.getPartnerId() != null) {
            partnerService.updatePartnerStatus(order.getPartnerId(), PartnerStatus.AVAILABLE);
            return order;
        }
        throw new OrderNotFoundException("Invalid OrderId!");
    }
}
