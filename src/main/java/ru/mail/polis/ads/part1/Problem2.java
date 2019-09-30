package ru.mail.polis.ads.part1;

import java.util.Scanner;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5740640
 */
public final class Problem2 {

    private static String[][] shortest;
    private static String str;

    private Problem2() {
        // Should not be instantiated
    }

    private static void solve() {
        final Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        if (str.isEmpty()) {
            return;
        }
        int length = str.length();
        shortest = new String[length][length];

        for (int len = 1; len <= length; len++) {
            for (int left = 0; left < length - len + 1; left++) {
                final int right = left + len - 1;
                if (len == 1) {
                    onLengthOne(left, right);
                } else if (len == 2) {
                    onLengthTwo(left, right);
                } else {
                    onLengthMoreThanTwo(left, right);
                }
            }
        }
        System.out.println(shortest[0][length - 1]);
    }

    private static void onLengthMoreThanTwo(int left, int right) {
        final StringBuilder builder = new StringBuilder();
        for (int i =0; i < 201; i++) {
            builder.append('.');
        }
        String min = builder.toString();
        for (int i = left; i < right; i++) {
            final String currentShortest = shortest[left][i] + shortest[i + 1][right];
            if (min.length() > currentShortest.length()) {
                min = currentShortest;
            }
        }
        if (
            str.charAt(left) == '(' && str.charAt(right) == ')'
                || str.charAt(left) == '[' && str.charAt(right) == ']'
        ) {
            final String newStr = str.charAt(left) + shortest[left + 1][right - 1] + str.charAt(right);
            if (newStr.length() < min.length()) {
                min = newStr;
            }
        }
        shortest[left][right] = min;
    }

    private static void onLengthTwo(int left, int right) {
        if (str.charAt(left) == '(' && str.charAt(right) == ')') {
            shortest[left][right] = "()";
        } else if (str.charAt(left) == '[' && str.charAt(right) == ']') {
            shortest[left][right] = "[]";
        } else {
            shortest[left][right] = shortest[left][left] + shortest[right][right];
        }
    }

    private static void onLengthOne(int left, int right) {
        if (str.charAt(left) == '(' || str.charAt(right) == ')') {
            shortest[left][right] = "()";
        } else if (str.charAt(left) == '[' || str.charAt(right) == ']') {
            shortest[left][right] = "[]";
        }
    }

    public static void main(final String[] arg) {
        solve();
    }
}
