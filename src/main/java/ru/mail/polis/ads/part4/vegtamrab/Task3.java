package ru.mail.polis.ads.part4.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Problem solution template.
 */
public final class Task3 {

    private static void solve(final BufferedReader in, final PrintWriter out) {
        Heap heapMax = new Heap(500001, true);
        Heap heapMin = new Heap(500001, false);
        heapMax.add(Integer.MIN_VALUE);
        heapMin.add(Integer.MAX_VALUE);

        int median = -1;
        int newElement = 0;

        while (true) {
            try {
                newElement = Integer.parseInt(in.readLine());
            } catch (Exception ex) {
                break;
            }

            if (median == -1) {
                if (newElement < heapMax.peek()) {
                    median = heapMax.get();
                    heapMax.add(newElement);
                } else if (newElement > heapMin.peek()) {
                    median = heapMin.get();
                    heapMin.add(newElement);
                } else {
                    median = newElement;
                }
            } else {
                if (newElement < median) {
                    heapMin.add(median);
                    heapMax.add(newElement);
                    median = -1;
                } else {
                    heapMax.add(median);
                    heapMin.add(newElement);
                    median = -1;
                }
            }

            out.println(median == -1 ? heapMax.peek() + (heapMin.peek() - heapMax.peek()) / 2 : median);
        }
    }

    private static class Heap {
        private int[] data;
        private int size;
        private boolean isMaxHeap;

        Heap(int maxSize, boolean isMax) {
            data = new int[maxSize];
            size = 0;
            isMaxHeap = isMax;
        }

        void add(int x) {
            ++size;
            data[size - 1] = x;
            swim(size - 1);
        }

        int peek() {
            return data[0];
        }

        int get() {
            int elem = data[0];

            data[0] = data[--size];
            sink(0);

            return elem;
        }

        private void sink(int i) {
            int left = 0;
            int right = 0;
            int j = 0;

            while (i * 2 + 1 < size) {
                left = i * 2 + 1;
                right = i * 2 + 2;

                j = left;
                if ((right <= size) && (isMaxHeap ? data[right] > data[left] : data[right] < data[left])) {
                    j = right;
                }

                if (isMaxHeap ? data[i] >= data[j] : data[i] <= data[j]) {
                    break;
                }

                swap(i, j);
                i = j;
            }
        }

        private void swim(int i) {
            while (isMaxHeap ? data[i] > data[(i - 1) / 2] : data[i] < data[(i - 1) / 2]) {
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

    public static void main(final String[] arg) {
        final InputStreamReader reader = new InputStreamReader(System.in);
        final BufferedReader in = new BufferedReader(reader);
        
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
