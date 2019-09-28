package ru.mail.polis.ads.part1.bogdanMendli;

import java.io.PrintWriter;
import java.util.Scanner;

public final class ThirdTask {

    private ThirdTask() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final String sequence = in.next();
        final int length = sequence.length();
        String[][] packed = new String[length][length];
        for (int i = 1; i <= length; i++) {
            for (int left = 0; left + i - 1 < length; left++) {
                final int right = left + i - 1;
                String minSequence = sequence.substring(left, left + i);
                if (i > 4) {
                    for (int right1 = left; right1 < right; right1++) {
                        final int left2 = right1 + 1;
                        final String temp = packed[left][right1] + packed[left2][right];
                        if (temp.length() < minSequence.length()) {
                            minSequence = temp;
                        }
                    }
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
                packed[left][right] = minSequence;
            }
        }
        out.println(packed[0][length - 1]);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
