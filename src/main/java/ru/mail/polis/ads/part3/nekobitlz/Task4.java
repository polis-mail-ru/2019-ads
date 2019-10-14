package ru.mail.polis.ads.part3.nekobitlz;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String[] lines = in.next().trim().replaceAll("[\\s]{2,}", " ").split(" ");
        BigInteger[] digits = new BigInteger[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            digits[i] = new BigInteger(line);
        }

        Arrays.sort(digits);

        out.print(digits[digits.length - n]);
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
        }
    }
}