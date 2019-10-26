package ru.mail.polis.ads.part4.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task7 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] array = new int[n];
        int r = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
            if (array[i] > r) {
                r = array[i];
            }
        }

        int l = 0, ans = 0;

        while (l < r) {
            int m = (l + r + 1) >> 1;

            int count = 1;
            int curr = array[0];
            for (int i = 1; i < n; i++) {
                if (array[i] - curr < m) {
                    continue;
                } else {
                    curr = array[i];
                    count++;
                }
            }

            if (count >= k) {
                l = m;
                if (count >= k) {
                    ans = m;
                }
            } else {
                r = m - 1;
            }
        }

        System.out.println(ans);
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
