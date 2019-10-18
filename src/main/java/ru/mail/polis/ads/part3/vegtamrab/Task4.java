package ru.mail.polis.ads.part3.vegtamrab;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String[] lines = in.next().trim().split("\\s+");
        BigInteger[] numbers = new BigInteger[lines.length];

        for (int i = 0; i < lines.length; ++i) {
            numbers[i] = new BigInteger(lines[i]);
        }

        Arrays.sort(numbers, Collections.reverseOrder());

        out.println(numbers[n - 1]);
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
                    tokenizer = new StringTokenizer(reader.readLine(), "\n");
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
            System.out.println();
        }
    }
}

