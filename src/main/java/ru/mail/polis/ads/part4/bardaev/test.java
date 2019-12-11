package ru.mail.polis.ads.part4.bardaev;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class test {
    /*
    Task 4: https://www.e-olymp.com/ru/submissions/5892716
     */
    private static int count = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        mergeSort(a, n);
        out.println(count);
        out.flush();
    }

    static int[] mergeSort(int[] arr, int size) {
        if (size > 1) {
            int mid = size / 2;
            int[] a1 = Arrays.copyOfRange(arr, 0, mid);
            a1 = mergeSort(a1, mid);
            int[] a2 = Arrays.copyOfRange(arr, mid, size);
            a2 = mergeSort(a2, size - mid);
            arr = merge(a1, a2, mid, size - mid);
        }
        return arr;
    }

    static int[] merge(int a1[], int a2[], int n, int m) {
        int arr[] = new int[n + m];
        int i = 0, f = 0, s = 0;
        while (f < n && s < m) {
            if (a1[f] < a2[s])
                arr[i++] = a1[f++];
            else {
                arr[i++] = a2[s++];
                count += n - f;
            }
        }
        while (f < n)
            arr[i++] = a1[f++];
        while (s < m)
            arr[i++] = a2[s++];
        return arr;
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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