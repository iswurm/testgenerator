package com.testgenerator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateGenerator {

    public static String generateDate() {
        Random random = new Random();
        int maxEdad = 90;
        int edad = random.nextInt(maxEdad + 1);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.YEAR, -edad);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Los meses en Calendar se indexan desde 0 (enero) a 11 (diciembre)
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String fechaNacimiento = String.format("%02d/%02d/%04d", day, month, year);
        return fechaNacimiento;
    }

        public static String generatePetDate() {
        Random random = new Random();
        int maxEdad = 15;
        int edad = random.nextInt(maxEdad + 1);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.YEAR, -edad);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Los meses en Calendar se indexan desde 0 (enero) a 11 (diciembre)
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String fechaNacimiento = String.format("%02d/%02d/%04d", day, month, year);
        return fechaNacimiento;
    }
}
