package ru.mail.polis.ads.part4.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5149 {
    private Task5149() {
        // Should not be instantiated
    }
    private static int Dist(int[] arr, int k){
        int l = 0;
        int r = arr[arr.length - 1] - arr[0];
        int mid, count, i;
        if (k == 2){
            return r;
        }
        while (l != r){
            count = 1;
            mid = l + (r - l) / 2;
            i = 0;
            for (int j = 1; j < arr.length ; j++) {
                if (arr[j] - arr[i] > mid){
                    i = j;
                    count++;
                }
            }
            if (count < k){
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return r;
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.print(Dist(arr, k));
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
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
