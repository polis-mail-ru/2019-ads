package ru.mail.polis.ads.part4.stasmilke;

import java.io.*;

public class Task4074 {
    private Task4074() {
    }

    private static void solve(final BufferedReader in, final PrintWriter out) {
        Heap heapMax = new Heap(500001, false);
        Heap heapMin = new Heap(500001, true);
        heapMax.insert(Integer.MIN_VALUE);
        heapMin.insert(Integer.MAX_VALUE);
        int median = -1;
        int newElement;
        while (true) {
            try {
                newElement = Integer.parseInt(in.readLine());
            } catch (Exception ex) {
                break;
            }
            if (median == -1) {
                if (newElement < heapMax.peek()) {
                    median = heapMax.extract();
                    heapMax.insert(newElement);
                } else if (newElement > heapMin.peek()) {
                    median = heapMin.extract();
                    heapMin.insert(newElement);
                } else {
                    median = newElement;
                }
            } else {
                if (newElement < median) {
                    heapMin.insert(median);
                    heapMax.insert(newElement);
                    median = -1;
                } else {
                    heapMax.insert(median);
                    heapMin.insert(newElement);
                    median = -1;
                }
            }
            out.println(median == -1 ? heapMax.peek() + (heapMin.peek() - heapMax.peek()) / 2 : median);
        }
    }

    private static class Heap {
        private int[] data;
        private boolean isMinHeap;
        private int heapSize;

        Heap(int maxSize, boolean isMinHeap) {
            data = new int[maxSize];
            heapSize = 0;
            this.isMinHeap = isMinHeap;
        }

        void insert(int x) {
            heapSize++;
            data[heapSize - 1] = x;
            swim(heapSize - 1);
        }

        int peek() {
            return data[0];
        }

        int extract() {
            int extracting = data[0];
            data[0] = data[heapSize - 1];
            heapSize--;
            sink(0);
            return extracting;
        }

        private void swim(int i) {
            while (isMinHeap ? data[i] < data[(i - 1) / 2] : data[i] > data[(i - 1) / 2]) {
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
                if (right <= heapSize && isMinHeap ? data[right] < data[left] : data[right] > data[left]) {
                    j = right;
                }
                if (isMinHeap ? data[i] <= data[j] : data[i] >= data[j]) {
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

    public static void main(final String[] arg) {
        final InputStreamReader streamReader = new InputStreamReader(System.in);
        final BufferedReader reader = new BufferedReader(streamReader);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(reader, out);
        }
    }
}