package ru.mail.polis.ads.part5.Kungurov;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * created by kirill kungurov on 22.10.2019
 * https://www.e-olymp.com/ru/submissions/5926083
 */
public class SolveOfProblem2169 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        out.println(print(arr));
        while (hasNextStep(arr, n)) {
            out.println(print(arr));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean hasNextStep(int[] arr, int n) {
        int j = n - 2;
        while (j != -1 && arr[j] >= arr[j + 1]) {
            j--;
        }
        if (j == -1) {
            return false;
        }
        int k = n - 1;
        while (arr[j] >= arr[k]) {
            k--;
        }
        swap(arr, j, k);
        int l = j + 1;
        int r = n - 1;
        while (l < r) {
            swap(arr, l++, r--);
        }
        return true;
    }

    private static String print(int[] arr) {
        StringBuilder res = new StringBuilder();
        for (int i : arr) {
            res.append(i);
            res.append(" ");
        }
        return res.toString();
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
