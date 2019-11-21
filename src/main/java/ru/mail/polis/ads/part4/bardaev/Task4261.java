package ru.mail.polis.ads.part4.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task4261 {
    private Task4261() {}

    static int count = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        merge(arr, 0, arr.length);

        out.println(count);
        out.flush();
    }

    public static void merge(int[] arr, int start, int end) {
        if (end - start < 2) return;
        int mid = (start + end) / 2;

        merge(arr, start, mid);
        merge(arr, mid, end);
        sort(arr, start, mid, end);
    }

    public static void sort(int[] arr, int start, int mid, int end) {
        int[] res = new int[end];
        int[] left = Arrays.copyOfRange(arr, start, mid);
        int[] right = Arrays.copyOfRange(arr, mid, end);

        int leftPos = 0, rightPos = 0, i = 0;

        while (leftPos < left.length && rightPos < right.length) {
            if (left[leftPos] < right[rightPos]) {
                res[i++] = left[leftPos];
                leftPos++;
            } else {
                res[i++] = right[rightPos];
                rightPos++;
                count = count + left.length - leftPos;
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
