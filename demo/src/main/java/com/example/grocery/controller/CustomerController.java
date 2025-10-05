package com.example.grocery.controller;

import com.example.grocery.service.CustomerDataLoader;
import com.example.grocery.model.FoodItem;
import org.springframework.web.bind.annotation.*; // Use RestController and PostMapping
import org.springframework.http.ResponseEntity; // For cleaner responses

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CustomerController {
    
    private static final String DATA_DIR = "/Users/rui/Desktop/HackRU-2025/customer_data_files";
    private static final String CUSTOMER_FILE_FORMAT = "customer%s.txt";

    @GetMapping("/inventory/{customerId}")
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody RegistrationRequest request) {
        
        String customerId = request.getCustomerId();
        String email = request.getEmail();
        String username = CustomerDataLoader.generateUsername(customerId, email); 
        
        String filename = String.format(CUSTOMER_FILE_FORMAT, customerId);
        Path filePath = Path.of(DATA_DIR, filename);

        if (Files.exists(filePath)) {
            return ResponseEntity
                .badRequest()
                .body("Customer ID " + customerId + " already exists.");
        }

        try {
            CustomerDataLoader.saveNewCustomerData(username, customerId, filePath);
            
            return ResponseEntity.ok("Registration successful for user: " + username);
            
        } catch (IOException e) {
            System.err.println("Error saving new customer data for ID: " + customerId + ". " + e.getMessage());
            return ResponseEntity
                .internalServerError()
                .body("Failed to create account due to a server error.");
        }
    }
}