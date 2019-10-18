package ru.mail.polis.ads.part4.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *  Название задачи: Куча ли?  https://www.e-olymp.com/ru/problems/3737
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5886104
 */
public class IsHeap {

    private static long[] array;


    private IsHeap() {
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        final int NUMBER_OF_ELEMENTS = in.nextInt();

        array = new long[NUMBER_OF_ELEMENTS+1];
        fillArray(in);

        out.println( checkHeap() ? "YES" : "NO");
    }


    private static void fillArray(final FastScanner in) {
        for (int i = 1; i < array.length; i++) {
            array[i] = in.nextLong();
        }
    }


    private static boolean checkHeap() {
        for (int i = 1; i < array.length; i++) {
            if ( (2*i < array.length && array[2*i] < array[i]) || (2*i+1 < array.length && array[2*i+1] < array[i]) ) {
                return false;
            }
        }

        return true;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
