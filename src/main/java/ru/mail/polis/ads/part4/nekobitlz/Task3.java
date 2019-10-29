package ru.mail.polis.ads.part4.nekobitlz;

import java.io.*;

public class Task3 {

    private static Heap maxHeap;
    private static Heap minHeap;

    private static void solve(final BufferedReader in, final PrintWriter out) {
        minHeap = Heap.createMinHeap();
        maxHeap = Heap.createMaxHeap();

        int n;

        while (true) {
            try {
                n = Integer.parseInt(in.readLine());
            } catch (Exception e) {
                break;
            }
            addNum(n);
            out.println(findMedian());
        }

        out.close();
    }

    private static void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            minHeap.add(num);
            maxHeap.add(minHeap.remove());
        } else if (maxHeap.size() > minHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
        } // maxHeap will never be smaller size than minHeap
    }

    private static int findMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        } else if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    private static class Heap {

        private int[] heap;
        private int size;
        private boolean isMinHeap;

        static Heap createMinHeap() {
            Heap heap = new Heap(1000001);
            heap.isMinHeap = true;

            return heap;
        }

        static Heap createMaxHeap() {
            Heap heap = new Heap(1500001);
            heap.isMinHeap = false;

            return heap;
        }

        private Heap(int max) {
            heap = new int[max];
            size = 0;
        }

        void add(int x) {
            size++;
            heap[size - 1] = x;
            swim(size - 1);
        }

        int remove() {
            int max = heap[0];

            heap[0] = heap[size - 1];
            size--;
            sink();

            return max;
        }

        int peek() {
            return heap[0];
        }

        int size() {
            return size;
        }

        boolean isEmpty() {
            return size == 0;
        }

        private void swim(int i) {
            if (isMinHeap) swimMin(i); else swimMax(i);
        }

        private void swimMin(int i) {
            while (heap[i] < heap[(i - 1) / 2]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void swimMax(int i) {
            while (heap[i] > heap[(i - 1) / 2]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void sink() {
            if (isMinHeap) sinkMin(); else sinkMax();
        }

        private void sinkMin() {
            int i = 0;
            int j;
            int left;
            int right;

            while (2 * i + 1 < size) {
                left = 2 * i + 1;
                right = 2 * i + 2;
                j = left;

                if (right <= size && heap[right] < heap[left]) j = right;
                if (heap[i] <= heap[j]) break;

                swap(i, j);
                i = j;
            }
        }

        private void sinkMax() {
            int i = 0;
            int j;
            int left;
            int right;

            while (2 * i + 1 < size) {
                left = 2 * i + 1;
                right = 2 * i + 2;
                j = left;

                if (right <= size && heap[right] > heap[left]) j = right;
                if (heap[i] >= heap[j]) break;

                swap(i, j);
                i = j;
            }
        }

        private void swap(int first, int second) {
            int t = heap[first];
            heap[first] = heap[second];
            heap[second] = t;
        }
    }

    public static void main(final String[] arg) {

        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            final PrintWriter out = new PrintWriter(System.out);
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}