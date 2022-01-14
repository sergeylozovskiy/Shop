package com.lozovskyi.shop.Task_4.utility;

import com.lozovskyi.shop.Task_1.entity.Product;

import java.io.*;
import java.util.Map;

public final class BackupUtil {
    private BackupUtil() {
        throw new UnsupportedOperationException();
    }

    public static void save(Map<Integer, Product> toSave, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(toSave);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Product> load(String fileName) {

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<Integer, Product>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}