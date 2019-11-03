package ru.mail.polis.ads.part5.maksimshengeliia;


import java.io.*;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/6018426
* */
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        //int n = new Scanner(System.in).nextByte();

        int n = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        showArray(array, out);
        lab: while (true) {

            if (n == 1) break;

            int i = array.length - 1;
            while (array[i] < array[i - 1]) {
                i--;
                if (i == 0) {
                    break lab;
                }
            }

            int j = array.length - 1;
            while (array[j] < array[i - 1]) {
                j--;
            }

            swap(array, j, i - 1);
            int left = i;
            int right = array.length - 1;
            while (right > left) {
                swap(array, right--, left++);
            }
            showArray(array, out);
        }
    }

    private static void swap(int[] a, int l, int r) {
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }

    private static void showArray(int[] array, PrintWriter out) {
        for (int value : array) {
            out.print(value + " ");
        }
        out.println();
    }


    private Task5() {
        // Should not be instantiated
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


