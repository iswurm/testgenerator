package com.testgenerator;

public class NameGenerator {
    public static void main(String[] args) {
        String nombre = generarNombreAleatorio();
        System.out.println("Nombre generado: " + nombre);
    }

    public static String generarNombreAleatorio() {
        String letras = "abcdefghijklmnopqrstuvwxyz";
        int longitudMaxima = 12;
        StringBuilder nombre = new StringBuilder();

        // Generar la primera letra en mayúscula
        int indicePrimeraLetra = (int) (Math.random() * letras.length());
        String primeraLetra = String.valueOf(letras.charAt(indicePrimeraLetra)).toUpperCase();
        nombre.append(primeraLetra);

        // Generar el resto del nombre
        for (int i = 1; i < longitudMaxima; i++) {
            int indiceAleatorio = (int) (Math.random() * letras.length());
            char letraAleatoria = letras.charAt(indiceAleatorio);
            nombre.append(letraAleatoria);
        }

        return nombre.toString();
    }

}
