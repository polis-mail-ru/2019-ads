package ru.mail.polis.ads.part1.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task3 {

    private static String[][] strings;
    private static String str;
    private static String minStr;
    private static int strLen;

    private Task3() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        str = in.next();
        strLen = str.length();
        strings = new String[strLen][strLen];
        for (int length = 1; length <= strLen; length++) {
            checkLength(length);
        }
        out.println(strings[0][strLen - 1]);
    }

    private static void checkLength(final int length) {
        for (int left = 0; left + length - 1 < strLen; ++left) {
            minStr = str.substring(left, left + length);
            final int right = left + length - 1;
            if (length > 4) {
                trySplit(left, right);
                for (int period = 1; period < length; ++period) {
                    checkPeriod(period, left, right, length);
                }
            }
            strings[left][right] = minStr;
        }
    }

    private static void checkPeriod(final int period, final int left, final int right, final int length) {
        if (length % period == 0) {
            boolean isPeriodic = true;
            for (int i = left + period; i <= right; ++i) {
                if (str.charAt(i) != str.charAt(i - period)) {
                    isPeriodic = false;
                    break;
                }
            }
            if (isPeriodic) {
                updateMinStr((length / period) + "(" + strings[left][left + period - 1] + ")");
            }
        }
    }

    private static void updateMinStr(String newMinStr) {
        if (newMinStr.length() < minStr.length()) {
            minStr = newMinStr;
        }
    }

    private static void trySplit(final int left, final int right) {
        for (int index = left; index < right; ++index) {
            String newMinStr = strings[left][index] + strings[index + 1][right];
            updateMinStr(newMinStr);
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
