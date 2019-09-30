package ru.mail.polis.ads.part1;

import java.util.Scanner;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5741169
 */
public final class Problem3 {

    private static String str;
    private static String[][] shortest;

    private Problem3() {
        // Should not be instantiated
    }

    private static void solve() {
        final Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        final int length = str.length();
        shortest = new String[length][length];

        for (int len = 1; len <= length; len++) {
            for (int left = 0; left < length - len + 1; left++) {
                final int right = left + len - 1;
                String min = str.substring(left, right + 1);
                if (len > 4) {
                    min = mergeFromArray(shortest, left, right, min);
                    min = wrapIfPeriodic(len, left, right, min);
                }
                shortest[left][right] = min;
            }
        }
        System.out.println(shortest[0][length - 1]);
    }

    private static String wrapIfPeriodic(final int len, final int left, final int right, final String min) {
        String result = min;
        for (int period = 1; period <= len / 2; period++) {
            if (len % period == 0) {
                result = wrapIfPeriodic(left, right, result, period);
            }
        }
        return result;
    }

    private static String wrapIfPeriodic(final int left, final int right, final String result, final int period) {
        String res = result;
        final int len = right - left + 1;
        final boolean isPeriodic = checkIsPeriodic(left, right, period);
        if (isPeriodic) {
            final String currentShortest = (len / period) + "(" + shortest[left][left + period - 1] + ")";
            if (currentShortest.length() < res.length()) {
                res = currentShortest;
            }
        }
        return res;
    }

    private static boolean checkIsPeriodic(final int left, final int right, final int period) {
        boolean isPeriodic = true;
        for (int j = left + period; j <= right; j++) {
            if (str.charAt(j) != str.charAt(j - period)) {
                isPeriodic = false;
                break;
            }
        }
        return isPeriodic;
    }

    static String mergeFromArray(String[][] shortest, final int left, final int right, final String min) {
        String result = min;
        for (int i = left; i < right; i++) {
            final String currentShortest = shortest[left][i] + shortest[i + 1][right];
            if (currentShortest.length() < result.length()) {
                result = currentShortest;
            }
        }
        return result;
    }

    public static void main(final String[] arg) {
        solve();
    }
}
