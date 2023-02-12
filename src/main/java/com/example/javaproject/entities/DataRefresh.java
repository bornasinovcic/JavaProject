package com.example.javaproject.entities;

import com.example.javaproject.generics.Changes;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataRefresh {
    private static final String PATH_NAME = "files/changes.ser";
    private static List<Changes<Item>> list = new ArrayList<>();

    public static synchronized void serialization(Item newMadeItem, Item selectedItem, User user) {
        try (FileOutputStream file = new FileOutputStream(PATH_NAME);
             ObjectOutputStream out = new ObjectOutputStream(file)) {
            list.add(new Changes<>(selectedItem, newMadeItem, user, LocalDateTime.now()));
            out.writeObject(list);
            System.out.println(PATH_NAME + " serialized");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static synchronized List<Changes<Item>> deserialization() {
        try (FileInputStream file = new FileInputStream(PATH_NAME);
             ObjectInputStream in = new ObjectInputStream(file)) {
            list = (List<Changes<Item>>) in.readObject();
            System.out.println(PATH_NAME + " deserialized");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No data inputted yet in list.");
        }
        return list;
    }
}
