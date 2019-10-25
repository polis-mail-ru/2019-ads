package ru.mail.polis.ads.part4.gogun;

import java.io.*;
import java.util.*;

public class Task5 {
    static int[] array;
    static int load;
    static String ans;

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

    private static void findLoad(int n) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] < max && array[i]+max > load) {
                ans = "No";
                return;
            }

            if (max < array[i])
                max = array[i];
        }
        ans = "Yes";
    }

    private static void solve(FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        load = in.nextInt();
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        findLoad(n);

        out.println(ans);
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
