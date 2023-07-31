package com.testgenerator;

import java.util.Random;

public class DniGenerator {

    public static void main(String[] args) {
        String dni = generateValidDNI();
        System.out.println("DNI generado: " + dni);
    }

    public static String generateValidDNI() {
        Random random = new Random();

        // Generamos 8 dígitos aleatorios para el número del DNI
        StringBuilder dniNumberBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            dniNumberBuilder.append(random.nextInt(10));
        }
        String dniNumber = dniNumberBuilder.toString();

        // Calculamos la letra de control
        char controlLetter = calculateControlLetter(dniNumber);

        return dniNumber + controlLetter;
    }

    public static char calculateControlLetter(String dniNumber) {
        int dniInt = Integer.parseInt(dniNumber);
        char[] controlLetters = "TRWAGMYFPDXBNJZSQVHLCKE".toCharArray();
        int controlIndex = dniInt % 23;
        return controlLetters[controlIndex];
    }
}
