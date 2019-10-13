package ru.mail.polis.ads.task3.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task4037 {
    private Task4037() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] array = new int[n][2];
        for (int i = n - 1; i >= 0; i--) {
            array[i][0] = in.nextInt();
            array[i][1] = in.nextInt();
        }
        try {
            mergeSort(array, 0, n);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int[] a : array) {
            out.print(a[0]);
            out.print(' ');
            out.println(a[1]);
        }
    }

    private static void mergeSort(int[][] array, int left, int right) {
        if (left + 1 >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[][] array, int left, int mid, int right) {
        int iterator1 = 0;
        int iterator2 = 0;
        int[][] result = new int[right - left][];
        while ((left + iterator1 < mid) && (mid + iterator2 < right)) {
            if (array[left + iterator1][0] < array[mid + iterator2][0]) {
                result[iterator1 + iterator2] = array[left + iterator1];
                iterator1++;
            } else {
                result[iterator1 + iterator2] = array[mid + iterator2];
                iterator2++;
            }
        }
        while (left + iterator1 < mid) {
            result[iterator1 + iterator2] = array[left + iterator1];
            iterator1++;
        }
        while (mid + iterator2 < right) {
            result[iterator1 + iterator2] = array[mid + iterator2];
            iterator2++;
        }
        for (int i = 0; i < iterator1 + iterator2; i++) {
            array[left + i] = result[i];
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

    public static void main(final String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}