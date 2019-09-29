package ru.mail.polis.ads.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5741169
 */
public final class Problem3 {
    private Problem3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        int length = str.length();
        String[][] shortest = new String[length][length];

        for (int len = 1; len <= length; len++) {
            for (int left = 0; left < length - len + 1; left++) {
                int right = left + len - 1;
                String min = str.substring(left, right + 1);
                if (len > 4) {
                    for (int i = left; i < right; i++) {
                        String currentShortest = shortest[left][i] + shortest[i + 1][right];
                        if (currentShortest.length() < min.length()) {
                            min = currentShortest;
                        }
                    }
                    for (int period = 1; period <= len / 2; period++) {
                        if (len % period == 0) {
                            boolean isPeriodic = true;
                            for (int j = left + period; j <= right; j++) {
                                if (str.charAt(j) != str.charAt(j - period)) {
                                    isPeriodic = false;
                                    break;
                                }
                            }
                            if (isPeriodic) {
                                String currentShortest = (len / period) + "(" + shortest[left][left + period - 1] + ")";
                                if (currentShortest.length() < min.length()) {
                                    min = currentShortest;
                                }
                            }
                        }
                    }
                }
                shortest[left][right] = min;
            }
        }

        System.out.println(shortest[0][length - 1]);
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
