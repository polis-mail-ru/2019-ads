package part5;

//https://www.e-olymp.com/ru/submissions/5925274

import java.io.*;
import java.util.StringTokenizer;

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s1 = in.next(), s2 = in.next();
        String word = "", pattern = "";
        if (s1.contains("*")){
            pattern = s1;
            word = s2;
        }
        else {
            pattern = s2;
            word = s1;
        }
        int n = word.length(), m = pattern.length();
        boolean[][] match = new boolean[n + 1][m + 1];
        match[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?')
                    match[i][j] = match[i-1][j-1];
                else if (pattern.charAt(j-1) == '*')
                    match[i][j] = match[i-1][j] || match[i-1][j-1] || match[i][j-1];
                else
                    match[i][j] = false;
            }
        }
        out.println(match[n][m] ? "YES" : "NO");
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
