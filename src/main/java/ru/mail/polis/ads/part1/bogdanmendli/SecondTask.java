package ru.mail.polis.ads.part1.bogdanmendli;

import java.io.PrintWriter;
import java.util.Scanner;

public final class SecondTask {

    private static String minSequence;
    private static String[][] resultSequences;
    private static String sequence;
    private static int length;

    private SecondTask() {
    }

    private static void checkSequence() {
        for (int i = 1; i <= length; i++) {
            for (int left = 0; left + i - 1 < length; left++) {
                final int right = left + i - 1;
                if (i == 1) {
                    resultSequences[left][right] = checkBracketLengthByOne(sequence.charAt(left), left, right);
                } else if (i == 2) {
                    resultSequences[left][right] = checkBracketLengthByTwo(
                        sequence.charAt(left),
                        sequence.charAt(right),
                        left,
                        right
                    );
                } else {
                    findMinimumSequence(left, right);
                }
            }
        }
    }

    private static String checkBracketLengthByOne(final char bracket, final int left, final int right) {
        if (bracket == '(' || bracket == ')') {
            return "()";
        } else if (bracket == '[' || bracket == ']') {
            return "[]";
        } else {
            throw new IllegalArgumentException("Something went wrong");
        }
    }

    private static String checkBracketLengthByTwo(
        final char bracketLeft,
        final char bracketRight,
        final int left,
        final int right
    ) {
        if (bracketLeft == '(' || bracketRight == ')') {
            return "()";
        } else if (bracketLeft == '[' || bracketRight == ']') {
            return "[]";
        } else {
            return resultSequences[left][left] + resultSequences[right][right];
        }
    }

    private static void findMinimumSequence(final int left, final int right) {
        minSequence = "00000".repeat(40) + '0';
        ThirdTask.checkLength(left, right);
        if (sequence.charAt(left) == '(' && sequence.charAt(right) == ')'
            || sequence.charAt(left) == '[' && sequence.charAt(right) == ']') {
            final String temp = sequence.charAt(left)
                + resultSequences[left + 1][right - 1]
                + sequence.charAt(right);
            if (temp.length() < minSequence.length()) {
                minSequence = temp;
            }
        }
        resultSequences[left][right] = minSequence;
    }

    private static void solve() {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        sequence = in.nextLine();
        if ("".equals(sequence)) {
            out.println();
            return;
        }
        length = sequence.length();
        resultSequences = new String[length][length];
        checkSequence();
        out.println(resultSequences[0][length - 1]);
    }

    public static void main(final String[] arg) {
        solve();
    }
}
