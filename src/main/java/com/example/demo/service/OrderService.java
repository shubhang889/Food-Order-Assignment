package com.example.demo.service;

import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.PartnerNotFoundException;
import com.example.demo.model.dao.Location;
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
        Partner nearestPartner = findNearestAvailablePartner(order.getPickupLocation());
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

    private Partner findNearestAvailablePartner(Location pickupLocation) {
        return partnerService.getAllPartners().stream()
                .filter(p -> p.getStatus() == PartnerStatus.AVAILABLE)
                .min((p1, p2) -> Double.compare(calculateDistance(p1.getLocation(), pickupLocation),
                        calculateDistance(p2.getLocation(), pickupLocation)))
                .orElse(null);
    }

    private double calculateDistance(Location loc1, Location loc2) {
                return Math.abs(loc1.getX() - loc2.getX()) + Math.abs(loc1.getY() - loc2.getY());
    }
}
