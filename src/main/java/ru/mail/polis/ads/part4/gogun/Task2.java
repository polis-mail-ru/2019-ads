package ru.mail.polis.ads.part4.gogun;

import java.io.*;
import java.util.*;

public class Task2 {
    static int[] array;
    static int n;

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final BufferedInputStream in) {
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

    private static void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swim(int k) {
        while (k > 1 && array[k] > array[k/2]) {
            swap(k, k/2);
            k = k/2;
        }
    }

    private static void sink(int k) {
        while (k*2 <= n) {
            int j = 2 * k;
            if (j < n && array[j] < array[j+1])
                j++;
            if (array[k] >= array[j])
                break;
            swap(k, j);
            k = j;
        }
    }

    private static void insert(int e) {
        if (array.length == 0)
            array[++n] = e;
        else {
            array[++n] = e;
            swim(n);
        }
    }

    private static void extract() {
        System.out.println(array[1]);
        swap(1, n);
        --n;
        sink(1);
    }

    private static void solve(FastScanner input, PrintWriter output) {

        int k = input.nextInt();
        array = new int[500001];
        n = 0;
        array[n] = -1;

        for (int i = 0; i < k; ++i) {
            switch (input.nextInt()) {
                case 0 :
                    insert(input.nextInt());
                    break;
                case 1:
                    extract();
                    break;
            }
        }


    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
