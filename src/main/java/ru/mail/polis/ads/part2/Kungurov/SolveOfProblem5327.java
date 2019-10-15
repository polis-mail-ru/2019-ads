package ru.mail.polis.ads.part2.Kungurov;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * created by kirill.kungurov on 15.09.2019
 * https://www.e-olymp.com/ru/submissions/5861493
 */
public final class SolveOfProblem5327 {

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String sequence = in.reader.readLine().trim();
        Stack<Character> stack = new Stack<Character>();
        for (int i=0;i<sequence.length();i++){
            if (sequence.charAt(i)=='('){
                stack.push('(');
            } else {
                if (!stack.empty()){
                    stack.pop();
                } else {
                    out.println("NO");
                    return;
                }
            }
        }
        if (stack.empty()){
            out.println("YES");
        }else {
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
