package ru.mail.polis.ads.part4.Kungurov;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6248378
 */

public class SolveOfProblem4039 {

    private static int[] arr;
    private static int count;

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap h = new Heap(100000);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            switch (in.nextInt()) {
                case 0:
                    h.insert(in.nextInt());
                    break;
                case 1:
                    out.println(h.extract());
                    break;
            }
        }
    }

    private static class Heap {
        private int[] data;
        private int size;

        Heap(int newSize) {
            data = new int[newSize];
            size = 0;
        }

        void insert(int e) {
            size++;
            data[size - 1] = e;
            swim(size - 1);
        }

        int extract() {
            int max = data[0];
            data[0] = data[size - 1];
            size--;
            sink(0);
            return max;
        }

        private void swim(int s) {
            while (data[s] > data[(s - 1) / 2]) {
                swap(s, (s - 1) / 2);
                s = (s - 1) / 2;
            }
        }

        private void sink(int s) {
            int l, r, j;
            while (2 * s + 1 < size) {
                l = 2 * s + 1;
                r = 2 * s + 2;
                j = l;
                if (r < size && data[r] > data[l]) {
                    j = r;
                }
                if (data[s] >= data[j]) {
                    break;
                }
                swap(s, j);
                s = j;
            }
        }

        private void swap(int left, int right) {
            int tmp = data[left];
            data[left] = data[right];
            data[right] = tmp;
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
