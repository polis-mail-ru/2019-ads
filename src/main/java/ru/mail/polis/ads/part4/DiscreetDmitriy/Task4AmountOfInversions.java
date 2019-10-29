package ru.mail.polis.ads.part4.DiscreetDmitriy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task4AmountOfInversions {
    private Task4AmountOfInversions() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        out.println(invCount(a));
    }

    private static long invCount(int[] a) {
        if (a.length < 2)
            return 0;

        int m = (a.length + 1) / 2;
        int[] left = Arrays.copyOfRange(a, 0, m);
        int[] right = Arrays.copyOfRange(a, m, a.length);

        return invCount(left) + invCount(right) + merge(a, left, right);
    }

    private static long merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int count = 0;

        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i + j] = right[j];
                j++;
            } else if (j == right.length
                    || left[i] <= right[j]) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                count += left.length - i;
                j++;
            }
        }

        return count;
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

