package ru.mail.polis.ads.part5.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class Permutations {

    private static void solve(FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        output(out, array);

        while (permute(array)) {
            output(out, array);
        }
    }

    private static void output(PrintWriter out, int[] numbers) {
        for (int number : numbers) {
            out.print(number + " ");
        }
        out.println();
    }

    private static boolean permute(int[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] > array[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[i] > array[j]) {
            j--;
        }

        swap(array, i, j);

        int left = i + 1;
        int right = array.length - 1;
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
        return true;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
