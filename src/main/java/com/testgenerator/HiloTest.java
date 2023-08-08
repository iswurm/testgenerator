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
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HiloTest implements Runnable {
    public String ip;
    public int i;
    public Integer timeoutAccion;
    public Integer timeoutNavegacion, numeroHilo;
    public boolean modo;
    public Integer numeroTests;
    static TestPerros testPerrosAleatorio = new TestPerros();
    static TestCaballos testCaballos = new TestCaballos();
    static TestGatos2 testGatosAleatorio = new TestGatos2();
    static TestCaballosAleatorio testCaballosAleatorio = new TestCaballosAleatorio();
    private static volatile boolean detener = false;

    Boolean resultado;
    public static Integer numeroFallosGatos = 0, numeroFallosPerros = 0, numeroFallosCaballos = 0, numeroExitosGatos = 0,
            numeroExitosCaballos = 0, numeroExitosPerros = 0,
            numeroTestExitosoLargoPerro = 0, numeroTestFallidoLargoPerro = 0, numeroTestExitosoCortoPerro = 0,
            numeroTestFallidoCortoPerro = 0,
            numeroTestExitosoLargoGato = 0, numeroTestFallidoLargoGato = 0, numeroTestActual = 0;
    public static long testExitosoLargoPerro = 0, testFallidoLargoPerro = 0, testExitosoLargoGato = 0, testFallidoLargoGato = 0,
            testExitosoLargoCaballo = 0, testFallidoLargoCaballo = 0, milisTotalGatos = 0, milisTotalPerros = 0,
            milisTotalCaballos = 0;
    public static long testExitosoCortoPerro = 100000, testFallidoCortoPerro = 100000, testExitosoCortoGato = 100000,
            testFallidoCortoGato = 100000, testExitosoCortoCaballo = 100000, testFallidoCortoCaballo = 100000, tiempoTotal = 0;
    public static int numeroTestExitosoCortoGato = 0, numeroTestFallidoCortoGato = 0, numeroTestExitosoLargoCaballo = 0,
            numeroTestFallidoLargoCaballo = 0, numeroTestExitosoCortoCaballo = 0, numeroTestFallidoCortoCaballo = 0;

    Instant inicio = Instant.now();
    int aleatorio;
    Random random = new Random();
    int nTestActual;

    public HiloTest(Integer timeoutAccion, Integer timeoutNavegacion, boolean modo, String ip, Integer numeroTests, Integer numeroHilo) {
        this.timeoutAccion = timeoutAccion;
        this.timeoutNavegacion = timeoutNavegacion;
        this.modo = modo;
        this.ip = ip;
        this.numeroTests = numeroTests;
        this.numeroHilo = numeroHilo;
    }

    public static void detener() {
        detener = true;
    }

    @Override
    public void run() {
        //while(!detener){
            for (int i = 0; i < numeroTests; i++) {

                nTestActual = (i+1);
                aleatorio = random.nextInt(3);
                String cadenaResultado = "";
                DateTimeFormatter formateadorHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                cadenaResultado = "## Test número " + nTestActual + " -- Hilo "+numeroHilo+" -- " + LocalTime.now().format(formateadorHora) +"\n";
                if (aleatorio == 0) {
                    Instant start = Instant.now();
                    resultado = testPerrosAleatorio.ejecutar(ip, timeoutAccion, timeoutNavegacion, modo,nTestActual);
                    Instant end = Instant.now();
                    long elapsedTimeInMilliseconds = end.toEpochMilli() - start.toEpochMilli();
                    if (resultado == true) {
                        cadenaResultado += "ÉXITO - Perros. Tiempo de ejecución: " + elapsedTimeInMilliseconds
                                + " milisegundos.\n";
                        numeroExitosPerros++;
                        milisTotalPerros += elapsedTimeInMilliseconds;
                        if( elapsedTimeInMilliseconds > testExitosoLargoPerro){
                            testExitosoLargoPerro = elapsedTimeInMilliseconds;
                            numeroTestExitosoLargoPerro = nTestActual;
                        }
                        if( elapsedTimeInMilliseconds < testExitosoCortoPerro){
                            testExitosoCortoPerro = elapsedTimeInMilliseconds;
                            numeroTestExitosoCortoPerro = nTestActual;
                        }
                        tiempoTotal += elapsedTimeInMilliseconds;
                    } else {
                        cadenaResultado += "FALLO - Perros. Tiempo de ejecución: " + elapsedTimeInMilliseconds
                                + " milisegundos.\n ";
                        numeroFallosPerros++;
                        if( elapsedTimeInMilliseconds > testFallidoLargoPerro){
                            testFallidoLargoPerro = elapsedTimeInMilliseconds;
                            numeroTestFallidoLargoPerro = nTestActual;
                        }
                        if( elapsedTimeInMilliseconds < testFallidoCortoPerro){
                            testFallidoCortoPerro = elapsedTimeInMilliseconds;
                            numeroTestFallidoCortoPerro = nTestActual;
                        }
                        tiempoTotal += elapsedTimeInMilliseconds;
                    }
                }
                if (aleatorio == 1) {
                    Instant start = Instant.now();
                    resultado = testGatosAleatorio.ejecutar(ip, timeoutAccion, timeoutNavegacion, modo,nTestActual);
                    Instant end = Instant.now();
                    long elapsedTimeInMilliseconds = end.toEpochMilli() - start.toEpochMilli();
                    if (resultado == true) {
                        cadenaResultado += "ÉXITO - Gatos. Tiempo de ejecución: " + elapsedTimeInMilliseconds
                                + " milisegundos.\n";
                        numeroExitosGatos++;
                        milisTotalGatos += elapsedTimeInMilliseconds;
                        if( elapsedTimeInMilliseconds > testExitosoLargoGato){
                            testExitosoLargoGato = elapsedTimeInMilliseconds;
                            numeroTestExitosoLargoGato = nTestActual;
                        }
                        if( elapsedTimeInMilliseconds < testExitosoCortoGato){
                            testExitosoCortoGato = elapsedTimeInMilliseconds;
                            numeroTestExitosoCortoGato = nTestActual;
                        }
                        tiempoTotal += elapsedTimeInMilliseconds;
                    } else {
                        cadenaResultado += "FALLO - Gatos. Tiempo de ejecución: " + elapsedTimeInMilliseconds
                                + " milisegundos.\n ";
                        numeroFallosGatos++;
                        if( elapsedTimeInMilliseconds > testFallidoLargoGato){
                            testFallidoLargoGato = elapsedTimeInMilliseconds;
                            numeroTestFallidoLargoGato = nTestActual;
                        }
                        if( elapsedTimeInMilliseconds < testFallidoCortoGato){
                            testFallidoCortoGato = elapsedTimeInMilliseconds;
                            numeroTestFallidoCortoGato = nTestActual;
                        }
                        tiempoTotal += elapsedTimeInMilliseconds;
                    }
                }
                if (aleatorio == 2) {
                    Instant start = Instant.now();
                    resultado = testCaballosAleatorio.ejecutar(ip, timeoutAccion, timeoutNavegacion, modo, nTestActual);
                    Instant end = Instant.now();
                    long elapsedTimeInMilliseconds = end.toEpochMilli() - start.toEpochMilli();

                    if (resultado == true) {
                        cadenaResultado += "ÉXITO - Caballos. Tiempo de ejecución: " + elapsedTimeInMilliseconds
                                + " milisegundos.\n";
                        numeroExitosCaballos++;
                        milisTotalCaballos += elapsedTimeInMilliseconds;
                        if( elapsedTimeInMilliseconds > testExitosoLargoCaballo){
                            testExitosoLargoCaballo = elapsedTimeInMilliseconds;
                            numeroTestExitosoLargoCaballo = nTestActual;
                        }
                        if( elapsedTimeInMilliseconds < testExitosoCortoCaballo){
                            testExitosoCortoCaballo = elapsedTimeInMilliseconds;
                            numeroTestExitosoCortoCaballo = nTestActual;
                        }
                        tiempoTotal += elapsedTimeInMilliseconds;
                    } else {
                        cadenaResultado += "FALLO - Caballos. Tiempo de ejecución: " + elapsedTimeInMilliseconds
                                + " milisegundos.\n ";
                        numeroFallosCaballos++;
                        if( elapsedTimeInMilliseconds > testFallidoLargoCaballo){
                            testFallidoLargoCaballo = elapsedTimeInMilliseconds;
                            numeroTestFallidoLargoCaballo = nTestActual;
                        }
                        if( elapsedTimeInMilliseconds < testFallidoCortoCaballo){
                            testFallidoCortoCaballo = elapsedTimeInMilliseconds;
                            numeroTestFallidoCortoCaballo = nTestActual;
                        }
                        tiempoTotal += elapsedTimeInMilliseconds;
                    }
                }
                System.out.println(cadenaResultado);
                Main.escribirEnArchivo("resultados.txt", cadenaResultado);
                if (detener) {
                    System.out.println("AAAAAAAAAA");
                    break;
                }
            }
        //}
        Instant fin = Instant.now();
        //detener();
        tiempoTotal = fin.toEpochMilli() - inicio.toEpochMilli();

        //Main.escribirEnArchivo("resultados.txt", estadisticas);

    }

    
}
