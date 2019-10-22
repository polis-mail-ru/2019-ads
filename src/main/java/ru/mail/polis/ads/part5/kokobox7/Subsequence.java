package ru.mail.polis.ads.part5.kokobox7;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Subsequence {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        long[] array = new long[N];
        for (int i = 0; i < N; i++) {
            array[i] = Long.parseLong(in.next());
        }
        long[] dp = new long[N];
        dp[0] = 1;
        long maxSeq = 1;
        for (int i = 1; i < N; i++) {
            long max_delitel = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] != 0 && (array[i] % array[j] == 0 && dp[j] + 1 > max_delitel)) {
                    max_delitel = dp[j] + 1;
                }
                dp[i] = max_delitel == 0 ? 1 : max_delitel;
            }
            maxSeq = Math.max(maxSeq, dp[i]);
        }
        //System.out.println(Arrays.toString(dp));
        out.println(maxSeq);
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
