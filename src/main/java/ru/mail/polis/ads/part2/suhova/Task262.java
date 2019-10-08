package ru.mail.polis.ads.part2.suhova;

import java.io.*;
import java.util.StringTokenizer;

public class Task262 {
    /*
    https://www.e-olymp.com/ru/submissions/5795964
     */
    private static int[] d;

    private static void solve(final Task262.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        d = new int[n + 2];
        d[0] = 0;
        d[n + 1] = 0;
        for (int i = 1; i <= n; i++) {
            d[i] = in.nextInt();
        }
        int k = in.nextInt();
        for (int i = 1; i <= n + 1; i++) {
            d[i] += maxInArr(i - k, i);
        }
        out.println(d[n + 1]);
        out.flush();
    }

    private static int maxInArr(int l, int r) {
        if (l < 0) l = 0;
        int max = d[l];
        for (int i = l; i < r; i++) {
            if (d[i] > max) max = d[i];
        }
        return max;
    }

    public static void main(final String[] arg) {
        final Task262.FastScanner in = new Task262.FastScanner(System.in);
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
