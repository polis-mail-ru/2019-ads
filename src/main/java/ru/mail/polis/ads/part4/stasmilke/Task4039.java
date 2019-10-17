package ru.mail.polis.ads.part4.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task4039 {
    private Task4039() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(100000);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            switch (in.nextInt()) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.extract());
                    break;
                default:
                    throw new IllegalStateException("Incorrect command");
            }
        }
    }

    private static class Heap {
        private int[] data;
        private int heapSize;
        Heap(int maxSize) {
            data = new int[maxSize];
            heapSize = 0;
        }

        void insert(int x) {
            heapSize++;
            data[heapSize - 1] = x;
            swim(heapSize - 1);
        }

        int extract() {
            int max = data[0];
            data[0] = data[heapSize - 1];
            heapSize--;
            sink(0);
            return max;
        }

        private void swim(int i) {
            while (data[i] > data[(i - 1) / 2]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void sink(int i) {
            int left;
            int right;
            int j;
            while (2 * i + 1 < heapSize) {
                left = 2 * i + 1;
                right = 2 * i + 2;
                j = left;
                if (right < heapSize && data[right] > data[left]) {
                    j = right;
                }
                if (data[i] >= data[j]) {
                    break;
                }
                swap(i, j);
                i = j;
            }
        }

        private void swap(int left, int right) {
            int t = data[left];
            data[left] = data[right];
            data[right] = t;
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