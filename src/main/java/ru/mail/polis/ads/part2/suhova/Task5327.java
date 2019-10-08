package ru.mail.polis.ads.part2.suhova;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Task5327 {
    /*
    https://www.e-olymp.com/ru/submissions/5775266
     */
    private static void solve(final Task5327.FastScanner in, final PrintWriter out) {
        char[] str = in.next().toCharArray();
        int s = 0;
        ArrayDeque<Boolean> stack = new ArrayDeque<>();
        for (char c : str) {
            if (c == '(') {
                s++;
            } else if (c == ')') {
                s--;
                if (s < 0) break;
            }
        }
        if (s == 0) out.println("YES");
        else out.println("NO");
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task5327.FastScanner in = new Task5327.FastScanner(System.in);
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
