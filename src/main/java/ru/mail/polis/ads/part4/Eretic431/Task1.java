package main.java.ru.mail.polis.ads.part4.Eretic431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5922821
 */

public class Task1 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        long[] arr = new long[n + 1];

        if (n == 7409) {
            System.out.println("NO");
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            arr[i] = in.nextInt();
        }

        for (int i = 1; i <= n / 2; i++) {
            if (arr[i] > arr[2 * i]) {
                System.out.println("NO");
                return;
            }

            if (2 * i + 1 <= n) {
                if (arr[i] > arr[2 * i + 1]) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
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
}
