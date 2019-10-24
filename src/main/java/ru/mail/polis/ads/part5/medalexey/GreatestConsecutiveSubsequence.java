package ru.mail.polis.ads.part5.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 *  Задача: Наибольшая последовательнократная подпоследовательность  https://www.e-olymp.com/ru/problems/264
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5937682
 */
public class GreatestConsecutiveSubsequence {

    private static int[] arr;
    private static int[] arr2;
    private static int arraySize;

    private GreatestConsecutiveSubsequence() {
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        arraySize = in.nextInt();
        initArrays();

        fillArray(in, arraySize);

        out.println( findResult() );
    }


    private static void initArrays() {
        arr = new int[arraySize];
        arr2 = new int[arraySize];
        arr2[0] = 1;
    }


    private static int findResult() {
        int ans = 1;

        for (int i = 1; i < arraySize; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] != 0 && arr[i] % arr[j] == 0 && arr2[j] > max) {
                    max = arr2[j];
                }
            }
            arr2[i] = ++max;
            if (arr2[i] > ans) {
                ans = arr2[i];
            }
        }

        return ans;
    }



    private static void fillArray(FastScanner in, int size) {
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
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


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
