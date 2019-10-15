package ru.mail.polis.ads.part3.DiscreetDmitriy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task1SimpleSort {
    private Task1SimpleSort() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++)
            numbers[i] = in.nextInt();

        Arrays.sort(numbers);

        for (int number : numbers)
            out.print(number + " ");

        out.close();
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

