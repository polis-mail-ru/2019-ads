package main.java.ru.mail.polis.ads.part3.Eretic431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.e-olymp.com/ru/submissions/5905519
 */

public class Task2 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        final HashMap<Integer, List<Integer>> hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            final Integer tmp = in.nextInt();
            final int lastDigit = tmp % 10;

            final List<Integer> list = hm.getOrDefault(lastDigit, new LinkedList<>());
            list.add(tmp);
            hm.put(lastDigit, list);
        }

        for (int i = 0; i < 10; i++) {
            final List<Integer> list = new LinkedList<>(hm.getOrDefault(i, new LinkedList<>()));
            list.sort(Integer::compare);

            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + " ");
            }
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
