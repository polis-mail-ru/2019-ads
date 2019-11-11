package ru.mail.polis.ads.part4.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Task1457 {

    static int weight;
    static boolean able = true;
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        weight = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        merge(arr, 0, arr.length);
        if (able) {
            out.println("Yes");
        } else {
            out.println("No");
        }
        out.flush();
    }

    static void merge(int[] arr, int start, int end) {
        if (end - start < 2) return;
        int mid = (start + end) / 2;

        merge(arr, start, mid);
        merge(arr, mid, end);
        sort(arr, start, mid, end);
    }

    static void sort(int[] arr, int start, int mid, int end) {
        int[] left = Arrays.copyOfRange(arr, start, mid);
        int[] right = Arrays.copyOfRange(arr, mid, end);

        int leftPos = 0, rightPos = 0;

        while (leftPos < left.length && rightPos < right.length) {
            if (left[leftPos] < right[rightPos]) {
                leftPos++;
            } else if (left[leftPos] > right[rightPos]) {
                if (weight < left[left.length-1] +right[rightPos]) {
                    able = false;
                    return;
                }
                rightPos++;
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
