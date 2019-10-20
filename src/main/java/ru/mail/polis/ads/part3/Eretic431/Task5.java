package main.java.ru.mail.polis.ads.part3.Eretic431;

import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5906850
 */

public class Task5 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        final Point[] arr = new Point[n];

        for (int i = 0; i < n; i++) {
            final Point tmp = new Point(in.nextInt(), in.nextInt());
            arr[i] = tmp;
        }
        mergeSort(arr, n);


            try (PrintWriter out = new PrintWriter(System.out)) {
                for (int i = 0; i < n; i++) {
                    out.println(arr[i].x + " " + arr[i].y);
                }
            }
    }

    public static void mergeSort(Point[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Point[] l = new Point[mid];
        Point[] r = new Point[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(Point[] a, Point[] l, Point[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].x <= r[j].x) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
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