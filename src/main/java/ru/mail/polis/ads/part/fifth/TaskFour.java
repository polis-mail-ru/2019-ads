package ru.mail.polis.ads.part.fifth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/6029824
 */
public class TaskFour {

    private static void solve(FastScanner in, final PrintWriter out) {
        String input = in.nextLine();
        String templateOne;
        String templateTwo;
        int n = 0;
        int m = 0;
        boolean[][] mask;
        if (input.contains("*") || input.contains("?")) {
            templateOne = input;
            templateTwo = in.nextLine();
        } else {
            templateOne = in.nextLine();
            templateTwo = input;
        }
        n = templateTwo.length();
        m = templateOne.length();
        mask = new boolean[n + 1][m + 1];
        mask[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (templateTwo.charAt(i - 1) == templateOne.charAt(j - 1) || templateOne.charAt(j - 1) == '?') {
                    mask[i][j] = mask[i - 1][j - 1];
                } else if (templateOne.charAt(j - 1) == '*') {
                    mask[i][j] = mask[i - 1][j] || mask[i - 1][j - 1] || mask[i][j - 1];
                } else {
                    mask[i][j] = false;
                }
            }
        }
        if (mask[n][m]) {
            out.println("YES");
        } else {
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

        String nextLine() {
            return next();
        }
    }

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
