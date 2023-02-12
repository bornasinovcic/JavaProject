package com.example.javaproject.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public record Hash() {
    public static String encryption(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA512");
            messageDigest.update(password.getBytes());
            byte[] result = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder(result.length);
            for (byte b : result) stringBuilder.append(String.format("%02x", b));
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}