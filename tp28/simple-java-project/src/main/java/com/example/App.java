package com.example;

/**
 * Application principale
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Application démarrée");

        int x = 10;
        int y = 20;

        if (x == 10) {
            System.out.println("X vaut 10");
        }

        String config = "default";
    }

    /**
     * Additionne deux nombres
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Soustrait deux nombres
     */
    public int subtract(int a, int b) {
        return a - b;
    }
}
