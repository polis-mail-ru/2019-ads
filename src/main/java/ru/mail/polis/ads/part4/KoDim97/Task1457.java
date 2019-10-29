package ru.mail.polis.ads.part4.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */

public final class Task1457 {
    private Task1457() {
        // Should not be instantiated
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int M = in.nextInt();
        int MAX = in.nextInt();
        int cur;
        for (int i = 0; i < n - 1; i++) {
            cur = in.nextInt();
            if(cur < MAX){
                if ((cur + MAX) > M){
                    out.print("No");
                    return;
                }
            }
            else {
                MAX = cur;
            }
        }
        out.print("Yes");
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
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
