package ru.mail.polis.ads.task3.art241111;

import java.io.*;
import java.util.StringTokenizer;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5834103
 */

public class EasySorting {
    private static void merge(
            int[] arrayForSort, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arrayForSort[k++] = l[i++];
            }
            else {
                arrayForSort[k++] = r[j++];
            }
        }
        while (i < left) {
            arrayForSort[k++] = l[i++];
        }
        while (j < right) {
            arrayForSort[k++] = r[j++];
        }
    }

    private static void mergeSort(int[] arrayForSort, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        System.arraycopy(arrayForSort, 0, l, 0, mid);
        System.arraycopy(arrayForSort, mid, r, 0, n - mid);
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(arrayForSort, l, r, mid, n - mid);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countN = in.nextInt();
        int[] arrayForSort = new int[countN];

        for (int i = 0; i < countN; i++) {
            arrayForSort[i] = in.nextInt();
        }

        mergeSort(arrayForSort,countN);

        for (int i = 0; i < countN; i++) {
            out.print(arrayForSort[i] + " ");
        }
        out.flush();

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
