package com.example.javaproject.generics;

import com.example.javaproject.entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Changes <T> implements Serializable {
    private T before, after;
    private User user;
    private LocalDateTime localDateTime;

    public Changes(T before, T after, User user, LocalDateTime localDateTime) {
        this.before = before;
        this.after = after;
        this.user = user;
        this.localDateTime = localDateTime;
    }

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }

    public T getAfter() {
        return after;
    }

    public void setAfter(T after) {
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
