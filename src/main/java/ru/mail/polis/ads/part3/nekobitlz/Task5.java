package ru.mail.polis.ads.part3.nekobitlz;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Robot[] robots = new Robot[n];

        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(in.nextInt(), in.nextInt());
        }

        mergeSort(robots);

        for (int i = 0; i < n; i++) {
            out.println(robots[i].toString());
        }
    }

    private static void merge(Robot[] elements, int begin, int middle, int end) {
        Robot[] left = Arrays.copyOfRange(elements, begin, middle);
        Robot[] right = Arrays.copyOfRange(elements, middle, end);

        int li = 0, ri = 0;

        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li].getMainNumber() <= right[ri].getMainNumber())) {
                elements[i] = left[li++];
            }
            else {
                elements[i] = right[ri++];
            }
        }
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

    private static class Robot {
        private int mainNumber;
        private int auxiliaryNumber;

        Robot(int mainNumber, int auxiliaryNumber) {
            this.mainNumber = mainNumber;
            this.auxiliaryNumber = auxiliaryNumber;
        }

        int getMainNumber() {
            return mainNumber;
        }

        @Override
        public String toString() {
            return mainNumber + " " + auxiliaryNumber;
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
