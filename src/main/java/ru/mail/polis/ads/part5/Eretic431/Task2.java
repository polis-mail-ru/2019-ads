package main.java.ru.mail.polis.ads.part5.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5924652
 */

public class Task2 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int w = in.nextInt();
        final int h = in.nextInt();
        final int n = in.nextInt();

        long start = Math.max(w, h);
        long end = n * Math.max(w, h);
        long m;

        while (start < end) {
            m = (start + end) / 2;
            long v = (m / w) * (m / h);
            if (n <= v) {
                end = m;
            } else {
                start = m + 1;
            }
        }

        System.out.println(start);
    }
}
