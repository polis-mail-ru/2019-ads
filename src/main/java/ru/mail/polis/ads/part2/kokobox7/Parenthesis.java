package ru.mail.polis.ads.part2.kokobox7;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Parenthesis {

    private static void solve(final Scanner in, final PrintWriter out) {
        LinkedList<Character> stack = new LinkedList<>();
        String input = in.next();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.add('(');
            } else if (input.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    out.println("NO");
                    return;
                } else if (stack.peek() == ')') {
                    out.println("NO");
                    return;
                }
                //-> in stack head is '('
                else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    /*private static class FastScanner {
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
    }*/

    public static void main(final String[] arg) {
        //final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out);
             Scanner in = new Scanner(System.in)) {
            solve(in, out);
        }
    }
}
