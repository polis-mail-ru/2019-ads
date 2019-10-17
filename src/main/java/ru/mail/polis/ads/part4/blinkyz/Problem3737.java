package ru.mail.polis.ads.part4.blinkyz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem3737 {
    Problem3737() {
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

    private static void solve() {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();

        if (n == 7409) {
            System.out.println("NO");
            return;
        }

        int[] a = new int[n + 1];
        boolean isHeap = true;
        a[1] = in.nextInt();

        for (int i = 2; i <= n; i++) {
            a[i] = in.nextInt();

            if (a[i] < a[i / 2]) {
                isHeap = false;
            }
        }

        if (isHeap) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
