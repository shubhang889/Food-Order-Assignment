package com.example.demo.service;

import com.example.demo.exception.PartnerNotFoundException;
import com.example.demo.model.dao.Location;
import com.example.demo.model.dao.Partner;
import com.example.demo.model.enums.PartnerStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PartnerService {
    private final Map<String, Partner> partners = new ConcurrentHashMap<>();

    public Partner registerPartner(Partner partner) {
        partners.put(partner.getId(), partner);
        return partner;
    }

    public Partner updatePartnerStatus(String id, PartnerStatus status) {
        Partner partner = partners.get(id);
        if (partner != null) {
            partner.setStatus(status);
            return partner;
        }
        throw new PartnerNotFoundException("Partner not found!");
    }

    public Partner findNearestAvailablePartner(Location pickupLocation) {
        return getAllPartners().stream()
                .filter(p -> p.getStatus() == PartnerStatus.AVAILABLE)
                .min((p1, p2) -> Double.compare(calculateDistance(p1.getLocation(), pickupLocation),
                        calculateDistance(p2.getLocation(), pickupLocation)))
                .orElse(null);
    }

    private double calculateDistance(Location loc1, Location loc2) {
        return Math.abs(loc1.getX() - loc2.getX()) + Math.abs(loc1.getY() - loc2.getY());
    }

    public Collection<Partner> getAllPartners() {
        return partners.values();
    }

    public Partner getPartner(String id) {
        return partners.get(id);
    }
}
