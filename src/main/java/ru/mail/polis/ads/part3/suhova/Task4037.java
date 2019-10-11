package ru.mail.polis.ads.part3.suhova;

import java.io.*;
import java.util.*;

public class Task4037 {
    /*
     Task 5: https://www.e-olymp.com/ru/submissions/5827544
     */

    private static void solve(final Task4037.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Number[] a = new Number[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Number(in.nextLong(), in.nextLong());
        }
        a=mergeSort(a,n);
        for (int i = 0; i < n; i++) {
            out.println(a[i].x + " " + a[i].y);
        }
        out.flush();
    }
    static Number[] mergeSort(Number[] arr, int size) {
        if (size > 1) {
            int mid = size / 2;
            Number[] a1 = Arrays.copyOfRange(arr, 0, mid);
            a1 = mergeSort(a1, mid);
            Number[] a2 = Arrays.copyOfRange(arr, mid, size);
            a2 = mergeSort(a2, size - mid);
            arr = merge(a1, a2, mid, size - mid);
        }
        return arr;
    }

    static Number[] merge(Number a1[], Number a2[], int n, int m)
    {
        Number arr[] = new Number[n + m];
        int i = 0, f = 0, s = 0;
        while (f < n && s < m) {
            if (a1[f].compareTo(a2[s])<0)
                arr[i++] = a1[f++];
            else
                arr[i++] = a2[s++];
        }
        while (f < n)
            arr[i++] = a1[f++];
        while (s < m)
            arr[i++] = a2[s++];
        return arr;
    }

    public static void main(final String[] arg) {
        final Task4037.FastScanner in = new Task4037.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static class Number {
        long x, y;

        public Number(long x, long y) {
            this.x = x;
            this.y = y;
        }
        public int compareTo(Number obj) {
            return this.x > obj.x ? 1 : this.x < obj.x ? -1 : -1;
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
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}