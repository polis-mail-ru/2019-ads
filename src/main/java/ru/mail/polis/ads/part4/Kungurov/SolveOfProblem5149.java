package ru.mail.polis.ads.part4.Kungurov;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6248118d
 */
public class SolveOfProblem5149 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.println(searchMaxDist(arr, k));
    }

    public static int searchMaxDist(int[] arr, int k) {
        int l = 0;
        int r = arr[arr.length - 1] - arr[0];

        if (k == 2) {
            return r;
        }

        while (l < r) {
            int c = k - 1;
            int m = (l + r) / 2;
            int j = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] - arr[j] > m) {
                    j = i;
                    c--;
                }
            }
            if (c > 0) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
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
