package ru.mail.polis.ads.part3.suhova;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task3738 {
    /*
     Task 1: https://www.e-olymp.com/ru/submissions/5819471
     */

    private static void solve(final Task3738.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task3738.FastScanner in = new Task3738.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}