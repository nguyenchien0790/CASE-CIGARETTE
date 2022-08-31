package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Config<T> {

    public static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static int getValidInteger() {
        int integer;
        while (true) {
            System.out.println("Enter choice");
            String s = scanner().nextLine();

            if (s.matches("\\d+")) {
                integer = Integer.parseInt(s);
                break;
            } else {
                System.out.println("Invalid number, try again!");
            }
        }
        return integer;
    }

    public T read(String path) {
        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (T) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error reading");
            return null;
        }
    }

    public void write(String path, T data) {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println("Error writing");
        }
    }

}
