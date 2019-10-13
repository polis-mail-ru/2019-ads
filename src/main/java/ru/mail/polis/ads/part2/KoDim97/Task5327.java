package ru.mail.polis.ads.part2.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Stack;
/**
 * Problem solution template.
 */
public final class Task5327 {
    private Task5327() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = in.next();
        Stack stack = new Stack();
        int size = s.length();
        for (int i = 0; i < size; i++) {
            if (s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }
            else {
                if (!stack.empty() && stack.peek().toString().equals("(")){
                    stack.pop();
                }
                else{
                    out.print("NO");
                    return;
                }
            }
        }
        if(stack.empty()){
            out.print("YES");
        }
        else {
            out.print("NO");
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
