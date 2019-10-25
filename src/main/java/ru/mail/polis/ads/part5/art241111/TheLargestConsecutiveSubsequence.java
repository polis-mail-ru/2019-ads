package ru.mail.polis.ads.part5.art241111;

import java.io.*;
import java.util.StringTokenizer;

public class TheLargestConsecutiveSubsequence {
    private static int search(int count, int[]a){
       int ans = 1;
       int[] d = new int[count];
       d[0] = 1;

       for (int i = 1; i < count; i++) {
            int max = 0;

            for (int j = 0; j < i; j++) {
                if (a[j] != 0 && a[i] % a[j] == 0 && d[j] > max) {
                    max = d[j];
                }
            }

            d[i] = max + 1;
            if (d[i] > ans) ans = d[i];
        }
        return ans;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        int[] a = new int[count];

        // Read
        for (int i = 0; i < count; i++) a[i] = in.nextInt();

        out.println(search(count,a));
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
