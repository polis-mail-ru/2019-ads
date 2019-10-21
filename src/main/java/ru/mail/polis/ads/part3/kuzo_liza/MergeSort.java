package ru.mail.polis.ads.part3.kuzo_liza;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class MergeSort {

    private MergeSort() {
    }

    private static class Robot {
        int mainNumber;
        int additionalNumber;

        Robot(int mainNumber, int additionalNumber) {
            this.mainNumber = mainNumber;
            this.additionalNumber = additionalNumber;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Robot[] array = new Robot[n];
        int numberM, numberA;

        for (int i = 0; i < n; i++) {
            numberM = in.nextInt();
            numberA = in.nextInt();
            array[i] = new Robot(numberM, numberA);
        }

        sort(array, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.println(array[i].mainNumber + " " + array[i].additionalNumber);
        }
    }

    static void sort(Robot[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            sort(array, left, middle);
            sort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    static void merge(Robot[] array, int left, int middle, int right) {
        int part1 = middle - left + 1;
        int part2 = right - middle;

        Robot[] l = new Robot[part1];
        Robot[] r = new Robot[part2];

        for (int i = 0; i < part1; i++) {
            l[i] = array[i + left];
        }

        for (int i = 0; i < part2; i++) {
            r[i] = array[i + middle + 1];
        }

        int i = 0, j = 0, k = left;
        while (i < part1 && j < part2) {
            if (l[i].mainNumber <= r[j].mainNumber) {
                array[k] = l[i];
                i++;
            } else {
                array[k] = r[j];
                j++;
            }
            k++;
        }

        while (i < part1) {
            array[k] = l[i];
            k++;
            i++;
        }

        while (j < part2) {
            array[k] = r[j];
            k++;
            j++;
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