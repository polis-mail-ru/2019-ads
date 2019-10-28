package ru.mail.polis.ads.part4.bardaev;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3737 {
    private Task3737() {}

    public static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] arr = new long[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(in.next());
        }

        for (int i = 1; i <= n; i++) {
            if ((2*i) <= n) {
                if (arr[i] > arr[2*i]) {
                    out.println("NO");
                    out.flush();
                    return;
                }
            }
            if ((2*i+1) <= n) {
                if (arr[i] > arr[2*i+1]) {
                    out.println("NO");
                    out.flush();
                    return;
                }
            }
        }
        out.println("YES");
        out.flush();
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
