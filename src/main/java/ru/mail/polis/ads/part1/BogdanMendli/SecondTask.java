package ru.mail.polis.ads.part1.BogdanMendli;

import java.io.PrintWriter;
import java.util.Scanner;

public class SecondTask {

    private static void solve(Scanner in, PrintWriter out) {
        String sequence = in.nextLine();
        if (sequence.equals("")) {
            out.println();
            return;
        }
        int length = sequence.length();
        String[][] resultSequences = new String[length][length];
        for (int i = 1; i <= length; i++) {
            for (int left = 0; left + i - 1 < length; left++) {
                int right = left + i - 1;
                if (i == 1) {
                    if ((sequence.charAt(left) == '(') || (sequence.charAt(left) == ')')) {
                        resultSequences[left][right] = "()";
                    } else if ((sequence.charAt(left) == '[') || (sequence.charAt(left) == ']')) {
                        resultSequences[left][right] = "[]";
                    } else throw new IllegalArgumentException("Something went wrong");
                } else if (i == 2) {
                    if ((sequence.charAt(left) == '(') && (sequence.charAt(right) == ')')) {
                        resultSequences[left][right] = "()";
                    } else if ((sequence.charAt(left) == '[') && (sequence.charAt(right) == ']')) {
                        resultSequences[left][right] = "[]";
                    } else {
                        resultSequences[left][right] = resultSequences[left][left] + resultSequences[right][right];
                    }
                } else {
                    StringBuilder minSequence = new StringBuilder(".");
                    for (int k = 0; k < 20; k++) {
                        minSequence.append("..........");
                    }
                    for (int right1 = left; right1 < right; right1++) {
                        int left2 = right1 + 1;
                        String temp = resultSequences[left][right1] + resultSequences[left2][right];
                        if (temp.length() < minSequence.length()) {
                            minSequence = new StringBuilder(temp);
                        }
                    }
                    if (((sequence.charAt(left) == '(') && (sequence.charAt(right) == ')'))
                        || ((sequence.charAt(left) == '[') && (sequence.charAt(right) == ']'))) {
                        String temp = sequence.charAt(left)
                            + resultSequences[left + 1][right - 1]
                            + sequence.charAt(right);
                        if (temp.length() < minSequence.length()) {
                            minSequence = new StringBuilder(temp);
                        }
                    }
                    resultSequences[left][right] = minSequence.toString();
                }
            }
        }
        out.println(resultSequences[0][length - 1]);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
