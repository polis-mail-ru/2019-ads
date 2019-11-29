package ru.mail.polis.ads.part4.Kungurov;

import java.io.*;
import java.util.StringTokenizer;

public class SolveOfProblem9016 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            out.println(binarySearch(arr, x) ? "YES" : "NO");
        }

    }

    private static boolean binarySearch(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;


        while (left <= right) {
            int m = (left + right) / 2;
            if (arr[m] == x) {
                return true;
            }
            if (arr[m] > x) {
                right = m;
            } else {
                left = m;
            }
        }
        return false;
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
