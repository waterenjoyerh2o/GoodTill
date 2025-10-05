package com.example.grocery.service;

import com.example.grocery.model.Customer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List; 

public class CustomerDataLoader {
    
    public static void saveNewCustomerData(String username, String accountId, Path filePath) throws IOException {
        
        List<String> lines = List.of(
            username,
            accountId,
            "--- ORDERS ---" 
        );

        Files.write(filePath, lines);
    }
    
    public static String generateUsername(String customerId, String email) {
        String namePart = email.split("@")[0];
        return namePart + customerId; 
    }

    public static Customer loadCustomerData(Path filePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadCustomerData'");
    }
}