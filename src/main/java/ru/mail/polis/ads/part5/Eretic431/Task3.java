package main.java.ru.mail.polis.ads.part5.Eretic431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6016186
 */

public class Task3 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int[] d = new int[n];
        d[0] = 1;
        int ans = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] != 0 && (arr[i] % arr[j] == 0) && d[j] > max) {
                    max = d[j];
                }
            }

            d[i] = max + 1;
            if (d[i] > ans) {
                ans = d[i];
            }
        }

        System.out.println(ans);
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
