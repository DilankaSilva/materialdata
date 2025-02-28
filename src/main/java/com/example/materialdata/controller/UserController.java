package com.example.materialdata.controller;

import com.example.materialdata.dto.LoginRequest;
import com.example.materialdata.dto.LoginResponse;
import com.example.materialdata.entity.Sauserm;
import com.example.materialdata.repository.SausermRepository;
import com.example.materialdata.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SausermRepository sausermRepository;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.authenticateUser(loginRequest);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<Sauserm> getUserByUserId(@PathVariable String userId) {
        Sauserm user = sausermRepository.findByUserIdIgnoreCase(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/login/v1")
    public ResponseEntity<?> loginUser(@RequestParam String userId) {
        Sauserm user = sausermRepository.findByUserIdIgnoreCase(userId);
        
        if (user != null) {
            // Creating response JSON with only required fields
            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId());
            response.put("rptUser", user.getRptUser());  // Required field
            response.put("userLevel", user.getUserLevel()); // Required field

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid User ID");
        }
    }
}