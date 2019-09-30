package ru.mail.polis.ads.part1.Marashov_Alexander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task3 {
    private Task3() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        int strLen = str.length();
        String[][] strings = new String[strLen][strLen];
        for (int length = 1; length <= strLen; length++) {
            for (int left = 0; left + length - 1 < strLen; ++left) {
                int right = left + length - 1;
                String minStr = str.substring(left, left + length);
                if (length > 4) {
                    for (int index = left; index < right; ++index) {
                        String newMinStr = strings[left][index] + strings[index + 1][right];
                        if (newMinStr.length() < minStr.length()) {
                            minStr = newMinStr;
                        }
                    }
                    for (int period = 1; period < length; ++period) {
                        if (length % period == 0) {
                            boolean isPeriodic = true;
                            for (int i = left + period; i <= right; ++i) {
                                if (str.charAt(i) != str.charAt(i - period)) {
                                    isPeriodic = false;
                                    break;
                                }
                            }
                            if (isPeriodic) {
                                String newStr = (length / period) + "(" + strings[left][left + period - 1] + ")";
                                if (newStr.length() < minStr.length()) {
                                    minStr = newStr;
                                }
                            }
                        }
                    }
                }
                strings[left][right] = minStr;
            }
        }
        out.println(strings[0][strLen - 1]);
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
