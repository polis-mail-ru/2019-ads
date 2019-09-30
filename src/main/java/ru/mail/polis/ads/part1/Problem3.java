package ru.mail.polis.ads.part1;

import java.util.Scanner;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5741169
 */
public final class Problem3 {
    private Problem3() {
        // Should not be instantiated
    }

    private static void solve() {
        final Scanner sc = new Scanner(System.in);
        final String str = sc.nextLine();
        final int length = str.length();
        final String[][] shortest = new String[length][length];

        for (int len = 1; len <= length; len++) {
            for (int left = 0; left < length - len + 1; left++) {
                final int right = left + len - 1;
                String min = str.substring(left, right + 1);
                if (len > 4) {
                    for (int i = left; i < right; i++) {
                        final String currentShortest = shortest[left][i] + shortest[i + 1][right];
                        if (currentShortest.length() < min.length()) {
                            min = currentShortest;
                        }
                    }
                    for (int period = 1; period <= len / 2; period++) {
                        if (len % period == 0) {
                            boolean isPeriodic = true;
                            for (int j = left + period; j <= right; j++) {
                                if (str.charAt(j) != str.charAt(j - period)) {
                                    isPeriodic = false;
                                    break;
                                }
                            }
                            if (isPeriodic) {
                                final String currentShortest = (len / period) + "(" + shortest[left][left + period - 1] + ")";
                                if (currentShortest.length() < min.length()) {
                                    min = currentShortest;
                                }
                            }
                        }
                    }
                }
                shortest[left][right] = min;
            }
        }

        System.out.println(shortest[0][length - 1]);
    }

    public static void main(final String[] arg) {
        solve();
    }
}
