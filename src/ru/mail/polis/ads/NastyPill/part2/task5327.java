package ru.mail.polis.ads.NastyPill.part2;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class task5327 {
    private task5327() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5778457
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = in.next();
        Stack<Character> stack = new Stack<>();
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(len > 0) {
                    len--;
                    stack.pop();
                } else {
                    out.println("NO");
                    return;
                }
            } else {
                len++;
                stack.add('(');
            }
        }
        if(len == 0) {
            out.println("YES");
        } else {
            out.println("NO");
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}