package ru.mail.polis.ads.part4.jhoysbou;

import java.io.*;

// Submission here https://www.e-olymp.com/ru/submissions/5980571

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


        int leftChild(int parent) {
            return 2*parent;
        }

        int rightChild(int parent) {
            return 2*parent + 1;
        }

        private boolean isLeaf(int pos)
        {
            if (pos * 2 > size) {
                return true;
            }
            return false;
        }

        private void swap(int fpos, int spos)
        {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        void heapify(int pos) {
            if (isLeaf(pos))
                return;

            if (Heap[pos] < Heap[leftChild(pos)]  ||
                    Heap[pos] < Heap[rightChild(pos)]) {

                if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    heapify(leftChild(pos));
                }
                else {
                    swap(pos, rightChild(pos));
                    heapify(rightChild(pos));
                }
            }
        }

        private int parent(int pos)
        {
            return pos / 2;
        }

        void insert(int element) {
            Heap[++size] = element;

            int current = size;
            while (current > 1 && Heap[current] > Heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
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


        int leftChild(int parent) {
            return 2*parent;
        }

        int rightChild(int parent) {
            return 2*parent + 1;
        }

        private boolean isLeaf(int pos)
        {
            if (pos > (size / 2) && pos <= size) {
                return true;
            }
            return false;
        }

        private void swap(int fpos, int spos)
        {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        void heapify(int pos) {
            if (isLeaf(pos))
                return;

            if (Heap[pos] > Heap[leftChild(pos)]  ||
                    Heap[pos] > Heap[rightChild(pos)]) {

                if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    heapify(leftChild(pos));
                }
                else {
                    swap(pos, rightChild(pos));
                    heapify(rightChild(pos));
                }
            }
        }

        private int parent(int pos)
        {
            return pos / 2;
        }

        void insert(int element) {
            Heap[++size] = element;

            int current = size;
            while (current > 1 && Heap[current] < Heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
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
