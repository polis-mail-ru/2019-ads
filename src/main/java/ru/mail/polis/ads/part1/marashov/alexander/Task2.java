package ru.mail.polis.ads.part1.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task2 {

    private static final char BR1_OPEN = '(';
    private static final char BR1_CLOSE = ')';
    private static final char BR2_OPEN = '[';
    private static final char BR2_CLOSE = ']';

    private static int[][] decisions;
    private static String brackets;
    private static int[][] length;
    private static PrintWriter out;

    private Task2() {

    }

    private static int getMinSequenceLength(final int left, final int right) {
        int answer;
        if (length[left][right] != -1) {
            answer = length[left][right];
        } else if (left == right) {
            length[left][right] = 0;
            answer = 0;
        } else if (brackets.charAt(left) == BR1_CLOSE || brackets.charAt(left) == BR2_CLOSE) {
            decisions[left][right] = left + 1;
            int len = getMinSequenceLength(left + 1, right) + 2;
            length[left][right] = len;
            answer = len;
        } else if (brackets.charAt(right - 1) == BR1_OPEN || brackets.charAt(right - 1) == BR2_OPEN) {
            decisions[left][right] = right - 1;
            answer = getMinSequenceLength(left, right - 1) + 2;
            length[left][right] = answer;
        } else {
            final int nextLeft = left + 1;
            final int prevRight = right - 1;
            if (brackets.charAt(left) == BR1_OPEN && brackets.charAt(prevRight) == BR1_CLOSE
                    || brackets.charAt(left) == BR2_OPEN && brackets.charAt(prevRight) == BR2_CLOSE) {

                decisions[left][right] = 0;
                answer = getMinSequenceLength(nextLeft, prevRight) + 2;
            } else {
                answer = 200;
            }

            for (int ind = nextLeft; ind < right; ++ind) {
                final int newLen = getMinSequenceLength(left, ind) + getMinSequenceLength(ind, right);

                if (newLen < answer) {
                    decisions[left][right] = ind;
                    answer = newLen;
                }
            }
            length[left][right] = answer;
        }
        return answer;
    }

    private static void printAnswer(final int left, final int right) {
        if (left == right) {
            return;
        }
        if (left == right - 1) {
            switch (brackets.charAt(left)) {
                case BR1_OPEN:
                case BR1_CLOSE:
                    out.print(Character.toString(BR1_OPEN) + BR1_CLOSE);
                    break;
                case BR2_OPEN:
                case BR2_CLOSE:
                    out.print(Character.toString(BR2_OPEN) + BR2_CLOSE);
                    break;
                default:
            }
        } else if (decisions[left][right] == 0) {
            out.print(brackets.charAt(left));
            printAnswer(left + 1, right - 1);
            out.print(brackets.charAt(right - 1));
        } else if (decisions[left][right] > 0) {
            printAnswer(left, decisions[left][right]);
            printAnswer(decisions[left][right], right);
        }
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        Task2.out = out;
        brackets = in.nextLine();
        if (brackets.length() == 0) {
            return;
        }
        decisions = new int[brackets.length() + 1][brackets.length() + 1];
        length = new int[brackets.length() + 1][brackets.length() + 1];
        for (int i = 0; i <= brackets.length(); ++i) {
            for (int j = 0; j <= brackets.length(); ++j) {
                length[i][j] = -1;
            }
        }
        getMinSequenceLength(0, brackets.length());
        printAnswer(0, brackets.length());
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
