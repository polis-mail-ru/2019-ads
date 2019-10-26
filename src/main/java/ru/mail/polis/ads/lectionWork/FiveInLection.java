package ru.mail.polis.ads.lectionWork;

import java.util.Scanner;

public class FiveInLection {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] d = new int[n];
        d[0] = 1;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (a[j] != 0 && a[i] % a[j] == 0 && d[j] > d[i]) {
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
