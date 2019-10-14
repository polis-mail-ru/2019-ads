package part2;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/5785939

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String braces = in.next();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < braces.length(); i++) {
            char c = braces.charAt(i);
            if (c == '(') stack.push(c);
            else if (c == ')') {
                if(stack.isEmpty()) {
                    out.print("NO");
                    return;
                }
                else if(stack.peek() == '(')
                    stack.pop();
                else{
                    out.print("NO");
                    return;
                }
            }
        }
        out.print(stack.isEmpty() ? "YES" : "NO");
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
