package ru.mail.polis.ads.part1.bogdanmendli;

import java.io.PrintWriter;
import java.util.Scanner;

public final class SecondTask {

    private static StringBuilder minSequence = new StringBuilder();
    private static String[][] resultSequences;
    private static String sequence;

    private SecondTask() {
    }

    private static void solve() {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        sequence = in.nextLine();
        if ("".equals(sequence)) {
            out.println();
            return;
        }
        final int length = sequence.length();
        resultSequences = new String[length][length];
        for (int i = 1; i <= length; i++) {
            for (int left = 0; left + i - 1 < length; left++) {
                final int right = left + i - 1;
                if (i == 1) {
                    if (sequence.charAt(left) == '(' || sequence.charAt(left) == ')') {
                        resultSequences[left][right] = "()";
                    } else if (sequence.charAt(left) == '[' || sequence.charAt(left) == ']') {
                        resultSequences[left][right] = "[]";
                    } else throw new IllegalArgumentException("Something went wrong");
                } else if (i == 2) {
                    if (sequence.charAt(left) == '(' && sequence.charAt(right) == ')') {
                        resultSequences[left][right] = "()";
                    } else if (sequence.charAt(left) == '[' && sequence.charAt(right) == ']') {
                        resultSequences[left][right] = "[]";
                    } else {
                        resultSequences[left][right] = resultSequences[left][left] + resultSequences[right][right];
                    }
                } else {
                    minSequence.delete(0, minSequence.length());
                    minSequence.append("0".repeat(101));
                    for (int right1 = left; right1 < right; right1++) {
                        final int left2 = right1 + 1;
                        final String temp = resultSequences[left][right1] + resultSequences[left2][right];
                        if (temp.length() < minSequence.length()) {
                            minSequence.delete(0, minSequence.length());
                            minSequence.append(temp);
                        }
                    }
                    if (sequence.charAt(left) == '(' && sequence.charAt(right) == ')'
                        || sequence.charAt(left) == '[' && sequence.charAt(right) == ']') {
                        final String temp = sequence.charAt(left)
                            + resultSequences[left + 1][right - 1]
                            + sequence.charAt(right);
                        if (temp.length() < minSequence.length()) {
                            minSequence.delete(0, minSequence.length());
                            minSequence.append(temp);
                        }
                    }
                    resultSequences[left][right] = minSequence.toString();
                }
            }
        }
        out.println(resultSequences[0][length - 1]);
    }

    public static void main(final String[] arg) {
        solve();
    }
}
