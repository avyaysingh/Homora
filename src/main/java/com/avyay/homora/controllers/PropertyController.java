package com.avyay.homora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.avyay.homora.enums.PropertyTypeEnum;
import com.avyay.homora.requests.CreatePropertyRequest;
import com.avyay.homora.responses.PropertyResponse;
import com.avyay.homora.services.PropertyService;
import com.avyay.homora.utilities.JwtUtility;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private JwtUtility jwtUtility;

    @PostMapping
    public ResponseEntity<?> createProperty(
            @RequestPart("property") CreatePropertyRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestHeader("Authorization") String token) {
        try {

            String ownerEmail = jwtUtility.extractEmail(token);

            PropertyResponse response = propertyService.createProperty(request, image, ownerEmail);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProperty(
            @PathVariable long id,
            @RequestBody CreatePropertyRequest request,
            @RequestHeader("AUthorization") String token) {

        try {
            String ownerEmail = jwtUtility.extractEmail(token);
            PropertyResponse response = propertyService.editProperty(id, request, ownerEmail);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProperties(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) PropertyTypeEnum type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity
                    .ok(propertyService.getAllProperties(title, location, minPrice, maxPrice, type, page, size));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<?> getPropertyById(@PathVariable Long propertyId) {
        try {
            return ResponseEntity.ok(propertyService.getPropertyResponse(propertyId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{propertyId}/interest")
    public ResponseEntity<?> expressInterest(@PathVariable Long propertyId,
            @RequestHeader("Authorization") String token) {
        try {
            String userEmail = jwtUtility.extractEmail(token);

            propertyService.sendInterestEmail(propertyId, userEmail);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
