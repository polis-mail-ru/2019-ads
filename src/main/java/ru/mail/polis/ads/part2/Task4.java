package ru.mail.polis.ads.part2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < str.length(); ++i) {
            if (count < 0) {
                flag = false;
                break;
            } else if (str.charAt(i) == '('){
                ++count;
            } else {
                --count;
            }
        }
        if (flag && count == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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
