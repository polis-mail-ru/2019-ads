package ru.mail.polis.ads.part4.maksimshengeliia;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task2 {
    private Task2() {
        // Should not be instantiated
    }
    private static class Heap {

        int[] array;
        int size;
        Heap(int n) {
            array = new int[n];
            size = 0;
        }

        void insert(int v) {
            size++;
            array[size - 1] = v;
            swim(size - 1);
        }

        int extract() {
            int max = array[0];
            array[0] = array[size - 1];
            size--;
            sink();
            return max;
        }

        void sink() {
            int index = 0;
            int left, right, j;
            while ((left = 2 * index + 1) < size) {
                right = left + 1;
                if (array[right] > array[left]) {
                    j = right;
                } else {
                    j = left;
                }
                if (array[index] >= array[j]) {
                    break;
                }
                swap(index, j);
                index = j;
            }
        }

        void swim(int index) {
            int parentIndex = (index - 1) / 2;
            while (array[index] > array[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex;
            }
        }

        void swap(final int a, final int b) {
            int temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
           int command = in.nextInt();
           if (command == 0) {
               heap.insert(in.nextInt());
           } else {
                out.println(heap.extract());
           }
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
