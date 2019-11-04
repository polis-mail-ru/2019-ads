package ru.mail.polis.ads.part5.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int width = in.nextInt();
        int height = in.nextInt();
        int n = in.nextInt();

        long minLength = Math.max(width, height);
        long composition = n * minLength;

        while (minLength < composition) {
            long mid = minLength + (composition - minLength) / 2;
            long mean = (mid / width) * (mid / height);
            if (mean >= n) {
                composition = mid;
            } else {
                minLength = mid + 1;
            }
        }

        out.println(minLength);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
