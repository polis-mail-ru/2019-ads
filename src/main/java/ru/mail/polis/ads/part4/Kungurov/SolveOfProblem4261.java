package ru.mail.polis.ads.part4.Kungurov;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6248418
 */
public class SolveOfProblem4261 {


    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        out.println(mergeSortAndCount(arr,0,size-1));
    }

    private static int mergeAndCount(int[] arr, int l, int m, int r)
    {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }
       while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    private static int mergeSortAndCount(int[] arr, int l, int r)
    {

        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;
            count += mergeSortAndCount(arr, l, m);
            count += mergeSortAndCount(arr, m + 1, r);
            count += mergeAndCount(arr, l, m, r);
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
