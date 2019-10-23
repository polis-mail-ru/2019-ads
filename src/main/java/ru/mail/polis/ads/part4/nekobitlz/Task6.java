package ru.mail.polis.ads.part4.nekobitlz;

import java.io.*;
import java.util.StringTokenizer;

public class Task6 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int x = in.nextInt();

            if (a[findIndex(a, x)] == x) out.println("YES");
            else out.println("NO");
        }
    }

    private static int findIndex(int[] a, int x) {
        int index = 0;
        int minIndex = 0;
        int maxIndex = a.length - 1;

        while (minIndex <= maxIndex) {
            int middle = (minIndex + maxIndex) / 2;

            if (a[middle] < x) {
                minIndex = middle + 1;
            } else if (a[middle] > x) {
                maxIndex = middle - 1;
            } else {
                index = middle;
                break;
            }
        }

        return index;
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
