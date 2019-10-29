package ru.mail.polis.ads.part4.Kopeyka885;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
class Heap {
    private ArrayList<Integer> heap;

    Heap(){
        heap = new ArrayList<>();
    }

    public void Insert(int number) {
        heap.add(number);
        int i = heap.size() - 1;
        int parent = (i - 1) / 2;
        while (i > 0 && heap.get(i) >= heap.get(parent)) {
            int swapper = heap.get(i);
            heap.set(i, heap.get(parent));
            heap.set(parent, swapper);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public int Extract() {

        int res = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int leftChild;
        int rightChild;
        int largestChild;

        int i = 0;

        while (true) {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largestChild)) {
                largestChild = leftChild;
            }

            if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largestChild)) {
                largestChild = rightChild;
            }

            if (largestChild == i) {
                break;
            }

            int temp = heap.get(i);
            heap.set(i, heap.get(largestChild));
            heap.set(largestChild, temp);
            i = largestChild;
        }

        return res;
    }
}

public final class Task4039 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int command;
        Heap heap = new Heap();
        for (int i = 0; i < size; i++) {
            command = in.nextInt();
            if (command == 0) {
                int number = in.nextInt();
                heap.Insert(number);
            } else {
                out.println(heap.Extract());
            }
        }
        out.flush();
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}