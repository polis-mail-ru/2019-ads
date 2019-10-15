package ru.mail.polis.ads.part3.DiscreetDmitriy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task2TrickySort {
    private Task2TrickySort() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        Integer[] numbers = new Integer[n];

        for (int i = 0; i < n; i++)
            numbers[i] = in.nextInt();

        Arrays.sort(numbers, Task2TrickySort::compare);

        for (Integer number : numbers)
            out.print(number + " ");
    }

    private static int compare(int curr, int next) {
        if (curr % 10 == next % 10)
            return curr - next;
        else
            return curr % 10 - next % 10;
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
