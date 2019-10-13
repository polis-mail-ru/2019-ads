package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] stairs = new int[n+2];
        for (int i = 1; i < (n + 1); i++) {
            stairs[i] = fs.nextInt();
        }
        int k = fs.nextInt();

        int[] max = new int[n+2];

        max[0] = 0;
        max[1] = max[0] + stairs[1];
        for (int i = 2; i < (n+2); i++) {
            max[i] = maximum(i, k, max) + stairs[i];
        }
        System.out.println(max[n+1]);
    }

    public static int maximum(int i, int k, int[] max) {
        int maximum = max[i-1];
        for (int j = 2; j <= k; j++) {
            if (i-j < 0) {
                break;
            }

            if (max[i-j] > maximum) {
                maximum = max[i-j];
            }
        }

        return maximum;
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
