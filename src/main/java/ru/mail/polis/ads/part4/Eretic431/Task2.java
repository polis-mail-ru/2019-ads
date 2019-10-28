package main.java.ru.mail.polis.ads.part4.Eretic431;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5968908
 */

public class Task2 {
    private enum Command {
        INSERT,
        EXTRACT
    }

    private static Command getCommand(int n) {
        if (n == 0) {
            return Command.INSERT;
        }
        return Command.EXTRACT;
    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final BinaryHeap bh = new BinaryHeap(100_000);
        final int n = in.nextInt();

        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < n; i++) {
                Command command = getCommand(in.nextInt());

                if (command == Command.INSERT) {
                    int tmp = in.nextInt();
                    bh.insert(tmp);
                    continue;
                }

                if (command == Command.EXTRACT) {
                    out.println((bh.extract()));
                }
            }
        } catch (Exception e) {
            throw e;
        }

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

    private static class BinaryHeap {
        private int[] pyramid;
        private int length;

        BinaryHeap(int capacity) {
            this.length = 0;
            pyramid = new int[capacity];
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
            if (length > 1) {
                siftDown(0);
            }

            return result;
        }

        private void siftUp(int n) {
            int child = n;
            int parent = parentIndex(n);

            while (pyramid[child] > pyramid[parent]) {
                swap(child, parent);
                child = parent;
                parent = parentIndex(child);
            }
        }

        private void siftDown(int n) {
            int largestChild;
            int leftChild;
            int rightChild;

            while (true) {
                leftChild = 2 * n + 1;
                rightChild = 2 * n + 2;
                largestChild = n;

                if (leftChild < length && pyramid[leftChild] > pyramid[largestChild])
                {
                    largestChild = leftChild;
                }

                if (rightChild < length && pyramid[rightChild] > pyramid[largestChild])
                {
                    largestChild = rightChild;
                }

                if (largestChild == n)
                {
                    break;
                }

                swap(n, largestChild);
                n = largestChild;
            }
        }

        private void swap(int lhs, int rhs) {
            final int tmp = pyramid[lhs];
            pyramid[lhs] = pyramid[rhs];
            pyramid[rhs] = tmp;
        }

        private int parentIndex(int n) {
            int parent;

            if (n == 0) {
                return 0;
            }

            if (n % 2 == 0) {
                parent = (n - 1) / 2;
            } else {
                parent = n / 2;
            }

            return parent;
        }
    }
}
