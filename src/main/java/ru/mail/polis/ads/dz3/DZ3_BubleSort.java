package ru.mail.polis.ads.dz3;

import java.util.Scanner;

public class DZ3_BubleSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] mass = new int[len];
        for (int i = 0; i < len; i++) {
            int a = in.nextInt();
            mass[i] = a;
        }
        bubleSort(mass);
    }

    private static void bubleSort(int[] mass) {
        int c;
        int counter = 0;
        for (int i = 0; i < mass.length; i++) {
            for (int j = 1; j < (mass.length - i); j++) {
                if (mass[j - 1] > mass[j]) {
                    int tmp = mass[j - 1];
                    mass[j - 1] = mass[j];
                    mass[j] = tmp;
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
