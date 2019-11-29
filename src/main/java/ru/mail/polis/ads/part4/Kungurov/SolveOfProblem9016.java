package ru.mail.polis.ads.part4.Kungurov;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6234299
 */
public class SolveOfProblem9016 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] qst = new int[q];
        for (int i = 0; i < q; i++) {
            qst[i] = in.nextInt();
        }
        for (int x : qst) {
            out.println(binarySearch(arr, x) ? "YES" : "NO");
        }

    }

    private static boolean binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x)
                return true;
            if (arr[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }
        return false;
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
