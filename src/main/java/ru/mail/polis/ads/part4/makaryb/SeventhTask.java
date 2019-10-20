package ru.mail.polis.ads.part4.makaryb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Made by БорискинМА
 * 20.10.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5907077
 */
public final class SeventhTask {

    private static int l = 0;
    private static int r = 0;
    private static int res = 0;

    private static int max = 10001;

    private static int[] array = new int[max];

    private SeventhTask() {}

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int k = in.nextInt();
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
            if (array[i] > r) {
                r = array[i];
            }
        }

        qSortImplementation(0, n-1);

        while (l < r) {
            int m = (l + r + 1) >> 1;
            int counter = 1;
            int cursor = array[0];

            for (int j = 1; j < n; j++) {
                if (!((array[j] - cursor) < m)) {
                    cursor = array[j];
                    counter++;
                }
            }

            if (counter > k-1) {
                l = m;
                if (counter > k-1) {
                    res = m;
                }
            }
            else {
                r = m -1;
            }
        }

        out.println(res);
    }

    private static void swap(int w1, int w2) {
        int temp = w1;
        //noinspection UnusedAssignment
        w1 = w2;
        //noinspection UnusedAssignment
        w2 = temp;
    }

    private static void qSortImplementation(int l, int r) {
        int i = l;
        int j = r;
        int key = array[(i+j) >> 1];
        while (i < j + 1) {
            while (array[i] < key) {
                i++;
            }
            while (array[j] > key) {
                j--;
            }

            if (i < j + 1) {
                swap(array[i], array[j]);
                i++;
                j--;
            }
        }

        if (i < r) {
            qSortImplementation(i ,r);
        }
        if (j > l) {
            qSortImplementation(l, j);
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
