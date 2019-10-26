package ru.mail.polis.ads.part4.Kungurov;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by Kirill Kungurov on 26.10.2019
 * https://www.e-olymp.com/ru/submissions/5957033
 */
public class SolveOfProblem3737 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] arr = new long[n + 1];
        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextLong();
        }

        for (int i = 1; i <= n; i++) {
            if (2 * i <= n) {
                if (arr[i]>arr[2*i]) {
                    out.println("NO");
                    return;
                }
            } else if (2*i + 1<=n){
                if (arr[i]>arr[2*i+1]){
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}