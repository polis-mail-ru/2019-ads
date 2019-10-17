package ru.mail.polis.ads.part3.shakirov_aa;

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        Pair[] array = new Pair[n];
        int number1, number2;
        for (int i = 0; i < n; i++) {
            number1 = fs.nextInt();
            number2 = fs.nextInt();
            array[i] = new Pair(number1, number2);
        }

        sort(array, 0, n - 1);

        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < n; i++) {
            out.println(array[i]);
        }

        out.close();
    }

    static void sort(Pair[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            sort(arr, left, middle);
            sort(arr, middle + 1, right);

            merge(arr, left, middle, right);
        }
    }

    static void merge(Pair[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Pair[] l = new Pair[n1];
        Pair[] r = new Pair[n2];

        for (int i = 0; i < n1; i++) {
            l[i] = arr[i + left];
        }

        for (int i = 0; i < n2; i++) {
            r[i] = arr[i + middle + 1];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (l[i].data1 <= r[j].data1) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            arr[k] = l[i];
            k++;
            i++;
        }

        while (j < n2) {
            arr[k] = r[j];
            k++;
            j++;
        }
    }

    private static class Pair{
        int data1;
        int data2;

        public Pair(int data1, int data2) {
            this.data1 = data1;
            this.data2 = data2;
        }

        @Override
        public String toString() {
            return data1 + " " + data2;
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
