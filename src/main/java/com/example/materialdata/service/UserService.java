package com.example.materialdata.service;

import com.example.materialdata.dto.LoginRequest;
import com.example.materialdata.dto.LoginResponse;
import com.example.materialdata.entity.Sauserm;
import com.example.materialdata.repository.SausermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    
    @Autowired
    private SausermRepository sausermRepository;

    // default password
    private static final String DEFAULT_PASSWORD = "1234";

    public LoginResponse authenticateUser (LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        
        Sauserm user = sausermRepository.findByUserIdIgnoreCase(userId);
        
        if (user == null) {
            return new LoginResponse(false, null, null, "User  not found");
        }
        
        // Use hard-coded default password
        if (!DEFAULT_PASSWORD.equals(loginRequest.getPassword())) {
            return new LoginResponse(false, null, null, "Invalid password");
        }
        
        return new LoginResponse(
            true,
            user.getRptUser (),
            user.getUserLevel(),
            "Login successful"
        );
    }
}