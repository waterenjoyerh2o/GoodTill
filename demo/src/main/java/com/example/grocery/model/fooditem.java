package com.example.grocery.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FoodItem {
    private String name;
    private double amount;
    private int quantity;
    private String category;
    private LocalDate purchaseDate;
    
    private static final int EXPIRATION_DAYS_PRODUCE = 7;
    private static final int EXPIRATION_DAYS_DAIRY = 14;
    private static final int EXPIRATION_DAYS_CANNED = 365;
    private static final int EXPIRATION_DAYS_OTHER = 10;

    public FoodItem(String name, double amount, int quantity, String category, LocalDate purchaseDate) {
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
        this.category = category;
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpirationDate() {
        int daysToAdd;
        switch (category) {
            case "Produce":
                daysToAdd = EXPIRATION_DAYS_PRODUCE;
                break;
            case "Dairy":
                daysToAdd = EXPIRATION_DAYS_DAIRY;
                break;
            case "Canned Goods":
                daysToAdd = EXPIRATION_DAYS_CANNED;
                break;
            default:
                daysToAdd = EXPIRATION_DAYS_OTHER;
        }
        return purchaseDate.plusDays(daysToAdd);
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(getExpirationDate());
    }

    public long getDaysUntilExpiration() {
        return ChronoUnit.DAYS.between(LocalDate.now(), getExpirationDate());
    }

    public String getName() { return name; }
    public double getAmount() { return amount; }
    public int getQuantity() { return quantity; }
    public String getCategory() { return category; }
    public LocalDate getPurchaseDate() { return purchaseDate; }

    @Override
    public String toString() {
        return String.format("%s (Qty: %d, Purchased: %s, Expires: %s, Days Left: %d)",
            name, quantity, purchaseDate, getExpirationDate(), getDaysUntilExpiration());
    }
}