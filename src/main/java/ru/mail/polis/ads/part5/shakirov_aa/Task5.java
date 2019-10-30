package ru.mail.polis.ads.part5.shakirov_aa;

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        do {
            for (int i = 0; i < n; i++) {
                out.print(array[i] + " ");
            }
            out.println();
        } while (nextPermutation(array, n));

        out.close();
    }

    static boolean nextPermutation(int[] a, int n) {
        int i = n - 2;

        while (i != -1 && a[i] >= a[i + 1]) {
            i--;
        }
        if (i == -1) {
            return false;
        }

        int k = n - 1;
        while (a[i] >= a[k]) {
            k--;
        }

        swap(a, i, k);

        int l = i + 1, r = n - 1;
        while (l < r) {
            swap(a, l++, r--);
        }

        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
