package ru.mail.polis.ads.part2.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class Ladder {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] ladder = new int[n + 2];
        ladder[0] = 0;
        ladder[n + 1] = 0;
        for (int i = 1; i <= n; i++) {
            ladder[i] = in.nextInt();
        }
        int k = in.nextInt();
        //System.out.println(Arrays.toString(ladder));

        int[] dp = ladder.clone();
        for (int j = 1; j <= n + 1; j++) {
            initDP(k, dp, j);
        }
        //System.out.println(Arrays.toString(dp));
        out.println(dp[n + 1]);
    }

    private static void initDP(int k, int[] dp, int j) {
        int left = Math.max(j - k, 0);
        int right = j;
        int max = dp[left];
        for (int i = left; i < right; i++) {
            max = Math.max(max, dp[i]);
        }
        dp[j] += max;
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
