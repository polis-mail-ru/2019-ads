package ru.mail.polis.ads.part2.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5327{

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack<Character> stack = new Stack<>();
        String str = in.next();

        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == '('){
                stack.push(str.charAt(i));
                continue;
            }
            if (stack.isEmpty()){
                System.out.println("NO");
                return;
            }
            if (str.charAt(i) == ')')
                stack.pop();
        }
        if (stack.isEmpty())
            System.out.println("YES");
        else
            System.out.println("NO");
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