package ru.mail.polis.ads.part4;

import java.io.*;

public class Task2 {

    private static class Heap {

        int[] array;
        int lastIndex;

        Heap() {
            this.array = new int[100001];
            lastIndex = 0;
        }

        private void insert(int num) {
            lastIndex++;
            array[lastIndex] = num;
            swim(lastIndex);
        }

        private int extract() {
            if (lastIndex < 1) {
                throw new ArrayIndexOutOfBoundsException("Heap is empty");
            }
            int result = array[1];
            array[1] = array[lastIndex];
            lastIndex--;
            if (lastIndex != 0) {
                sink(1);
            }
            return result;
        }

        private void swim(int index) {
            while (index / 2 > 0 && array[index / 2] < array[index]) {
                swap(index / 2, index);
                index = index / 2;
            }
        }

        private void sink(int index) {
            while (index <= lastIndex / 2) {
                int maxIndex = index * 2;
                maxIndex += index * 2 + 1 <= lastIndex && array[index * 2 + 1] > array[index * 2] ? 1 : 0;
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
    }

    private static void solve() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Heap heap = new Heap();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");
            if (Integer.parseInt(tokens[0]) == 1) {
                writer.write(heap.extract() + "\n");
            } else {
                heap.insert(Integer.parseInt(tokens[1]));
            }
        }
        writer.flush();
    }


    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}