package ru.mail.polis.ads.part5.marashov.alexander;

import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final long[] array = new long[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextLong();
        }
        final int[] d = new int[n];
        d[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (array[j] != 0 && array[i] % array[j] == 0 && d[j] > d[i]) {
                    d[i] = d[j];
                }
            }
            d[i]++;
        }
        int maxCount = 1;
        for (int i = 1; i < n; ++i) {
            maxCount = Math.max(d[i], maxCount);
        }
        System.out.println(maxCount);
    }
}
