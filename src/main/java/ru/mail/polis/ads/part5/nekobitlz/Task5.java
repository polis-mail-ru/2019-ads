package ru.mail.polis.ads.part5.nekobitlz;

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {
    private static int[] digits;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        digits = new int[n];

        for (int i = 0; i < n; i++) {
            digits[i] = i + 1;
        }

        while (true) {
            for (int digit: digits) {
                out.print(digit + " ");
            }
            out.println();

            int k = n - 2; // k + 1 = n - 1;

            while (k > -1) {
                if (digits[k] < digits[k + 1]) break;
                k--;
            }

            if (k == -1) break;

            int min = findMinimum(k + 1, n);
            swap(k, min);
            reverse(k + 1, n - 1);
        }
    }

    private static void reverse(int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            swap(startIndex, endIndex);
            startIndex++;
            endIndex--;
        }
    }

    private static int findMinimum(int startIndex, int endIndex) {
        int min = Integer.MAX_VALUE;
        int minIndex = startIndex;
        int element = digits[startIndex - 1];

        for (int i = startIndex; i < endIndex; i++) {
            if (digits[i] > element && digits[i] < min) {
                min = digits[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    private static void swap(int i, int j) {
        int helper = digits[i];
        digits[i] = digits[j];
        digits[j] = helper;
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
