package com.example.javaproject.entities;

public enum Roles {
    admin("ADMIN"),
    user("USER");
    private final String role;
    Roles(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
