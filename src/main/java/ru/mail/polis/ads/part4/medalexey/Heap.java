package ru.mail.polis.ads.part4.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *  Название задачи: Хипуй!  https://www.e-olymp.com/ru/problems/4039
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5897822
 */
public class Heap {

    private static int[] heap;
    private static int amount;

    private Heap() {
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        heap = new int[100000];
        amount = 0;
        final int NUMBER_OF_COMMANDS = in.nextInt();

        for (int i = 0; i < NUMBER_OF_COMMANDS; i++) {
            if (in.nextInt() == 1) {
                out.println( extract() );
            } else {
                insert( in.nextInt() );
            }
        }
    }


    private static void insert(final int element) {
        heap[++amount] = element;
        swim(amount);
    }


    private static void swim(int element) {
        while (element > 1 && heap[element] > heap[element/2]) {
            swap(element, element / 2);
            element /= 2;
        }
    }


    private static void swap(int first, int second) {
        int tmp = heap[first];
        heap[first] = heap[second];
        heap[second] = tmp;
    }


    private static int extract() {
        int result = heap[1];
        swap(1, amount--);

        sink();
        return result;
    }


    private static void sink() {
        int k = 1;

        while (2 * k <= amount) {
            int j = 2 * k;

            if (j < amount && heap[j] < heap[j + 1]) {
                j++;
            }

            if (heap[k] >= heap[j]) {
                break;
            }

            swap(k, j);
            k = j;
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