package ru.mail.polis.ads.part4.jhoysbou;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Submission here https://www.e-olymp.com/ru/submissions/5936315

public class FifthExercise {

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

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        final int length = scanner.nextInt();
        final int carrying = scanner.nextInt();
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < length; i++) {
            int value = scanner.nextInt();

            if (value < maxValue && value + maxValue > carrying) {
                System.out.println("No");
                return;
            }

            maxValue = Math.max(value, maxValue);
        }

        System.out.println("Yes");
    }


}
