package ru.mail.polis.ads.part4.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task5 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int M = in.nextInt();
        int[] array = new int[n];
        int maximum = Integer.MIN_VALUE;

        boolean isAble = true;

        for (int i = n - 1; i >= 0 ; i--) {
            array[i] = in.nextInt();
            if ((array[i] < maximum) && (array[i] + maximum > M)) {
                isAble = false;
                break;
            }
            if (array[i] > maximum) {
                maximum = array[i];
            }
        }

        if (isAble) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
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
