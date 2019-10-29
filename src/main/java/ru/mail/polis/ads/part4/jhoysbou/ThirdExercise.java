package ru.mail.polis.ads.part4.jhoysbou;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

// Submission here https://www.e-olymp.com/ru/submissions/5965364

public class ThirdExercise {

    private static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer firstNumber, Integer secondNumber) {
            return firstNumber > secondNumber ? -1 : firstNumber < secondNumber ? 1 : 0;
        }
    }

    public static void main(String[] args) throws IOException {
        final int maxSize = 500001;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        Heap leftHeap = new Heap(maxSize);
        Heap rightHeap = new Heap(maxSize, new MyComparator());

        Integer nextNumber = null;
        try {
            nextNumber = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            // its okay
        }
        int median = Integer.MAX_VALUE;
        boolean isEmpty = true;

        while (true) {

            if (isEmpty) {
                median = nextNumber;
                out.println(median);
            }

            if (median != Integer.MAX_VALUE && !isEmpty) {
                if (nextNumber >= median) {
                    rightHeap.insert(nextNumber);
                    leftHeap.insert(median);
                }
                else if (nextNumber < median) {
                    leftHeap.insert(nextNumber);
                    rightHeap.insert(median);
                }
                out.println((leftHeap.peek() + rightHeap.peek()) / 2);
                median = Integer.MAX_VALUE;
            }
            else if (!isEmpty) {
                median = (rightHeap.peek() + leftHeap.peek()) / 2;
                if (nextNumber >= median) {
                    rightHeap.insert(nextNumber);
                    median = rightHeap.poll();
                }
                else {
                    leftHeap.insert(nextNumber);
                    median = leftHeap.poll();
                }
                out.println(median);
            }

            try {
                nextNumber = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                break;
            }

            isEmpty = false;
        }
        out.close();
        reader.close();
    }

    private static class Heap {
        private int[] Heap;
        private int size;
        private Comparator<Integer> c = (o1, o2) -> o1 > o2 ? 1 : o1 < o2 ? -1 : 0;

        Heap(int capacity) {
            this.size = 0;
            Heap = new int[capacity + 1];
            Arrays.fill(Heap, Integer.MAX_VALUE);

        }

        Heap(int capacity, Comparator<Integer> c) {
            this.c = c;
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

            if (c.compare( Heap[pos], Heap[leftChild(pos)]) == -1 ||
                    c.compare(Heap[pos], Heap[rightChild(pos)]) == -1) {

                if (c.compare(Heap[leftChild(pos)], Heap[rightChild(pos)]) == 1) {
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
            while (current != 0 && c.compare(Heap[current], Heap[parent(current)]) == 1) {
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
