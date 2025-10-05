package com.example.grocery.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String username;
    private String accountId;
    private List<FoodItem> inventory;

    public Customer(String username, String accountId) {
        this.username = username;
        this.accountId = accountId;
        this.inventory = new ArrayList<>();
    }

    public void addFoodItem(FoodItem item) {
        this.inventory.add(item);
    }

    public String getUsername() { return username; }
    public String getAccountId() { return accountId; }
    public List<FoodItem> getInventory() { return inventory; }
    
    @Override
    public String toString() {
        return String.format("Customer: %s (ID: %s) with %d items.", 
            username, accountId, inventory.size());
    }
}
