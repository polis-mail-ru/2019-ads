package ru.mail.polis.ads.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2169 {
    private Task2169() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/6021783
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int num = in.nextInt();
        int[] seq = new int[num];
        for (int i = 0; i < num; i++) {
            seq[i] = i + 1;
        }
        do {
            for (int i = 0; i < seq.length; i++) {
                out.print(seq[i] + " ");
            }
            out.println();
        } while (nextSeq(seq));
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean nextSeq(int[] sequence) {
        int i = sequence.length;
        do {
            if(i < 2) {
                return false;
            }
            i--;
        } while (sequence[i - 1] > sequence[i]);
        int j = sequence.length;
        while (i < j && sequence[i - 1] > sequence[--j]);
        swap(sequence, i - 1, j);
        j = sequence.length;
        while (i < --j) {
            swap(sequence, i++, j);
        }
        return true;
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
