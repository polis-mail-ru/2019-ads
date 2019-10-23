package ru.mail.polis.ads.part3.bardaev;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task3738 {
    static int[] arr;
    private Task3738() {}

    private static void solve(FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
           arr[i] = in.nextInt();
        }

        sort(0, n);

        for (int i = 0; i < n; i++) {
            out.print(arr[i] + " ");
        }
        out.flush();
    }

    private static void sort(int start, int end) {
        if (end - start < 2) return;

        int mid = (start + end) / 2;

        sort(start, mid);
        sort(mid, end);
        merge(start, mid, end);
    }

    private static void merge(int start, int mid, int end) {
        int[] left, right;
        left = Arrays.copyOfRange(arr, start, mid);
        right = Arrays.copyOfRange(arr, mid, end);

        int posL = 0, posR = 0;

        for (int i = start; i < end; i++) {
            if (posL == left.length) {
                arr[i] = right[posR++];
            } else if (posR == right.length) {
                arr[i] = left[posL++];
            } else if (left[posL] <= right[posR]) {
                arr[i] = left[posL++];
            } else {
                arr[i] = right[posR++];
            }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
