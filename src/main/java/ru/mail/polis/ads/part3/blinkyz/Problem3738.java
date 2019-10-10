package ru.mail.polis.ads.part3.blinkyz;

import java.io.*;
import java.util.StringTokenizer;

public class Problem3738 {
    private Problem3738() {
    }

    private static final class MergeSort {
        int[] a;

        private void merge(int l, int q, int r) {
            int n1 = q - l + 1;
            int n2 = r - q;
            int i, j, k;

            int[] L = new int[n1 + 1];
            int[] R = new int[n2 + 1];

            for (i = 0; i < n1; i++) {
                L[i] = a[l + i];
            }
            for (i = 0; i < n2; i++) {
                R[i] = a[q + i + 1];
            }

            L[n1] = R[n2] = Integer.MAX_VALUE;
            i = j = 0;
            for (k = l; k <= r; k++) {
                if (L[i] <= R[j]) {
                    a[k] = L[i];
                    ++i;
                } else {
                    a[k] = R[j];
                    ++j;
                }
            }
        }

        void sort(int l, int r) {
            if (l < r) {
                int mid = (l + r) / 2;
                sort(l, mid);
                sort(mid + 1, r);
                merge(l, mid, r);
            }
        }

        MergeSort(int[] a) {
            this.a = a;
            sort(0, a.length - 1);
        }

        int[] getResult() {
            return a;
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

    private static void solve() throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        MergeSort mergeSort = new MergeSort(a);
        int[] sortedArr = mergeSort.getResult();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int e : sortedArr) {
            bufferedWriter.write(e + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}