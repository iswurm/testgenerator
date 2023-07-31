package com.testgenerator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.nio.file.Paths;
import java.time.Instant;

public class Main {
    static Example testPerros = new Example();
    static Test2 test2 = new Test2();
    static TestCaballos testCaballos = new TestCaballos();
    static TestGatos testGatos = new TestGatos();

    public static void main(String[] args) {
        Thread hiloPerros = new Thread(() -> testPerros.ejecutar());
        Thread hiloCaballos = new Thread(() -> testCaballos.ejecutar());
        Thread hiloGatos = new Thread(() -> testGatos.ejecutar());

        hiloPerros.start();
        hiloCaballos.start();
        hiloGatos.start();

        try {
            hiloPerros.join();
            hiloCaballos.join();
            hiloGatos.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
