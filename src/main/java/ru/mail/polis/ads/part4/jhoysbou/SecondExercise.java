package ru.mail.polis.ads.part4.jhoysbou;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/5893015

public class SecondExercise {

    public static void main (String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        final int length = scanner.nextInt();
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());

        for (int i = 0; i < length; i++) {
            int argument = scanner.nextInt();

            if (argument == 0) {
                heap.add(scanner.nextInt());
            }
            else {
                out.println(heap.poll());
            }

            out.flush();
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

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer firstNumber, Integer secondNumber) {
            return firstNumber > secondNumber ? -1 : firstNumber < secondNumber ? 1 : 0;
        }

    }

}
