package com.testgenerator;

import java.util.Random;

public class MicrochipGenerator {
    

        public static String generateMicrochip() {
        Random random = new Random();
        int longitudMinima = 5;
        int longitudMaxima = 9;
        int longitud = longitudMinima + random.nextInt(longitudMaxima - longitudMinima + 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int digito = random.nextInt(10); 
            sb.append(digito);
        }

        return sb.toString();
    }
}
