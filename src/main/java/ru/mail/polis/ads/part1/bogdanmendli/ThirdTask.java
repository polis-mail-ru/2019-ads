package ru.mail.polis.ads.part1.bogdanmendli;

import java.io.PrintWriter;
import java.util.Scanner;

public final class ThirdTask {

    private static String sequence;
    private static String minSequence;
    private static String[][] packed;


    private ThirdTask() {
    }


    private static void findMinimumSequence(final int left, final int right, final int i) {
        checkLength(left, right);
        for (int j = 1; j < i; j++) {
            if (i % j == 0) {
                boolean isRepeat = true;
                for (int k = left + j; k <= right; k++) {
                    if (sequence.charAt(k) != sequence.charAt(k - j)) {
                        isRepeat = false;
                        break;
                    }
                }
                if (isRepeat) {
                    final String temp = i / j + "(" + packed[left][left + j - 1] + ")";
                    if (temp.length() < minSequence.length()) {
                        minSequence = temp;
                    }
                }
            }
        }
    }

    static void checkLength(final int left, final int right) {
        for (int right1 = left; right1 < right; right1++) {
            final int left2 = right1 + 1;
            final String temp = packed[left][right1] + packed[left2][right];
            if (temp.length() < minSequence.length()) {
                minSequence = temp;
            }
        }
    }

    private static void solve() {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        sequence = in.next();
        final int length = sequence.length();
        packed = new String[length][length];
        for (int i = 1; i <= length; i++) {
            for (int left = 0; left + i - 1 < length; left++) {
                final int right = left + i - 1;
                minSequence = sequence.substring(left, left + i);
                if (i > 4) {
                    findMinimumSequence(left, right, i);
                }
                packed[left][right] = minSequence;
            }
        }
        out.println(packed[0][length - 1]);
    }

    public static void main(final String[] arg) {
        solve();
    }
}
