package com.example.demo.model.dto;

import com.example.demo.model.dao.Location;

public class AssignOrderRequest {
    private String orderId;
    private Location pickupLocation;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
}
