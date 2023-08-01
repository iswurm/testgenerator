package com.testgenerator;

import java.util.Random;

public class CPGenerator {

    public static String generarCodigoPostal() {
        Random random = new Random();
        int codigoPostalMinimo = 1000;
        int codigoPostalMaximo = 52999;

        int codigoPostal = codigoPostalMinimo + random.nextInt(codigoPostalMaximo - codigoPostalMinimo + 1);
        return String.format("%05d", codigoPostal);
    }
}
