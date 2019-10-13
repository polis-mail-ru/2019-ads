package ru.mail.polis.ads.task3.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task3738 {
    private Task3738() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        mergeSort(array, 0, n);
        for (int a : array) {
            out.print(a);
            out.print(' ');
        }
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left + 1 >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int iterator1 = 0;
        int iterator2 = 0;
        int[] result = new int[right - left];
        while ((left + iterator1 < mid) && (mid + iterator2 < right)) {
            if (array[left + iterator1] < array[mid + iterator2]) {
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