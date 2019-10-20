package part2;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/5855849

public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String expr = in.next();
            char[] syms = expr.toCharArray();
            StringBuilder[] sb = new StringBuilder[syms.length];
            for (int j = 0; j < sb.length; j++) {
                sb[j] = new StringBuilder("");
            }
            Index index = new Index(syms.length - 1);
            getLevels(syms, index, 0, sb);
            for (int j = syms.length - 1; j >= 0; j--)
                out.print(sb[j].toString());
            out.println();
        }
    }

    private static void getLevels(char[] expr, Index index, int depth, StringBuilder[] result) {
        result[depth].append(expr[index.index]);
        index.index--;
        if (Character.isUpperCase(expr[index.index + 1])) {
            getLevels(expr, index, depth + 1, result);
            getLevels(expr, index, depth + 1, result);
        }
    }

    private static class Index{
        int index;

        Index(int index) {
            this.index = index;
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
