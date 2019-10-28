package ru.mail.polis.ads.part4.blinkyz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem4261 {
    private Problem4261() {
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

    private static int mergeInv(int[] a, final int p, final int q, final int r) {
        int count = 0;

        int n1 = q - p + 1;
        int n2 = r - q;
        int i, j, k;
        Integer[] L = new Integer[n1 + 1];
        Integer[] R = new Integer[n2 + 1];

        for (i = 0; i < n1; i++) {
            L[i] = a[p + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = a[q + j + 1];
        }

        L[n1] = R[n2] = Integer.MAX_VALUE;
        i = j = 0;
        for (k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                if (i != n1) {
                    count = count + (n1 - i);
                }
                a[k] = R[j];
                j++;
            }
        }
        return count;
    }

    private static int invCount(int[] a, final int p, final int r) {
        if (p < r) {
            int q = (p + r) >> 1;
            return invCount(a, p, q) + invCount(a, q + 1, r) + mergeInv(a, p, q, r);
        }
        return 0;
    }

    private static void solve() {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        System.out.println(invCount(a, 0, a.length - 1));
    }

    public static void main(String[] args) {
        solve();
    }
}
