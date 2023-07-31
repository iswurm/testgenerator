package com.testgenerator;
import java.security.SecureRandom;

public class EmailGenerator {
    private static final String[] DOMAINS = { "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "example.com", "gmail.es", "sisnet360.com" };
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";

    private static SecureRandom random = new SecureRandom();

    public static String generateRandomUsername() {
        int longitudNombre =  (int)Math.floor(Math.random() * (12 - 6 + 1) + 6);
        StringBuilder sb = new StringBuilder(longitudNombre);
        for (int i = 0; i < longitudNombre; i++) {
            int randomIndex = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String generateRandomEmail() {
        String username = generateRandomUsername();
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        return username + "@" + domain;
    }

    public static void main(String[] args) {
        String randomEmail = generateRandomEmail();
        System.out.println("Random Email: " + randomEmail);
    }
}