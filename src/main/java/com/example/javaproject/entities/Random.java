package com.example.javaproject.entities;

public class Random {
    public static String randomString() {
        int n = 16;
        String arrayOfValues = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (arrayOfValues.length() * Math.random());
            stringBuilder.append(arrayOfValues.charAt(index));
        }
        return stringBuilder.toString();
    }
}
