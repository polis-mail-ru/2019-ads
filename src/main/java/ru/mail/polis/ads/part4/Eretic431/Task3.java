package main.java.ru.mail.polis.ads.part4.Eretic431;

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {
    public static void main(String[] args) {
        final InputStreamReader input = new InputStreamReader(System.in);
        final BufferedReader in = new BufferedReader(input);
        final PrintWriter out = new PrintWriter(System.out);
        final BinaryHeap gt = new BinaryHeap(500_001, false);
        final BinaryHeap ls = new BinaryHeap(500_001, true);

        int n;
        try {
            for (int i = 0; i < 2; i++) {
                n = Integer.parseInt(in.readLine());
                if (i == 0) {
                    ls.insert(n);
                    System.out.println(n);
                    continue;
                }

                System.out.println((n + ls.peek()) / 2);
                if (n >= ls.peek()) {
                    gt.insert(n);
                } else {
                    ls.insert(n);
                    gt.insert(ls.extract());
                }
            }
        } catch (Exception e) {
            return;
        }

        while (true) {
            try {
                n = Integer.parseInt(in.readLine());
            } catch (Exception e) {
                break;
            }

            if (n < ls.peek()) {
                ls.insert(n);

                if (ls.length() - gt.length() == 2) {
                    gt.insert(ls.extract());
                    out.println((ls.peek() + gt.peek()) / 2);
                } else {
                    out.println(ls.peek());
                }
                continue;
            }

            if (n > gt.peek()) {
                gt.insert(n);

                if (ls.length() - gt.length() == 0) {
                    out.println((ls.peek() + gt.peek()) / 2);
                } else {
                    ls.insert(gt.extract());
                    out.println(ls.peek());
                }
                continue;
            }

            if (ls.length() == gt.length()) {
                out.println(n);
            } else {
                gt.insert(n);
                out.println((ls.peek() + gt.peek()) / 2);
            }
        }
        out.flush();
    }

    private static class BinaryHeap {
        private int[] pyramid;
        private int length;
        private boolean max;

        BinaryHeap(int capacity, boolean max) {
            this.length = 0;
            this.max = max;
            pyramid = new int[capacity];
        }

        int length() {
            return length;
        }

        void insert(int n) {
            length++;
            pyramid[length - 1] = n;
            siftUp(length - 1);
        }

        int peek() {
            return pyramid[0];
        }

        int extract() {
            final int result = pyramid[0];
            pyramid[0] = pyramid[length - 1];
            pyramid[length - 1] = 0;
            length--;
            siftDown(0);

            return result;
        }

        boolean isEmpty() {
            if (length == 0) {
                return true;
            }
            return false;
        }

        private void siftUp(int n) {
            int child = n;
            int parent;

            if (n == 0) {
                parent = 0;
            }

            if (n % 2 == 0) {
                parent = (n - 1) / 2;
            } else {
                parent = n / 2;
            }


            boolean condition;
            if (max) {
                condition = pyramid[child] > pyramid[parent];
            } else {
                condition = pyramid[child] < pyramid[parent];
            }

            while (condition) {
                swap(child, parent);
                child = parent;

                if (child == 0) {
                    parent = 0;
                }

                if (child % 2 == 0) {
                    parent = (n - 1) / 2;
                } else {
                    parent = n / 2;
                }

                if (max) {
                    condition = pyramid[child] > pyramid[parent];
                } else {
                    condition = pyramid[child] < pyramid[parent];
                }
            }
        }

        private void siftDown(int n) {
            int minmaxChild;
            int leftChild;
            int rightChild;

            while (true) {
                leftChild = 2 * n + 1;
                rightChild = 2 * n + 2;
                minmaxChild = n;

                boolean leftCondition = max ? pyramid[leftChild] > pyramid[minmaxChild] : pyramid[leftChild] < pyramid[minmaxChild];
                boolean rightCondition = max ? pyramid[rightChild] > pyramid[minmaxChild] : pyramid[rightChild] < pyramid[minmaxChild];

                if (leftChild < length && leftCondition)
                {
                    minmaxChild = leftChild;
                }

                if (rightChild < length && rightCondition)
                {
                    minmaxChild = rightChild;
                }

                if (minmaxChild == n)
                {
                    break;
                }

                swap(n, minmaxChild);
                n = minmaxChild;
            }
        }

        private void swap(int lhs, int rhs) {
            final int tmp = pyramid[lhs];
            pyramid[lhs] = pyramid[rhs];
            pyramid[rhs] = tmp;
        }
    }
}

