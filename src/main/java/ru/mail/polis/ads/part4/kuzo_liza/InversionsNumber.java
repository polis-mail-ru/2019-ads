package ru.mail.polis.ads.part4.kuzo_liza;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InversionsNumber {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        out.println(sort(array));
    }

    private static int merge(int[] array, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int counter = 0;

        while (i < left.length || j < right.length) {
            if (i == left.length) {
                array[i + j] = right[j];
                j++;
            }

            else if (j == right.length) {
                array[i + j] = left[i];
                i++;
            }

            else if (left[i] <= right[j]) {
                array[i + j] = left[i];
                i++;
            }

            else {
                array[i + j] = right[j];
                counter += left.length - i;
                j++;
            }
        }

        return counter;
    }

    private static int sort(int[] array) {
        if (array.length < 2) return 0;

        int middle = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        return sort(left) + sort(right) + merge(array, left, right);
    }


    public static void main(String[] args) {
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
