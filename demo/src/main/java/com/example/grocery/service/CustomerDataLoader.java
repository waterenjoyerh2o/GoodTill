package com.example.grocery.service;

import com.example.grocery.model.Customer;
import com.example.grocery.model.FoodItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerDataLoader {
    
    private static final Pattern ITEM_LINE_PATTERN = Pattern.compile(
        "([^,]+), \\$(\\d+\\.\\d{2}), Quantity: (\\d+), Category: ([^,]+), Date: (\\d{4}-\\d{2}-\\d{2})"
    );

    public static Customer loadCustomerData(Path filePath) throws IOException {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            
            String username = reader.readLine();
            String accountId = reader.readLine();

            if (username == null || accountId == null) {
                throw new IOException("File format error: Missing username or account ID in " + filePath);
            }

            Customer customer = new Customer(username, accountId);

            reader.readLine(); 

            String line;
            while ((line = reader.readLine()) != null) {
                FoodItem item = parseOrderLine(line);
                if (item != null) {
                    customer.addFoodItem(item);
                }
            }

            return customer;
            
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            throw e; 
        }
    }

    private static FoodItem parseOrderLine(String line) {
        Matcher matcher = ITEM_LINE_PATTERN.matcher(line);

        if (matcher.find()) {
            try {
                String name = matcher.group(1).trim();
                double amount = Double.parseDouble(matcher.group(2));
                int quantity = Integer.parseInt(matcher.group(3));
                String category = matcher.group(4).trim();
                LocalDate purchaseDate = LocalDate.parse(matcher.group(5));
                
                return new FoodItem(name, amount, quantity, category, purchaseDate);
            } catch (Exception e) {
                System.err.println("Skipping malformed order line: " + line + " Error: " + e.getMessage());
                return null; 
            }
        }
        return null; 
    }

    public static List<Customer> loadAllCustomers(String directoryPath) {
        List<Customer> allCustomers = new ArrayList<>();
        
        try {
            Path dir = Path.of(directoryPath);
            java.nio.file.Files.list(dir)
                .filter(p -> p.toString().endsWith(".txt"))
                .forEach(path -> {
                    try {
                        allCustomers.add(loadCustomerData(path));
                    } catch (IOException e) {
                    }
                });
        } catch (IOException e) {
            System.err.println("Error accessing directory: " + directoryPath + ". Did you run the Python script?");
        }
        return allCustomers;
    }
}
