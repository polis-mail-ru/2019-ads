package ru.mail.polis.ads.part3.kuzo_liza;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int result = 0;

        for (int i = 0; i < n ; i++) {
            array[i] = scanner.nextInt();
        }

        for (int i = 0; i < n - 1 ; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
