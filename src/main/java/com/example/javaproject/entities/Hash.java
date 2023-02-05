package com.example.javaproject.entities;


public record Hash() {
    public static String decryption(String s) {
        int value = 5;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            c -= value;
            stringBuilder.append(c);
        }
        return stringBuilder.toString();

    }

    public static String encryption(String s) {
        int value = 5;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            c += value;
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
