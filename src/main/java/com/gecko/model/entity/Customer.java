package com.gecko.model.entity;

import javax.validation.constraints.NotBlank;

public class Customer {
    private int customerId;
    private String customerName;
    private String email;
    private String imageUrl;
    private String address;
    private String phoneNumber;
    private String password;
    private boolean admin = false;
    private boolean banned = false;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String email, String imageUrl, String address, String phoneNumber, String password, boolean admin, boolean banned) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.admin = admin;
        this.banned = banned;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}