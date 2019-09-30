package ru.mail.polis.ads.part1;

import java.util.Scanner;

/**
 *  submission - https://www.e-olymp.com/ru/submissions/5745908
 */
public final class Problem1 {
    private Problem1() {
        // Should not be instantiated
    }

    private static void solve() {
        final Scanner sc = new Scanner(System.in);
        final String str = String.valueOf(sc.nextInt());
        System.out.println(str.charAt(0) + " " + str.charAt(1));
    }

    public static void main(final String[] arg) {
        solve();
    }
}
