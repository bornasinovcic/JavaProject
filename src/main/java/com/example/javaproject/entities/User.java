package com.example.javaproject.entities;

import java.io.Serializable;

public class User extends Id implements Serializable {
    private final String userName, userPassword;
    private final Roles role;

    public User(String itemId, String userName, String userPassword, Roles role) {
        super(itemId);
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Roles getRole() {
        return role;
    }

    public static class UserBuilder {
        private String itemId;
        private String userName;
        private String userPassword;
        private Roles role;

        public UserBuilder setItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public UserBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder setUserPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public UserBuilder setRole(Roles role) {
            this.role = role;
            return this;
        }

        public User createUser() {
            return new User(itemId, userName, userPassword, role);
        }
    }

    @Override
    public String toString() {
        return userName + " [" + getItemId() + "] [" + getRole() + "]";
    }
}
