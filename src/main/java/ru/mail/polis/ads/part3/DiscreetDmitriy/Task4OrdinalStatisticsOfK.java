package ru.mail.polis.ads.part3.DiscreetDmitriy;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task4OrdinalStatisticsOfK {
    private Task4OrdinalStatisticsOfK() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String[] lines = in.next().trim().split("\\s+");
        int length = lines.length;
        BigInteger[] numbers = new BigInteger[length];

        for (int i = 0; i < length; i++)
            numbers[i] = new BigInteger(lines[i]);

        Arrays.sort(numbers);

        out.println(numbers[length - n]);
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

