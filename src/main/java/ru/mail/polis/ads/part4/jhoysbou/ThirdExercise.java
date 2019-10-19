package ru.mail.polis.ads.part4.jhoysbou;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/5899595

public class ThirdExercise {

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

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        PriorityQueue<Integer> leftHeap = new PriorityQueue<>(new MyComparator());
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>();


        Integer nextNumber = scanner.nextInt();
        int median = Integer.MAX_VALUE;
        boolean isEmpty = true;


        while (nextNumber != null) {

            if (isEmpty) {
                median = nextNumber;
                System.out.println(median);
            }

            if (median != Integer.MAX_VALUE && !isEmpty) {
                if (nextNumber >= median) {
                    rightHeap.add(nextNumber);
                    leftHeap.add(median);
                }
                else if (nextNumber < median) {
                    leftHeap.add(nextNumber);
                    rightHeap.add(median);
                }
                System.out.println((leftHeap.peek() + rightHeap.peek()) / 2);
                median = Integer.MAX_VALUE;
            }
            else if (!isEmpty) {
                median = (rightHeap.peek() + leftHeap.peek()) / 2;
                if (nextNumber >= median) {
                    rightHeap.add(nextNumber);
                    median = rightHeap.poll();
                }
                else {
                    leftHeap.add(nextNumber);
                    median = leftHeap.poll();
                }
                System.out.println(median);
            }
            nextNumber = scanner.nextInt();
            isEmpty = false;
        }
    }

}
