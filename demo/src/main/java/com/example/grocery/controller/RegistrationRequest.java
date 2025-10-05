package com.example.grocery.controller;

public class RegistrationRequest {
    private String customerId;
    private String email;

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}