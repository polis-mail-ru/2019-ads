package ru.mail.polis.ads.part3.kuzo_liza;

import java.util.Scanner;

public class TrickySort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int array[] = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                if (array[i] % 10 > array[j] % 10) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                } else if (array[i] % 10 == array[j] % 10) {
                    if (array[i] > array[j]) {
                        int tmp = array[i];
                        array[i] = array[j];
                        array[j] = tmp;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}