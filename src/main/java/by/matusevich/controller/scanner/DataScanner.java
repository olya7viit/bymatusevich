package by.matusevich.controller.scanner;

import java.util.Scanner;

public final class DataScanner {

    private DataScanner() {

    }

    public static double enterDoubleFromConcol() {
        Scanner sc = new Scanner(System.in);
        double a;
        while (!sc.hasNextDouble()) {
            sc.next();
        }
        a = sc.nextDouble();
        return a;
    }

    public static int enterIntFromConcol() {
        Scanner sc = new Scanner(System.in);
        int a;
        while (!sc.hasNextDouble()) {
            sc.next();
        }
        a = sc.nextInt();
        return a;
    }

    public static String enterStringFromConcol() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
