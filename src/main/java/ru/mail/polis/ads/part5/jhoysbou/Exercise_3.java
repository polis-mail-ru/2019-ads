package ru.mail.polis.ads.part5.jhoysbou;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/5984685

public class Exercise_3 {

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
        int length = scanner.nextInt();
        long[] array = new long[length];

        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println(solve(array));

    }

    private static int solve(long[] array) {
        int length = array.length;
        int[] dynamicArray = new int[length];
        dynamicArray[0] = 1;
        int max = 0;

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] != 0 && array[i] % array[j] == 0 && dynamicArray[j] > dynamicArray[i]) {
                    dynamicArray[i] = dynamicArray[j];
                }
            }
            dynamicArray[i]++;
            max = Math.max(max, dynamicArray[i]);
        }

        return max;
    }


}
