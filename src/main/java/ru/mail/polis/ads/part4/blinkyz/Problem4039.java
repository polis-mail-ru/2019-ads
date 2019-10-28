package ru.mail.polis.ads.part4.blinkyz;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Problem4039 {
    private Problem4039() {
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
    }

    private static final class Heap {
        private Integer[] a;

        private int heapSize;

        public Heap() {
            this.a = new Integer[(int) 1e5];
            heapSize = 0;
        }

        private int parent(int i) {
            return (i - 1) / 2;
        }

        private int left(int i) {
            return 2 * i + 1;
        }

        private int right(int i) {
            return 2 * i + 2;
        }

        public void add(Integer e) throws IllegalStateException {
            if (heapSize == a.length) {
                throw new IllegalStateException("Heap is full");
            }
            a[heapSize] = Integer.MIN_VALUE;
            increaseKey(heapSize, e);
            heapSize++;
        }

        public int extractMax() throws NoSuchElementException {
            if (heapSize == 0) {
                throw new NoSuchElementException("Heap is empty");
            }
            int max = a[0];
            a[0] = a[heapSize - 1];
            heapSize--;
            maxHeapify(0);
            return max;
        }

        private void increaseKey(int i, int newKey) throws IllegalStateException {
            if (newKey < a[i]) {
                throw new IllegalStateException("New key is lower than old key");
            }
            a[i] = newKey;
            while (i > 0 && a[parent(i)] < a[i]) {
                swap(a, parent(i), i);
                i = parent(i);
            }
        }

        private void maxHeapify(int i) {
            int l = left(i);
            int r = right(i);
            int largest;

            if (l <= heapSize && a[l] > a[i]) {
                largest = l;
            } else {
                largest = i;
            }

            if (r <= heapSize && a[r] > a[largest]) {
                largest = r;
            }

            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(largest);
            }
        }

        private void swap(Integer[] a, int i, int j) {
            Integer temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    private static void solve() throws IOException {
        FastScanner in = new FastScanner(System.in);
        Heap heap = new Heap();

        int n = in.nextInt();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < n; i++) {
            int cmd = in.nextInt();
            if (cmd == 0) {
                heap.add(in.nextInt());
            } else {
                bufferedWriter.write(String.valueOf(heap.extractMax()));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
