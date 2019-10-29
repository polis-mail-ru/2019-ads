package ru.mail.polis.ads.part.fourth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5964485
 */
public class TaskOne {

    private static void solve(FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        array[0] = in.nextInt();
        boolean flag = true;
        for (int i = 1; i < n; i++) {
            array[i] = in.nextInt();
            if (array[i] < array[(i - 1) / 2]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
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
