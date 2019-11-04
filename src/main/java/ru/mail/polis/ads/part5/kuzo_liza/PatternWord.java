package ru.mail.polis.ads.part5.kuzo_liza;

import java.io.*;
import java.util.StringTokenizer;

public class PatternWord {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        char[] p;
        char[] w;

        if(input.contains("*")){
            p = input.toCharArray();
            w = in.next().toCharArray();
        }
        else{
            p = in.next().toCharArray();;
            w = input.toCharArray();
        }

        int n = w.length;
        int m = p.length;

        boolean [][] d = new boolean[n + 1][m + 1];
        d[0][0]=true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m ; j++) {
                if (w[i - 1] == p[j - 1] || p[j - 1] == '?'){
                    d[i][j] = d[i - 1][j - 1];
                }
                else if (p[j - 1] == '*'){
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                }
                else{
                    d[i][j] = false;
                }
            }
        }
        out.print(d[n][m]? "YES":"NO");
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