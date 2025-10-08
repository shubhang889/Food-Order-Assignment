package com.example.demo.model.dao;

public class Order {
    private String orderId;
    private String partnerId;
    private Location pickupLocation;

    public Order(String orderId, Location pickupLocation) {
        this.orderId = orderId;
        this.pickupLocation = pickupLocation;
    }

        public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
}
