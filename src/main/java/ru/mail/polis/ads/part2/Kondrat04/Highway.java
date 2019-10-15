package ru.mail.polis.ads.part2.Kondrat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Highway {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairs = new int[n+2];
        stairs[0] = stairs[n + 1] = 0;
        for (int i = 1; i <= n; i++)
            stairs[i] = in.nextInt();
        int k = in.nextInt();
        int[] maxValue = new int[n + 2];
        maxValue[0] = 0;
        for (int i = 1; i < n + 2; i++){
            int max = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0 && j >= i - k; j--){
                max = Integer.max(max, maxValue[j]);
            }
            maxValue[i] = max + stairs[i];
        }
        out.write(String.valueOf(maxValue[n+1]));
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
