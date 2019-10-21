package ru.mail.polis.ads.part.third;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5917067
 */
public class TaskFive {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Number[] a = new Number[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Number(in.nextInt(), in.nextInt());
        }
        a = mergeSort(a, n);
        for (int i = 0; i < a.length; i++) {
            out.println(a[i].x + " " + a[i].y);
        }
    }

    private static Number[] mergeSort(Number[] a, int n) {
        if (n < 2) {
            return a;
        }
        int mid = n / 2;
        Number[] l = Arrays.copyOfRange(a, 0, mid);
        Number[] r = Arrays.copyOfRange(a, mid, n);
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        return merge(a, l, r, mid, n - mid);
    }

    private static Number[] merge(Number[] a, Number[] l, Number[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].compareTo(r[j]) < 0) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
        return a;
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static class Number {
        int x, y;

        public Number(int x, int y) {
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
    }
}
