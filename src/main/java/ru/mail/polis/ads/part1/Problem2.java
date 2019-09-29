package ru.mail.polis.ads.part1;

import java.util.Scanner;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5740640
 */
public final class Problem2 {
    private Problem2() {
        // Should not be instantiated
    }

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.isEmpty()) {
            return;
        }
        int length = str.length();
        String[][] shortest = new String[length][length];

        for (int len = 1; len <= length; len++) {
            for (int left = 0; left < length - len + 1; left++) {
                int right = left + len - 1;
                if (len == 1) {
                    onLengthOne(str, shortest, left, right);
                } else if (len == 2) {
                    onLengthTwo(str, shortest, left, right);
                } else {
                    onLengthMoreThanTwo(str, shortest, left, right);
                }
            }
        }
        System.out.println(shortest[0][length - 1]);
    }

    private static void onLengthMoreThanTwo(String str, String[][] shortest, int left, int right) {
        String dot = ".";
        String min = dot.repeat(201);
        for (int i = left; i < right; i++) {
            String currentShortest = shortest[left][i] + shortest[i + 1][right];
            if (min.length() > currentShortest.length()) {
                min = currentShortest;
            }
        }
        if (
            str.charAt(left) == '(' && str.charAt(right) == ')'
                || str.charAt(left) == '[' && str.charAt(right) == ']'
        ) {
            String newStr = str.charAt(left) + shortest[left + 1][right - 1] + str.charAt(right);
            if (newStr.length() < min.length()) {
                min = newStr;
            }
        }
        shortest[left][right] = min;
    }

    private static void onLengthTwo(String str, String[][] shortest, int left, int right) {
        if (str.charAt(left) == '(' && str.charAt(right) == ')') {
            shortest[left][right] = "()";
        } else if (str.charAt(left) == '[' && str.charAt(right) == ']') {
            shortest[left][right] = "[]";
        } else {
            shortest[left][right] = shortest[left][left] + shortest[right][right];
        }
    }

    private static void onLengthOne(String str, String[][] shortest, int left, int right) {
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