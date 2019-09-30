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

    private static int getMinLength(final int left, final int right) {
        final boolean noAnswer = length[left][right] == -1;
        if (!noAnswer) {
            return length[left][right];
        }

        if (left == right) {
            length[left][right] = 0;
            return 0;
        }

        int answer = 200;
        if (brackets.charAt(left) == BR1_CLOSE || brackets.charAt(left) == BR2_CLOSE) {
            decisions[left][right] = left + 1;
            answer = getMinLength(left + 1, right) + 2;
            length[left][right] = answer;
        } else if (brackets.charAt(right - 1) == BR1_OPEN || brackets.charAt(right - 1) == BR2_OPEN) {
            decisions[left][right] = right - 1;
            answer = getMinLength(left, right - 1) + 2;
            length[left][right] = answer;
        } else {
            if (brackets.charAt(left) == BR1_OPEN && brackets.charAt(right - 1) == BR1_CLOSE
                    || brackets.charAt(left) == BR2_OPEN && brackets.charAt(right - 1) == BR2_CLOSE) {

                decisions[left][right] = 0;
                answer = getMinLength(left + 1, right - 1) + 2;
            }

            answer = minLengthAt(left, right, answer);
            length[left][right] = answer;
        }
        return answer;
    }

    private static int minLengthAt(final int left, final int right, final int min) {
        int answer = min;
        for (int ind = left + 1; ind < right; ++ind) {
            final int newLen = getMinLength(left, ind) + getMinLength(ind, right);
            if (newLen < answer) {
                decisions[left][right] = ind;
                answer = newLen;
            }
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
        getMinLength(0, brackets.length());
        printAnswer(0, brackets.length());
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
