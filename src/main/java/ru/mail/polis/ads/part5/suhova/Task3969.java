package ru.mail.polis.ads.part5.suhova;

import java.util.Scanner;

public class Task3969 {
    /*
    Task 2: https://www.e-olymp.com/ru/submissions/5924581
     */
    private static void solve() {
        Scanner s = new Scanner(System.in);
        long w = s.nextInt();
        long h = s.nextInt();
        long n = s.nextInt();
        long l = Math.max(w, h);
        long r = n * Math.max(w, h);
        long d, n1;
        do {
            d = (l + r) / 2;
            n1 = (d / h) * (d / w);
            if (n1 >= n) r = d;
            else l = d + 1;
        } while (l < r);
        System.out.print(l);
    }

    public static void main(final String[] arg) {
        solve();
    }
}
