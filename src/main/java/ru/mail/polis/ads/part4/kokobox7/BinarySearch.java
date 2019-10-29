package ru.mail.polis.ads.part4.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class BinarySearch {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        int x;
        for (int i = 0; i < q; i++) {
            x = in.nextInt();
            out.println(binarySearch(array, x) ? "YES" : "NO");
        }
    }

    static boolean binarySearch(int[] array, int x) {
        int left = 0;
        int right = array.length - 1;
        int median = (left + right) / 2;

        while (right != left ) {
            if (array[median] == x) {
                return true;
            } else if (array[median] < x) {
                if (left == median){
                    left = right;
                }
                else {
                    left = median;
                }
            } else {
                right = median;
            }
            median = (left + right) / 2;
        }

        return (x == array[median]);
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
