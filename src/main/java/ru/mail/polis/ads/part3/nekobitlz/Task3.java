package ru.mail.polis.ads.part3.nekobitlz;

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Integer[] digits = new Integer[n];

        for (int i = 0; i < n; i++) {
            digits[i] = in.nextInt();
        }

        out.print(bubbleSort(digits));
    }

    private static int bubbleSort(Integer[] digits) {
        int count = 0;

        for (int i = 0; i < digits.length; i++) {
            for (int j = digits.length - 1; j > i; j--) {
                if (digits[j - 1] > digits[j]) {
                    int x = digits[j - 1];
                    digits[j - 1] = digits[j];
                    digits[j] = x;
                    count++;
                }
            }
        }

        return count;
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
