package ru.mail.polis.ads.part1.suhova;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Task1084 {
    private static void solve(final Task1084.FastScanner in, final PrintWriter out) {
        char[] str = in.next().toCharArray();
        Deque<Character> stack = new ArrayDeque<Character>();
        StringBuilder res = new StringBuilder();
        for(char c: str){
            switch(c){
                case '(':
                case '[':
                    stack.addLast(c);
                    res.append(c);
                    break;
                case ')':
                    if (!stack.isEmpty()&&stack.peekLast()=='('){
                        stack.pollLast();
                        res.append(c);
                    } else {
                        res.append('(');
                        res.append(')');
                    }
                    break;
                case ']':
                    if (!stack.isEmpty()&&stack.peekLast()=='['){
                        stack.pollLast();
                        res.append(c);
                    } else {
                        res.append('[');
                        res.append(']');
                    }
                    break;
            }
        }
        while(!stack.isEmpty()){
            if(stack.pollLast()=='(') res.append(')');
            else res.append(']');
        }
        String s = new String(res);
        out.println(s);
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
        final Task1084.FastScanner in = new Task1084.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}