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
public final class Task3968 {
    private Task3968() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/6013403
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Double c = Double.parseDouble(in.next());
        Double x;
        Double x0 = Math.sqrt(c);
        Double fx0 = Math.sqrt(x0);
        Double dfx0 = 2 * x0 + 0.5 / Math.sqrt(x0);
        Double ddfx0 = 2 - 0.25 / (x0 * Math.sqrt(x0));
        Double x1 = x0 - dfx0 / ddfx0 + Math.sqrt(dfx0 * dfx0 - 2 * fx0 * ddfx0) / ddfx0;
        Double x2 = x0 - dfx0 / ddfx0 - Math.sqrt(dfx0 * dfx0 - 2 * fx0 * ddfx0) / ddfx0;
        if (Math.abs(x1 - x0) < Math.abs(x2 - x0)) {
            x = x1;
        } else {
            x = x2;
        }
        while (Math.abs(x - x0) > 0.000001) {
            x0 = x;
            fx0 = x0 * x0 + Math.sqrt(x0) - c;
            dfx0 = 2 * x0 + 0.5 / Math.sqrt(x0);
            ddfx0 = 2 - 0.25 / (x0 * Math.sqrt(x0));
            x1 = x0 - dfx0 / ddfx0 + Math.sqrt(dfx0 * dfx0 - 2 * fx0 * ddfx0) / ddfx0;
            x2 = x0 - dfx0 / ddfx0 - Math.sqrt(dfx0 * dfx0 - 2 * fx0 * ddfx0) / ddfx0;
            if (Math.abs(x1 - x0) < Math.abs(x2 - x0)) {
                x = x1;
            } else{
                x = x2;
            }
            x = x0 - fx0 / dfx0;
        }
        long res = Math.round(x * 1000000);
        out.println(((double) res) / 1000000);
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
