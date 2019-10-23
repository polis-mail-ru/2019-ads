package ru.mail.polis.ads.part5.bardaev;

import java.util.Scanner;

public class Task3969 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long w = in.nextLong();
        long h = in.nextInt();
        long n = in.nextInt();

        long L = Math.max(w, h);
        long R = n * L;

        long m, v;

        while (L < R) {
            m = (L + R) / 2;

            v = (m / w) * (m / h);

            if (n <= v) {
                R = m;
            } else {
                L = m + 1;
            }
        }

        System.out.println(Math.max(R, L ));
    }
}
