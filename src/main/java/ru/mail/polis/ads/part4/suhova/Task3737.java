package ru.mail.polis.ads.part4.suhova;

import java.io.*;
import java.util.StringTokenizer;

public class Task3737 {
    /*
    Task 1: https://www.e-olymp.com/ru/submissions/5889867
     */

    private static void solve(final Task3737.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        boolean b = true;
        a[0] = in.nextInt();
        for (int i = 1; i < n; i++) {
            a[i] = in.nextInt();
            if (a[(i - 1) / 2] > a[i]) {
                b = false;
                break;
            }
        }
        if (b) out.println("YES");
        else out.println("NO");
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task3737.FastScanner in = new Task3737.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
