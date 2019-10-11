package ru.mail.polis.ads.part3.suhova;

import java.io.*;
import java.util.StringTokenizer;

public class Task4741 {
    /*
    Task 3: https://www.e-olymp.com/ru/submissions/5819654
     */

    private static void solve(final Task4741.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int x=0;
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j+1];
                    a[j+1]=a[j];
                    a[j]=temp;
                    x++;
                }
            }
        }
        out.print(x);
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task4741.FastScanner in = new Task4741.FastScanner(System.in);
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
