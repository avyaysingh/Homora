package com.avyay.homora.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avyay.homora.requests.UserRequest;
import com.avyay.homora.responses.PropertyResponse;
import com.avyay.homora.services.UserService;
import com.avyay.homora.utilities.JwtUtility;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtility jwtUtility;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(
            @RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtility.extractEmail(token);
            return ResponseEntity.ok(userService.getUserProfile(email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody UserRequest userRequest) {
        try {
            String email = jwtUtility.extractEmail(token);
            userService.updateUserProfile(email, userRequest);
            return ResponseEntity.ok("Profile updated successfully!");
        } catch (Exception e) {
            // TODO: handle exception

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/properties")
    public ResponseEntity<?> getUserProperties(@RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtility.extractEmail(token);
            List<PropertyResponse> properties = userService.getUserProperties(email);

            return ResponseEntity.ok(properties);
        } catch (Exception e) {
            // TODO: handle exception

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
