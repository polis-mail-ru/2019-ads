package ru.mail.polis.ads.part1;

import java.util.Scanner;

public class Task3 {
    private static String string;
    private static String[][] packed;

    private Task3() {
    }

    private static void solve() {
        final Scanner scanner = new Scanner(System.in);
        string = scanner.nextLine();
        final int length = string.length();
        packed = new String[length][length];
        for (int len = 1; len <= length; len++) {
            for (int left = 0; left + len - 1 < length; left++) {
                int right = left + len - 1;
                String min = string.substring(left, right + 1);
                if (len > 4) {
                    for (int right1 = left; right1 < right; right1++) {
                        int left2 = right1 + 1;
                        String t = "";
                        t = packed[left][right1] + packed[left2][right];
                        if (t.length() < min.length()) {
                            min = t;
                        }
                    }
                    for (int period = 1; period < len; period++) {
                        if (len % period == 0) {
                            boolean isPeriod = true;
                            for (int i = left + period; i <= right; i++) {
                                if (string.charAt(i) != string.charAt(i - period)) {
                                    isPeriod = false;
                                    break;
                                }
                            }
                            if (isPeriod) {
                                String t = len / period + "(" + packed[left][left + period - 1] + ")";
                                if (t.length() < min.length()) {
                                    min = t;
                                }
                            }
                        }
                    }
                }
                packed[left][right] = min;
            }
        }
        System.out.println(packed[0][length - 1]);
    }

    public static void main(String[] args) {
        solve();
    }
}
