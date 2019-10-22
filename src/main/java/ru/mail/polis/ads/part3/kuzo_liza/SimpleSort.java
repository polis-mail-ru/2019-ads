package ru.mail.polis.ads.part3.kuzo_liza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SimpleSort {

    private SimpleSort(){
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int curr = i - (i - j) / 2;
        while (i < j) {
            while (i < curr && (array[i] <= array[curr])) {
                i++;
            }
            while (j > curr && (array[curr] <= array[j])) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == curr)
                    curr = j;
                else if (j == curr)
                    curr = i;
            }
        }
        quickSort(array, start, curr);
        quickSort(array, curr+1, end);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n ; i++) {
            array[i] = in.nextInt();
        }

        quickSort(array, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.print(array[i] + " ");
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