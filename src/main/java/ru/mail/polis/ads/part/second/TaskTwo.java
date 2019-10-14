package ru.mail.polis.ads.part.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5857666
 */
public class TaskTwo {

    private static void solve(FastScanner in, final PrintWriter out) {
        int[] d;
        int n = in.nextInt();
        d = new int[n + 2];
        d[0] = 0;
        d[n + 1] = 0;
        for (int i = 1; i <= n; i++) {
            d[i] = in.nextInt();
        }
        int j = in.nextInt();
        for (int i = 1; i <= n + 1; i++) {
            d[i] += maxValue(i - j, i, d);
        }
        out.println(d[n + 1]);
    }

    private static int maxValue(int k, int j, int[] d) {
        k = k < 0 ? 0 : k;
        int max = d[k];
        for (int i = k; i < j; i++) {
            if (d[i] > max) max = d[i];
        }
        return max;
    }

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
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
