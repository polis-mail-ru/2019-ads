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

    private static long findStall(int k) {
        long left = 0;
        long right = array[array.length - 1] - array[0];
        long mid;
        while (left != right) {
            mid = (left + right) / 2;
            int g = 0;
            int j = 0;
            for (int i = 1; i < array.length; ++i) {
                if (array[i] - array[j] > mid) {
                    ++g;
                    j = i;
                }
            }
            if (g < k - 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private static void solve(FastScanner input, PrintWriter output) {


        int n = input.nextInt();
        int k = input.nextInt();

        array = new int[n];

        for (int i = 0; i < n; i++) {
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
