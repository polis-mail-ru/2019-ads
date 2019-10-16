package ru.mail.polis.ads.part3.DiscreetDmitriy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task5MergeSort {
    private Task5MergeSort() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Robot[] robots = new Robot[n];

        for (int i = 0; i < n; i++)
            robots[i] = new Robot(in.nextInt(), in.nextInt());

        mergeSort(robots);

        for (Robot robot : robots)
            out.println(robot.toString());
    }

    private static class Robot {
        int firstNumber;
        int secondNumber;

        Robot(int mainNumber, int secondNumber) {
            this.firstNumber = mainNumber;
            this.secondNumber = secondNumber;
        }

        @Override
        public String toString() {
            return firstNumber + " " + secondNumber;
        }
    }

    private static void merge(Robot[] elements, int begin, int middle, int end) {
        Robot[] left = Arrays.copyOfRange(elements, begin, middle);
        Robot[] right = Arrays.copyOfRange(elements, middle, end);

        int leftIndex = 0, rightIndex = 0;

        for (int i = begin; i < end; i++)
            if (leftIndex < left.length && (rightIndex == right.length
                    || left[leftIndex].firstNumber <= right[rightIndex].firstNumber))
                elements[i] = left[leftIndex++];
            else
                elements[i] = right[rightIndex++];
    }

    private static void mergeSort(Robot[] elements, int begin, int end) {
        if (end - begin <= 1) return;

        int middle = (begin + end) / 2;

        mergeSort(elements, begin, middle);
        mergeSort(elements, middle, end);
        merge(elements, begin, middle, end);
    }

    private static void mergeSort(Robot[] elements) {
        mergeSort(elements, 0, elements.length);
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

