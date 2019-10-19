package ru.mail.polis.ads.part3.maksimshengeliia;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5897770
* */
public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        String[] strings = s.split("\\s+");
        int length = strings.length;
        BigInteger[] array = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            array[i] = new BigInteger(strings[i]);
        }
        Arrays.sort(array);
        out.println(array[length - n]);

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
