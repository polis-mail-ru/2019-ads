package ru.mail.polis.ads.part5.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task991 {
    private Task991() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s1 = in.next();
        String s2 = in.next();
        if (s1.contains("*") || s1.contains("?")){
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        int n = s1.length();
        int m = s2.length();
        boolean d[][] = new boolean[n+1][m+1];
        d[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1) || s2.charAt(j-1) == '?'){
                    d[i][j] = d[i-1][j-1];
                }
                else if (s2.charAt(j-1) == '*'){
                    d[i][j] = (d[i-1][j] || d[i-1][j-1] || d[i][j-1]);
                }
                else {
                    d[i][j] = false;
                }
            }
        }
        if (d[n][m]){
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
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}