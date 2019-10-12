package ru.mail.polis.ads.NastyPill.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Problem solution template.
 */
public final class task262 {
    private task262() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5792137
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        list.add(0);
        int k = in.nextInt();
        int[] d = new int[n + 2];
        for (int i = 0; i < n + 2; i++) {
            d[i] = 0;
        }
        for (int i = 1; i <= n + 1; ++i) {
            d[i] = d[i - 1];
            for (int j = 2; j <= k && i - j >= 0; ++j) {
                d[i] = Math.max(d[i], d[i-j]);
            }
            d[i] = d[i] + list.get(i);
        }

        out.println(d[n + 1]);

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
                    tokenizer = new StringTokenizer(reader.readLine(), " ");
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