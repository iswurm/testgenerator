package com.testgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RazaGatoGenerator {

    // Lista de razas de gatos
    private static List<String> razasGato = new ArrayList<>();

    // Agregar razas de gatos a la lista
    static {
        razasGato.add("Siamés");
        razasGato.add("Persa");
        razasGato.add("Bengalí");
        razasGato.add("Maine Coon");
        razasGato.add("Ragdoll");
        // Puedes agregar más razas si lo deseas
    }

    // Método que devuelve una raza de gato aleatoria
    public static String obtenerRazaGatoAleatoria() {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(razasGato.size());
        return razasGato.get(indiceAleatorio);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        for (int i = 0; i < 5; i++) {
            String razaGatoAleatoria = obtenerRazaGatoAleatoria();
            System.out.println("Raza de gato aleatoria " + (i + 1) + ": " + razaGatoAleatoria);
        }
    }
}