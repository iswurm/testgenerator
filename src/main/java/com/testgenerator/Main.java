package com.testgenerator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.Instant;

public class Main {
    static Example testPerros = new Example();
    static Test2 test2 = new Test2();
    static TestCaballos testCaballos = new TestCaballos();
    static TestGatos testGatos = new TestGatos();
    static TestGatosChromium testGatosChromium = new TestGatosChromium();
    static TestGatosFirefox testGatosFirefox = new TestGatosFirefox();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // Creamos los hilos para cada método
        // Thread hiloPerros = new Thread(() -> {
        //     String resultadoPerros = testPerros.ejecutar();
        //     escribirEnArchivo("resultados.txt", resultadoPerros);
        // });

        // Thread hiloCaballos = new Thread(() -> {
        //     String resultadoCaballos = testCaballos.ejecutar();
        //     escribirEnArchivo("resultados.txt", resultadoCaballos);
        // });

        Thread hiloGatos = new Thread(() -> {
            String resultadoGatos = testGatos.ejecutar();
            // escribirEnArchivo("resultados.txt", resultadoGatos);
            System.out.print(resultadoGatos);
        });
        Thread hiloGatosChromium = new Thread(() -> {
            String resultadoGatosChromium = testGatosChromium.ejecutar();
            // escribirEnArchivo("resultados.txt", resultadoGatosChromium);
            System.out.print(resultadoGatosChromium);
        });
        Thread hiloGatosFirefox = new Thread(() -> {
            String resultadoGatosFirefox = testGatosFirefox.ejecutar();
            // escribirEnArchivo("resultados.txt", resultadoGatosFirefox);
            System.out.print(resultadoGatosFirefox);
        });

        // Iniciamos los hilos
        // hiloPerros.start();
        // hiloCaballos.start();
        hiloGatos.start();
        hiloGatosChromium.start();
        hiloGatosFirefox.start();

        // Esperamos a que todos los hilos terminen antes de continuar
        try {
            // hiloPerros.join();
            // hiloCaballos.join();
            hiloGatos.join();
            hiloGatosChromium.join();
            hiloGatosFirefox.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void escribirEnArchivo(String nombreArchivo, String contenido) {
        synchronized (lock) {
            try (FileWriter fw = new FileWriter(new File(nombreArchivo), true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println(contenido);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


