package ru.mail.polis.ads.part.fourth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5964630
 */
public class TaskTwo {

    private static int size = 0;

    private static void solve(FastScanner in, PrintWriter out) {
        int[] heap = new int[in.nextInt()];
        for (int i = 0; i < heap.length; i++) {
            if (in.nextInt() == 0) {
                insert(in.nextInt(), heap);
            } else {
                out.println(extract(heap));
            }
        }
    }

    private static void insert(int value, int[] heap) {
        heap[size++] = value;
        int k = size - 1;
        while (heap[k] > heap[(k - 1) / 2] && k > 0) {
            int par = (k - 1) / 2;
            int tmp = heap[k];
            heap[k] = heap[par];
            heap[par] = tmp;
            k = par;
        }
    }

    private static int extract(int[] heap) {
        int result = heap[0];
        heap[0] = heap[--size];
        int k = 0;
        while (((2 * k) + 1) < size) {
            int children = 2 * k + 1;
            if (children < size && heap[children] < heap[children + 1]) {
                children++;
            }
            if (heap[k] >= heap[children]) {
                break;
            }
            int tmp = heap[k];
            heap[k] = heap[children];
            heap[children] = tmp;
            k = children;
        }
        return result;
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
