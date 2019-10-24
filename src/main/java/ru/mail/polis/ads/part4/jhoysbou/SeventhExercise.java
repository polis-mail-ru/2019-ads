package ru.mail.polis.ads.part4.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/5936837

public class SeventhExercise {

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
        int stall = scanner.nextInt();
        int cows = scanner.nextInt();
        int[] array = new int[stall + 1];

        for (int i = 1; i <= stall; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println(getDistance(array, cows, stall));

    }

    private static int getDistance(int[] array, int cows, int stalls) {
        int left = 0;
        int right = array[stalls] - array[1];

        if (cows == 2) {
            return right;
        }

        while (left != right) {
            int middle = (left + right) / 2;
            int count = 1;
            int index = 1;

            for (int i = 2; i <= stalls; i++) {
                if (array[i] - array[index] >= middle) {
                    index = i;
                    count++;
                }
            }

            if (count >= cows) {
                left = middle + 1;
            }
            else right = middle;
        }

        return left - 1;

    }
}
