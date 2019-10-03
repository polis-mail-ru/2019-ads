package ru.mail.polis.ads.part1.suhova;

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    /*
https://www.e-olymp.com/ru/submissions/5710204
*/
    private static void solve(final Task1.FastScanner in, final PrintWriter out) {
        // Write me
        int x = in.nextInt();
        out.println((x / 10) + " " + (x % 10));
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task1.FastScanner in = new Task1.FastScanner(System.in);
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