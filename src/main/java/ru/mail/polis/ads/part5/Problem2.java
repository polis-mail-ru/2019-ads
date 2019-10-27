package ru.mail.polis.ads.part5;

import java.util.Scanner;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5924529
 */
public class Problem2 {

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        long w = sc.nextInt();
        long h = sc.nextInt();
        long n = sc.nextInt();

        long left = Math.max(w, h);
        long right = n * Math.max(w, h);

        do {
            long m = (left + right) / 2;
            long v = (m / w) * (m / h);
            if (v >= n) {
                right = m;
            } else {
                left = m + 1;
            }
        } while (left < right);

        System.out.println(left);
    }

    public static void main(String[] args) {
        solve();
    }
}
