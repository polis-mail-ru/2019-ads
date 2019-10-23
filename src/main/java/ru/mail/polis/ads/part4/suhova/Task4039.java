package ru.mail.polis.ads.part4.suhova;

import java.io.*;
import java.util.StringTokenizer;


public class Task4039 {
    /*
    Task 2: https://www.e-olymp.com/ru/submissions/5891157
     */
    private static int[] heap;
    private static int size = 0;

    private static void solve(final Task4039.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        heap = new int[n];
        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 0) insert(in.nextInt());
            else out.println(extract());
        }
    }

    private static void insert(int x) {
        heap[size++] = x;
        int k = size - 1;
        while (k > 0 && heap[k] > heap[(k - 1) / 2]) {
            int par = (k - 1) / 2;
            int temp = heap[k];
            heap[k] = heap[par];
            heap[par] = temp;
            k = par;
        }
    }

    private static int extract() {
        int res = heap[0];
        heap[0] = heap[--size];
        int k = 0;
        while (2 * k + 1 < size) {
            int child = 2 * k + 1;
            if (child < size && heap[child] < heap[child + 1]) child++;
            if (heap[k] >= heap[child]) break;
            int temp = heap[k];
            heap[k] = heap[child];
            heap[child] = temp;
            k = child;
        }
        return res;
    }

    public static void main(final String[] arg) {
        final Task4039.FastScanner in = new Task4039.FastScanner(System.in);
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