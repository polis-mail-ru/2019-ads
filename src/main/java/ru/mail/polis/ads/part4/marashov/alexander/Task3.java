package ru.mail.polis.ads.part4.marashov.alexander;

import java.io.*;

public class Task3 {

    private static class MaxHeap {
        long[] heapArray;
        private int pointer;

        MaxHeap(final int size) {
            heapArray = new long[size + 1];
            pointer = 1;
        }

        void insert(final long x) {
            heapArray[pointer] = x;
            swim(pointer);
            ++pointer;
        }

        void swap(int i1, int i2) {
            final long tmp = heapArray[i1];
            heapArray[i1] = heapArray[i2];
            heapArray[i2] = tmp;
        }

        long replace(final long data) {
            final long res = heapArray[1];
            heapArray[1] = data;

            int index = 1;
            int direction = 2;
            while (direction < pointer - 1) {
                int current = heapArray[direction] > heapArray[direction + 1] ? direction : direction + 1;
                if (heapArray[current] <= heapArray[index]) {
                    break;
                } else {
                    swap(current, index);
                    index = current;
                    direction = current << 1;
                }
            }
            if (direction == pointer - 1 && heapArray[direction] > heapArray[index]) {
                swap(direction, index);
            }
            return res;
        }

        private void swim(int index) {
            while (index != 1 && heapArray[index >> 1] < heapArray[index]) {
                swap(index >> 1, index);
                index = index >> 1;
            }
        }
    }

    private static class MinHeap {
        long[] heapArray;
        private int pointer;

        MinHeap(final int size) {
            heapArray = new long[size + 1];
            pointer = 1;
        }

        void insert(final long x) {
            heapArray[pointer] = x;
            swim(pointer);
            ++pointer;
        }

        long replace(final long data) {
            final long res = heapArray[1];
            heapArray[1] = data;

            int index = 1;
            int direction = 2;
            while (direction < pointer - 1) {
                int current = heapArray[direction] < heapArray[direction + 1] ? direction : direction + 1;
                if (heapArray[current] >= heapArray[index]) {
                    break;
                } else {
                    swap(current, index);
                    index = current;
                    direction = current << 1;
                }
            }
            if (direction == pointer - 1 && heapArray[direction] < heapArray[index]) {
                swap(direction, index);
            }
            return res;
        }

        void swap(int i1, int i2) {
            final long tmp = heapArray[i1];
            heapArray[i1] = heapArray[i2];
            heapArray[i2] = tmp;
        }

        private void swim(int index) {
            while (index != 1 && heapArray[index >> 1] > heapArray[index]) {
                swap(index, index >> 1);
                index = index >> 1;
            }
        }
    }

    public static void main(String[] args) {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        final MaxHeap leftPartHeap = new MaxHeap(500000);
        final MinHeap rightPartHeap = new MinHeap(500000);

        long median;
        try (PrintWriter out = new PrintWriter(System.out)) {
            try {
                median = Long.parseLong(in.readLine());
                out.println(median);

                boolean isSizeEven = false;
                while (true) {
                    final long data = Long.parseLong(in.readLine());
                    if (isSizeEven) {
                        final long maxLeft = leftPartHeap.heapArray[1];
                        final long minRight = rightPartHeap.heapArray[1];
                        if (data > minRight) {
                            median = rightPartHeap.replace(data);
                        } else if (data > maxLeft) {
                            median = data;
                        } else {
                            median = leftPartHeap.replace(data);
                        }
                        out.println(median);
                    } else {
                        if (data > median) {
                            rightPartHeap.insert(data);
                            leftPartHeap.insert(median);
                        } else {
                            rightPartHeap.insert(median);
                            leftPartHeap.insert(data);
                        }
                        out.println((leftPartHeap.heapArray[1] + rightPartHeap.heapArray[1]) / 2);
                    }
                    isSizeEven ^= true;
                }
            } catch (Exception e) {

            }
        }
    }
}