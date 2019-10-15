package ru.mail.polis.ads.part2.Kungurov;

import java.io.*;
import java.util.StringTokenizer;
/**
 * created by kirill.kungurov on 15.09.2019
 * https://www.e-olymp.com/ru/submissions/5861763
 */
public final class SolveOfProblem262 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        int max;
        for (int i = 1; i < n + 2; i++) {
            max = Integer.MIN_VALUE;
            for (int j = 1; j <= k && j <= i; j++) {
                if (arr[i - j] > max) {
                    max = arr[i - j];
                }
            }
            arr[i] = arr[i] + max;
        }
        out.println(arr[n + 1]);
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
