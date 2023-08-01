package com.testgenerator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateGenerator {

    public static String generateDate() {
        Random random = new Random();
        int maxEdad = 90;
        int minEdad = 18;
        int edad = minEdad + random.nextInt(maxEdad + 1);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.YEAR, -edad);

        int year = calendar.get(Calendar.YEAR);
        int month = random.nextInt(12) + 1; // Los meses van de 1 a 12 (enero a diciembre)
        int day = random.nextInt(getMaxDayOfMonth(year, month)) + 1; // Día aleatorio del mes

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
        int month = random.nextInt(12) + 1; // Los meses van de 1 a 12 (enero a diciembre)
        int day = random.nextInt(getMaxDayOfMonth(year, month)) + 1; // Día aleatorio del mes

        String fechaNacimiento = String.format("%02d/%02d/%04d", day, month, year);
        return fechaNacimiento;
    }

    public static int getMaxDayOfMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
