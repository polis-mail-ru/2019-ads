package ru.mail.polis.ads.part2.kuzo_liza;

import java.util.Scanner;

public class Ladder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] d = new int[n + 2];
        int max;
        d[0] = 0;
        d[n + 1] = 0;

        for (int i = 1; i <= n; i++) {
            d[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();

        for (int i = 1; i <= n + 1; i++) {
            max = Integer.MIN_VALUE;
            for (int j = i - k; j < i; j++) {
                if (j < 0) {
                    j = 0;
                }
                if (max < d[j]) {
                    max = d[j];
                }
            }
            d[i] += max;
        }
        System.out.println(d[n + 1]);
    }
}