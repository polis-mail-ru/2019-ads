package ru.mail.polis.ads.part4.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
class BinaryHeap {
    private int[] elements;
    private int count;
    public BinaryHeap(){
        this.elements = new int[100000];
        count = 0;
    }
    public void add(int element) {
        elements[count] = element;
        siftUp(count);
        count++;
    }
    public int extractMax() {
        int result = elements[0];
        elements[0] = elements[count - 1];
        deleteLast();
        if (!isEmpty()) {
            siftDown(0);
        }
        return result;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private void deleteLast() {
        count--;
    }

    private void siftDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < count && elements[left] > elements[i]) {
            largest = left;
        }
        if (right < count && elements[right] > elements[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            siftDown(largest);
        }
    }
    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (elements[i] < elements[parent])
                return;
            swap(i, parent);
            i = parent;
        }
    }
    private void swap(int index1, int index2) {
        int temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }
}

public final class Task4039 {
    private Task4039() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        BinaryHeap heap = new BinaryHeap();
        int n = in.nextInt();
        int command;
        for (int i = 0; i < n; i++) {
            command = in.nextInt();
            if (command == 0){
                heap.add(in.nextInt());
            }
            else {
                out.println(heap.extractMax());
            }
        }
        return;
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
