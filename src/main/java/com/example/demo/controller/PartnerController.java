package com.example.demo.controller;

import com.example.demo.model.dto.PartnerRegistrationRequest;
import com.example.demo.model.dto.UpdateStatusRequest;
import com.example.demo.model.dao.Partner;
import com.example.demo.service.PartnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public ResponseEntity<Partner> registerPartner(@RequestBody PartnerRegistrationRequest request) {
        Partner newPartner = new Partner(request.getId(), request.getName(), request.getLocation());
        return ResponseEntity.ok(partnerService.registerPartner(newPartner));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Partner> updatePartnerStatus(@PathVariable String id, @RequestBody UpdateStatusRequest request) {
        Partner updatedPartner = partnerService.updatePartnerStatus(id, request.getStatus());
        if (updatedPartner != null) {
            return ResponseEntity.ok(updatedPartner);
        }
        return ResponseEntity.notFound().build();
    }
}
