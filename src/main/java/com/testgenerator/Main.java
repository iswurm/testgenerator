package com.testgenerator;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    static TestPerros testPerrosAleatorio = new TestPerros();
    static TestCaballos testCaballos = new TestCaballos();
    static TestGatos2 testGatosAleatorio = new TestGatos2();
    static TestCaballosAleatorio testCaballosAleatorio = new TestCaballosAleatorio();
    public static int nTestActual;
    public static int numeroHilos = 4;
    private static ArrayList<Runnable> arrayTest  = new ArrayList<Runnable>();
    private static ArrayList<Thread> arrayHilos = new ArrayList<Thread>();
    public static LocalDate diaActual = LocalDate.now();
    public static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es"));
    public static String diaActualEnCastellano = diaActual.format(formateador);
    public static boolean modo = true;
    public static Integer numeroTests = 1;
    public static String ip = "";
    public static Integer timeoutAccion = 10000;
    public static Integer timeoutNavegacion = 10000;

    public static void main(String[] args) {
        
        /* ----------------------- INICIO PARTE MAIN --------------------------- */
        if (args.length != 0 ) {
            for (String valor : args) {
                String[] result = valor.split("=");
                String indice = result[0];
                String contenido = result[1];
                if(indice.equals("NUMEROTESTS")){
                    try {
                        numeroTests = Integer.parseInt(contenido);
                      } catch (NumberFormatException e) {
                        System.out.print("NUMEROTESTS debe ser un Int. Introducido: "+contenido+" \n");
                        lanzarMensajeError();
                      } 
                }
                if(indice.equals("IP")){
                    ip = contenido;
                }
                if(indice.equals("TIMEACCION")){
                    try {
                        timeoutAccion = Integer.parseInt(contenido) * 1000;
                      } catch (NumberFormatException e) {
                        System.out.print("TIMEACCION debe ser un Integer. Introducido: "+contenido+" \n");
                        lanzarMensajeError();
                      }
                }
                 if(indice.equals("TIMENAV")){
                    try {
                        timeoutNavegacion = Integer.parseInt(contenido) * 1000;
                      } catch (NumberFormatException e) {
                        System.out.print("TIMEACCION debe ser un Integer. Introducido: "+contenido+" \n");
                        lanzarMensajeError();
                      }    
                }
                if(indice.equals("NUMHILOS")){
                    try {
                        numeroHilos = Integer.parseInt(contenido);
                      } catch (NumberFormatException e) {
                        System.out.print("NUMHILOS debe ser un Integer. Introducido: "+contenido+" \n");
                        lanzarMensajeError();
                      }
                }
            }
            if (ip == ""){
                System.out.print("No se ha introducido IP \n");
                lanzarMensajeError();
            }
        }else{
            lanzarMensajeError();
        }
            attachShutDownHook();
                String cabecera =
            "###############################################################################################################\n" +
            "#                                                                                                             #\n" +
            "    Resultados de " + numeroTests + " Tests | IP --> " + ip + " | Fecha actual:  " + diaActualEnCastellano + "    \n" +
            "    Timeout Accion " + (timeoutAccion/1000) + " segundos | Timeout Navegacion " + (timeoutNavegacion/1000) + " segundos | Numero de Hilos: "+numeroHilos+" \n" +
            "#                                                                                                             #\n" +
            "###############################################################################################################\n";
                System.out.println(cabecera);
                escribirEnArchivo("resultados.txt", cabecera);
                //Crear instancias de Runnable

                for(Integer i = 0; i<numeroHilos; i++){
                     arrayTest.add(new HiloTest(timeoutAccion, timeoutNavegacion, modo, ip, numeroTests, (i+1)));
                     arrayHilos.add(new Thread(arrayTest.get(i)));
                }

                for(Integer i = 0; i<numeroHilos; i++){
                    arrayHilos.get(i).start();
                }
                for(Integer i = 0; i<numeroHilos; i++){
                    try{
                        arrayHilos.get(i).join();
                    }catch(InterruptedException e){
                        escribirEnArchivo("logErrores.txt", e.getLocalizedMessage());
                       
                    }
                }
 
        /* ----------------------- FIN PARTE MAIN --------------------------- */
        

    }
    public static void attachShutDownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread() {
          @Override
          public void run() {
            //HiloTest.detener();
            int segundos = (int)HiloTest.tiempoTotal / 1000;
            int minutos = segundos / 60;
            segundos %= 60;
            int horas = minutos / 60;
            minutos %= 60;
            
            int exitosTotales = HiloTest.numeroExitosCaballos + HiloTest.numeroExitosGatos + HiloTest.numeroExitosPerros;
            double porcentajeExito = (double) exitosTotales / (numeroTests*numeroHilos);
            double porcentaje = porcentajeExito * 100;

            double numeroTestsGatos = HiloTest.numeroExitosGatos + HiloTest.numeroFallosGatos;
            double porcentajeExitoGatos = (double) HiloTest.numeroExitosGatos/numeroTestsGatos*100;
            double numeroTestsPerros = HiloTest.numeroExitosPerros + HiloTest.numeroFallosPerros;
            double porcentajeExitoPerros = (double) HiloTest.numeroExitosPerros/numeroTestsPerros*100;
            double numeroTestsCaballos = HiloTest.numeroExitosCaballos + HiloTest.numeroFallosCaballos;
            double porcentajeExitoCaballos = (double) HiloTest.numeroExitosCaballos/ numeroTestsCaballos*100;
            double mediaTestGatos = (double) HiloTest.milisTotalGatos/HiloTest.numeroExitosGatos;
            double mediaTestPerros = (double) HiloTest.milisTotalPerros/HiloTest.numeroExitosPerros;
            double mediaTestCaballos = (double) HiloTest.milisTotalCaballos/HiloTest.numeroExitosCaballos;
            double mediaTestsExitosos = (double)(HiloTest.milisTotalCaballos+HiloTest.milisTotalGatos+HiloTest.milisTotalPerros)/(HiloTest.numeroExitosCaballos+HiloTest.numeroExitosGatos+HiloTest.numeroExitosPerros);

            // Formatear el porcentaje con dos decimales
            DecimalFormat formato = new DecimalFormat("#.##");
            String porcentajeFormateado = formato.format(porcentaje);
            String estadisticas = "=============================\nResultado de las pruebas\n=============================\n\n";
            estadisticas += "-------------------------------------------------------\n";
            estadisticas += "Porcentaje de ÉXITO GATOS = " + formato.format(porcentajeExitoGatos) + "% de " + numeroTestsGatos + " tests. Media GATOS: "+ formato.format(mediaTestGatos) + " ms.\n";
            estadisticas += "Test más rápido GATOS = " + HiloTest.testExitosoCortoGato + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoGato+"\n";
            estadisticas += "Test más lento GATOS = " + HiloTest.testExitosoLargoGato + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoGato+"\n";
            estadisticas += "Test fallido más rápido GATOS = " + HiloTest.testFallidoCortoGato + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoGato+"\n";
            estadisticas += "Test fallido más lento GATOS = " + HiloTest.testFallidoLargoGato + "ms que fue el test número"+HiloTest.numeroTestFallidoLargoGato+"\n";
            estadisticas += "-------------------------------------------------------\n";
            estadisticas += "Porcentaje de ÉXITO PERROS = " + formato.format(porcentajeExitoPerros) + "% de " + numeroTestsPerros + " tests. Media PERROS: "+ formato.format(mediaTestPerros) + " ms.\n";
            estadisticas += "Test más rápido PERROS = " + HiloTest.testExitosoCortoPerro + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoPerro+"\n";
            estadisticas += "Test más lento PERROS = " + HiloTest.testExitosoLargoPerro + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoPerro+"\n";
            estadisticas += "Test fallido más rápido PERROS = " + HiloTest.testFallidoCortoPerro + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoPerro+"\n";
            estadisticas += "Test fallido más lento PERROS = " + HiloTest.testFallidoLargoPerro + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoPerro+"\n";
            estadisticas += "-------------------------------------------------------\n";
            estadisticas += "Porcentaje de ÉXITO CABALLOS = " + formato.format(porcentajeExitoCaballos) + "% de " + numeroTestsCaballos + " tests. Media CABALLOS: "+ formato.format(mediaTestCaballos) + " ms.\n";
            estadisticas += "Test más rápido CABALLOS = " + HiloTest.testExitosoCortoCaballo + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoCaballo+"\n";
            estadisticas += "Test más lento CABALLOS = " + HiloTest.testExitosoLargoCaballo + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoCaballo+"\n";
            estadisticas += "Test fallido más rápido CABALLOS = " + HiloTest.testFallidoCortoCaballo + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoCaballo+"\n";
            estadisticas += "Test fallido más lento CABALLOS = " + HiloTest.testFallidoLargoCaballo + "ms que fue el test número "+HiloTest.numeroTestFallidoCortoCaballo+"\n";
            estadisticas += "-------------------------------------------------------\n";
            estadisticas += "Porcentaje de ÉXITO TOTAL= " + porcentajeFormateado + "%.\n";
            estadisticas += "Tiempo Total: "+String.format("%d horas %d minutos %d segundos", horas, minutos, segundos)+"\n\n";
            estadisticas += "Media Total Tests Exitosos: "+formato.format(mediaTestsExitosos/numeroHilos)+" milisegundos en "+exitosTotales+" tests exitosos.\n\n";
            

            escribirEnArchivo("resultados.txt", estadisticas);

           }
        });
        
         System.out.println("Creado el hook en el programa");
 
    }
    public synchronized static void escribirEnArchivo(String nombreArchivo, String contenido) {

        try (FileWriter fw = new FileWriter(new File(nombreArchivo), true);
                PrintWriter pw = new PrintWriter(fw)) {
            pw.println(contenido);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void lanzarMensajeError() {
        System.out.println("===============================================================\n");
        System.out.println("DEBE ESCRIBIR UN COMANDO CON ESTE FORMATO:");
        System.out.println("java -jar ejecutablejava.jar NUMEROTESTS=X IP=X \n");
        System.out.println("EJEMPLO:");
        System.out.println("java -jar autoqa-playwright.jar NUMEROTESTS=10 IP=https://13.81.113.206/sisnet/\n");
        System.out.println("===============================================================");
        System.exit(1);
    }
}
