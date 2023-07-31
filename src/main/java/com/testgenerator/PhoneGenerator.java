package com.testgenerator;

import java.util.Random;

public class PhoneGenerator {

    // Método que genera un número de teléfono móvil de España aleatorio
    public static String generarNumeroTelefonoMovil() {
        Random random = new Random();

        // Código de país para España
        int codigoPais = 34;

        // Código de operador (primer dígito es 6 o 7)
        int codigoOperador = 6 + random.nextInt(2); // Genera 6 o 7

        // Generar los 7 dígitos restantes del número local
        int numeroLocal = random.nextInt(90000000) + 10000000;

        // Formatear el número de teléfono
        return String.format("%d%d", codigoOperador, numeroLocal);
    }

    public static String generarNumeroTelefonoFijo() {
        Random random = new Random();

        // Código de país para España
        int codigoPais = 34;

        // Generar el primer dígito del código de área (entre 8 y 9)
        int primerDigitoArea = 8 + random.nextInt(2); // 8 o 9

        // Generar los 2 dígitos restantes del código de área (entre 00 y 99)
        int codigoArea = primerDigitoArea * 100 + random.nextInt(100);

        // Generar los 7 dígitos restantes del número local
        int numeroLocal = random.nextInt(900000) + 100000;

        // Formatear el número de teléfono
        return String.format("%d%d", codigoArea, numeroLocal);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        for (int i = 0; i < 5; i++) {
            String numeroTelefonoAleatorio = generarNumeroTelefonoMovil();
            System.out.println("Número de teléfono móvil de España aleatorio " + (i + 1) + ": " + numeroTelefonoAleatorio);
        }
    }
}
