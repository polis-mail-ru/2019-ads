package ru.mail.polis.ads.part5.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/5957761

public class Exercise_2 {

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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);

        long width = scanner.nextLong();
        long height = scanner.nextLong();
        long number = scanner.nextLong();

        long low = Math.max(height, width);
        long high = number * low;
        long mid = (high + low) / 2;

        while (low < high) {
            long guessNumber = (mid / width) * (mid / height);

            if (guessNumber >= number) {
                high = mid;
            }
            else {
                low = mid + 1;
            }

            mid = (high + low) / 2;

        }

        System.out.println(low);

    }

}
