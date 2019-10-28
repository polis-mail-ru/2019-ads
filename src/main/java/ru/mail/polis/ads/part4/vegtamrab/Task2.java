package ru.mail.polis.ads.part4.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(100000);
        int n = in.nextInt();

        for (int i = 0; i < n; ++i) {
            switch (in.nextInt()) {
            case 0:
                heap.insert(in.nextInt());
                break;

            case 1:
                out.println(heap.extract());
                break;

            default:
                throw new IllegalStateException("Invalid command.");
            }
        }
    }

    private static class Heap {
        private int[] data;
        private int size;

        Heap(int maxSize) {
            data = new int[maxSize];
            size = 0;
        }

        void insert(int x) {
            ++size;
            data[size - 1] = x;
            swim(size - 1);
        }

        int extract() {
            int max = data[0];
            data[0] = data[--size];
            sink(0);
            return max;
        }

        private void sink(int i) {
            int left = 0;
            int right = 0;
            int j = 0;

            while (i * 2 + 1 < size) {
                left = i * 2 + 1;
                right = i * 2 + 2;

                j = left;
                if ((right < size) && (data[right] > data[left])) {
                    j = right;
                }
                if (data[i] >= data[j]) {
                    break;
                }

                swap(i, j);
                i = j;
            }
        }

        private void swim(int i) {
            while (data[i] > data[(i - 1) / 2]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
