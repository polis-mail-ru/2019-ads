package ru.mail.polis.ads.part4.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5149 {
    private Task5149() {}

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] points = new int[n];
        for (int i = 0; i < n; ++i) {
            points[i] = in.nextInt();
        }

        if (k == 2 && n == 3) {
            out.println(points[points.length-1] - points[0]);
            System.exit(0);
        }

        int left = 0;
        int right = points[points.length-1] - points[0];
        int middle;
        int cow;
        int pos;

        while (left != right) {
            middle = (left+right) / 2;
            cow = 1;
            pos = 0;
            for (int i = 1; i < n; i++) {
                if (points[i] - points[pos] >= middle) {
                    pos = i;
                    cow++;
                }
            }
            if (cow >= k) {
                left = middle + 1;
            } else{
                right = middle;
            }
        }

        out.println(left - 1);
        out.flush();
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
