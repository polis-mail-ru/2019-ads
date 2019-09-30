package ru.mail.polis.ads.part1.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task3 {

    private static String[][] strings;
    private static String str;
    private static String minStr;

    private Task3() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        str = in.next();
        final int strLen = str.length();
        strings = new String[strLen][strLen];
        for (int length = 1; length <= strLen; length++) {
            for (int left = 0; left + length - 1 < strLen; ++left) {
                minStr = str.substring(left, left + length);
                final int right = left + length - 1;
                if (length > 4) {
                    trySplit(left, right);
                    checkPeriods(left, right, length);
                }
                strings[left][right] = minStr;
            }
        }
        out.println(strings[0][strLen - 1]);
    }

    private static void checkPeriods(final int left, final int right, final int length) {
        for (int period = 1; period < length; ++period) {
            if (length % period == 0) {
                boolean isPeriodic = true;
                for (int i = left + period; i <= right; ++i) {
                    if (str.charAt(i) != str.charAt(i - period)) {
                        isPeriodic = false;
                        break;
                    }
                }
                if (isPeriodic) {
                    final String newStr = (length / period) + "(" + strings[left][left + period - 1] + ")";
                    if (newStr.length() < minStr.length()) {
                        minStr = newStr;
                    }
                }
            }
        }
    }

    private static void trySplit(final int left, final int right) {
        for (int index = left; index < right; ++index) {
            String newMinStr = strings[left][index] + strings[index + 1][right];
            if (newMinStr.length() < minStr.length()) {
                minStr = newMinStr;
            }
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
