package ru.mail.polis.ads.part4.gogun;

import java.io.*;
import java.util.*;

public class Task7 {
    static int[] array;

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final BufferedInputStream in) {
            reader = new BufferedReader(new InputStreamReader(System.in));
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

    private static int findStall(int k) {
        int left = 0;
        int right = array[array.length - 1] - array[0];
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            int g = 1;
            int j = 1;
            for (int i = 1; i < array.length; ++i) {
                if (array[i] - array[j] >= mid) {
                    j = i;
                    g++;
                }
                if (g >= k)
                    left = mid + 1;
                else
                    right = mid;
            }
        }
        return left - 1;
    }

    private static void solve(FastScanner input, PrintWriter output) {


        int n = input.nextInt();
        int k = input.nextInt();

        array = new int[n];

        for (int i = 0; i < n; ++i) {
            array[i] = input.nextInt();
        }

        output.println(findStall(k));
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
