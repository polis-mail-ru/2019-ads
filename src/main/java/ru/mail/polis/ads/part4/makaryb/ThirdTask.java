package ru.mail.polis.ads.part4.makaryb;

import java.io.*;

/**
 * Made by БорискинМА
 * 20.10.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5905381
 */
public final class ThirdTask {

    private static int max = 1000000;

    private static MaxHeap pqMax;
    private static MinHeap pqMin;

    private static int cursor = 0;
    private static int minCursor = 0;
    private static int maxCursor = 0;

    private static long peek;

    private ThirdTask() {}

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {

        String line = in.readLine();

        pqMax = new MaxHeap(max);
        pqMin = new MinHeap(max);

        while (line != null) {
            cursor++;

            int x = Integer.parseInt(line);

            if (cursor % 2 == 0) {
                if (x > peek) {
                    pqMin.Heap[++minCursor] = peek;
                    pqMax.Heap[++maxCursor] = x;

                    pqMin.swim(minCursor);
                    pqMax.swim(maxCursor);
                }
                else {
                    pqMin.Heap[++minCursor] = x;
                    pqMax.Heap[++maxCursor] = peek;

                    pqMin.swim(minCursor);
                    pqMax.swim(maxCursor);
                }
                long result = (pqMax.Heap[1] + pqMin.Heap[1]) / 2;
                out.println(result);
            }
            else {
                if (x < pqMin.Heap[1]) {
                    peek = pqMin.Heap[1];

                    pqMin.Heap[1] = x;

                    pqMin.sink(1, minCursor);
                }
                else if (x > pqMax.Heap[1]) {
                    peek = pqMax.Heap[1];

                    pqMax.Heap[1] = x;

                    pqMax.sink(1, maxCursor);
                }
                else {
                    peek = x;
                }
                out.println(peek);
            }
            line = in.readLine();
        }

        out.close();
    }

    // Java program to implement Max Heap
    private static class MaxHeap {
        private long[] Heap;
        private int size;

        MaxHeap(int maxsize) {
            this.size = 0;
            Heap = new long[maxsize + 1];
            Heap[1] = Integer.MAX_VALUE;
        }

        private void swap(int fpos, int spos) {
            long temp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = temp;
        }

        private void swim(int k) {
            while (k > 1 && Heap[k] < Heap[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k, int cursor) {
            while (2 * k <= cursor) {
                int j = 2 * k;

                if (j < cursor && Heap[j] > Heap[j + 1]) {
                    j++;
                }

                if (Heap[k] <= Heap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }

    // Java implementation of Min Heap
    private static class MinHeap {
        private long[] Heap;
        private int size;

        MinHeap(int maxsize) {
            this.size = 0;
            Heap = new long[maxsize + 1];
            Heap[1] = Integer.MIN_VALUE;
        }

        private void swap(int fpos, int spos) {
            long temp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = temp;
        }

        private void swim(int k) {
            while (k > 1 && Heap[k] > Heap[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k, int cursor) {
            while (2 * k <= cursor) {
                int j = 2 * k;
                if (j < cursor && Heap[j] < Heap[j + 1]) {
                    j++;
                }
                if (Heap[k] >= Heap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }

    public static void main(final String[] arg) throws IOException {
        final BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")))) {
            try {
                solve(in, out);
            } catch (IOException ignored) {}
        }
    }
}
