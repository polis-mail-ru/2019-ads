package ru.mail.polis.ads.part4.jhoysbou;

import java.io.*;

// Submission here https://www.e-olymp.com/ru/submissions/5983971

public class ThirdExercise {

    public static void main(String[] args) throws IOException {
        final int maxSize = 500001;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        maxHeap leftHeap = new maxHeap(maxSize);
        minHeap rightHeap = new minHeap(maxSize);

        Integer nextNumber;
        try {
            nextNumber = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            return;
        }
        int median = 0;
        int counter = 1;

        while (nextNumber != null) {

            if (counter == 1) {
                median = nextNumber;
            }
            else if (counter % 2 == 0)  {
                if (nextNumber >= median) {
                    rightHeap.insert(nextNumber);
                    leftHeap.insert(median);
                }

                else if (nextNumber < median) {
                    leftHeap.insert(nextNumber);
                    rightHeap.insert(median);
                }

                median = (leftHeap.peek() + rightHeap.peek()) / 2;

            }
            else {
                if (nextNumber >= median) {
                    rightHeap.insert(nextNumber);
                    median = rightHeap.poll();
                }
                else {
                    leftHeap.insert(nextNumber);
                    median = leftHeap.poll();
                }

            }
            out.println(median);
            try {
                nextNumber = Integer.parseInt(reader.readLine());
                counter++;
            } catch (Exception e) {
                break;
            }

        }
        out.close();
        reader.close();
    }

    private static class maxHeap {
        private int[] Heap;
        private int size;

        maxHeap(int capacity) {
            this.size = 0;
            Heap = new int[capacity + 1];

        }

        private void swap(int fpos, int spos)
        {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        void heapify(int pos) {
            if (pos * 2 > size)
                return;

            if (Heap[pos] < Heap[pos * 2]  ||
                    Heap[pos] < Heap[pos * 2 + 1]) {

                if (Heap[pos * 2] > Heap[pos * 2 + 1]) {
                    swap(pos, pos * 2);
                    heapify(pos * 2);
                }
                else {
                    swap(pos, pos * 2 + 1);
                    heapify(pos * 2 + 1);
                }
            }
        }

        void insert(int element) {
            Heap[++size] = element;

            int current = size;
            while (current > 1 && Heap[current] > Heap[current / 2]) {
                swap(current, current / 2);
                current = current / 2;
            }
        }

        int peek() {
            return Heap[1];
        }

        int poll()
        {
            int popped = Heap[1];
            Heap[1] = Heap[size--];
            heapify(1);
            return popped;
        }
    }

    private static class minHeap {
        private int[] Heap;
        private int size;

        minHeap(int capacity) {
            this.size = 0;
            Heap = new int[capacity + 1];
        }

        private void swap(int fpos, int spos)
        {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        void heapify(int pos) {
            if (pos * 2 > size)
                return;

            if (Heap[pos] > Heap[pos * 2]  ||
                    Heap[pos] > Heap[pos * 2 + 1]) {

                if (Heap[pos * 2] < Heap[pos * 2 + 1]) {
                    swap(pos, pos * 2);
                    heapify(pos * 2);
                }
                else {
                    swap(pos, pos * 2 + 1);
                    heapify(pos * 2 + 1);
                }
            }
        }

        void insert(int element) {
            Heap[++size] = element;

            int current = size;
            while (current > 1 && Heap[current] < Heap[current / 2]) {
                swap(current, current / 2);
                current = current / 2;
            }
        }

        int peek() {
            return Heap[1];
        }

        int poll()
        {
            int popped = Heap[1];
            Heap[1] = Heap[size--];
            heapify(1);
            return popped;
        }
    }

}
