package ru.mail.polis.ads.part2.atani20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import java.util.ArrayList;
/**
 * Problem solution template.
 */
public final class Stack {
    private Stack() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList stack = new ArrayList();
        String str;
        do{
            str = in.next();
            if(str.equals("push")){
                int n = in.nextInt();
                stack.add(n);
                out.println("ok");
            }
            else if(str.equals("pop")){
                if(stack.isEmpty()){
                    out.println("error");
                    continue;
                }
                out.println(stack.get(stack.size()-1));
                stack.remove(stack.size()-1);
            }
            else if(str.equals("back")){

                if(stack.isEmpty()){
                    out.println("error");
                    continue;
                }
                out.println(stack.get(stack.size() - 1));
            }
            else if(str.equals("size")){
                out.println(stack.size());
            }
            else if(str.equals("clear")){
                stack.clear();
                out.println("ok");
            }
        }while (!str.equals("exit"));
        out.println("bye");
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