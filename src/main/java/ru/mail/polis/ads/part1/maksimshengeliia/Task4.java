package ru.mail.polis.ads.part1.maksimshengeliia;

import java.io.*;
import java.util.StringTokenizer;

/*
 * https://www.e-olymp.com/ru/submissions/5790594
 * */
public class Task4 {
    private Task4() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] arr1, arr2;
        int[][] matrix;

        int l1 = in.nextInt();
        arr1 = new int[l1 + 1];
        for(int i = 0; i < l1; i++) {
            arr1[i + 1] = in.nextInt();
        }

        int l2 = in.nextInt();
        arr2 = new int[l2 + 1];
        for(int i = 0; i < l2; i++) {
            arr2[i + 1] = in.nextInt();
        }

        matrix = new int[l1 + 1][l2 + 1];
        calculate(matrix, arr1, arr2);
        out.println(matrix[l1][l2]);
    }


    static void calculate(int[][] dynamic, int[] arr1, int[] arr2) {

        for (int i = 1; i < arr1.length; i++) {
            for (int j = 1; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    dynamic[i][j] = dynamic[i-1][j-1] + 1;
                } else {
                    dynamic[i][j] = Math.max(dynamic[i-1][j], dynamic[i][j-1]);
                }
            }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
