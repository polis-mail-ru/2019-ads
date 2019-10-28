package ru.mail.polis.ads.part4.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task4039 {
    private Task4039() {}

    public static int[] arr;
    public static int n;
    public static int size = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        arr = new int[n+1];

        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 0) insert(in.nextInt());
            else out.println(extract());
        }
        out.flush();
    }

    public static void insert(int x) {
        if (size == 0) {
            arr[1] = x;
            size++;
            return;
        }

        arr[++size] = x;
        int k = size;
        while (arr[k] > arr[k/2] && k > 0 && k/2 != 0) {
            int parent = k / 2;
            arr[k] = arr[parent];
            arr[parent] = x;
            k = parent;
        }
    }

    public static int extract() {
        int resuult = arr[1];
        arr[1] = arr[size];
        arr[size] = 0;
        size--;
        int k = 1;

        while (k*2 <= size) {
            int j = 2 * k;
            if (j < size && arr[j] < arr[j+1]) j++;
            if (arr[k] >= arr[j]) break;
            int temp = arr[j];
            arr[j] = arr[k];
            arr[k] = temp;
            k = j;
        }

        return resuult;
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