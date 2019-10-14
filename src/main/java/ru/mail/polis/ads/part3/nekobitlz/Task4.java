package ru.mail.polis.ads.part3.nekobitlz;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Task4 {

    private static void solve(final FastScanner at, final PrintWriter out) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int max = Integer.MIN_VALUE;
        String[] lines = in.nextLine().trim().replaceAll("[\\s]{2,}", " ").split(" ");
        int[] digits = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            digits[i] = Integer.parseInt(lines[i]);
            max = max(digits[i], max);
        }

        int[] sortedArray = countingSort(digits, max);

        out.print(sortedArray[sortedArray.length - n]);
    }

    private static int[] countingSort(int[] elements, int limit) {
        int[] count = new int[limit + 1];

        for (int element: elements) {
            count[element]++;
        }

        for (int j = 1; j <= limit; j++) {
            count[j] += count[j - 1];
        }

        int[] result = new int[elements.length];

        for (int j = elements.length - 1; j >= 0; j--) {
            result[count[elements[j]] - 1] = elements[j];
            count[elements[j]]--;
        }

        return result;
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