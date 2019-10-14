package ru.mail.polis.ads.part.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5856187
 */
public class TaskFour {

    private static void solve(final FastScanner in, final PrintWriter out) {
        char[] input = in.next().toCharArray();
        int i = 0;
        for (char c : input) {
            if (c == '(') {
                i++;
            } else if (c == ')') {
                i--;
                if (i < 0) {
                    break;
                }
            }
        }
        if (i == 0) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
    }
}
