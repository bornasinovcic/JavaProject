package com.example.javaproject.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Change implements Serializable {
    private Item before, after;
    private User user;
    private LocalDateTime localDateTime;

    public Change(Item before, Item after, User user, LocalDateTime localDateTime) {
        this.before = before;
        this.after = after;
        this.user = user;
        this.localDateTime = localDateTime;
    }

    public Item getBefore() {
        return before;
    }

    public void setBefore(Item before) {
        this.before = before;
    }

    public Item getAfter() {
        return after;
    }

    public void setAfter(Item after) {
        this.after = after;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
