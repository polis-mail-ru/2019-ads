package ru.mail.polis.ads.part1.Marashov_Alexander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public final class Task2 {
    private Task2() {

    }

    private static final char
            BR1_OPEN = '(',
            BR1_CLOSE = ')',
            BR2_OPEN = '[',
            BR2_CLOSE = ']';

    private static int[][] decisions;
    private static String brackets;
    private static int[][] length;
    private static PrintWriter out;

    private static int getMinSequenceLength(int left, int right) {

        if (length[left][right] != -1) {
            return length[left][right];
        }

        if (left == right) {
            length[left][right] = 0;
            return 0;
        }

        if (brackets.charAt(left) == BR1_CLOSE || brackets.charAt(left) == BR2_CLOSE) {
            decisions[left][right] = left + 1;
            int len = getMinSequenceLength(left + 1, right) + 2;
            length[left][right] = len;
            return len;
        }

        if (brackets.charAt(right - 1) == BR1_OPEN || brackets.charAt(right - 1) == BR2_OPEN) {
            decisions[left][right] = right - 1;
            int len = getMinSequenceLength(left, right - 1) + 2;
            length[left][right] = len;
            return len;
        }

        int nextLeft = left + 1;
        int prevRight = right - 1;

        int len;
        if ((brackets.charAt(left) == BR1_OPEN && brackets.charAt(prevRight) == BR1_CLOSE)
                || (brackets.charAt(left) == BR2_OPEN && brackets.charAt(prevRight) == BR2_CLOSE)) {

            decisions[left][right] = 0;
            len = getMinSequenceLength(nextLeft, prevRight) + 2;
        } else {
            len = 200;
        }

        for (int ind = nextLeft; ind < right; ++ind) {
            int newLen = getMinSequenceLength(left, ind) + getMinSequenceLength(ind, right);

            if (newLen < len) {
                decisions[left][right] = ind;
                len = newLen;
            }
        }
        length[left][right] = len;
        return len;
    }

    private static void printAnswer(int left, int right) {
        if (left == right) {
            return;
        }
        if (left == right - 1) {
            switch (brackets.charAt(left)) {
                case BR1_OPEN:
                case BR1_CLOSE:
                    out.print("" + BR1_OPEN + BR1_CLOSE);
                    break;
                case BR2_OPEN:
                case BR2_CLOSE:
                    out.print("" + BR2_OPEN + BR2_CLOSE);
                    break;
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        Task2.out = out;
        brackets = new Scanner(System.in).nextLine();
        if (brackets.length() == 0)
            return;
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

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
