package ru.mail.polis.ads.part5;

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
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/6000035
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s1 = in.next();
        String s2 = in.next();
        if(!s2.contains("*") && !s2.contains("?")) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        System.out.println(s1);
        System.out.println(s2);
        String s = "[A-Z]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s2.length(); i++) {
            if(s2.charAt(i) == '*') {
                sb.append(s);
            }
            if(s2.charAt(i) == '?') {
                sb.append(s);
                sb.append("{1}");
                continue;
            }
            sb.append(s2.charAt(i));
        }
        if(s1.matches(sb.toString())) {
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
