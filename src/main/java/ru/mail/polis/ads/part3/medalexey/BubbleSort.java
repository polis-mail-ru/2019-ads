package ru.mail.polis.ads.part3.medalexey;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 *  Задача: Сортировка пузырьком - 2 (https://www.e-olymp.com/ru/problems/4741)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5825675
 */
public class BubbleSort {

    private BubbleSort() {

    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        final List<Integer> elements = new ArrayList<>();
        final int numberOfElements = in.nextInt();

        readElements(in, elements, numberOfElements);

        out.println(getNumberOfExchanges(elements));
    }


    private static void readElements(final FastScanner in, final List<Integer> dest, final int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            dest.add(in.nextInt());
        }
    }


    private static int getNumberOfExchanges(final List<Integer> elements) {
        int result = 0;
        int tmp;

        for (int i = 0; i < elements.size() - 1; i++) {
            for (int j = 0; j < elements.size() - 1; j++) {
                if (elements.get(j) > elements.get(j+1)) {
                    tmp = elements.get(j);
                    elements.set(j, elements.get(j+1));
                    elements.set(j+1, tmp);
                    result++;
                }
            }
        }

        return result;
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
