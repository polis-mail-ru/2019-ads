package ru.mail.polis.ads.part5.kuzo_liza;

import java.io.*;
import java.util.StringTokenizer;

public class Permutations {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if (n == 1){
            out.println(1);
            return;
        }
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }
        printPermutations(numbers, out);
    }

    private static void printPermutations(int[] numbers, PrintWriter out)
    {
        do {
            for (int num : numbers) {
                out.print(num + " ");
            }
            out.println();

        } while (nextPermutation(numbers));
    }

    private static boolean nextPermutation(int[] numbers)
    {
        int i = numbers.length - 1;
        while (numbers[i - 1] >= numbers[i]) {
            if (--i == 0) {
                return false;
            }
        }

        int j = numbers.length - 1;
        while (j > i && numbers[j] <= numbers[i - 1]) {
            j--;
        }

        swap(numbers, i - 1, j);
        reverse(numbers, i);
        return true;
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private static void reverse(int[] numbers, int start) {
        for (int i = start, j = numbers.length - 1; i < j; i++, j--) {
            swap(numbers, i, j);
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
