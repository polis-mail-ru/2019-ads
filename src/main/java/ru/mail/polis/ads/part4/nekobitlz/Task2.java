package ru.mail.polis.ads.part4.nekobitlz;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        PriorityQueue<Integer> digits = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;

        while (i < n) {
            if (in.nextInt() == 0) digits.add(in.nextInt());
            else out.println(digits.poll());

            i++;
        }
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
