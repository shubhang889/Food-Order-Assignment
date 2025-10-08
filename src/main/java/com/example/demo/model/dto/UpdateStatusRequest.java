package com.example.demo.model.dto;

import com.example.demo.model.enums.PartnerStatus;

public class UpdateStatusRequest {
    private PartnerStatus status;

    public PartnerStatus getStatus() {
        return status;
    }

    public void setStatus(PartnerStatus status) {
        this.status = status;
    }
}
