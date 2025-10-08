package com.example.demo.model.dao;

import com.example.demo.model.enums.PartnerStatus;

public class Partner {
    private String id;
    private String name;
    private PartnerStatus status;
    private Location location;

    public Partner(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.status = PartnerStatus.AVAILABLE;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartnerStatus getStatus() {
        return status;
    }

    public void setStatus(PartnerStatus status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
