package ru.mail.polis.ads.part5.DiscreetDmitriy;

import java.io.*;
import java.util.StringTokenizer;

public class Task5Permutation {
    private Task5Permutation() {
    }

    private static int n;
    private static int[] numbers;

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        numbers = new int[n];

        for (int i = 0; i < n; i++)
            numbers[i] = i + 1;

        do {
            for (int number : numbers)
                out.print(number + " ");

            out.println();
        } while (permutation());

        out.close();
    }

    private static boolean permutation() {
        int i = n - 2;

        while (i != -1 && numbers[i] > numbers[i + 1])
            i--;

        if (i == -1)
            return false;


        int j = n - 1;

        while (numbers[i] > numbers[j])
            j--;

        swap(i, j);
        int l = i + 1;
        int r = n - 1;

        while (l < r)
            swap(l++, r--);

        return true;
    }

    private static void swap(int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
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
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}

