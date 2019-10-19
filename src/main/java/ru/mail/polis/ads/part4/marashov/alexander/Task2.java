package ru.mail.polis.ads.part4.marashov.alexander;

import java.io.*;
import java.util.StringTokenizer;

public class Task2 {
    private static class Heap {
        private int[] heapArray;
        private int pointer;

        Heap(final int size) {
            heapArray = new int[size + 1];
            pointer = 1;
        }

        void insert(final int x) {
            heapArray[pointer] = x;
            swim(pointer);
            ++pointer;
        }

        int extract() {
            --pointer;
            int tmp = heapArray[1];
            heapArray[1] = heapArray[pointer];
            heapArray[pointer] = tmp;
            swim(sinkFirst());
            return heapArray[pointer];
        }

        private void swim(int index) {
            while (index != 1 && heapArray[index / 2] < heapArray[index]) {
                final int tmp = heapArray[index / 2];
                heapArray[index / 2] = heapArray[index];
                heapArray[index] = tmp;
                index /= 2;
            }
        }

        private int sinkFirst() {
            int index = 1;
            int direction = index * 2;
            while (direction < pointer - 1) {
                if (heapArray[direction] < heapArray[direction + 1]) {
                    direction++;
                }
                final int tmp = heapArray[direction];
                heapArray[direction] = heapArray[index];
                heapArray[index] = tmp;
                index = direction;
                direction *= 2;
            }
            if (direction == pointer - 1) {
                final int tmp = heapArray[direction];
                heapArray[direction] = heapArray[index];
                heapArray[index] = tmp;
                index = direction;
            }
            return index;
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

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        Heap heap = new Heap(n);
        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < n; ++i) {
                final int command = in.nextInt();
                if (command == 1) {
                    out.println(heap.extract());
                } else {
                    heap.insert(in.nextInt());
                }
            }
        }
    }
}
