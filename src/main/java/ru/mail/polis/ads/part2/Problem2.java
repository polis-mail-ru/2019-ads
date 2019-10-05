package ru.mail.polis.ads.part2;

import java.util.Scanner;

/**
 * submission https://www.e-olymp.com/ru/submissions/5784667
 */
public class Problem2 {

    private static int n;
    private static int[] array;
    private static int k;

    private Problem2() {
        // Should not be instantiated
    }

    private static void solve() {
        readData();

        for (int i = 1; i < n + 2; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i - k; j < i; j++) {
                if (j < 0) {
                    continue;
                }
                if (array[j] > max) {
                    max = array[j];
                }
            }
            array[i] = max + array[i];
        }

        System.out.println(array[n + 1]);
    }

    private static void readData() {
        final Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        array = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            array[i] = sc.nextInt();
        }
        k = sc.nextInt();
    }

    public static void main(final String[] arg) {
        solve();
    }
}
