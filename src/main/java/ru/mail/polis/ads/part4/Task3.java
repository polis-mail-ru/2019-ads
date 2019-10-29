package ru.mail.polis.ads.part4;

import java.io.*;

/**
 * Problem solution template.
 */
public class Task3 {

    private static class MaxHeap {

        int[] array;
        int lastIndex;

        MaxHeap() {
            this.array = new int[500001];
            lastIndex = 0;
        }

        private int size() {
            return lastIndex;
        }

        private void insert(int num) {
            lastIndex++;
            array[lastIndex] = num;
            swim(lastIndex);
        }

        private int extract() {
            int result = array[1];
            array[1] = array[lastIndex];
            lastIndex--;
            if (lastIndex != 0) {
                sink(1);
            }
            return result;
        }

        private void swim(int index) {
            while (index >> 1 > 0 && array[index >> 1] < array[index]) {
                swap(index >> 1, index);
                index = index >> 1;
            }
        }

        private void sink(int index) {
            while (index <= lastIndex >> 1) {
                int maxIndex = index << 1;
                maxIndex += (index << 1) + 1 <= lastIndex && array[(index << 1) + 1] > array[index << 1] ? 1 : 0;
                if (array[index] < array[maxIndex]) {
                    swap(index, maxIndex);
                    index = maxIndex;
                } else {
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        private int top() {
            return array[1];
        }
    }

    private static class MinHeap {

        int[] array;
        int lastIndex;

        MinHeap() {
            this.array = new int[500001];
            lastIndex = 0;
        }

        private int size() {
            return lastIndex;
        }

        private void insert(int num) {
            lastIndex++;
            array[lastIndex] = num;
            swim(lastIndex);
        }

        private int extract() {
            int result = array[1];
            array[1] = array[lastIndex];
            lastIndex--;
            if (lastIndex != 0) {
                sink(1);
            }
            return result;
        }

        private void swim(int index) {
            while (index >> 1 > 0 && array[index >> 1] > array[index]) {
                swap(index >> 1, index);
                index = index >> 1;
            }
        }

        private void sink(int index) {
            while (index <= lastIndex >> 1) {
                int maxIndex = index << 1;
                maxIndex += (index << 1) + 1 <= lastIndex && array[(index << 1) + 1] < array[(index << 1)] ? 1 : 0;
                if (array[index] > array[maxIndex]) {
                    swap(index, maxIndex);
                    index = maxIndex;
                } else {
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        private int top() {
            return array[1];
        }
    }

    private static void solve() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))
        ) {
            MaxHeap maxHeap = new MaxHeap();
            MinHeap minHeap = new MinHeap();
            int count = 0;
            while (true) {
                String token = reader.readLine();
                if (token == null) break;
                int n = Integer.parseInt(token);
                count++;
                if (n >= minHeap.top()) {
                    minHeap.insert(n);
                } else {
                    maxHeap.insert(n);
                }

                if (maxHeap.size() > minHeap.size()) {
                    minHeap.insert(maxHeap.extract());
                } else if (maxHeap.size() + 1 < minHeap.size()) {
                    maxHeap.insert(minHeap.extract());
                }

                if (count % 2 == 1) {
                    writer.write(minHeap.top() + "\n");
                } else {
                    writer.write(((minHeap.top() + maxHeap.top()) >> 1) + "\n");
                }
            }
        }
    }


    public static void main(final String[] arg) {

        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}